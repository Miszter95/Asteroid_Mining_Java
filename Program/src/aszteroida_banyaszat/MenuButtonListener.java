package aszteroida_banyaszat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * A menu gombjainak esemenykezeloje.
 */

public class MenuButtonListener implements ActionListener{

	/**
	 * Akkor hivodik meg, amikor egy muvelet, esemeny (menugomb megnyomasa) megtortenik.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {

		int thely;
		int uhely;
		try {
			thely = Integer.parseInt(Menu.telepeshova.getText());
			Menu.telepeshova.setBackground(Color.WHITE);
		}
		catch (NumberFormatException e){
			thely = -1;
			Menu.telepeshova.setBackground(Color.RED);
		}
		try {
			uhely = Integer.parseInt(Menu.ufohova.getText());
			Menu.ufohova.setBackground(Color.WHITE);
		}
		catch (NumberFormatException e){
			uhely = -1;
			Menu.ufohova.setBackground(Color.RED);
		}
		
		
		if(uhely > 60 || uhely < 1)
			Menu.ufohova.setBackground(Color.RED);
		
		if(thely > 60 || thely < 1)
			Menu.telepeshova.setBackground(Color.RED);
		
		if(ae.getActionCommand() == "exit")	{
			System.exit(0);
		}
		
		if(ae.getActionCommand() == "help") {
			
			try {
				
				JPanel szabalyzat = new JPanel();
				JLabel szabalyhatter = new JLabel();
				szabalyhatter.setIcon(new ImageIcon(ImageIO.read(new File("szabalyzat.png"))));
				szabalyzat.add(szabalyhatter);
				JOptionPane szabaly = new JOptionPane(szabalyzat);
				JDialog jdialog = szabaly.createDialog("Szabalyzat");
				jdialog.setVisible(true);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(ae.getActionCommand() == "tortenet") {
			
			try {
				Scanner in = new Scanner(new File("tortenet.txt"));
				ArrayList<String> tortenetszovegem = new ArrayList<>();
				
				while(in.hasNextLine()) {
					tortenetszovegem.add(in.nextLine());
				}
				in.close();
				
				JTextArea tortenet = new JTextArea(66,110);
				tortenet.setFont(tortenet.getFont().deriveFont(18F));
				tortenet.setLineWrap(true);
				tortenet.setEditable(false);
				for(int i = 0; i < tortenetszovegem.size(); i++) {
					
					if(i != 0) {
						tortenet.append("\n");
					}
					tortenet.append(tortenetszovegem.get(i));
				}
				
				JScrollPane sptortenet = new JScrollPane(tortenet);
				sptortenet.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				sptortenet.setPreferredSize(new Dimension(700, 800));
				JOptionPane tortenetablak = new JOptionPane(sptortenet);
				JDialog jdialog = tortenetablak.createDialog("Tortenet");
				jdialog.setVisible(true);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		if(ae.getActionCommand() == "2") {
			if(uhely > 0 && thely > 0 && uhely < 61 && thely < 61 && Menu.megvan == 0) {
				Jatek.getInstance().settelepesszam(2);
				Jatek.getInstance().settelepeshely(thely);
				Jatek.getInstance().setufohely(uhely);
				Menu.megvan = 1;
				Jatek.getInstance().Kezdes();
				Jatek.getInstance().Mehet();
				Menu.korKezdes = 1;
			}
		}
		if(ae.getActionCommand() == "3") {
			if(uhely > 0 && thely > 0 && uhely < 61 && thely < 61 && Menu.megvan == 0) {
				Jatek.getInstance().settelepesszam(3);
				Jatek.getInstance().settelepeshely(thely);
				Jatek.getInstance().setufohely(uhely);
				Menu.megvan = 1;
				Jatek.getInstance().Kezdes();
				Jatek.getInstance().Mehet();
				Menu.korKezdes = 1;
			}
		}
		if(ae.getActionCommand() == "4") {
			if(uhely > 0 && thely > 0  && uhely < 61 && thely < 61 && Menu.megvan == 0) {
				Jatek.getInstance().settelepesszam(4);
				Jatek.getInstance().settelepeshely(thely);
				Jatek.getInstance().setufohely(uhely);
				Menu.megvan = 1;
				Jatek.getInstance().Kezdes();
				Jatek.getInstance().Mehet();
				Menu.korKezdes = 1;
			}	
		}
	}

	
}
