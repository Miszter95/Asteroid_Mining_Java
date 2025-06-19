package aszteroida_banyaszat;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Az eger kattintasanak esemenykezeloje.
 */

public class PalyaEgerKezelo implements MouseListener{

	/**
	 * Csak akkor hivodik meg, amikor a felhasznalo rakattint a hallgatott komponensre.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (Jatek.getInstance().controller.palyaAktiv) {
			View kattintottPanel = HovaKatt(e.getX(), e.getY());
			if (kattintottPanel == null)
				return;
			if (Jatek.getInstance().controller.aktivGomb == "Atadas") {
				if (Jatek.getInstance().controller.valasztottNy != null) {
					Telepes t = ((TelepesView) kattintottPanel).getModellPar();
					Jatek.getInstance().aktivTelepes.Atadas(Jatek.getInstance().controller.valasztottNy, t);
					Jatek.getInstance().controller.valasztottNy = null;
				}
			} else {
				Mezo mezo = ((MezoView) kattintottPanel).getModellPar();
				switch (Jatek.getInstance().controller.aktivGomb) {
					case "Mozgas":
						if(Jatek.getInstance().aktivTelepes.getMezo().Szomszedsag(mezo))
							Jatek.getInstance().aktivTelepes.Mozgas(mezo);
					break;
				}
			}
		}
	}

	/**
	 * Kotelezoen megvalositando esemenykezelo metodusok
	 * Ezeket az esemenyeket nem kezeljuk, igy uresen szerepelnek
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	/**
	 * Megadja, hogy melyik mezo JPaneljere kattintottak.
	 * A Controller-ben levo palyaAktiv flag-et false-ra allitja at.
	 * @param x
	 * @param y
	 * @return
	 */
	public View HovaKatt(int x, int y) {
		if (Jatek.getInstance().controller.aktivGomb == "Atadas") {
			for (UrutazoView uv : Jatek.getInstance().urutazoViewk) {
				if (x >= uv.x && x <= uv.x + uv.img.getWidth(null) && y >= uv.y && y <= uv.y + uv.img.getHeight(null)) {
					Jatek.getInstance().controller.palyaAktiv = false;
					return uv;
				}
			}
		} else {
			for (MezoView mv : Jatek.getInstance().mezoViewk) {
				if (x >= mv.x && x <= mv.x + mv.img.getWidth(null) && y >= mv.y && y <= mv.y + mv.img.getHeight(null)) {
					Jatek.getInstance().controller.palyaAktiv = false;
					return mv;
				}
			}
		}
		return null;
	}

	
}
