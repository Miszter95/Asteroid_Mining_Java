package aszteroida_banyaszat;

public class Uran extends Nyersanyag{			//Uran osztaly skeleton

	/**
	 * Az aszteroidaovben megtalalhato (meg jatakban levo) uran nyersanyagok szamat tarolja.
	 * Ha erteke 3 ala csokken, a jatek megnyerhetetlenne valik. 
	 */
	private static int uranSzam = 0;
	
	/**
	 * Az uran expozicioszamat jelzo valtozo (ha 3 lesz az erteke, akkor felrobban az uran)
	 */
	private int expozicioSzam = 0;
	
	/**
	 * Az uran osztaly konstruktora, amely beallitja az attributumait
	 * @param n - Az uran neve
	 */
	public Uran(String n){
		super(n);
		uranSzam++;
	}
	
	/**
	 * Az uran osztaly masik konstruktora, amely beallitja az attributumait
	 * @param n - Az uran neve
	 * @param ex - Az uran expozicioszama
	 */
	public Uran(String n, int ex) {
		super(n);
		expozicioSzam = ex;
	}
	
	/**
	 * Akkor hivodik meg, amikor egy uran objektum megsemmisul. Csokkenti az uranSzam valtozot es jelzi a jatek veget, ha annak erteke 3 ala esett.
	 */
	public void Megsemmisul() {
		uranSzam--;
		if( aszteroida != null)
			this.aszteroida.setNullMag();
		this.aszteroida = null;
		if(uranSzam < 3)	
			Jatek.getInstance().Vege();
	}
	
	/**
	 * Akkor hivodik meg, ha egy teljesen atfurt, napkozelben levo aszteroida magjaban van az adott uran objektum. Ekkor ez az objektum felrobban (megsemmisul).
	 */
	public void NapkozeliVeszely() {
		expozicioSzam++;
		if (expozicioSzam >= 3) {
			aszteroida.Robbanas();
			Megsemmisul();
		}
	}

	/**
	 * Az uranSzam valtozot csokkenti eggyel 
	 */
	@Override
	public void SzamCsokkentes() {
		uranSzam--;
	}
	
	
}
