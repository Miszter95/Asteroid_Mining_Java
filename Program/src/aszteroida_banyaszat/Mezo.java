package aszteroida_banyaszat;

import java.util.ArrayList;


abstract public class Mezo implements Leptetheto {			//Mezo osztaly skeleton

	/**
	 * a mezovel szomszedos mezok referenciajat tartalmazza
	 */
	protected ArrayList<Mezo> szomszedok;	
	
	/**
	 * a mezon tartozkodo urutazok referenciajat tartalmazza
	 */
	protected ArrayList<Urutazo> urutazok;	
	
	
	/**
	 * A Mezo neve
	 */
	private String nev;
	
	
	/**
	 * Mezo konstruktora
	 * Letrehozza a mezot, beallitja a nevet
	 * inicializlja az urutazokat rajta es a szomszedokat
	 * @param nev
	 */
	public Mezo(String nev) {
		this.nev = nev;
		urutazok = new ArrayList<Urutazo>();
		szomszedok = new ArrayList<Mezo>();
	}
	
	/**
	 * A mezo retegszamat adja vissza
	 * @return  mezo reteszama, amely alapertelmezesben 0
	 */
	public int getReteg() {
		return 0;
	}
	
	/**
	 * mezo letrehozasaban, inicializalasaban segit
	 */
	abstract public void Init(ArrayList<Mezo> sz);		
	
	/**
	 * abstract fuggveny
	 */
	abstract public void NapviharEri();
	
	/**
	 * kapott mezovel ellenorzi a szomszedsagot
	 * @param m - egy mezo
	 * @return - ha szomszedos akkor igazzal ter vissza, ellenkezo esetben false
	 */
	public boolean Szomszedsag(Mezo m) {		
		return szomszedok.contains(m);
	}
	
	/**
	 * a mezon banyaszni szeretne egy telepes
	 */
	public Nyersanyag Banyaszva() {
		return null;
	}
	
	/**
	 * a mezore urutazo erkezik, frissiti az urutazok listat
	 * @param u - egy urutazo
	 */
	public void UrutazoJon(Urutazo u) {			
		urutazok.add(u);
		u.setMezo(this);
	}
	
	/**
	 * a mezorol urutazo tavozik, frissiti az urutazok listat
	 * @param u - egy urutazo
	 */
	public void UrutazoMegy(Urutazo u) {			
		urutazok.remove(u);
	}
	
	/**
	 * a mezon bazist szeretne epiteni egy telepes
	 */
	public boolean EpitesEllenorzes() {
		return false;
	}	
	
	/**
	 * a mezo felszinen levo nyersanyagokat adja vissza
	 */
	public ArrayList<Nyersanyag> GetFelszin() {
		return null; 
	}
	
	/**
	 * a mezon furni szeretne egy urutazo
	 */
	public void Furva() {}		
	
	/**
	 * egy telepes uj magkent ad at egy nyersanyagot a mezonek
	 * @param ny - egy neyresanyag
	 * @return - false
	 */
	public boolean UjMag(Nyersanyag ny) {
		return false;
	}	
	
	/**
	 * jelzi a mezonek, hogy  rateleportolt egy urutazo
	 */
	public void UrutazoTeleport(Urutazo u) {}		
	
	/**
	 * felszinrerak egy nyersanyagot
	 * @param ny - egy nyersanyag
	 */
	public boolean FelszinreRak(Nyersanyag ny) {
		return false;
	}
	
	/**
	 * Leveszi a felszinrol a megadott nyersanyagot
	 */
	public boolean FelszinrolKivesz(Nyersanyag ny) {
		return false;
	}
	
	/**
	 * Robbanas eri a mezot
	 */
	public void RobbanasEri() {}
	
	/**
	 * visszaadja a mezo nevet
	 */
	@Override
	public String toString() {
		return nev;
	}
	
	/**
	 * visszater a szomszedokkal
	 * szomszedok getter fuggvenye
	 * @return - a szomszedok
	 */
	public ArrayList<Mezo> GetSzomszedok() {
		return szomszedok;
	}			
	
	/**
	 * Hozzaad a szomszedokhoz egy mezot
	 * @param sz - egy mezo, ezzel boviti a listat
	 */
	public void AddSzomszed(Mezo sz) {
		szomszedok.add(sz);
	}
	
	/**
	 * Kiveszi a mezo szomszedai kozul a megadott mezot
	 * @param sz - egy mezo
	 */
	public void RemoveSzomszed(Mezo sz) {
		szomszedok.remove(sz);
	}
	
	/**
	 * Megnezi, hogy banyaszhato-e a mezo
	 */
	public boolean Banyaszhato() {
		return false;
	}
	
	/**
	 * Megnezi, hogy furhato-e a mezo
	 */
	public boolean Furhato() {
		return false ;
	}
	
	/**
	 * kiirja a mezorol az informaciokat
	 */
	public String informaciok() {
		return nev;
	}
	
	/**
	 * felvesz egy urutazot, a mezon levo urutazokhoz
	 * @param u - egy Urutazo
	 */
	public void addUrutazo(Urutazo u) {			
		urutazok.add(u);
	}
	
	/**
	 * Rajzolasnal szukseges, hogy tudjuk hanyan vannak az aszteroidan
	 * @return db szam
	 */
	public int getUrutazokdb() {
		return urutazok.size();
	}
	
	/**
	 * visszaadja az urutazokat
	 */
	public ArrayList<Urutazo> getUrutazok() {
		return urutazok;
	}
	/**
	 * beallitja a kapott parameter alapjan a view valtozot
	 * @param mv - a mezohoz tartozo view objektum
	 */
	public abstract void setView(MezoView mv);
	
	/**
	 * lekerdezi a mezohoz tartozo view elemet
	 */
	 public abstract MezoView getView();
}
