package aszteroida_banyaszat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Maganak a jatek ablakanak (JFrame) az osztalya, ahol a jatek grafikusan megjelenik. 
 */

public class JatekAblak extends JFrame{

	private static final JatekAblak egyed = new JatekAblak();
	private JatekAblak() {};
	/**
	 * Egy referencia a Jatek osztalyra.
	 */
	
	private Jatek jatek;
	
	/**
	 * Egy referencia a Muveletsor osztalyra.
	 */
	
	protected Muveletsor muveletsor;
	
	
	/**
	 * A JatekAblak JFrame-en belul egy kontener amibe a komponenseket
	 * (pl.: gomb, JComboBox) tesszuk.
	 */
	
	protected JPanel Kontener;
	
	/**
	 * A kommunikaciosTB-hez és a mezoInfoTB-hez egy kontener a JatekAblak JFrame-en.
	 */
	
	protected JPanel kommunikaciosP;
	
	/**
	 * Egy szovegdoboz, amely uzeneteket, figyelmezteteseket
	 * és informaciokat jelenit meg a felhasznalo szamara.
	 */
	
	protected JTextArea kommunikaciosTB;
	
	/**
	 * Egy szovegdoboz, amely az eppen aktiv mezo adatait
	 * jeleniti meg a felhasznalo szamara.
	 */
	
	protected JTextArea mezoInfoTB;
	
	/**
	 * Scrollhoz kell
	 */
	JScrollPane scroll;
	
	/**
	 * Ide kell majd fokuszaljon a a scroll
	 */
	Rectangle focusable;
	
	/**
	 * Frissiti a mezoInfoTB attributum (szovegdoboz) tartalmat, amikor szukseges (pl.: a telepes masik mezore lepett at).
	 * Frissiti a szovegdobozt, hogy mindig az aktualis mezo adatai szerepeljenek rajta.
	 * @param m A megadott mezo
	 */
	
	public void MezoInfoFrissit(Mezo m) {
		Font font = new Font("Verdana", Font.BOLD, 15);
		mezoInfoTB.setText(m.informaciok());
		mezoInfoTB.setFont(font);
		mezoInfoTB.setEditable(false);
	}
	
	/**
	 * Akkor hivodik meg, amikor a felhasznalo egy olyan muveletet szeretne veghezvinni, amely nem lehetseges.
	 * Kiirja a kommunikaciosTB valtozora, hogy sikertelen volt a muvelet es meg azt is, hogy miert volt sikertelen.
	 * @param s A kiirando uzenet szovege
	 */
	
	public void SikertelenMuvelet(String s) {
		Font font = new Font("Verdana", Font.BOLD, 20);
		if(Jatek.getInstance().aktivTelepes != null) {
			if(Jatek.getInstance().aktivTelepes.toString().equals("T1"))
				kommunikaciosTB.setForeground(Color.PINK);
			if(Jatek.getInstance().aktivTelepes.toString().equals("T2"))
				kommunikaciosTB.setForeground(Color.ORANGE);
			if(Jatek.getInstance().aktivTelepes.toString().equals("T3"))
				kommunikaciosTB.setForeground(Color.WHITE);
			if(Jatek.getInstance().aktivTelepes.toString().equals("T4"))
				kommunikaciosTB.setForeground(Color.YELLOW);
		}
		kommunikaciosTB.setText(s);
		kommunikaciosTB.setFont(font);
		kommunikaciosTB.setEditable(false);
	}
	
	
	/**
	 * A singleton mukodest segiti megvalositani
	 * @return - az egyetlen JatekAblak referenciaja
	 */
	public static JatekAblak getInstance() {
		return egyed;
	}

	public void elkeszites() throws IOException {
		kommunikaciosP = new JPanel();
		kommunikaciosP.setPreferredSize(new Dimension(210, 750));
		kommunikaciosTB = new JTextArea();
		kommunikaciosTB.setPreferredSize(new Dimension(210, 375));
		kommunikaciosTB.setBackground(Color.gray);
		mezoInfoTB = new JTextArea();
		mezoInfoTB.setPreferredSize(new Dimension(210, 375));
		mezoInfoTB.setBackground(Color.gray);
		kommunikaciosP.setBackground(Color.black);
		kommunikaciosP.add(kommunikaciosTB);
		kommunikaciosP.add(mezoInfoTB);
		
		MezoInfoFrissit(Jatek.getInstance().aktivTelepes.getMezo());
		
		JPanel palya = new JPanel();
		muveletsor = new Muveletsor();
    
		Jatek.getInstance().palya = new Palya();
		JPanel hatter = Jatek.getInstance().palya;
		JLabel labelhatter = new JLabel();
		labelhatter.setPreferredSize(new Dimension(5400, 5400));
		hatter.add(labelhatter);
		hatter.setSize(hatter.getPreferredSize());
	    hatter.setLocation(0, 0);
	    
	    JLayeredPane layered = new JLayeredPane();
		layered.add(hatter, JLayeredPane.DEFAULT_LAYER);
		
		for(int i = 0; i<Jatek.getInstance().mezoViewk.size(); ++i) {
	    	JPanel mezo = new JPanel();
	    	mezo.setPreferredSize(new Dimension(250, 250));
	    	mezo.setSize(mezo.getPreferredSize());
		    mezo.setLocation(Jatek.getInstance().mezoViewk.get(i).getX(), Jatek.getInstance().mezoViewk.get(i).getY());
			mezo.setOpaque(false);
			if(Jatek.getInstance().mezoViewk.get(i) == Jatek.getInstance().aktivTelepes.getMezo().getView())
				focusable = mezo.getBounds();
	    }
	  
	   layered.setPreferredSize(new Dimension(5400, 5400));
	    
	    scroll = new JScrollPane(layered);
	    scroll.getViewport().setPreferredSize(new Dimension(1250, 730));
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getViewport().scrollRectToVisible(focusable);
        
        palya.setLayout(new BorderLayout());
	    palya.add(scroll);
	    hatter.addMouseListener(Jatek.getInstance().controller.palyaEgerKezelo);
	    
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(muveletsor);
		getContentPane().add(palya);
		add(kommunikaciosP);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("ASZTEROIDABANYASZAT");
		setSize(1550, 880);
		getContentPane().setBackground(Color.black);
		setResizable(false);
	}
}
