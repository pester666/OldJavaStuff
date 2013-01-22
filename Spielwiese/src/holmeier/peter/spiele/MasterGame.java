package holmeier.peter.spiele;

import Prog1Tools.GameEngine;
import Prog1Tools.GameModel;

public class MasterGame extends Mastermind implements GameModel {

	private final static int MAXRUNDE = 10;

	private int runde = 1;

	private int[] versuch = { 1, 2, 3, 4 };
	private int[] original = findeZahl();

	private boolean fertig;
	private boolean gewonnen;

	private String ausgabe = "MASTERMIND\n\n"
			+ "Raten Sie die Zahl! Sie haben " + MAXRUNDE + " Versuche...!";

	public int rows() {
		return 1;
	}

	public int columns() {
		return 4;
	}

	public String getGameName() {
		return "Mastermind";
	}

	public String getMessages() {
		return ausgabe;
	}

	public char getContent(int row, int col) {
		return (char) (versuch[col] + '0');
	}

	public String getFireLabel() {
		if (!fertig)
			return "Rateversuch Nr. " + runde;

		if (gewonnen)
			return "GEWONNEN!";

		else
			return "Leider verloren...";
	}

	private boolean validate() {
		int zahl = versuch[0] + versuch[1] * 10 + versuch[2] * 100 + versuch[3]
				* 1000;
		return bereiteAuf(zahl) != null;
	}

	public void buttonPressed(int row, int col) {
		if (runde <= MAXRUNDE && !fertig) {
			do {
				versuch[col] = (versuch[col] + 1) % 10;
			} while (!validate());
		}
	}

	public void firePressed() {
		if (runde <= MAXRUNDE && !fertig) {
			runde++;
			fertig = runde > MAXRUNDE || treffer(original, versuch);
			gewonnen = treffer(original, versuch);
			if (fertig && gewonnen)
				ausgabe = "Spiel beendet. \n\nSie haben gewonnen!";
			if (fertig && !gewonnen)
				ausgabe = "Spiel beendet. \n\nSie haben verloren!";
			if (!fertig)
				ausgabe = auswerten(original, versuch) + "\n\n"
						+ "Anzahl der verbleibenden Versuche: "
						+ (MAXRUNDE - runde + 1);

		}
	}

	public static void main(String[] args) {

		new GameEngine(new MasterGame());
	}
}
