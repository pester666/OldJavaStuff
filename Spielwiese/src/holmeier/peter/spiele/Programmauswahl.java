package holmeier.peter.spiele;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class Programmauswahl {

	public static void main(String[] args) throws ParseException {

		System.out.println("Waehlen Sie ein zu startendes Programm:");
		System.out.println("^-^-^-^-^-^-^-^-^-^-^-^-^-^-^");
		System.out.println("Nummer 1 =  	Mastermind");
		System.out.println("Nummer 2 =   	Black Jack");
		System.out.println("Nummer 3 =   	 Ratespiel");
		System.out.println("^-^-^-^-^-^-^-^-^-^-^-^-^-^-^");
		int wahl = IOTools.readInteger("Ihre Eingabe:");

		boolean prog = false;

		while (!prog) {
			switch (wahl) {
			case 1:
				Mastermind.main(args);
				break;
			case 2:

				BlackJack.main(args);
				break;
			case 3:
				Ratespiel.main(args);
				break;
			default:
				break;
			}
			prog = true;
		}

	}

}
