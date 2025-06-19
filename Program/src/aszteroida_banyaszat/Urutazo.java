package aszteroida_banyaszat;

import java.util.ArrayList;

public abstract class Urutazo implements Leptetheto {			//Urutazo osztaly skeleton
	/**
	 * Urutazo neve
	 */
	protected String nev;
	
	/**
	 * Abban a pillanatban melyik mezon all az urutazo.
	 */
	protected Mezo mezo;			
	
	/**
	 * Urutazo konstruktora, beallitja a nevet es a mezot
	 * @param n - nev
	 * @param a - mezo amin all
	 */
	public Urutazo(String n, Mezo a) {
		nev = n;
		mezo = a;
		Aszteroidaov.getInstance().UjUrutazo(this);
	}
	
	/**
	 * Visszaadja a mezot amin all az Urutazo
	 * @return
	 */
	public Mezo getMezo() {
		return mezo;
	}
	
	/**
	 * Az urutazo mozgatasa a parameterkent megadott mezore.
	 * @param m - a mezo ahova mozog
	 */
	abstract public void Mozgas(Mezo m);
	
	public void Furas() {}
	public void Banyaszas() {}
	public void Meghal() {}
	
	public abstract void RobbanasEri();
	public abstract void NapviharEri();
	
	/**
	 * Visszaadja a nyersanyagraktarat
	 * @return - null, mert csak a telepesnek van nyersanyagraktara
	 */
	public ArrayList<Nyersanyag> getNyersanyagraktar() {
		return null;
	}
	
	/**
	 * Az urutazo teleportal egy parameterkent kapott celpontra
	 * @param m
	 */
	public void Teleport(Mezo m) {
		this.mezo = m;
		m.addUrutazo(this);
	}
	
	/**
	 * Az urutazo nevet adja vissza
	 */
	@Override
	public String toString() {
		return nev;
	}

	/**
	 * Beallitja a mezot amin all
	 * @param mezo2 - az uj mezo ertek
	 */
	public void setMezo(Mezo mezo2) {
		this.mezo = mezo2;
	}
	
	/**
	 * Kiiraj az informaciokat
	 * @return
	 */
	public String informaciok() {
		return nev;
	}
	
	/**
	 * view setter leszarmazottaknak
	 */
	public abstract void setView(UrutazoView uv);
	
	/**
	 * view getter leszarmazottaknak
	 */
	public abstract UrutazoView getView();

}
