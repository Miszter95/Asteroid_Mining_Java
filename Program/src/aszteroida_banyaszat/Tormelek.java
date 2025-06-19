package aszteroida_banyaszat;

import java.util.ArrayList;

public class Tormelek extends Mezo{			

	/**
	 * A tormelek view-jara mutato referencia
	 */
	private TormelekView view;
	
	/**
	 * Tormelek konstruktora
	 * @param n - a tormelek neve
	 */
	public Tormelek(String n) {
		super(n);
	}

	/**
	 * Letrehozza az aszteroidaovben a tormeleket, es beallitja a szomszedait
	 */
	@Override
	public void Init(ArrayList<Mezo> sz) {
		szomszedok = sz;
		Aszteroidaov.getInstance().UjMezo(this);
	}

	/**
	 * A jatek korokre van osztva ez a tormelek lepese
	 */
	@Override
	public void Lepes() {}

	/**
	 * A tormeleket napvihaeri
	 */
	@Override
	public void NapviharEri() {
		for(Urutazo u : urutazok) {
			u.NapviharEri();
		}
	}
	
	/**
	 * Kiirja a tormelek informacioit
	 */
	@Override
	public String informaciok() {
		String ts = this.toString() + "\n" + "szomszedok: ";
		if(this.szomszedok.size() == 0) {
			ts += "0" + "\n" + "urutazok: ";
		}
		else {
			for(int i = 0; i<this.szomszedok.size(); ++i) 
				ts += this.szomszedok.get(i).toString() + ",";
			ts += "\n" + "urutazok: ";
		}
		if(this.urutazok.size() == 0) {
			ts += "0";
		}
		else {
			for(int i = 0; i<this.urutazok.size()-1; ++i)
				ts += this.urutazok.get(i).toString() + ",";
			ts += this.urutazok.get(this.urutazok.size()-1).toString();
		}
		
		return ts;
	}
	
	/**
	 * view setter
	 */
	public void setView(MezoView tv) {
		view = (TormelekView)tv;
	}
	
	/**
	 * view getter
	 */
	public MezoView getView() {
		return view;
	}
}
