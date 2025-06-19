package aszteroida_banyaszat;

import java.awt.Graphics;

/**
 * A telepes kirajzolasaert es helyenek eltarolasaert felelos osztaly.
 */

public class TelepesView extends UrutazoView {
	
	/**
	 * Telepes modell objektuma
	 */
	private Telepes modell ;
	
	/**
	 * Konstruktor - Letrehozza a parameterben kapott telepeshez tartozo view objekumot
	 */
	public TelepesView(Telepes t, int xi, int yi) {
		modell = t;
		x = t.getMezo().getView().getX() + xi;
		y = t.getMezo().getView().getY() + yi;
		
		int num=1;
		String telepesnev= t.toString();
		for(int i = 0; i < telepesnev.length(); i++) {
			if(Character.isDigit(telepesnev.charAt(i))) {
				String c = String.valueOf(telepesnev.charAt(i));
				num = Integer.parseInt(c);
			}
		}
		img = Jatek.getInstance().keszlet.telepesKepek.get(num-1);
	}

	/**
	 * Visszaadja, hogy az adott grafikus elemhez melyik modellbeli elem tartozik.
	 * @return A Telepes modellbeli eleme
	 */
	public Telepes getModellPar() {
		return modell;
	}
	
	/**
	 * Kirajzolja a Telepest
	 */
	@Override
	public void Draw(Graphics g) {
		g.drawImage(img, x, y, null);	
	}
}
