package aszteroida_banyaszat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * A jatekteret szimbolizalo aszteroidaov osztaly.
 * Tarolja a jatekteren levo mezoket (aszteroidak, teleportkapuk, tormelekek)
 * es urutazokat (telepesek, robotok, ufok)
 * Ez az osztaly felel a palya letrehozasaert.
 */
public class Aszteroidaov implements Leptetheto{
	
	/**
	 * Az aszteroidaov. Csak egy lehet belole, singleton.
	 */
	private static final Aszteroidaov egyed = new Aszteroidaov();
	
	/**
	 * Az aszteroidaovben levo mezok (aszteroidak, tormelekek, teleport kapuk)
	 */
	private ArrayList<Mezo> mezok = new ArrayList<Mezo>();	
	
	/**
	 * Az aszteroidaovben levo urutazok (telepesek, ufok, robotok)
	 */
	private ArrayList<Urutazo> urutazok = new ArrayList<Urutazo>();
	
	/**
	 * azon mezok tombje, akik napviharba kerultek elozo kornel
	 */
	private ArrayList<Mezo> napviharban = new ArrayList<Mezo>();
	
	/**
	 * Konstruktor
	 */
	private Aszteroidaov(){}
	
	/**
	 * Letrehozza es inicializalja a jatekteret
	 * A palya leirasat a palya.txt fajlbol olvassa be
	 * @throws IOException
	 */
	public void PalyaEpites() throws IOException {
		//eddigi esetleges palya torlese
		this.mezok.clear();
		this.urutazok.clear();
		Main.mezoknevei.clear();
		Main.urutazoknevei.clear();
		
		//lista, amibe az egyes sorokat rakjuk
		ArrayList<String> input = new ArrayList<String>();
		//readerek, amivel beolvassuk a fajl tartalmat
		FileReader fr = new FileReader("palya.txt");
		BufferedReader br = new BufferedReader(fr);
		//betoltjuk a file tartalmat az input listaba
		while(true)
		{
			//olvasunk egy sort
			String line = br.readLine();
			//ha vege az olvasasnak kilepunk
			if(line == null) break;
			else input.add(line);
		}
		//input streameket bezarjuk
		br.close();
		//input feldolgozasa 
		//betolt
		int mezoszam = Integer.parseInt(input.get(0));
		//eloszor az Aszteroidak reteget es magjat allitjuk be
		ArrayList<Mezo> temp = new ArrayList<Mezo>();
		for(int i = 1; i<mezoszam+1; i++)
		{
			String[] aszt = input.get(i).split(" ");
			switch(aszt[2]) 
			{
				case "U":
					Nyersanyag u = new Uran("uran", Integer.parseInt(aszt[3]));
					Aszteroida au = new Aszteroida(aszt[0], false, Integer.parseInt(aszt[1]), u );
					temp.add(au);
					Main.mezoknevei.put(aszt[0], au);
					break;
				case "SZ":
					Nyersanyag sz = new Szen("szen");
					Aszteroida asz = new Aszteroida(aszt[0], false, Integer.parseInt(aszt[1]), sz );
					temp.add(asz);
					Main.mezoknevei.put(aszt[0], asz);
					break;
				case "V":
					Nyersanyag v = new Vas("vas");
					Aszteroida av = new Aszteroida(aszt[0], false, Integer.parseInt(aszt[1]), v );
					temp.add(av);
					Main.mezoknevei.put(aszt[0], av);
					break;
				case "VJ":
					Nyersanyag vj = new Vizjeg("vizjeg");
					Aszteroida avj = new Aszteroida(aszt[0], false, Integer.parseInt(aszt[1]), vj );
					temp.add(avj);
					Main.mezoknevei.put(aszt[0], avj);
					break;
				case "0":
					Aszteroida a = new Aszteroida(aszt[0], false, Integer.parseInt(aszt[1]), null );
					temp.add(a);
					Main.mezoknevei.put(aszt[0], a);
					break;
			}
			
		}
		//majd a szomszedokat is hozzaadjuk
		for(int i = mezoszam+1; i < 2*mezoszam+1; i++ ) 
		{
			String[] sor = input.get(i).split(" ");
			ArrayList<Mezo> szomszedok = new ArrayList<Mezo>();
			//hashmappel kikeresi a szomszedok referenciait
			for(int j = 3; j<sor.length; j++)
			{
				szomszedok.add(Main.mezoknevei.get(sor[j]));
			}
			temp.get(i-mezoszam-1).Init(szomszedok);
			
			AszteroidaView av = new AszteroidaView(temp.get(i-mezoszam-1), 1, Integer.parseInt(sor[1]), Integer.parseInt(sor[2]));
			temp.get(i-mezoszam-1).setView(av);		//modellhez hozzarendeljuk a view-t
			Jatek.getInstance().mezoViewk.add(av); //hozzaadom a mezoviewekhez
			
		}
		
		//telepesszam a megnyomott gomb alapjan
		//telepes helye amit a jtxtfieldbol jon, ufo szinten
		for(int i = 1; i <= Jatek.getInstance().telepesszam; i++)
		{
			//aszteroida, ahova az i-ik telepes kerul
			Aszteroida a = (Aszteroida)Main.mezoknevei.get("A"+Jatek.getInstance().telepeshely);
			Telepes t = new Telepes("T"+i, a);
			Main.urutazoknevei.put(t.toString(), t);
	
    		a.UrutazoJon(t);
            
            Jatek.getInstance().UjTelepes(t, a);
			//nem fog kelleni, csak hogy legyen aktovtelepes 
			Jatek.getInstance().aktivTelepes = t;
		}
		//ufok szamanak szamitasa, majd lerakasa
		int ufoszam = Jatek.getInstance().telepesszam/2; 
		//ufok helye adott a textfieldben
		for(int i = 1; i <= ufoszam; i++)
		{
			//aszteroida, ahova az i-ik telepes kerul
			Aszteroida a = (Aszteroida)Main.mezoknevei.get("A"+Jatek.getInstance().ufohely);
			Ufo u = new Ufo("UFO"+i, a);
	    	Main.urutazoknevei.put(u.toString(), u);
			a.UrutazoJon(u);
			Jatek.getInstance().UjUfo(u, a);
		}
		
		//letrejon a JatekAblak
		JatekAblak.getInstance().elkeszites();
	}
	
