package aszteroida_banyaszat;

abstract public class Nyersanyag {			//Nyersanyag osztaly skeleton
	
	/**
	 * Nyersanyaghoz tartozo view objektum
	 */
	private NyersanyagGomb view;
	
	/**
	 * nyersanyag neve
	 */
	private String nev;
	
	/**
	 * annak az aszteroidanak a referenciaja, amiben mag a nyersanyag
	 */
	protected Aszteroida aszteroida;	
	
	/**
	 * Nyersanyag inicializaloja
	 * @param n - nyersanyag neve lesz
	 */
	public Nyersanyag(String n) {
		nev = n;
		aszteroida = null;
	}
	
	/**
	 * Nyersanyagot inicalizal
	 * @param n
	 * @param a
	 */
	public Nyersanyag(String n, Aszteroida a) {
		nev = n;
		if (a.UjMag(this))
			aszteroida = a;
		else 
			aszteroida = null;
	}
	
	/**
	 * Akkor hivodik, amikor az adott nyersanyag objektum megsemmisul. 
	 * Csokkenti az adott nyersanyagtipus sajat statikus valtozojat es jelzi a jatek veget, ha annak erteke 3 ala esett.
	 */
	abstract void Megsemmisul();	
	
	/**
	 * Akkor hivodik, ha az aszteroida, aminek a magjaban van teljesen at van furva es napkozelben van.
	 * Ilyenkor alapesetben nem tortenik semmi, kiveve (jelenleg) a radioaktiv nyersanyagokat (uran) es vizjeget.
	 */
	abstract void NapkozeliVeszely();		 
	
	/**
	 * visszaadja, hogy a parameterkent kapott nyersanyag vele ugyanolyan tipusu-e
	 * @param ny - ezzel a nyersanyaggal hasonlitja ossze magat
	 * @return hogy azonos tipusuak-e
	 */
	public boolean Osszehasonlit(Nyersanyag ny) {			
		return this.getClass().getName().equals(ny.getClass().getName());
	}
	
	/**
	 * visszaadja a nyersanyag nevet
	 */
	@Override
	public String toString() {
		return nev;
	}
	
	/**
	 * beallitja az aszteroidat, amiben mag az adott nyersanyag
	 * @param a
	 */
	public void SetAszteroida(Aszteroida a) { 
		aszteroida = a;
	}
	
	/**
	 * Csokkenti a jatekban levo nyersanyag darabjat
	 */
	abstract public void SzamCsokkentes();
}
