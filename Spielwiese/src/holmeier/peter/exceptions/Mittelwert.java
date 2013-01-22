package holmeier.peter.exceptions;

import holmeier.peter.IOTools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Mittelwert {

	public static int parse(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			System.out.println("Eingabefehler: " + ex.getMessage());
			return 0;
		}
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		try {
			String dateiname = IOTools.readLine("Dateiname: ");
			BufferedReader datei = new BufferedReader(new FileReader(dateiname));
			int anzahl = parse(datei.readLine());
			int summe = 0;
			for (int i = 0; i < anzahl; i++)
				summe = summe + parse(datei.readLine());
			double mw = (double) summe / anzahl;
			System.out.println("mittelwert: " + mw);
		} catch (FileNotFoundException ex) {
			System.out.println("Diese Datei existiert nicht!");
		} catch (IOException ex) {
			System.out.println("Fehler beim Einlesen: " + ex.getMessage());
		}
	}
}
