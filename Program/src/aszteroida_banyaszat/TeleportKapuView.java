package aszteroida_banyaszat;

import java.awt.Graphics;

/**
 * A teleportkapu kirajzolasaert es helyenek eltarolasaert felelos osztaly.
 */

public class TeleportKapuView extends MezoView {
	
	/**
	 * Teleportkapu modell objektuma
	 */
	private TeleportKapu modell ;
	
	/**
	 * Konstruktor
	 * Annak a mezonek az X, Y koordinatajat kapja meg ahonnan le lett teve
	 */
	public TeleportKapuView(TeleportKapu tk, int X, int Y) {
		x = X+200;
		y = Y-200;
		modell = tk;
		img = Jatek.getInstance().keszlet.teleportkapuKep;
		Jatek.getInstance().DrawAll();
	}
	
	/**
	 * Beallitja, hogy egy teleportkapu megkergulese soran melyik mezore mozgott at.
	 * @param m A mozgas celallomasa
	 */
	public void Mozgott(Mezo m) {
		x = m.getView().x + 200;
		y = m.getView().y + 200;
		Jatek.getInstance().DrawAll();
	}
	
	/**
	 * Visszaadja, hogy az adott grafikus elemhez melyik modellbeli elem tartozik.
	 * @return A A teleportkapu modellbeli eleme
	 */
	
	public TeleportKapu getModellPar() {
		return modell;
	}
	
	/**
	 * Kirajzolja a teleportkaput
	 */
	
	@Override
	public void Draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}

	@Override
	public void kiveszNapvihar() {
		
	}
}
