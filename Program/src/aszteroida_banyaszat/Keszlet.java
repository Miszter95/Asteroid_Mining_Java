package aszteroida_banyaszat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Tarolja az urutazok es mezok megjelenitesehez szukseges kepeket.
 */

public class Keszlet {

	/**
	 * A telepes megjelenitesehez tartozo kep
	 */
	protected ArrayList<Image> telepesKepek = new ArrayList<Image>();
	
	/**
	 * A robot megjelenitesehez tartozo kep
	 */
	protected Image robotKep;
	
	/**
	 * Az ufo megjelenitesehez tartozo kep
	 */
	protected Image UfoKep;
	
	/**
	 * Az aszteroida megjelenitesehez tartozo kepek. Kulon kep van a maggal rendelkezo/
	 * / ureges/ napviharban levo/ nem napviharban levo aszteroidakhoz.
	 */
	protected ArrayList<Image> aszteroidaKepek = new ArrayList<Image>();
	
	/**
	 * A tormelek megjelenitesehez tartozo kep
	 */
	protected Image tormelekKep;
	
	/**
	 * A telelportkapukhoz tartozo kep
	 */
	protected Image teleportkapuKep;
	
	/**
	 * A kulonbozo nyersanyagokhoz tartozo kepek listaja
	 */
	protected ArrayList<Image> nyersanyagKepek = new ArrayList<Image>();
	
	/**
	 * A muveletkhez tartozo kepek listaja
	 */
	protected ArrayList<Image> muveletKepek = new ArrayList<Image>();
	
	public Keszlet() throws IOException {
		for(int i = 1; i<Jatek.getInstance().telepesszam+1; ++i) {
			telepesKepek.add((ImageIO.read(new File("Urutazok/Telepes/telepes"+ i + ".png"))));
		}
		robotKep = (ImageIO.read(new File("Urutazok/robot.png")));
		UfoKep = (ImageIO.read(new File("Urutazok/ufo.png")));
		for(int i = 1; i<7; ++i) {
			aszteroidaKepek.add((ImageIO.read(new File("Mezok/Aszteroida1/aszteroida1_"+ i + ".png"))));
		}
		tormelekKep = (ImageIO.read(new File("Mezok/tomelek1.png")));

		teleportkapuKep = ImageIO.read(new File("Mezok/kapu.png"));
		
		nyersanyagKepek.add((ImageIO.read(new File("Nyersanyagok/nincs.png"))));
		nyersanyagKepek.add((ImageIO.read(new File("Nyersanyagok/nincstk.png"))));
		nyersanyagKepek.add((ImageIO.read(new File("Nyersanyagok/szen.png"))));
		nyersanyagKepek.add((ImageIO.read(new File("Nyersanyagok/teleport.png"))));
		nyersanyagKepek.add((ImageIO.read(new File("Nyersanyagok/uran.png"))));
		nyersanyagKepek.add((ImageIO.read(new File("Nyersanyagok/vas.png"))));
		nyersanyagKepek.add((ImageIO.read(new File("Nyersanyagok/vizjeg.png"))));
		
		muveletKepek.add((ImageIO.read(new File("Muveletek/lepes.png"))));
		muveletKepek.add((ImageIO.read(new File("Muveletek/furas.png"))));
		muveletKepek.add((ImageIO.read(new File("Muveletek/banyaszas.png"))));
		muveletKepek.add((ImageIO.read(new File("Muveletek/robotepites.png"))));
		muveletKepek.add((ImageIO.read(new File("Muveletek/kapuepites.png"))));
		muveletKepek.add((ImageIO.read(new File("Muveletek/bazisepites.png"))));
		muveletKepek.add((ImageIO.read(new File("Muveletek/atadas.png"))));
		muveletKepek.add((ImageIO.read(new File("Muveletek/magba.png"))));
		muveletKepek.add((ImageIO.read(new File("Muveletek/felszinre.png"))));
		
	}
	
}
