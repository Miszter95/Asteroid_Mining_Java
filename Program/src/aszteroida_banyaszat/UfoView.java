package aszteroida_banyaszat;

import java.awt.Graphics;

/**
 * Az ufo kirajzolasaert es helyenek eltarolasaert felelos osztaly.
 */

public class UfoView extends UrutazoView {
	
	/**
	 * Az ufo modell objektuma
	 */
	
	private Ufo modell ;
	
	/**
	 * Konstruktor - Letrehozza a parameterben kapott ufohoz tartozo view objekumot
	 */
	public UfoView(Ufo u, int xi, int yi) {
		modell = u;
		x = u.getMezo().getView().getX() + xi;
		y = u.getMezo().getView().getY() + yi;
		img = Jatek.getInstance().keszlet.UfoKep;
	}
	
	/**
	 * Visszaadja, hogy az adott grafikus elemhez melyik modellbeli elem tartozik.
	 * @return Az ufo modellbeli eleme
	 */
	public Ufo getModellPar() {
		return modell;
	}
	
	/**
	 * Kirajzolja az ufot
	 */
	
	@Override
	public void Draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
}
