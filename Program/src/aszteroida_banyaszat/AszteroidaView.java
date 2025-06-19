package aszteroida_banyaszat;

import java.awt.Graphics;
import java.io.IOException;

/**
 * Az aszteroida kirajzolasaert es helyenek eltarolasaert felelos osztaly.
 */

public class AszteroidaView extends MezoView {
	
	/**
	 * Aszteroida modell objektuma
	 */
	private Aszteroida modell ;
	
	/**
	 * Egy szam, ami jelzi, hogy az aszteroida melyik kepet kapta
	 * A valtozas eseten kell
	 */
	private int nr;
	
	/**
	 * Visszaadja, hogy az adott grafikus elemhez melyik modellbeli elem tartozik.
	 * @return Az Aszteroida modellbeli eleme
	 * @throws IOException 
	 */
	public AszteroidaView(Mezo mezo, int i, int x1, int y1) throws IOException {
		modell = (Aszteroida) mezo;
		x = x1;
		y = y1;
		nr = i;
		if(modell.getReteg() == 0)
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(0); 
		else
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(1);
	}

	/**
	 * Egy aszteroida nap kozelsege miatt bekovetkezett torteneseit allitja be.
	 * Meghivja az UjTormelek metodust, ha az aszteroida az uran miatt felrobban.
	 */
	public void Napkozelbe() {
		if(modell.getReteg() == 0)
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(2);
		else
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(3);
		Jatek.getInstance().DrawAll();
	}
	
	/**
	 *  Egy aszteroidanak napvihar miatt bekovetkezett torteneseit allitja be.
	 */
	public void Napviharba() {
		if(modell.getReteg() == 0)
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(4);
		else
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(5);
		Jatek.getInstance().DrawAll();
	}
	
	public Aszteroida getModellPar() {
		return modell;
	}


	/**
	 * Kirajzolja az aszteroidat
	 */
	@Override
	public void Draw(Graphics g) {
		g.drawImage(img, x, y, null);
		
	}
	
	/**
	 * Ha mar nincs napviharban a hozzatartozo modell objektum, visszaalitja a kepet "rendesre"
	 */
	public void kiveszNapvihar(){
		if(modell.getReteg() == 0)
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(0); 
		else
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(1);
	}
	
	/**
	 * getter az view x koordinatajara
	 * @return - a kirajzolas x koodinataja
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * getter az view y koordinatajara
	 * @return - a kirajzolas y koodinataja
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * getter az view kepszamara
	 * @return - a kep szama, ami kirajzolasnal betoltodik az aszteroidakepek kozul
	 */
	public int getnr() {
		return nr;
	}

	/**
	 * Modositja az aszteroida kepet
	 * @param reteg - az aszteroida retege
	 * @param naperinti -vagy a napvihar vagy a napkozeliseg erteke
	 * @param i - ha 0 akkor napkozelisegrol van szo, es ha 1 akkor napvihar
	 */
	public void setimg(int reteg, boolean naperinti, int i) {
		if(naperinti == true && reteg == 0 && i==0) {
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(2);
			Jatek.getInstance().DrawAll();
		}
		if(naperinti == true && reteg == 0 && i==1) {
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(4);
			Jatek.getInstance().DrawAll();
		}
		if(naperinti == false && reteg == 0 && i<=1) {
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(0);
			Jatek.getInstance().DrawAll();
		}
		if(naperinti == false && reteg < 0 && i<=1) {
			img = Jatek.getInstance().keszlet.aszteroidaKepek.get(1);
			Jatek.getInstance().DrawAll();
		}
	}
}
