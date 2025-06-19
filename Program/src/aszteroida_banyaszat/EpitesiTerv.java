package aszteroida_banyaszat;
import java.util.ArrayList;

public class EpitesiTerv {			//EpitesiTerv osztaly skeleton
	/**
	 * EpitesiTerv neve
	 */
	private String nev;

	/**
	 * Az epiteni kivant nyersanyaghoz a hozzavalok
	 */
	private ArrayList<Nyersanyag> hozzavalok;			
	
	/**
	 * EpitesiTerv konstruktora, beallitja a terv nevet, illetve inicializalja a hozzavalokat
	 * @param n - A terv neve
	 */
	public EpitesiTerv(String n) {
		nev = n;
		hozzavalok = new ArrayList<Nyersanyag>();
	}
	
	/**
	 * A tervhez szukseges hozzavalokat adja hozza
	 * @param ny - az a nyersanyag amit hozzaad
	 */
	public void NyersanyagHozzadas(Nyersanyag ny) {
		hozzavalok.add(ny);
		ny.SzamCsokkentes();
	}
	
	/**
	 * Ellenorzi, hogy a parameterkent kapott listaban van-e eleg a szukseges nyersanyagokbol az epiteshez.
	 * @param ny - Az a nyersanyagraktar, amiben megnezi, hogy van e eleg nyersanyag a megepiteshez
	 * @return - Ha megvan minden szukseges nyersanyag, akkor igazzal ter vissza, ellenkezo esetben false-al
	 */
	public boolean NyersanyagEllenoriz(ArrayList<Nyersanyag> ny) {			
		boolean valasz = false;
		ArrayList<Nyersanyag> segedny = new ArrayList<Nyersanyag>(ny); // a nyersanyagok, amikbol ki is lehet venni, hogy kovetni lehessen, hogy mit ellenorzott mar le
		if(ny.size() < hozzavalok.size()) //mivel eleve nincs is meg a kello mennyiseg igy alapbol nem is tud epiteni
			return false;
		for(int i = 0; i<hozzavalok.size(); ++i) { //az osszes hozzavalon vegig megy
			for(int j = 0; j<segedny.size() && valasz == false; ++j) { //osszehasonlitja a meglevo nyersanyagokkal
				if(hozzavalok.get(i).Osszehasonlit(segedny.get(j)) == true) { //tehat ha a hozzavalo megtalalhato a meglevok kozott, kivesszuk azt
					valasz = true; 
					segedny.remove(j);
				}
			}
			if(valasz == false) //Nincs meg a keresett hozzavalo, tehat nem epitheto
				return false;
			valasz = false; //a kovetkezo elemnel feltetelezzek, hogy nincs meg
		}
		
		
		return true;
	}
	
	/**
	 * A hozzavalokhoz szukseget getter
	 * @return - visszater a hozzavalokkal
	 */
	public ArrayList<Nyersanyag> GetHozzavalok() {
		return hozzavalok;
	}
	
	/**
	 * Visszater a terv nevevel
	 */
	@Override
	public String toString() {
		return nev;
	}
}
