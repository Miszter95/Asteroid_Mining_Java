package aszteroida_banyaszat;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

/**
 * Az urutazo kirajzolasaert es helyenek eltarolasaert felelos osztaly.
 */

public abstract class UrutazoView extends JButton implements View {

	/**
	 * Az urutazo
	 */
	protected Image img;
	
	/**
	 * Az urutazo x koordinataja
	 */
	protected int x;
	
	/**
	 * Az urutazo koordinataja
	 */
	protected int y;
	
	/**
	 * Beallitja, hogy egy urutazo mozgasa soran melyik mezore lepett at.
	 * @param m A mozgas celallomasa
	 */
	public void Mozgott(Mezo m) {
		int xx = 0;
		int yy = 0;
		for(int i = 0; i<m.getUrutazokdb(); ++i) {
			if(i < 4) {
				xx = (55*(i))+10;
				yy = -50;
			}
			else
				if(i < 8) {
					xx = (55*(i-4))+10;
					yy = 30;
				}
				else
					if(i < 12) {
						xx = (55*(i-8))+10;
						yy = 100;
					}
					else {
						xx = (55*(i-12))+10;
						yy = 190;
					}
			m.getUrutazok().get(i).getView().setX(m.getView().getX() + xx);
			m.getUrutazok().get(i).getView().setY(m.getView().getY() + yy);
		}
		Jatek.getInstance().DrawAll();
	}
	
	/**
	 * Visszaadja, hogy az adott grafikus elemhez melyik modellbeli elem tartozik.
	 * @return Az urutazo modellbeli eleme
	 */
	abstract public Urutazo getModellPar();
	
	/**
	 * getter a kirajzolasi x koordinatahoz
	 * @return - az x koordinata erteke
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * getter a kirajzolasi y koordinatahoz
	 * @return - az y koordinata erteke
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Kirajzolja a mezot
	 */
	public abstract void Draw(Graphics g);
	
	/**
	 * setter a kirajzolasi x koordinatahoz
	 * @param xx - az uj x koordinata
	 */
	public void setX(int xx) {
		x = xx;
	}
	
	/**
	 * setter a kirajzolasi y koordinatahoz
	 * @param yy - az uj y koordinata
	 */
	public void setY(int yy) {
		y = yy;
	}
}
