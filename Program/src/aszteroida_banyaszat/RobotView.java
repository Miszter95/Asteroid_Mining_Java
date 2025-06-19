package aszteroida_banyaszat;

import java.awt.Graphics;

/**
 * A robot kirajzolasaert es helyenek eltarolasaert felelos osztaly.
 */
public class RobotView extends UrutazoView {
	
	/**
	 * MI robot modell objektuma
	 */
	private MI_robot modell ;
	
	/**
	 * Konstruktor
	 */
	public RobotView(MI_robot r, int xi, int yi) {
		modell = r;
		x = r.getMezo().getView().getX() + xi;
		y = r.getMezo().getView().getY() + yi;
		img = Jatek.getInstance().keszlet.robotKep;
	}
	
	/**
	 * Visszaadja, hogy az adott grafikus elemhez melyik modellbeli elem tartozik.
	 * @return Az MI robot modellbeli eleme
	 */
	public MI_robot getModellPar() {
		return modell;
	}
	
	/**
	 * Kirajzolja az MI robotot
	 */
	@Override
	public void Draw(Graphics g) {
		g.drawImage(img, x, y, null);	
		
	}
}
