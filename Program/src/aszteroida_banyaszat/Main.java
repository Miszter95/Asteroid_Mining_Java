package aszteroida_banyaszat;

import java.io.IOException;
import java.util.HashMap;


public class Main {
	
	/**
	 * mezoknevei es uratozoknevei, taroljak, hogy melyik nevhez, melyik Aszteroidaovben letezo objektum tartozik
	 */
	public static HashMap<String, Mezo> mezoknevei = new HashMap<String, Mezo>();
	public static HashMap<String, Urutazo> urutazoknevei = new HashMap<String, Urutazo>();
	
	/**
	 * A program main fuggvenye.
	 * Letrehozza a menut, majd futtatja a jatekciklust, amig nincs vege a jateknak
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Menu m = new Menu();
		while(m.korKezdes != 1) {		//amig nem lehet kezdeni addig alszik ez a szal
            try {
                Thread.sleep(200);
            }catch(InterruptedException e) {
            }
        }	
		while(Jatek.getInstance().getMehet() && !Jatek.getInstance().isVege()) {
			Jatek.getInstance().Kor();
		}
		
	}
}