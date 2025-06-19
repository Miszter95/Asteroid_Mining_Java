package aszteroida_banyaszat;

import java.awt.Graphics;

/**
 * A tormelek kirajzolasaert es helyenek eltarolasaert felelos osztaly.
 */

public class TormelekView extends MezoView {
	
	/**
	 * Tormelek modell objektuma
	 */
	private Tormelek modell ;
	
	public TormelekView(Tormelek t, int X, int Y, int i) {
		x = X;
		y = Y;
		modell = t;
		img = Jatek.getInstance().keszlet.tormelekKep;
	}
	/**
	 * Visszaadja, hogy az adott grafikus elemhez melyik modellbeli elem tartozik.
	 * @return A tormelek modellbeli eleme
	 */
	public Tormelek getModellPar() {
		return modell;
	}
	
	/**
	 * Kirajzolja a tormeleket
	 */
	@Override
	public void Draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	@Override
	public void kiveszNapvihar() {
		
	}

	
}
