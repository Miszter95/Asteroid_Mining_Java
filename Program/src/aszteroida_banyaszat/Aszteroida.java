package aszteroida_banyaszat;

import java.util.ArrayList;
import java.util.Random;

public class Aszteroida extends Mezo {			//Aszteroida osztaly skeleton

	/**
	 * Az aszteroida magja
	 */
	private Nyersanyag mag;
	
	/**
	 * Az aszteroidan tartozkodo telepeseknel levo nyersanyagok
	 */
	private ArrayList<Nyersanyag> telepes;
	
	/**
	 * Az aszteroida felszinen talalhato nyersanyagok
	 */
	private ArrayList<Nyersanyag> felszin;

	/**
	 * //az aszteroida kopenyenek vastagsaga
	 */
	private int retegSzam;
	
	/**
	 * Megadja, hogy az aszteroida napkozelben van-e
	 */
	private boolean napkozel;
	
	/**
	 * a bazis megepitesehez szukseges hozzavalok listajat tartalmazza 
	 */
	private EpitesiTerv bazisterv;
	
	/**
	 * Az aszteroida view-jara mutato referencia
	 */
	private AszteroidaView view;
	
	/**
	 * Az aszteroida osztaly konstruktora, amely beallitja a parametereit
	 * @param nev Az aszteroida neve
	 * @param b Az aszteroida napkozelben van-e
	 * @param retegszam Az aszteroida retegszama
	 * @param m Az aszteroida magjaban talalhato nyersanyag
	 */
	public Aszteroida(String nev, boolean b, int retegszam, Nyersanyag m) {
		super(nev);
		this.retegSzam = retegszam;
		this.napkozel = b;
		this.mag = m;
		if(mag != null)
			mag.SetAszteroida(this);
		
		telepes = new ArrayList<Nyersanyag>();
		urutazok = new ArrayList<Urutazo>();
		felszin = new ArrayList<Nyersanyag>();
		
		bazisterv = new EpitesiTerv("bazisterv");
		
		for (int i=0; i<3; i++)
			bazisterv.NyersanyagHozzadas(new Vas("vas"));
		
		for (int i=0; i<3; i++)
			bazisterv.NyersanyagHozzadas(new Szen("szen"));
		
		for (int i=0; i<3; i++)
			bazisterv.NyersanyagHozzadas(new Uran("uran"));
		
		for (int i=0; i<3; i++)
			bazisterv.NyersanyagHozzadas(new Vizjeg("vizjeg"));
		
	}
	
	/**
	 * Az aszteroida magjat visszaado getter
	 * @return Az aszteroida magja
	 */
	
	public Nyersanyag getMag() {
		return mag;
	}
	
	/**
	 * Az aszteroida retegszam attributumanak settere
	 * @param reteg A beallitani kivant retegszam erteke
	 */

	public void setRetegSzam(int reteg) {
		this.retegSzam = reteg;
	}
	
	/**
	 * Az aszteroida napkozel attributumanak settere
	 * @param param A beallitani kivant napkozel erteke
	 */
	
	public void setNapkozel(boolean param) {
		this.napkozel = param;
		if(param)
			mag.NapkozeliVeszely();
	}
	
	/**
	 * Az aszteroida mag attributumanak null-lara allito settere
	 */
	
	public void setNullMag() {
		this.mag = null;
	}
	
	/**
	 * Az aszteroida letrehozasaban, inicializalasaban segit
	 */
	
	@Override
	public void Init(ArrayList<Mezo> sz) {
		szomszedok = sz;
		Aszteroidaov.getInstance().UjMezo(this);
	}
	
	/**
	 * Egy urutazo megfurja az aszteroida felszinet, ezaltal 1-gyel kevesebb retegszama
	 * lesz az aszteroidanak es figyel a napkozelben levo anyagok torteneseire is
	 */
	
	@Override
	public void Furva() {
		if (retegSzam > 0)
			retegSzam--;
		view.setimg(retegSzam, napkozel, 0); 
		if(retegSzam == 0 && napkozel && mag!=null) {
			mag.NapkozeliVeszely();
		}
		
		JatekAblak.getInstance().MezoInfoFrissit(this);
	}
	
	/**
	 * Egy telepes kinyeri az aszteroida magjaban levo nyersanyagot
	 */
	
	@Override
	public Nyersanyag Banyaszva() {
		if (retegSzam == 0) {
			Nyersanyag kinyert = mag;
			mag = null;
			JatekAblak.getInstance().MezoInfoFrissit(this);
			return kinyert;
		}
		return null;
	}
	
	/**
	 * Az aszteroida robbanasban megsemmisul es tormelek lesz belole
	 */
	
	public void Robbanas() {
		ArrayList<Urutazo> segedurutazok = new ArrayList<Urutazo>(urutazok);
		
		for (Urutazo u : segedurutazok) {
			u.RobbanasEri();
		}
		
		for (Nyersanyag ny : felszin) {
			ny.Megsemmisul();
		}
		
		ArrayList<Mezo> segedmezo = new ArrayList<Mezo>(szomszedok);
		for (Mezo sz : segedmezo) {
			sz.RobbanasEri();
		}
		
		Aszteroidaov.getInstance().Tormelekke(this);
	}
	
	/**
	 * Uj urutazo erkezik az aszteroida felszinere, frissiti az absztrakt oset
	 */
	
	@Override
	public void UrutazoJon(Urutazo u) {
		urutazok.add(u);
		if (u.getNyersanyagraktar() != null)
			telepes.addAll(u.getNyersanyagraktar());						//(Mezo) urutazok listajat es a telepes listat.
		u.setMezo(this);
	}
	
	/**
	 * Egy urutazo tavozik az aszteroida felszinerol, frissiti az absztrakt oset
	 */
	
