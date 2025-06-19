package aszteroida_banyaszat;

import java.util.Random;

public class Ufo extends Urutazo{

	/**
	 * Az ufo view-jara mutato referencia
	 */
	private UfoView view;
	
	/**
	 * Ufo konstruktora
	 * @param n - beallitja a nevet erre
	 * @param a - elhelyezi erre az aszteroidara
	 */
	public Ufo(String n, Mezo a) {
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
	 * Ufo banyaszik, mivel vegtelen mennyiseg lehet nala es halhatatlan,
	 * igy igazabol elpusztitjuk a nyersanyagot
	 */
	@Override
	public void Banyaszas() {
		Nyersanyag ny = mezo.Banyaszva();
		if (ny != null) {
			ny.Megsemmisul();
		}
	}
	
	/**
	 * Az UFO lepese random, de teszteles megkonnyitese miatt most az elso szomszedosra mozog
	 */
	@Override
	public void Lepes() {
		if (mezo.Banyaszhato()) {
			Banyaszas();
		} else {
			Random random = new Random();
			Mezo cel = mezo.GetSzomszedok().get(random.nextInt(mezo.GetSzomszedok().size()));
			Mozgas(cel);
		}
	}

	/**
	 * Robbanas eri az Ufot ezaltal egy szomszedos mezon landol
	 * ugyancsak random, egy a teszteles miatt most az elso szomszedra repul
	 */
	@Override
	public void RobbanasEri() {
		Random random = new Random();
		Mezo cel = mezo.GetSzomszedok().get(random.nextInt(mezo.GetSzomszedok().size()));
		this.Mozgas(cel);
		
	}

	/**
	 * tuleli a napvihart
	 */
	@Override
	public void NapviharEri() {}
	
	/**
	 * view setter 
	 */
	public void setView(UrutazoView uv) {
		view = (UfoView)uv;
	}
	/**
	 * view getter 
	 */
	public UrutazoView getView() {
		return view;
	}
}
