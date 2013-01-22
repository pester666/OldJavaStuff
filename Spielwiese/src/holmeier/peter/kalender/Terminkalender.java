package holmeier.peter.kalender;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class Terminkalender {
	public static void main(String[] args) throws ParseException {

		String[] termine = new String[24];

		for (int i = 0; i < 24; i++)
			termine[i] = " ";

		boolean fertig = false;
		while (fertig)
			;
		{
			System.out.println("1 = Neuer Eintrag");
			System.out.println("2 = Termine ausgeben");
			System.out.println("3 = Programm beenden");
			int auswahl = IOTools.readInteger("Ihre Wahl:");
			switch (auswahl) {
			case 1:
				int nummer = IOTools.readInteger("Wie viel Uhr?");
				if (nummer < 0 | nummer > 23) {
					System.out.println("Eingabefehler!");
					break;
				}
				String eingabe = IOTools.readLine("Termin:");
				termine[nummer] = eingabe;
				break;
			case 2:
				for (int i = 0; i < 24; i++)
					System.out.println(i + " Uhr: " + termine[i]);
				break;
			case 3:
				fertig = true;
				break;
			default:
				System.out.println("Eingabefehler!");
			}
		}
	}
}
