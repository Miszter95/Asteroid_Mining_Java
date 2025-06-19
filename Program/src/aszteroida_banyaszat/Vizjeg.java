package aszteroida_banyaszat;

public class Vizjeg extends Nyersanyag{			//Vizjeg osztaly skeleton

	/**
	 * Az aszteroidaovben megtalalhato (meg jatakban levo) vizjeg nyersanyagok szamat tarolja.
	 * Ha erteke 3 ala csokken, a jatek megnyerhetetlenne valik.
	 */
	private static int vizjegSzam = 0;
	
	/**
	 * A vizjeg osztaly konstruktora, amely beallitja az attributumait
	 * @param n A vizjeg neve
	 */
	public Vizjeg(String n){
		super(n);
		vizjegSzam++;
	}
	
	/**
	 * Akkor hivodik meg, amikor egy vizjeg objektum megsemmisul. Csokkenti a vizjegSzam valtozot es jelzi a jatek veget, ha annak erteke 3 ala esett.
	 */
	public void Megsemmisul() {
		vizjegSzam--;
		if(aszteroida != null)
			this.aszteroida.setNullMag();
		this.aszteroida = null;
		if(vizjegSzam < 3) {	
			Jatek.getInstance().Vege();
		}
	}
	
	/**
	 * Akkor hivodik meg, ha egy teljesen atfurt, napkozelben levo aszteroida magjaban van az adott vizjeg objektum. Ekkor ez az objektum szublimal (megsemmisul).
	 */
	@Override
	public void NapkozeliVeszely() {
		Megsemmisul();
	}

	/**
	 * A vizjegSzam valtozot csokkenti eggyel
	 */
	@Override
	public void SzamCsokkentes() {
		vizjegSzam--;
	}
}
