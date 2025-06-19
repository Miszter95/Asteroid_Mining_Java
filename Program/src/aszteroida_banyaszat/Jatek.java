package aszteroida_banyaszat;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Jatek osztaly 
 * A jatek lebonyolitasaert felel (kezdes, korokre bontas)
 * Tovabba az uj objektumok kirajzolasaert is felelos.
 */
public class Jatek {
		
		/**
		 * A jatek. Csak egy lehet belole, singleton.
		 */
		private static final Jatek egyed = new Jatek();
		
		/**
		 * 
		 */
		private boolean mehet = false;
		
		/**
		 * Jatek veget jelzo flag valtozo
		 */
		private boolean vege = false;
		
		/**
		 * Megadja a korben az aktualisan lepo Telepest.
		 */
		
		protected Telepes aktivTelepes;
		
		/**
		 * Egy referencia a program Controllerere.
		 */
		protected Controller controller = new Controller();
		
		/**
		 * A palyan levo Mezok view objektumai.
		 */
		protected ArrayList<MezoView> mezoViewk  = new ArrayList<MezoView>();
		
		/**
		 * A palyan levo Urutazok view objektumai.
		 */
		protected ArrayList<UrutazoView> urutazoViewk = new ArrayList<UrutazoView>();
		
		/**
		 * Egy referencia a Keszlet osztalyra.
		 */
		protected Keszlet keszlet;
		
		/**
		 * jatekban szereplo telepesek szama
		 */
		protected int telepesszam;
		
		/**
		 * jatekban szereplo telepesek helye
		 */
		protected int telepeshely;
		
		/**
		 * jatekban szereplo ufok helye
		 */
		protected int ufohely;
		
		/**
		 * A palya kepet tartalmazo panel
		 */
		JPanel palya;
		
		/**
		 * Konstruktor
		 */
		private Jatek() {}
		
		/**
		 * Kirajzolja a komponenseket az ablakra.
		 */
		public void DrawAll() {
			palya.repaint();
		}
		
		public void UrutazoMeghal(Urutazo u) {
			ArrayList<UrutazoView> seged = new ArrayList<UrutazoView>(urutazoViewk);
			for(UrutazoView uv : seged) {
				if (uv.getModellPar().equals(u)) {
					urutazoViewk.remove(uv);
				}
			}
			DrawAll();
		}
		
		
		/**
		 * Kirajzol egy uj telepest a megadott mezore.
		 * @param t Kirajzolni kivant Telepes
		 */
		public void UjTelepes(Telepes t, Mezo m) {
			int x = 0;
			int y = 0;
			int db = m.getUrutazokdb();
			if(db <= 4) {
				x = (55*(db-1))+10;
				y = -50;
			}
			else
				if(db <= 8) {
					x = (55*(db-5))+10;
					y = 30;
				}
				else
					if(db <= 12) {
						x = (55*(db-9))+10;
						y = 100;
					}
					else {
						x = (55*(db-13))+10;
						y = 190;
					}
			TelepesView tv = new TelepesView(t, x, y);
			t.setView(tv);
			urutazoViewk.add(tv);
		}
		
		/**
		 * Kirajzol egy uj ufot a megadott mezore.
		 * @param u Kirajzolni kivant Ufo
		 */
		public void UjUfo(Ufo u, Mezo m) {
			int x = 0;
			int y = 0;
			int db = m.getUrutazokdb();
			if(db <= 4) {
				x = (55*(db-1))+10;
				y = -50;
			}
			else
				if(db <= 8) {
					x = (55*(db-5))+10;
					y = 30;
				}
				else
					if(db <= 12) {
						x = (55*(db-9))+10;
						y = 100;
					}
					else {
						x = (55*(db-13))+10;
						y = 190;
					}
			UfoView uv = new UfoView(u, x, y);
			u.setView(uv);
			urutazoViewk.add(uv);
		}
		
		/**
		 * Letrehoz egy uj tormeleket a megadott aszteroidabol.
		 * @param a Megadott aszteroida
		 * @param t Kirajzolni kivant Tormelek
		 */
		
		public void UjTormelek(Aszteroida a, Tormelek t) {
			TormelekView tv = new TormelekView(t, a.getView().getX(), a.getView().getY(), a.getView().getnr());
			ArrayList<MezoView> seged = mezoViewk;
			for(MezoView mv : seged) {
				if(mv.getModellPar() == a) {
					mezoViewk.remove(mv);
					mezoViewk.add(tv);
					t.setView(tv);
					DrawAll();
					return;
				}
			}
		}
		
