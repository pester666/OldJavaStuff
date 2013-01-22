package holmeier.peter.zellen;

import holmeier.peter.IOTools;

import java.text.ParseException;

import Prog1Tools.GameEngine;
import Prog1Tools.GameModel;

public class Life implements GameModel {

	private Petrischale zellen;

	public Life(int breite, int hoehe, int lebendig) {
		zellen = new Petrischale(breite, hoehe, lebendig);
	}

	public int rows() {
		return zellen.getBreite();
	}

	public int columns() {
		return zellen.getLaenge();
	}

	public String getMessages() {
		return "Spiel das Spiel des Lebens. \n" + "Schaue was passiert, \n"
				+ "Wenn das Leben eben \n" + "vor sich hinmutiert.";
	}

	public String getGameName() {
		return "Game of Life";
	}

	public String getFireLabel() {
		return "Nächste Generation";
	}

	public char getContent(int row, int col) {
		return (zellen.getInhalt(row, col).istLebendig()) ? 'O' : ' ';
	}

	public void buttonPressed(int row, int col) {
		zellen.schalteUm(row, col);
	}

	public void firePressed() {
		zellen = new Petrischale(zellen);
	}

	public static void main(String[] args) throws ParseException {
		new GameEngine(new Life(IOTools.readInteger("Anzahl der Zeilen  :"),
				IOTools.readInteger("Anzahl der Spalten :"), IOTools
						.readInteger("Anzahl der Lebenden:")));
	}

}
