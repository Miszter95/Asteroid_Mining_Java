package aszteroida_banyaszat;

import java.util.ArrayList;
import java.util.Random;

public class TeleportKapu extends Mezo implements Leptetheto {			//TeleportKapu osztaly skeleton

	/**
	 * Az adott teleportkapu parjat tarolja
	 */
	private TeleportKapu par;	
	
	/**
	 * Igaz, ha mar erte napvihar, vagyis megkergult
	 */
	private boolean napviharErte;
	
	/**
	 *  A teleportkaput megepito telepes referenciaja. Ha le van rakva, akkor null.
	 */
	private Telepes telepes;
	
	/**
	 * A teleportkapu view-jara mutato referencia
	 */
	
	private TeleportKapuView view;
	
	/**
	 * Teleportkapu inicializalasa
	 * @param nev - teleportkapu neve 
	 * @param t - telepes akinel van a zsebeben, null ha ki van helyezve
	 */
	public TeleportKapu(String nev, Telepes t) {
		super(nev);
		napviharErte = false;
		telepes = t;
	}
	
	/**
	 * Teleportkapu inicializalasa, beallitja a nevet, azt, hogy kinel van, illetve a parjat
	 * @param nev - a teleportkapu neve
	 * @param t - a telepes akinel van
	 * @param parKapu - a kapu parja
	 */
	public TeleportKapu(String nev, Telepes t, TeleportKapu parKapu) {
		super(nev);
		this.par = parKapu;
		napviharErte = false;
		telepes = t;
	}
	
	/**
	 * "Palyara allitja a teleportkaput"
	 * beallitja a szomszedokat, es beteszi az aszteroidaov mezoi koze
	 */
	@Override
	public void Init(ArrayList<Mezo> sz) {
		telepes = null;
		szomszedok = sz;
		Aszteroidaov.getInstance().UjMezo(this);
		Jatek.getInstance().UjKapu(this);
	}
	
	/**
	 * Beallitja a parjat
	 * @param tk - egy teleportkapu
	 */
	public void setPar(TeleportKapu tk) {
		this.par=tk;
	}
	
	/**
	 * Visszaadja a parjat
	 * @return
	 */
	public TeleportKapu getPar() {
		return this.par;
	}
	
	/**
	 * A teleportkapu megsemmisul, kikerul a palya grafjabol is 
	 * vagy az ot tartalmazo telepes kapuk raktarabol.
	 */
	public void Megsemmisul() {
		if(this.hasPar())
			this.par.setPar(null);
		if (par != null) {
			par.Megsemmisul();
		}
		if (telepes != null) {
			telepes.RemoveKapu(this);
		}
		telepes = null;
		for (Mezo sz : szomszedok) {
			sz.RemoveSzomszed(this);
		}
		Aszteroidaov.getInstance().MezoTorles(this);
	}
	
	/**
	 * Az adott teleportkapu parjara teleportolja a raerkezo urutazot
	 */
	@Override
	public void UrutazoJon(Urutazo u) {
		if (this.par.telepes != null) {
			return;
		}
		u.Teleport(par);
	}
	
	/**
	 * A teleportkapura urutazo teleportal, eltarolja az urutazok attributumban.
	 */
	@Override
	public void UrutazoTeleport(Urutazo u) {		
		urutazok.add(u);
	}
	
	/**
	 * Egy teleportkaput mozgat egy szomszedos mezo valamely szomszedjara
	 */
	public void Mozgas() {
		//kihez mozogjon
		Random random = new Random();
		Mezo cel = this;
		while(cel == this) {
			cel = szomszedok.get(random.nextInt(szomszedok.size()));
		}
		//regi szomszedoktol torli magat
		for (Mezo sz : szomszedok) {
			sz.RemoveSzomszed(this);
		}
		
		//uj szomszedok beallitasa
		szomszedok.clear();
		szomszedok.add(cel);
		szomszedok = cel.GetSzomszedok();

		//uj szomszedoknal hozzaadja magat
		int korl = szomszedok.size();
		for(int i = 0; i < korl; i++)
		{
			szomszedok.get(i).AddSzomszed(this);
		}
		view.Mozgott(cel);
	}

	/**
	 * A teleportkaput robbanas eri, amely hatasara megsemmisul a parjaval egyutt.
	 */
	@Override
	public void RobbanasEri() {
		Megsemmisul();
	}

	/**
	 *  A teleportkaput napvihar eri,  igy megkergul.
	 */
	@Override
	public void NapviharEri() {
		napviharErte = true;
		
		Mozgas();
		
		for (Urutazo u : urutazok) {
			u.NapviharEri();
		}
	}
	
	/**
	 *  A jatek korokre van osztva ez a TeleportKapu lepese. Ha megkergult, akkor mozog.
	 */
	@Override
	public void Lepes() {
		if (napviharErte)
			Mozgas();
	}
	
	/**
	 * Kiirja a teleportkapu informacioit
	 */
	@Override
	public String informaciok() {
		String ts = this.toString() + "\n" + "szomszedok: ";
		if(this.szomszedok.size() == 0) {
			ts += "0" + "\n" + "urutazok: ";
		}
		else {
			for(int i = 0; i<this.szomszedok.size(); ++i) 
				ts += this.szomszedok.get(i).toString() + ",";
			ts += "\n " + "urutazok: ";
		}
		if(this.urutazok.size() == 0) {
			ts += "0";
		}
		else {
			for(int i = 0; i<this.urutazok.size()-1; ++i)
				ts += this.urutazok.get(i).toString() + ",";
			ts += this.urutazok.get(this.urutazok.size()-1).toString();
		}
		
		return ts;
	}
	
	/**
	 * Segedfuggveny a teleportkapu megsemmisulesehez
	 * Igazzal ter vissza, ha van parja a teleportkapunak
	 */
	public boolean hasPar() 
	{
		if(this.par !=null) return true;
		else return false;
	}
	
	/**
	 * view setter
	 */
	public void setView(MezoView tkv) {
		view = (TeleportKapuView)tkv;
	}
	
	/**
	 * view getter
	 */
	public MezoView getView() {
		return view;
	}
}
