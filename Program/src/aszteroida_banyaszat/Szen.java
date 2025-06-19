package aszteroida_banyaszat;

public class Szen extends Nyersanyag{			//Szen oszaly skeleton

	/**
	 * Az aszteroidaovben megtalalhato (meg jatakban levo) szen nyersanyagok szamat tarolja.
	 * Ha erteke 3 ala csokken, a jatek megnyerhetetlenne valik.
	 */
	private static int szenSzam = 0;
	
	/**
	 * A szen osztaly konstruktora, amely beallitja az attributumait
	 * @param n A szen neve
	 */
	public Szen(String n){
		super(n);
		szenSzam++;
	}
	
	/**
	 * Akkor hivodik meg, amikor egy szen objektum megsemmisul. Csokkenti a szenSzam valtozot es jelzi a jatek veget, ha annak erteke 3 ala esett.
	 */
	public void Megsemmisul() {								
		szenSzam--;					
		if( aszteroida != null)
			this.aszteroida.setNullMag();
		this.aszteroida = null;
		if(szenSzam < 3) {				
			Jatek.getInstance().Vege();
		}
	}

	/**
	 * Akkor hivodik meg, ha egy teljesen atfurt, napkozelben levo aszteroida magjaban van az adott szen objektum. 
	 */
	@Override
	void NapkozeliVeszely() {}

	/**
	 * A szenSzam valtozot csokkenti eggyel 
	 */
	@Override
	public void SzamCsokkentes() {
		szenSzam--;
	}
}