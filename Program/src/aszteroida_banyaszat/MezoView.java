package aszteroida_banyaszat;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * A mezo kirajzolasaert es helyenek eltarolasaert felelos osztaly.
 */
public abstract class MezoView extends JPanel implements View {

	/**
	 * A mezo kepe
	 */
	protected Image img;
	
	/**
	 * A mezo x koordinataja
	 */
	protected int x;
	
	/**
	 * A mezo y koordinataja
	 */
	protected int y;
	
	/**
	 * Visszaadja, hogy az adott grafikus elemhez melyik modellbeli elem tartozik.
	 * @return A Mezo modellbeli eleme
	 */
	abstract public Mezo getModellPar();

	/**
	 * Lekerdezi az x koordinatat
	 * @return - az x koordinata
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Lekerdezi az y koordinatat
	 * @return - az y koordinata
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Beallitja az x koordinatat rajzolashoz
	 * @param newX - uj x koordinata
	 */
	public void setX(int newX) {
		x = newX;
	}
	
	/**
	 * Beallitja az y koordinatat rajzolashoz
	 * @param newY - uj y koordinata
	 */
	public void setY(int newY) {
		y = newY;
	}
	
	/**
	 * Kirajzolja a mezot
	 */
	public abstract void Draw(Graphics g);

	public abstract void kiveszNapvihar();
}