	@Override
	public void UrutazoMegy(Urutazo u) {
		urutazok.remove(u);
		if (u.getNyersanyagraktar() != null)
			telepes.removeAll(u.getNyersanyagraktar());
	}
	
	/**
	 * Atnezi a rendelkezesre allo nyersanyagokat es eldonti, hogy a felszinen megepitheto-e a telepesek bazisa.
	 */
	
	@Override
	public boolean EpitesEllenorzes() {
		return bazisterv.NyersanyagEllenoriz(telepes);
	}
	
	/**
	 * Ha az aszteroida ureges, akkor uj nyersanyag kerul a magjaba
	 */
	
	@Override
	public boolean UjMag(Nyersanyag ny) {
		if (mag == null && retegSzam==0) {
			mag = ny;
			ny.SetAszteroida(this);
			JatekAblak.getInstance().MezoInfoFrissit(this);
			return true;
		}
		return false;
	}
	
	/**
	 * Aszteroida lepese, allitja a napkozeliseget es napkozelben, kifurt magnal meghivja a mag NapkozeliVeszely() fuggvenyet az aszteroida napkozelbe kerulese
	 */
	@Override
	public void Lepes() {
		Random random = new Random();
		int kozelseg = random.nextInt(20);
		
	
		if (kozelseg != 0)
			napkozel = false;
		else {
			napkozel = true;
			view.Napkozelbe();
		}
			
		
		if (napkozel && retegSzam == 0 && mag != null) {
			mag.NapkozeliVeszely();
		}
	}
	
	/**
	 * Az aszteroida napkozelseget beallitja a megadott parameterre es NapkozeliVeszely-t jelez a magnak, ha kifurt a magja es eppen napkozelbe kerult
	 * @param kozelseg
	 */
	public void Lepes(boolean kozelseg) {
		
		napkozel = kozelseg;
		
		if (napkozel && retegSzam == 0 && mag != null) {
			mag.NapkozeliVeszely();
			for(int i = 0; i<felszin.size(); ++i) {
				felszin.get(i).NapkozeliVeszely();
			}
		}
	}
	
	/**
	 * Visszaadja a felszinen talalhato nyersanyagokat
	 */
	
	public ArrayList<Nyersanyag> GetFelszin() {
		return felszin;
	}
	
	/**
	 * Az aszteroida felszinere rak egy nyersanyagot
	 * Berak egy nyersanyagot a felszin listaba 
	 */
	
	@Override
	public boolean FelszinreRak(Nyersanyag ny) {
		felszin.add(ny);
		JatekAblak.getInstance().muveletsor.FrissitFelszin(felszin);
		return true;
	}
	
	/**
	 * Az aszteroida felszinerol levesz egy nyersanyagot
	 * Kivesz egy nyersanyagot a felszin listabol
	 */
	
	@Override
	public boolean FelszinrolKivesz(Nyersanyag ny) {
		if (felszin.contains(ny)) {
			felszin.remove(ny);
			JatekAblak.getInstance().muveletsor.FrissitFelszin(felszin);
			return true;
		}
		return false;
	}
	
	/**
	 * A mezot napvihar eri, ezert meghivja az osszes rajtatartozkodo urutazo NapviharEri fuggvenyet
	 */
	
	@Override
	public void NapviharEri() {
		if (retegSzam != 0 || mag != null) {
			ArrayList<Urutazo> segedurutazok = new ArrayList<Urutazo>(urutazok);
			for(Urutazo u : segedurutazok) {
				u.NapviharEri();
			}
		}
		view.Napviharba();
	}
	
	/**
	 * Megnezi, hogy banyaszhato-e az aszteroida 
	 */
	
	@Override
	public boolean Banyaszhato( ) {
		if (retegSzam == 0 && mag != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Megnezi, hogy furhato-e az aszteroida
	 */
	
	@Override
	public boolean Furhato() {
		view.setimg(retegSzam, false, 2);
		if (retegSzam > 0) 
			return true;
		return false;
	}
	
	/**
	 * Informaciokat ad meg az aszteroidarol
	 */
	
	@Override
	public String informaciok() {
		String mags;
		if(this.mag == null) {
			mags = "0";
		}
		else 
			mags = this.mag.toString();
		String s = this.toString() + "\n" + "reteg: " + this.retegSzam + "\n" + "mag: " + mags + "\n" + "szomszedok: ";
		if(this.szomszedok.size() == 0) {
			s += "0" + "\n" + "felszin: ";
		}
		else {
			for(int i = 0; i<this.szomszedok.size(); ++i) 
				s += this.szomszedok.get(i).toString() + ",";
			s += "\n" + "felszin: ";
		}
		
		if(this.felszin.size()==0) {
			s += "0" + "\n" + "urutazok:";
		}
		else {
			for(int i = 0; i<this.felszin.size(); ++i)
				s += this.felszin.get(i).toString() + ",";
			s += "\n" + "urutazok: ";
		}
		if(this.urutazok.size() == 0) {
			s += "0";
		}
		else {
			for(int i = 0; i<this.urutazok.size()-1; ++i)
				s += this.urutazok.get(i).toString() + ",";
			s += this.urutazok.get(this.urutazok.size()-1).toString();
		}
		
		return s;
	}
	
	/**
	 * view setter
	 */
	public void setView(MezoView av) {
		view = (AszteroidaView)av;
	}
	
	/**
	 * view getter
	 * @return az objektumhoz tartozo view objektum
	 */
	public AszteroidaView getView() {
		return view;
	}
	/**
	 *  retegSzam getter
	 * @return az aszteroida retegszama
	 */
	public int getReteg() {
		return retegSzam;
	}
}
