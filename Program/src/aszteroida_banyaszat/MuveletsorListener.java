package aszteroida_banyaszat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A muveletek (pl.: furas, banyaszas, stb.) gombjainak esemenykezeloje.
 */

public class MuveletsorListener implements ActionListener{

	/**
	 * Akkor hivodik meg, amikor egy muvelet, esemeny (muvelet gomb megnyomasa) megtortenik.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Jatek.getInstance().isVege())
			return;
		switch (arg0.getActionCommand()) {
			case "Mozgas":
				Jatek.getInstance().controller.aktivGomb = "Mozgas";
				Jatek.getInstance().controller.palyaAktiv = true;
			break;
			case "Furas":
				Jatek.getInstance().controller.aktivGomb = "Furas";
				Jatek.getInstance().aktivTelepes.Furas();
			break;
			case "Banyaszas":
				Jatek.getInstance().controller.aktivGomb = "Banyaszas";
				Jatek.getInstance().aktivTelepes.Banyaszas();
			break;
			case "RobotEpites":
				Jatek.getInstance().controller.aktivGomb = "RobotEpites";
				Jatek.getInstance().aktivTelepes.Robotepites();
			break;
			case "KapuEpites":
				Jatek.getInstance().controller.aktivGomb = "KapuEpites";
				Jatek.getInstance().aktivTelepes.TeleportkapuparEpites();
			break;
			case "BazisEpites":
				Jatek.getInstance().controller.aktivGomb = "BazisEpites";
				Jatek.getInstance().aktivTelepes.BazisEpites();
			break;
			case "Atadas":
				Jatek.getInstance().controller.aktivGomb = "Atadas";
			break;
			case "Visszarakas":
				Jatek.getInstance().controller.aktivGomb = "Visszarakas";
			break;
			case "Felvetel":
				Jatek.getInstance().controller.aktivGomb = "Felvetel";
				Jatek.getInstance().aktivTelepes.FelszinrolLeszed((Nyersanyag)JatekAblak.getInstance().muveletsor.Felszin.getSelectedItem());
			break;
			case "TeleportKapu":
				TeleportKapuGomb tkg = (TeleportKapuGomb) arg0.getSource();
				Jatek.getInstance().aktivTelepes.TeleportLerakas(tkg.getModellPar());
			break;
			case "Nyersanyag":
				if (Jatek.getInstance().controller.aktivGomb == "Atadas") {
					NyersanyagGomb nyg = (NyersanyagGomb) arg0.getSource();
					Jatek.getInstance().controller.valasztottNy = nyg.getModelPar();
					Jatek.getInstance().controller.palyaAktiv = true;
				}
				if (Jatek.getInstance().controller.aktivGomb == "Visszarakas") {
					NyersanyagGomb nyg = (NyersanyagGomb) arg0.getSource();
					Jatek.getInstance().aktivTelepes.Visszahelyezes(nyg.getModelPar());
				}
			break;
		}
	}

	
}
