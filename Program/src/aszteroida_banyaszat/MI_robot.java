package aszteroida_banyaszat;

import java.util.ArrayList;
import java.util.Random;

public class MI_robot extends Urutazo{			//MI_robot osztaly skeleton

	/**
	 * Az MI_robot view-jara mutato referencia
	 */
	private RobotView view;
	
	/**
	 * Inicializalja a robotot
	 * @param n - ez lesz a neve
	 * @param a - mezo amin rajta lesz
	 */
	public MI_robot(String n, Mezo a) {
		super(n, a);
	}
	
	@Override
	public void Mozgas(Mezo m) {
		if(mezo.Szomszedsag(m)) {
			mezo.UrutazoMegy(this);
			m.UrutazoJon(this);
			view.Mozgott(m);
		}
	}
	
	/**
	 * A robot meghal
	 */
	public void Meghal() {			
		mezo.UrutazoMegy(this);
		mezo = null;
		Aszteroidaov.getInstance().UrutazoMeghalt(this);
		Jatek.getInstance().UrutazoMeghal(this);
	}
	
	/**
	 * A jatek korokre van bontva. Minden korben egyszer lephet a robot. Egy lepes lehet mozgas vagy furas.
	 */
	@Override
	public void Lepes() {										
		if (mezo.Furhato()) {
			Furas();
		} else {
			Random random = new Random();
			Mezo cel = mezo.GetSzomszedok().get(random.nextInt(mezo.GetSzomszedok().size()));
			Mozgas(cel);
		}
	}
	
	/**
	 * Ha a robot nincs elbujva,amikor napvihar eri, akkor meghal, de eldob egy nyersanyagot az ot felepito nyersanyagok kozul
	 */
	@Override
	public void NapviharEri() {			
		Random random = new Random();
		switch(random.nextInt(3)) {
			case(0):
				Vas v = new Vas("v");
				NyersanyagLeadas(v);
				break;
			case(1):
				Uran u = new Uran("u");
				NyersanyagLeadas(u);
				break;
			case(2):
				Szen sz = new Szen("sz");
				NyersanyagLeadas(sz);
				break;
		}
		Meghal();
	}
	
	
	/**
	 * A mezot furni kezdi
	 */
	@Override
	public void Furas() {
		mezo.Furva();
	}
	
	
	/**
	 * A robot ekkor egy szomszedos aszteroidara kerul.
	 */
	@Override
	public void RobbanasEri() {		
		ArrayList<Mezo> szomszedok = mezo.GetSzomszedok();
		Random random = new Random();
		Mozgas(mezo.GetSzomszedok().get(random.nextInt(szomszedok.size())));
	}

	/**
	 * Eldob egy nyersanyagot.
	 * @param ny
	 */
	public void NyersanyagLeadas(Nyersanyag ny) {
		mezo.FelszinreRak(ny);											
	}

	/**
	 * Visszaadja a nyersanyagraktarat
	 */
	@Override
	public ArrayList<Nyersanyag> getNyersanyagraktar() {
		return null;
	}
	
	/**
	 * view setter 
	 */
	public void setView(UrutazoView rv) {
		view = (RobotView)rv;
	}
	/**
	 * view getter 
	 */
	public UrutazoView getView() {
		return view;
	}
}
