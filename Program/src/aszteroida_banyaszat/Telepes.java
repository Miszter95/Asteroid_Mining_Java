package aszteroida_banyaszat;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Telepes extends Urutazo {			//Telepes osztaly skeleton

	/**
	 * Statikus mezo, az egesz osztalyra vonatkozik. A jatekban levo telepesek szamat mutatja.
	 * Jatek kezdetekor beallitodik, ha meghal egy telepes akkor 1-el csökkenteni kell. Ha 1-re csokken vege a jateknak.
	 */
	private static int TelepesSzam = 0;	
	/**
	 * telepes nyersanyagraktara
	 * A telepesnel 10db kibanyaszott nyersanyag lehet.
	 */
	private ArrayList<Nyersanyag> nyersanyagraktar;	
	/**
	 * telepes teleportkapui
	 * egyszerre csak 3 lehet náluk
	 */
	private ArrayList<TeleportKapu> kapuk;	
	/**
	 * egy robot felepitesehezhez szukseges hozzavalok listaja
	 */
	private EpitesiTerv robotTerv;		
	/**
	 * egy teleport felepitesehezhez szukseges hozzavalok listaja
	 */
	private EpitesiTerv teleportTerv;		
	
	/**
	 * A telepes view-jara mutato referencia
	 */
	private TelepesView view;
	
	/**
	 * lepeshez flag
	 */
	private boolean vegzett = false;
	
	/**
	 * Telepes konstruktora, letrehozza a telepesnek az epitesi terveket
	 * @param n - Telepest neve
	 * @param m - az a mezo amin all
	 */
	public Telepes(String n, Mezo m) {
		super(n,m);
		TelepesSzam++;
		kapuk = new ArrayList<TeleportKapu>();
		nyersanyagraktar=new ArrayList<Nyersanyag>();
		
		robotTerv = new EpitesiTerv("robotTerv");
		// robothoz szukseges nyersanyagok:
		robotTerv.NyersanyagHozzadas(new Vas("vas"));
		robotTerv.NyersanyagHozzadas(new Szen("szen"));
		robotTerv.NyersanyagHozzadas(new Uran("uran"));
		
		teleportTerv = new EpitesiTerv("teleportTerv");
		// teleportkapuhoz szukseges nyersanyagok:
		teleportTerv.NyersanyagHozzadas(new Vas("vas"));
		teleportTerv.NyersanyagHozzadas(new Vas("vas"));
		teleportTerv.NyersanyagHozzadas(new Vizjeg("vizjeg"));
		teleportTerv.NyersanyagHozzadas(new Uran("uran"));
	}
	
	@Override
	public void Mozgas(Mezo m) {
		if(mezo.Szomszedsag(m)) {
			mezo.UrutazoMegy(this);
			m.UrutazoJon(this);
			view.Mozgott(mezo);
			vegzett = true;
		}
		if(!vegzett) JatekAblak.getInstance().SikertelenMuvelet("Nem tudok\narra a\nmezore lepni");
	}

	/**
	 * Kibanyassza  a nyersanyagat annak az aszteroidanak amelyen tartozkodik
	 */
	@Override
	public void Banyaszas() {	
		if(nyersanyagraktar.size() < 10 && mezo.Banyaszhato()) {
			Nyersanyag ny = mezo.Banyaszva();
			if (ny != null)
				nyersanyagraktar.add(ny);
			vegzett = true;
		}
		if(!vegzett) JatekAblak.getInstance().SikertelenMuvelet("Nem tudom\nkibanyaszni\naz aszteroida\nmagjat!");
		JatekAblak.getInstance().muveletsor.Frissit(this);
	}
	
	/**
	 * Megfurja azt a mezot amin all
	 */
	@Override
	public void Furas() {
		if(mezo.getReteg() > 0) {
			mezo.Furva();
			vegzett = true;
		}
		if(!vegzett) JatekAblak.getInstance().SikertelenMuvelet("Nem tudom\nkifurni\naz aszteroida\nreteget!");
	}
	
	/**
	 * A telepes meghal. 
	 * Csokkenti a TelepesSzam statikus valtozo erteket 1-el. 
	 * Ellenorzi, hogy maradt-e eleg telepes a jatek megnyeresehez, 
	 * ha nem akkor veget vet a jataknak.
	 */
	@Override
	public void Meghal() {
		mezo.UrutazoMegy(this);
		
		for (Nyersanyag ny : nyersanyagraktar) {
			ny.Megsemmisul();
		}
		nyersanyagraktar = null;
		ArrayList<TeleportKapu> segedkapuk = new ArrayList<TeleportKapu>(getKapuk());
		for (TeleportKapu tk : segedkapuk) {
			tk.Megsemmisul();
		}
		kapuk = null;
		
		Aszteroidaov.getInstance().UrutazoMeghalt(this);
		
		Jatek.getInstance().UrutazoMeghal(this);
		JOptionPane.showConfirmDialog(JatekAblak.getInstance(), "A " + nev + " telepes meghalt!","Figyelmeztetes!",JOptionPane.PLAIN_MESSAGE);
		
		TelepesSzam--; 			//A telepes meghal. Csokkenti a TelepesSzam statikus valtozo erteket 1-el.
		if(TelepesSzam <2)
			Jatek.getInstance().Vege();			//Ellenorzni, hogy maradt-e eleg telepes a jatak megnyeresehez, ha nem akkor veget vet a jateknak.
		vegzett = true;
		JatekAblak.getInstance().SikertelenMuvelet("AuGhhgaaaofXgHuu");
		JOptionPane.showMessageDialog(JatekAblak.getInstance(), "Minden telepes meghalt!\nA jatekot elvesztettetek!", "Lose the game!", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	/**
	 * A telepes levesz egy nyersanyagot az aszteroida felszinerol 
	 * es a nyersanyag raktaraba teszi
	 * @param ny - a kivalasztott nyersanyag
	 */
	public void FelszinrolLeszed(Nyersanyag ny) {			
		if(nyersanyagraktar.size() < 10)
		{
			if (mezo.FelszinrolKivesz(ny)) {
				nyersanyagraktar.add(ny);
				JatekAblak.getInstance().muveletsor.Frissit(this);
				vegzett = true;
			}
		}
		if(!vegzett) JatekAblak.getInstance().SikertelenMuvelet("Nem tudok\na felszenrol\nnyersanyagot\nleszedni, mivel\nnincs a felszenen\nnyersanyag!");
	}
	
	/**
	 * Lead egy nyersanyagot, es a felszinre teszi
	 * @param ny - egy nyersanyag amit le akar tenni
	 */
	public void NyersanyagLeadas(Nyersanyag ny) {			
		if (mezo.FelszinreRak(ny))
			nyersanyagraktar.remove(ny);
	}
	
	/**
	 * A telepes a nyersanyagraktarabol visszatesz egy nyersanyagot egy ureges aszteroida magjaba.
	 * @param ny
	 */
	public void Visszahelyezes(Nyersanyag ny) {			
		if (nyersanyagraktar.contains(ny)) {
			if (mezo.UjMag(ny)) {
				nyersanyagraktar.remove(ny);
				JatekAblak.getInstance().muveletsor.Frissit(this);
				vegzett = true;
			}
			if(!vegzett) JatekAblak.getInstance().SikertelenMuvelet("Nem tudom\na nyersanyagot\naz aszteroida\nmagjaba\nvisszatenni!");
		}  
	}  
	
	/**
	 * Egy telepes kozvetlenul atad egy darab nyersanyagot 
	 * a nyersanyag raktarabol egy masik telepesnek.
	 * Az atadas nem erinti a mezo felszinet.
	 * @param ny - nyersanyag
	 * @param t - ennek a telepesnek
	 */
	public void Atadas(Nyersanyag ny, Telepes t) {	
		if (t.RaktarTele()) {
			return;
		}
		t.AddNyersanyag(ny);
		this.RemoveNyersanyag(ny);
		JatekAblak.getInstance().muveletsor.Frissit(this);
		vegzett = true;
	}
	
	/**
	 * A telepes letrehoz egy MI robotot ha megvannak hozza a megfelelo nyersanyagjai 
	 * (A robot azonnal utnak indul es nem kell letenni úgy mint a teleportkaput).
	 */
	public void Robotepites() {		
		if (robotTerv.NyersanyagEllenoriz(nyersanyagraktar)) {
			MI_robot robot = new MI_robot("robot", mezo);
			boolean valasz = false;
			ArrayList<Nyersanyag> segedny = nyersanyagraktar;
			for(int i = 0; i<robotTerv.GetHozzavalok().size(); ++i) {
				for(int j = 0; j<segedny.size() && valasz == false; ++j) 
					if(robotTerv.GetHozzavalok().get(i).Osszehasonlit(segedny.get(j)) == true) {
						valasz = true;
						segedny.remove(j);
					}
				
				valasz = false;
			}
			setNyersanyagraktar(segedny);
			mezo.UrutazoJon(robot);
			Aszteroidaov.getInstance().UjUrutazo(robot);
			
			JatekAblak.getInstance().muveletsor.Frissit(this);
			Jatek.getInstance().UjRobot(robot, mezo);
			vegzett = true;
		}
		if(!vegzett) JatekAblak.getInstance().SikertelenMuvelet("Nem tudok\nrobotot epiteni!");
	}
	
	/**
	 * A telepes letrehoz egy teleportkapupart. A kapupar a telepesnel marad, neki kell majd letennie.
	 */
	public void TeleportkapuparEpites() {			
		if (getKapuk().size() >= 2)
			return;
		
		if (teleportTerv.NyersanyagEllenoriz(nyersanyagraktar)) {
			TeleportKapu tk1= new TeleportKapu("tk1", this);
			TeleportKapu tk2= new TeleportKapu("tk2", this, tk1);
			tk1.setPar(tk2);
			
			boolean valasz = false;
			ArrayList<Nyersanyag> segedny = new ArrayList<Nyersanyag>(nyersanyagraktar);
			for(int i = 0; i<teleportTerv.GetHozzavalok().size(); ++i) {
				for(int j = 0; j<segedny.size() && valasz == false; ++j) {
					if(teleportTerv.GetHozzavalok().get(i).Osszehasonlit(segedny.get(j)) == true) {
						valasz = true;
						segedny.remove(j);
					}
				}
				valasz = false;
			}
			setNyersanyagraktar(segedny);
			getKapuk().add(tk1);
			getKapuk().add(tk2);
			
			JatekAblak.getInstance().muveletsor.Frissit(this);
			vegzett = true;
		}
		if(!vegzett) JatekAblak.getInstance().SikertelenMuvelet("Nem tudok\nteleportkaput\nepiteni!");
	}
	
	/**
	 * A telepes leteszi a teleportkaput.
	 * @param tk - egy teleportkapu amit letesz
	 */
	public void TeleportLerakas(TeleportKapu tk) {
        if (getKapuk().remove(tk)) {
            ArrayList<Mezo> szomszedok = new ArrayList<Mezo>();
            szomszedok.addAll(mezo.GetSzomszedok());
            szomszedok.add(mezo);
            tk.Init(szomszedok);
            mezo.szomszedok.add(tk);
            vegzett = true;
        }

        if(!vegzett) JatekAblak.getInstance().SikertelenMuvelet("Nem tudtam lerakni\na teleportkaput!");
    }
	
	/**
	 * A jatek korokre van bontva. Minden korben egyszer lephet a telepes. 
	 * Egy lepes lehet mozgas,furas, banyaszas, robot/teleportkapu epites es nyersanyag visszahelyezees.
	 */
	@Override
	public void Lepes() {
		Jatek.getInstance().aktivTelepes=this;
		Jatek.getInstance().DrawAll();
		JatekAblak.getInstance().MezoInfoFrissit(mezo);
		JatekAblak.getInstance().muveletsor.FrissitFelszin(mezo.GetFelszin());
		JatekAblak.getInstance().muveletsor.Frissit(this);
		JatekAblak.getInstance().SikertelenMuvelet(nev+" telepes lep!");
		while(!vegzett) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		vegzett = false;
	}
	
	/**
	 * A telepes bazis epitest kezdemenyez. Ha megvannak a megfelelo nyersanyagok az aszteroidan, akkor a telepesek nyernek.
	 */
	public void BazisEpites() {			
		if (mezo.EpitesEllenorzes()) {
			Jatek.getInstance().Vege();
			JOptionPane.showMessageDialog(JatekAblak.getInstance(), "A bazist sikeresen felepitettetek!\nGratulalunk gyoztetek!");
			System.exit(0);
		}
		if(!Jatek.getInstance().isVege()) JatekAblak.getInstance().SikertelenMuvelet("Nem tudunk bazist\nepiteni ezen\naz aszteroidan!");
	}
	
	/**
	 * Az aszteroida felrobbant amin a telepes allt. Ekkor a telepes meghal.
	 */
	@Override
	public void RobbanasEri() {	
		this.Meghal();
	}
	
	/**
	 * Ha a telepes nincs elbujva, akkor eldob random egy nyersanyagot a nyersanyagraktarabol, majd meghal.
	 */
	@Override
	public void NapviharEri() {		
		/*Random random = new Random();
		int rindex = random.nextInt(nyersanyagraktar.size());
		this.NyersanyagLeadas(nyersanyagraktar.get(rindex));*/
		if (nyersanyagraktar.size() > 0)
			this.NyersanyagLeadas(nyersanyagraktar.get(0));
		this.Meghal();
	}
	
	 /**
	  * A telepes a parameterkent megkapott nyersanyagot dobja le.
	  * Ha a telepes nincs elbujva, akkor eldob random egy nyersanyagot a nyersanyagraktarabol, majd meghal.
	  * @param ny
	  */
	public void NapviharEri(Nyersanyag ny) {			
		this.NyersanyagLeadas(ny);
		this.Meghal();
	}
	
	/**
	 * Hozzaadja a teleportkapuihoz a parameterben megadott kaput
	 * @param tk - egy teleportkapu
	 */
	public void setKapu(TeleportKapu tk) {
		getKapuk().add(tk);
	}
	
	/**
	 * Beallitja a nyersanyagraktarat 
	 * @param ny - az atadott nyersanyagok
	 */
	public void setNyersanyagraktar(ArrayList<Nyersanyag> ny) {
		nyersanyagraktar=ny;
	}
	
	/**
	 * Visszaadja a nyersanyagraktarat
	 */
	public ArrayList<Nyersanyag> getNyersanyagraktar(){
		return nyersanyagraktar;
	}
	
	/**
	 * Megnezi, hogy a raktara tele van-e.
	 * Egyszerre csak 10 nyersanyag lehet nala
	 * @return
	 */
	public boolean RaktarTele() {
		if (nyersanyagraktar.size() == 10) {
			return true;
		}
		return false;
	}
	
	/**
	 * Boviti a nyersanyagraktarat a megadott nyersanyaggal
	 * @param ny
	 */
	public void AddNyersanyag(Nyersanyag ny) {
		if(RaktarTele() == false)
			nyersanyagraktar.add(ny);
	}
	
	/**
	 * Eltavolotija a nyresanyagot a raktarbol
	 * @param ny - nyersanyag, amit elvesz
	 */
	public void RemoveNyersanyag(Nyersanyag ny) {
		nyersanyagraktar.remove(ny);
	}
	
	/**
	 * Eltavolitja a telepestel a teleportkaput a listajabol
	 * @param tk - egy teleportkapu
	 */
	public void RemoveKapu(TeleportKapu tk) {
		getKapuk().remove(tk);
	}
	
	/**
	 * Kiirja a Telepesrel szolo informaciokat
	 */
	@Override
	public String informaciok() {
		String nyersak = this.toString() + "(";
		if(this.nyersanyagraktar.size() == 0)
			nyersak += "0)";
		else {
			for(int i = 0; i<this.nyersanyagraktar.size()-1; ++i)
				nyersak += this.nyersanyagraktar.get(i).toString() + ",";
			nyersak += nyersanyagraktar.get(this.nyersanyagraktar.size()-1).toString() + ")";
		}
			
		return nyersak;
	}

	/**
	 * Visszaadja a nala levo teleportkapuk listajat
	 * @return
	 */
	public ArrayList<TeleportKapu> getKapuk() {
		return kapuk;
	}
	
	/**
	 * view setter 
	 */
	public void setView(UrutazoView tv) {
		view = (TelepesView)tv;
	}
	/**
	 * view getter 
	 */
	public UrutazoView getView() {
		return view;
	}
}