	/**
	 * A singleton mukodest segit megvalositani
	 * @return - az egyetlen aszteroidaov referenciaja
	 */
	public static Aszteroidaov getInstance() {
		return egyed;
	}
	
	/**
	 * aszteroidaov neve
	 */
	@Override
	public String toString() {
		return "aszteroidaov";
	}
	
	/**
	 * Lepteti az aszteroidaovben levo mezoket, majd az urutazokat is.
	 */
	@Override
	public void Lepes() {	
		NapviharEri();
		
		ArrayList<Mezo> segedm = new ArrayList<Mezo>(mezok);
		for (Mezo m : segedm) {
			m.Lepes();
		}
		
		ArrayList<Urutazo> segedu = new ArrayList<Urutazo>(urutazok);
		for (Urutazo u : segedu) {
			u.Lepes();
		}
	}
	
	/**
	 * Kivalaszt egy random aszteroidat, aminek kornyezetet napvihar eri
	 */
	public void NapviharEri() {
		for(int i = 0; i<napviharban.size(); ++i) {
			napviharban.get(i).getView().kiveszNapvihar();
		}
		Jatek.getInstance().DrawAll();
		napviharban.clear();
		Random random = new Random();
		Mezo cel = getMezok().get(random.nextInt(getMezok().size()));
		napviharban.add(cel);
		cel.NapviharEri();
		
		ArrayList<Mezo> celsz = new ArrayList<Mezo>(cel.GetSzomszedok()); 
		for (Mezo m : celsz) {
			napviharban.add(m);
			m.NapviharEri();
		}
	}
	
	/**
	 * Uj mezot ad a jatekterhez
	 * @param m - mezo, amit hozzad a mezok listajahoz
	 */
	public void UjMezo(Mezo m) {													
		getMezok().add(m);													
	}
	/**
	 * Torol egy mezot a jatekterrol
	 * Altalaban aszteroida robbanasakor, teleportkapu megsemmisulesekor hivodik
	 * @param m - a mezo, amit torol
	 */
	public void MezoTorles(Mezo m) {
		getMezok().remove(m);
	}
	
	/**
	 * Uj urutazot ad a jatekhoz.
	 * Inicializalaskor es robotok letrehozasakor hivodik.
	 * @param u - az uj urutazo
	 */
	public void UjUrutazo(Urutazo u) {
		getUrutazok().add(u);
	}
	
	/**
	 * Torol egy urutazot a jatekbol.
	 * @param u - az urutazo, amit torolni kell
	 */
	public void UrutazoMeghalt(Urutazo u) {
		getUrutazok().remove(u);
	}
	
	/**
	 * A parameterkent kapott aszteroidat tormelekke alakitja
	 * Aszteroida robbanasakor hivodik
	 * @param a - az aszteroida, amelyik felrobbant es tormelekke kell valtoztatni
	 */
	public void Tormelekke(Aszteroida a) {		//a parameterkent kapott aszteroidat tormelekke alakitja
		String s="";
		for(int i=0; i<a.toString().length();i ++) {
			if(Character.isDigit(a.toString().charAt(i)))
					s+=a.toString().charAt(i);
					
		}
		Tormelek t = new Tormelek("T" + s);
		for(int i = 0; i<a.GetSzomszedok().size(); ++i) {
			boolean seged = false;
			for(int j = 0; j<a.GetSzomszedok().get(i).GetSzomszedok().size() && seged == false; ++j) {
				if(a.GetSzomszedok().get(i).GetSzomszedok().get(j).toString() == a.toString()) {
					a.GetSzomszedok().get(i).GetSzomszedok().set(j, t);
					seged = true;
				}
			}
		}
		
		//aszteroida levetele az aszteroidaovrol
		this.mezok.remove(a);
		//tormelek hozzadasa
		t.Init(a.GetSzomszedok());
		
		Jatek.getInstance().UjTormelek(a, t);
	}
	
	/**
	 * Beallitja a tarolt mezok listajat a parameterkent kapott listara
	 * @param mezok - mezok listaja
	 */
	public void setMezok(ArrayList<Mezo> mezok) {
		this.mezok = mezok;
	}
	
	/**
	 * Beallitja a tarolt urutazok listajat a parameterkent kapott listara
	 * @param urutazok - urutazok listaja
	 */
	public void setUrutazok(ArrayList<Urutazo> urutazok) {
		this.urutazok = urutazok;
	}

	/**
	 * Getter az urutazok listajahoz
	 * @return - urutazok listaja
	 */
	public ArrayList<Urutazo> getUrutazok() {
		return urutazok;
	}

	/**
	 * Getter a mezok listajahoz
	 * @return - mezpk listaja
	 */
	public ArrayList<Mezo> getMezok() {
		return mezok;
	}
}