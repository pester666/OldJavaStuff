package holmeier.peter.spiele;

import Prog1Tools.GameEngine;
import Prog1Tools.GameModel;

public class AchtdamenGui implements GameModel {

	private int aktNr = 0;

	public AchtdamenGui() {
		AchtdamenEngine.invoke();
	}

	public void buttonPressed(int row, int col) {
	}

	public int columns() {
		return 8;
	}

	public int rows() {
		return 8;
	}

	public void firePressed() {
		if (aktNr != AchtdamenEngine.loesung.length - 1)
			aktNr++;
	}

	public String getFireLabel() {
		return "Nächste bitte!";
	}

	public String getGameName() {
		return "Achtdamenproblem";
	}

	public String getMessages() {
		if (aktNr < 91)
			return "Lösung Nr: " + (aktNr + 1);
		else
			return "Lösung Nr: 92 - Letzte Lösung!";
	}

	public char getContent(int row, int col) {
		if (AchtdamenEngine.loesung[aktNr][row][col])
			return 'D';
		else
			return ' ';
	}

	public static void main(String[] args) {
		new GameEngine(new AchtdamenGui());
	}
}
