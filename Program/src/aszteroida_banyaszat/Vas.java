package aszteroida_banyaszat;

public class Vas extends Nyersanyag{		
	
	/**
	 * Az aszteroidaovben megtalalhato (meg jatakban levo) vas nyersanyagok szamat tarolja.
	 * Ha erteke 3 ala csokken, a jatek megnyerhetetlenne valik.
	 */
	private static int vasSzam = 0;	
	
	/**
	 * A vas osztaly konstruktora, amely beallitja az attributumait
	 * @param n A vas neve
	 */
	public Vas(String n){
		super(n);
		vasSzam++;
	}
	
	/**
	 * Akkor hivodik meg, amikor egy vas objektum megsemmisul. Csokkenti a vasSzam valtozot es jelzi a jatek veget, ha annak erteke 3 ala esett.
	 */
	public void Megsemmisul() {
		this.aszteroida = null;
		if( aszteroida != null)
			this.aszteroida.setNullMag();
		if(--vasSzam < 3) {				//Csokkenti a vasSzam valtozot es jelzi a jatek veget, ha annak erteke 3 ala esett.
			Jatek.getInstance().Vege();
		}
	}

	/**
	 * Akkor hivodik meg, ha egy teljesen atfurt, napkozelben levo aszteroida magjaban van az adott vas objektum.
	 */
	@Override
	void NapkozeliVeszely() {}

	/**
	 * A vasSzam valtozot csokkenti eggyel 
	 */
	@Override
	public void SzamCsokkentes() {
		vasSzam--;
	}
}
