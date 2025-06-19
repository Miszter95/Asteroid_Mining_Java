package aszteroida_banyaszat;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * A telepesnel levo teleportkaput reprezentalja.
 */

public class TeleportKapuGomb extends JButton {

	/**
	 * A teleportkapu objektuma
	 */
	private TeleportKapu modell;
	
	/**
	 * Visszaadja, hogy az adott grafikus elemhez,
	 * gombhoz, melyik modell beli elem tartozik.
	 * @return A TeleportKapu modellbeli eleme
	 */
	public TeleportKapu getModellPar() {
		return modell;
	}
	
	/**
	 * Konstruktor
	 * @param tk
	 * @throws IOException
	 */
	public TeleportKapuGomb(TeleportKapu tk) throws IOException {
		setIconT(tk);
	}
	
	/**
	 * Konstruktor
	 * @throws IOException
	 */
	public TeleportKapuGomb() throws IOException {
		setIconT();
	}
	
	/**
	 * Beallitja a gomb ikonjat
	 */
	public void setIconT() {
		this.setIcon(new ImageIcon(Jatek.getInstance().keszlet.nyersanyagKepek.get(1)));
		this.setToolTipText("Ures");
	}
	
	/**
	 * beallitja a gomb ikonjat es osszerendeli a parameterben kapott teleportkapuval
	 * @param tk - a gomb modellbeli eleme
	 */
	public void setIconT(TeleportKapu tk) {
		this.setIcon(new ImageIcon(Jatek.getInstance().keszlet.nyersanyagKepek.get(3)));
		modell = tk;
		this.setToolTipText("Teleportkapu");
	}
}
