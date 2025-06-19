package aszteroida_banyaszat;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * A telepesnel levo nyersanyagot reprezentalja.
 */
public class NyersanyagGomb extends JButton{

	/**
	 * A nyersanyag objektuma
	 */
	private Nyersanyag modell;
	
	/**
	 * Visszaadja, hogy az adott grafikus elemhez, gombhoz,
	 * melyik modellbeli elem tartozik.
	 * @return A Nyersanyag modellbeli eleme
	 */
	public Nyersanyag getModelPar() {
		return modell;
	}
	
	/**
	 * Konstruktor
	 * @param ny
	 */
	public NyersanyagGomb(Nyersanyag ny) {
		setIconNy(ny);
	}
	
	/**
	 * Konstruktor
	 */
	public NyersanyagGomb() {
		setIconNy();
	}
	
	/**
	 * beallitja a gomb ikonjat a kapott parameter alapjan
	 * @param ny - a nyersanyag, ami alapjan be kell allitani az ikont
	 */
	public void setIconNy(Nyersanyag ny) {
		if(ny.Osszehasonlit(new Vizjeg("vizjeg"))) {
			this.setIcon(new ImageIcon(Jatek.getInstance().keszlet.nyersanyagKepek.get(6)));
			this.setToolTipText("Vizjeg");
		}
		if(ny.Osszehasonlit(new Vas("vas"))) {
			this.setIcon(new ImageIcon(Jatek.getInstance().keszlet.nyersanyagKepek.get(5)));
			this.setToolTipText("Vas");
		}
		if(ny.Osszehasonlit(new Uran("uran"))) {
			this.setIcon(new ImageIcon(Jatek.getInstance().keszlet.nyersanyagKepek.get(4)));
			this.setToolTipText("Uran");
		}
		if(ny.Osszehasonlit(new Szen("szen"))) {
			this.setIcon(new ImageIcon(Jatek.getInstance().keszlet.nyersanyagKepek.get(2)));
			this.setToolTipText("Szen");
		}
		 modell = ny;
	}
	
	/**
	 * Uresre allitja a gomb ikonjat
	 */
	public void setIconNy() {
		this.setIcon(new ImageIcon(Jatek.getInstance().keszlet.nyersanyagKepek.get(0)));
		this.setToolTipText("Ures");
	}
}
