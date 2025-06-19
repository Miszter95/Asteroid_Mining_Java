package aszteroida_banyaszat;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A menu megjeleniteseert felelos. Itt lehet kivalasztani, hogy hany darab telepessel 
 * szeretnenk jatszani, vagy itt lehet kilepni a jatekbol.
 */
public class Menu extends JFrame {

	/**
	 * Ezzel a gombbal lehet kilepni a jatekbol
	 */
	protected JButton exit;
	
	/**
	 * A jatekot 2 telepessel elinditja
	 */
	protected JButton kettot;
	
	/**
	 * A jatekot 3 telepessel elinditja
	 */
	protected JButton haromt;
	
	/**
	 * A jatekot 4 telepessel elinditja
	 */
	protected JButton negyt;
	
	/**
	 * ide lehet beirni, hogy a telepesek hova keruljenek, hol kezdjenek
	 */
	public static JTextField telepeshova;
	
	/**
	 * ide lehet beirni, hogy az ufok hova keruljenek, hol kezdjenek
	 */
	public static JTextField ufohova;
	public static int megvan;
	public static int korKezdes = 0;
	
	/**
	 * Konstruktor
	 * @throws IOException
	 */
	public Menu() throws IOException {
		setTitle("Menu");
		setSize(410, 600);
		GridLayout gl = new GridLayout(7, 1);
		setLayout(gl);
		megvan = 0;
		
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem help = new JMenuItem("Szabalyzat");
		JMenuItem tortenet = new JMenuItem("Jatektortenet");
		file.add(help);
		file.add(tortenet);
		menu.add(file);
		setJMenuBar(menu);
		
		
		JPanel borito = new JPanel(new FlowLayout());
		JLabel kep = new JLabel();
		ImageIcon kepicon = new ImageIcon(ImageIO.read(new File("boritocska.png")));
		ImageIcon kepi = new ImageIcon(kepicon.getImage().getScaledInstance(400,100, Image.SCALE_SMOOTH));
		kep.setIcon(kepi);
		borito.add(kep);
		borito.setSize(borito.getPreferredSize());
		add(borito);
		
		JPanel p1 = new JPanel(new FlowLayout());
		add(p1);
		JTextArea area1=new JTextArea("ASZTEROIDABANYASZAT");
		p1.add(area1);
		area1.setFont(new Font("Serif", 0, 30));
		area1.setForeground(new Color(127,255,212));
		area1.setEditable(false);
		area1.setBackground(new Color(10,0,87));
		p1.setBackground(new Color(10,0,87));
		
		JPanel dbpanel = new JPanel(new FlowLayout());
	    dbpanel.setLayout(new GridLayout(2,2));
	    dbpanel.add(new Label("Telepesek helye (1-60 pl.: 60):"));
	    dbpanel.add(new Label("Ufok helye:(1-60 pl.: 60):"));
	    telepeshova = new JTextField(10);
	    ufohova = new JTextField(10);
	    dbpanel.add(telepeshova);
	    dbpanel.add(ufohova);
	    add(dbpanel);
		
		JPanel p2 = new JPanel(new FlowLayout());
		kettot = new JButton();
		kettot.setText("2 JATEKOS");
		p2.add(kettot);
		p2.setSize(kettot.getPreferredSize());
		add(p2);
		
		JPanel p3 = new JPanel(new FlowLayout());
		haromt = new JButton();
		haromt.setText("3 JATEKOS");
		p3.add(haromt);
		p3.setSize(haromt.getPreferredSize());
		add(p3);
		
		JPanel p4 = new JPanel(new FlowLayout());
		negyt = new JButton();
		negyt.setText("4 JATEKOS");
		p4.add(negyt);
		p4.setSize(negyt.getPreferredSize());
		add(p4);
		
		JPanel p5 = new JPanel(new FlowLayout());
		exit = new JButton();
		exit.setText("EXIT");
		p5.add(exit);
		p5.setSize(exit.getPreferredSize());
		add(p5);
		
		help.setActionCommand("help");
		help.addActionListener(Jatek.getInstance().controller.menuBListener);
		tortenet.setActionCommand("tortenet");
		tortenet.addActionListener(Jatek.getInstance().controller.menuBListener);
		exit.setActionCommand("exit");
		exit.addActionListener(Jatek.getInstance().controller.menuBListener);
		kettot.setActionCommand("2");
		kettot.addActionListener(Jatek.getInstance().controller.menuBListener);
		haromt.setActionCommand("3");
		haromt.addActionListener(Jatek.getInstance().controller.menuBListener);
		negyt.setActionCommand("4");
		negyt.addActionListener(Jatek.getInstance().controller.menuBListener);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