		/**
		 * Kapu kihelyezesekor leztrehoz egy view objektumot a kihelyezett kapunak
		 * @param tk - a lehelyezett teleportkapu
		 */
		public void UjKapu(TeleportKapu tk) {
			int x = Jatek.getInstance().aktivTelepes.getMezo().getView().getX();
			int y = Jatek.getInstance().aktivTelepes.getMezo().getView().getY();
			TeleportKapuView tkv = new TeleportKapuView(tk, x, y);
			Mezo tempM = tk.GetSzomszedok().get(0);
			mezoViewk.add(tkv);
			tk.setView(tkv);
			DrawAll();
		}
		
		/**
		 * MI_robot epitesekor letrehoz hozza egy view objektumot.
		 * @param r - a megepitett robot
		 */
		public void UjRobot(MI_robot r, Mezo m) {
			RobotView rv = null;
			int xx = 0;
			int yy = 0;
			for(int i = 0; i<m.getUrutazokdb(); ++i) {
				if(i < 4) {
					xx = (55*(i))+10;
					yy = -50;
				}
				else
					if(i < 8) {
						xx = (55*(i-4))+10;
						yy = 30;
					}
					else
						if(i < 12) {
							xx = (55*(i-8))+10;
							yy = 100;
						}
						else {
							xx = (55*(i-12))+10;
							yy = 190;
						}
				if(i==m.getUrutazokdb()-1)
					rv = new RobotView(r, xx, yy);
				else {
					m.getUrutazok().get(i).getView().setX(m.getView().getX() + xx);
					m.getUrutazok().get(i).getView().setY(m.getView().getY() + yy);
				}
			}
			urutazoViewk.add(rv);
			r.setView(rv);
			DrawAll();
		}
		
		/**
		 * A singleton mukodest segiti megvalositani
		 * @return - az egyetlen jatek referenciaja
		 */
		public static Jatek getInstance() {
			return egyed;
		}
		
		/**
		 * A program kezdetekor elinditja a jatekot.
		 * Inicializalja a betoltendo kepeket tartalmazo keszletet es megepitteti az aszteroidaovvel a jatek modelljet
		 */
		public void Kezdes() { 
			try {
				keszlet = new Keszlet();
				Aszteroidaov.getInstance().PalyaEpites();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * A jatek egy koret bonyolitja le.
		 * Ezt a fuggvenyt hivja a mainben levo jatekciklus, amig megy a jatek
		 */
		public void Kor() {	
			Aszteroidaov.getInstance().Lepes();
		}
		
		/**
		 * Befejezi a jatek mukodeset, azzal, hogy igazra allitja a vege valtozot
		 */
		public void Vege() {			
			vege = true;
		}

		/**
		 * setter a telepesszam valtozohoz
		 * @param i - a jatekban szereplo telepesek szama
		 */
		public void settelepesszam(int i) {
			telepesszam = i;
		}
		
		/**
		 * /**
		 * Beallitja, a telepeshely valtozot a parameter szerint
		 * @param i - az aszteroida szama, ahova a telepesek kerulni fognak a jatek elejen
		 */
		public void settelepeshely(int i) {
			telepeshely = i;
		}
		
		/**
		 * Beallitja, az ufohely valtozot a parameter szerint
		 * @param i - az aszteroida szama, ahova az ufok kerulni fognak a jatek elejen
		 */
		public void setufohely(int i) {
			ufohely = i;
		}
		
		/**
		 * Tulajdonkeppen getter a vege valtozora, ami addig hamis, amig megy a jatek
		 * @return - vege valtozo - igaz, ha valamilyen okbol vege van a jateknak
		 */
		public boolean isVege() {
			return vege;
		}
		
		/**
		 * Igazra allitja a mehet valtozot
		 * Akkor hivodik, ha minden inicializalva lett es indulhat a jatek
		 */
		public void Mehet() {
			mehet = true;
		}
		
		/**
		 * getter a mehet valtozora
		 * @return - mehet valtozo - akkor igaz, ha minden inicializalva van es indulhat a jatek
		 */
		public boolean getMehet() {
			return mehet;
		}
}
