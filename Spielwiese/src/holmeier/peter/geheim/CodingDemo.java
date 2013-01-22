package holmeier.peter.geheim;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class CodingDemo {

	Encoder enc;
	int key;

	public static void demo(Encoder enc) {
		String line = IOTools.readLine("Zu verschlüsselnde Zeile: ");
		String encoded = enc.encode(line);
		System.out.println("Verschlüsselt: " + encoded);
		String decoded = enc.decode(encoded);
		System.out.println("Entschlüsselt: " + decoded);
		if (line.equals(decoded))
			System.out.println("VERSCHLÜSSELUNG ERFOLGREICH!");
		else
			System.out.println("PROGRAMMFEHLER!");
	}

	public static void main(String[] args) throws ParseException {
		System.out.println("----Codierungs--Demo----");
		System.out.println("========================");
		System.out.println("1 = Inflater-Algorythmus");
		System.out.println("2 =  XOR-Verschlüsselung");
		System.out.println("3 =         Inflater/XOR");
		System.out.println("========================");

		int auswahl = IOTools.readInteger("Ihre Wahl:");
		switch (auswahl) {
		case 1:
			demo(new Inflater());
			break;
		case 2:
			int key = IOTools.readInteger("Bitte Schlüssel eingeben:");
			Encoder enc = new XorEncoder(key);
			demo(enc);
			break;
		case 3:
			key = IOTools.readInteger("Bitte Schluessel eingeben: ");
			enc = new XorInflater(key);
			demo(enc);
			break;
		default:
			System.out.println("Ungültige Auswahl!");
		}
	}

}
