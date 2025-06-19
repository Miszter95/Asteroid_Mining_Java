package aszteroida_banyaszat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

/**
 * A telepesek vezerlesehez tartozo grafikus elemeket tarolja.
 */

public class Muveletsor extends JPanel {

	/**
	 * Ezzel a gombbal lehet mozgatni a telepest.
	 */
	
	protected JButton Mozgas;
	
	/**
	 * Ezzel a gombbal lehet furni.
	 */
	
	protected JButton Furas;
	
	/**
	 * Ezzel a gombbal lehet banyaszni.
	 */
	
	protected JButton Banyaszas;

	/**
	 * Ezzel a gombbal lehet robotot epiteni.
	 */
	
	protected JButton RobotEpites;
	
	/**
	 * Ezzel a gombbal lehet teleportkaput epiteni.
	 */
	
	protected JButton KapuEpites;
	
	/**
	 * Ezzel a gombbal lehet a bazist felepiteni.
	 */
	
	protected JButton BazisEpites;
	
	/**
	 * Ezzel a gombbal lehet nyersanyag atadast kezdemenyezni.
	 */
	
	protected JButton Atadas;
	
	/**
	 * Ezzel a gombbal lehet egy nyersanyagot elhelyezni egy ureges aszteroida magjaba.
	 */
	
	protected JButton Visszarakas;
	
	/**
	 * Ezzel a gombbal lehet felvenni egy nyersanyagot egy aszteroida felszinerol.
	 */
	
	protected JButton Felvetel;
	
	/**
	 * Ez a 10 gomb jeleniteni meg a telepes nyersanyag raktaranak elemeit. Ezekkel lehet kivalasztani,
	 * hogy pl. melyiket rakja be egy ureges aszteroida magjaba.
	 */
	
	protected ArrayList<NyersanyagGomb> Nyersanyagok = new ArrayList<NyersanyagGomb>();
	
	/**
	 * Ezek a gombok jelenitik meg a telepesnel levo teleportkapukat.
	 * Ezekkel valaszthatja ki, hogy pl. melyiket szeretne megepiteni.
	 */
	
	protected ArrayList<TeleportKapuGomb> Kapuk = new ArrayList<TeleportKapuGomb>();
	
	/**
	 * Megjeleniti annak az aszteroidanak a felszinen levo nyersanyagokat, amelyen a telepes jelenleg all.
	 */
	
	protected JComboBox<Nyersanyag> Felszin;
	
	/**
	 * Frissiti a telepeshez tartozo nyersanyagok es kapuk kepeit
	 * @param t A megadott telepes
	 */
	
	public void Frissit(Telepes t) {
		ArrayList<Nyersanyag> nyersak = t.getNyersanyagraktar();
        if(nyersak.size() > 0) {
        	for(int i = 0; i<nyersak.size(); ++i) {
            	Nyersanyagok.get(i).setIconNy(nyersak.get(i));
            }
        }
        
        if(nyersak.size() < 10) {
        	for(int i = nyersak.size(); i<10; ++i) {
        		Nyersanyagok.get(i).setIconNy();
        	}
        }
        
        ArrayList<TeleportKapu> kaputk = t.getKapuk();
        if(kaputk.size() > 0) {
        	for(int i = 0; i<kaputk.size(); ++i) {
        		Kapuk.get(i).setIconT(kaputk.get(i));
            }
        }
        
        if(kaputk.size() < 3) {
        	for(int i = kaputk.size(); i<3; ++i) {
        		Kapuk.get(i).setIconT();
        	}
        }
	}
	
	/**
	 *  Frissiti a Felszin legordulomenut
	 *  Akkor hivodik, ha egy uj telepes lep
	 * @param fny - a nyersanyaglista ami alapjan frissiteni kell a legordulomenut
	 */
	public void FrissitFelszin(ArrayList<Nyersanyag> fny) {
		Felszin.removeAllItems();
		if (fny != null) {
			for (Nyersanyag ny : fny)
				Felszin.addItem(ny);
		}
	}
	
