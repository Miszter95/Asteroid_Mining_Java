package aszteroida_banyaszat;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A grafikus elemeket tartalmazo Palya osztaly
 *
 */
public class Palya extends JPanel {
	public Palya(){}
	
	/**
	 * Palya ujrarajzolasat kezeli
	 */
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(new File("hatter.jpg")), 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (MezoView mv : Jatek.getInstance().mezoViewk) {
			mv.Draw(g);
		}
		
		for (UrutazoView uv : Jatek.getInstance().urutazoViewk) {
			uv.Draw(g);
		}
	}
}
