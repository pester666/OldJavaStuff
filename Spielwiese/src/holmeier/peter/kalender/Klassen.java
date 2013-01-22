package holmeier.peter.kalender;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class Klassen {

	public static void main(String[] args) throws ParseException {

		Adresse[] adressen = new Adresse[20];
		Adresse adr;

		boolean fertig = false;

		for (int i = 0; i < 20; i++)
			adressen[i] = new Adresse();
		adr = adressen[0];

		System.out.println("================================");
		System.out.println("(>^_^)> Adressverwaltung <(^-^<)");
		System.out.println("================================");
		while (!fertig) {
			System.out.println(" ");
			System.out.println("1 = Adresseingabe");
			System.out.println("2 = Adressausgabe");
			System.out.println("3 = aktuelle Adresse wechseln");
			System.out.println("4 = Programm beenden");
			int auswahl = IOTools.readInteger("Ihre Wahl:");
			switch (auswahl) {
			case 1:
				adr.name = IOTools.readLine("Name			:");
				adr.strasse = IOTools.readLine("Strasse		:");
				adr.hausnummer = IOTools.readInteger("Hausnummer	:");
				adr.wohnort = IOTools.readLine("Wohnort		:");
				adr.postleitzahl = IOTools.readInteger("PLZ			:");
				adr.telefon = IOTools.readInteger("Telefon		:");
				adr.mail = IOTools.readLine("E-Mail		:");
				adr.kommentar = IOTools.readLine("Kommentar		:");
				break;
			case 2:
				System.out.println(adr.name);
				System.out.println(adr.strasse + " " + adr.hausnummer);
				System.out.println(adr.postleitzahl + " " + adr.wohnort);
				System.out.println("Telefon: " + adr.telefon);
				System.out.println("E-Mail: " + adr.mail);
				System.out.println("KOMMENTAR: " + adr.kommentar);
				break;
			case 3:
				int n = IOTools.readInteger("Neue Adressnummer "
						+ " (zwischen 0 und 19).");
				adr = adressen[n];
				break;
			case 4:
				fertig = true;
				System.out.println("Programm beendet!");
				break;
			default:
				System.out.println("Eingabefehler!");

			}// switch
		}// while
	}
}