	/**
	 * Konstruktor - letrehozza es inicializalja a muveletsoron szerepelo gombokat es legordulomenut
	 * @throws IOException
	 */
	public Muveletsor() throws IOException {
		setBounds(0,0,1300,50);
		setPreferredSize(new Dimension(1300, 70));
        setBackground(Color.black); 
        setFocusable(true);
        JPanel panelbuttons = new JPanel();
        JPanel panelraktar = new JPanel();
        
        Mozgas = new JButton(new ImageIcon(Jatek.getInstance().keszlet.muveletKepek.get(0)));
        Mozgas.setToolTipText("Mozgas egy szomszedos aszteroidara");
        Mozgas.setBackground(Color.gray);
        Mozgas.setBorder(BorderFactory.createEmptyBorder());
        
        Furas =new JButton(new ImageIcon(Jatek.getInstance().keszlet.muveletKepek.get(1)));
        Furas.setToolTipText("Aszteroida egy retegenek atfurasa");
        Furas.setBackground(Color.gray);
        Furas.setBorder(BorderFactory.createEmptyBorder());
        
        Banyaszas =new JButton(new ImageIcon(Jatek.getInstance().keszlet.muveletKepek.get(2)));
        Banyaszas.setToolTipText("Az aszteroida magjanak kibanyaszasa");
        Banyaszas.setBackground(Color.gray);
        Banyaszas.setBorder(BorderFactory.createEmptyBorder());
        
        RobotEpites=new JButton(new ImageIcon(Jatek.getInstance().keszlet.muveletKepek.get(3)));
        RobotEpites.setToolTipText("MI robot epitese");
        RobotEpites.setBackground(Color.gray);
        RobotEpites.setBorder(BorderFactory.createEmptyBorder());
        
        KapuEpites=new JButton(new ImageIcon(Jatek.getInstance().keszlet.muveletKepek.get(4)));
        KapuEpites.setToolTipText("Teleportkapu-par epitese (2db)");
        KapuEpites.setBackground(Color.gray);
        KapuEpites.setBorder(BorderFactory.createEmptyBorder());
        
        BazisEpites=new JButton(new ImageIcon(Jatek.getInstance().keszlet.muveletKepek.get(5)));
        BazisEpites.setToolTipText("Bazisepites");
        BazisEpites.setBackground(Color.gray);
        BazisEpites.setBorder(BorderFactory.createEmptyBorder());
        
        Atadas=new JButton(new ImageIcon(Jatek.getInstance().keszlet.muveletKepek.get(6)));
        Atadas.setToolTipText("Nyersanyag atadas egy masik telepesnek");
        Atadas.setBackground(Color.gray);
        Atadas.setBorder(BorderFactory.createEmptyBorder());
        
        Visszarakas=new JButton(new ImageIcon(Jatek.getInstance().keszlet.muveletKepek.get(7)));
        Visszarakas.setToolTipText("Egy nyersanyag ureges aszteroidaba valo visszahelyezese vagy egy teleportkapu kihelyezese egy aszteroida szomszedsagaba ");
        Visszarakas.setBackground(Color.gray);
        Visszarakas.setBorder(BorderFactory.createEmptyBorder());
        
        Felvetel=new JButton(new ImageIcon(Jatek.getInstance().keszlet.muveletKepek.get(8)));
        Felvetel.setToolTipText("Egy nyersanyag felvetele a mezo felszinerol");
        Felvetel.setBackground(Color.gray);
        Felvetel.setBorder(BorderFactory.createEmptyBorder());
        
        panelbuttons.add(Mozgas); 
        panelbuttons.add(Furas); 
        panelbuttons.add(Banyaszas);
        panelbuttons.add(RobotEpites);
        panelbuttons.add(KapuEpites);
        panelbuttons.add(BazisEpites);
        panelbuttons.add(Atadas);
        panelbuttons.add(Visszarakas); 
        panelbuttons.add(Felvetel);
        panelbuttons.setBackground(Color.black);
        
        Mozgas.setActionCommand("Mozgas");
		Mozgas.addActionListener(Jatek.getInstance().controller.muveletsorListener);
		
		Furas.setActionCommand("Furas");
		Furas.addActionListener(Jatek.getInstance().controller.muveletsorListener);
		
		Banyaszas.setActionCommand("Banyaszas");
		Banyaszas.addActionListener(Jatek.getInstance().controller.muveletsorListener);
		
		RobotEpites.setActionCommand("RobotEpites");
		RobotEpites.addActionListener(Jatek.getInstance().controller.muveletsorListener);
		
		KapuEpites.setActionCommand("KapuEpites");
		KapuEpites.addActionListener(Jatek.getInstance().controller.muveletsorListener);
		
		BazisEpites.setActionCommand("BazisEpites");
		BazisEpites.addActionListener(Jatek.getInstance().controller.muveletsorListener);
		
		Atadas.setActionCommand("Atadas");
		Atadas.addActionListener(Jatek.getInstance().controller.muveletsorListener);
		
		Visszarakas.setActionCommand("Visszarakas");
		Visszarakas.addActionListener(Jatek.getInstance().controller.muveletsorListener);
		
		Felvetel.setActionCommand("Felvetel");
		Felvetel.addActionListener(Jatek.getInstance().controller.muveletsorListener);
        
        panelraktar.setBackground(Color.black);
        
        ArrayList<Nyersanyag> nyersak = Jatek.getInstance().aktivTelepes.getNyersanyagraktar();
        if(nyersak.size() > 0) {
        	for(int i = 0; i<nyersak.size(); ++i) {
            	Nyersanyagok.add(new NyersanyagGomb(nyersak.get(i)));
            }
        }
        
        if(nyersak.size() < 10) {
        	for(int i = nyersak.size(); i<10; ++i) {
        		Nyersanyagok.add(new NyersanyagGomb());
        	}
        }
        
        for(int i = 0; i<10; ++i) {
        	Nyersanyagok.get(i).setBackground(Color.gray);
        	Nyersanyagok.get(i).setBorder(BorderFactory.createEmptyBorder());
        	panelraktar.add(Nyersanyagok.get(i));
        	Nyersanyagok.get(i).setActionCommand("Nyersanyag");
        	Nyersanyagok.get(i).addActionListener(Jatek.getInstance().controller.muveletsorListener);
        }
        
        ArrayList<TeleportKapu> kaputk = Jatek.getInstance().aktivTelepes.getKapuk();
        if(kaputk.size() > 0) {
        	for(int i = 0; i<kaputk.size(); ++i) {
        		Kapuk.add(new TeleportKapuGomb(kaputk.get(i)));
            }
        }
        
        if(kaputk.size() < 3) {
        	for(int i = kaputk.size(); i<3; ++i) {
        		Kapuk.add(new TeleportKapuGomb());
        	}
        }
        
        for(int i = 0; i<3; ++i) {
        	Kapuk.get(i).setBackground(Color.gray);
        	Kapuk.get(i).setBorder(BorderFactory.createEmptyBorder());
        	Kapuk.get(i).setActionCommand("TeleportKapu");
        	Kapuk.get(i).addActionListener(Jatek.getInstance().controller.muveletsorListener);
        	panelraktar.add(Kapuk.get(i));
        }
        
        JTextArea area=new JTextArea("Felszin: ");
        area.setFont(new Font("Serif", 0, 15));
        area.setEditable(false);
        area.setForeground(Color.BLUE);
        area.setBackground(Color.BLACK);
        
        Mezo m = Jatek.getInstance().aktivTelepes.getMezo();
        ArrayList<Nyersanyag> nyersik = m.GetFelszin();
        
        ArrayList<String> ny = new ArrayList<String>();
        for(int i = 0; i<nyersik.size(); ++i) {
        	ny.add(nyersik.getClass().getName());
        }
        
        Felszin = new JComboBox<Nyersanyag>();
        add(panelbuttons);
        add(panelraktar);
        add(area);
        add(Felszin);
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
	}
}
