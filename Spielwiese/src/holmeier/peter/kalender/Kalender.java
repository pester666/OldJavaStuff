package holmeier.peter.kalender;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class Kalender {
	public static void main(String[] args) throws ParseException {

		String[][] termine;
		termine = new String[31][];

		for (int i = 0; i < termine.length; i++) {
			termine[i] = new String[24];
			for (int j = 0; j < termine[i].length; j++)
				termine[i][j] = "";
		}
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
				int tag = IOTools.readInteger("Welcher Tag?");
				if (tag < 0 || tag > 31) {
					System.out.println("Eingabefehler!");
					break;
				}
				int nummer = IOTools.readInteger("Wie viel Uhr?");
				if (nummer < 0 || nummer > 23) {
					System.out.println("Eingabefehler!");
					break;
				}
				String eingabe = IOTools.readLine("Termin:");
				termine[tag - 1][nummer] = eingabe;
				break;
			case 2:
				int t = IOTools.readInteger("Welcher Tag?");
				if (t < 1 || t > 31) {
					System.out.println("Eingabefehler!");
					break;
				}
				for (int i = 0; i < termine[t - 1].length; i++)
					System.out.println(i + ".00 Uhr: " + termine[t - 1][i]);
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
