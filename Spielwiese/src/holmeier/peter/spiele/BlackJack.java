package holmeier.peter.spiele;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class BlackJack {

	public static class Schlitten {
		public int[] karten;
		public int pos;
	}

	public static int wert(int n) {
		int w = n % 13;
		if (w <= 8)
			return w + 2;
		else if (w == 12)
			return 11;
		else
			return 10;
	}

	public static void mischen(int[] feld) {
		for (int i = 0; i < feld.length; i++) {
			int j = (int) (feld.length * Math.random());

			int dummy = feld[i];
			feld[i] = feld[j];
			feld[j] = dummy;
		}
	}

	public static int[] schlitten(int n) {

		int[] schlitten = new int[n * 52];
		for (int i = 0; i < schlitten.length; i += 52)
			for (int j = 0; j < 52; j++)
				schlitten[i + j] = j;
		mischen(schlitten);
		return schlitten;
	}

	public static int karte(Schlitten schlitten) {
		if (schlitten.pos == schlitten.karten.length) {
			System.out.println("\nSchlitten wird neu gefuellt...\n");
			mischen(schlitten.karten);
			schlitten.pos = 0;
		}
		return schlitten.karten[schlitten.pos++];
	}

	public static String name(int n) {
		String[] farben = { "Kreuz", "Pik", "Herz", "Karo" };
		String[] werte = { "Zwei", "Drei", "Vier", "Fuenf", "Sechs", "Sieben",
				"Acht", "Neun", "Zehn", "Bube", "Dame", "Koenig", "Ass" };

		return farben[n / 13] + " " + werte[n % 13];
	}

	public static int ausgabe(String p, Schlitten s) {
		int karte = karte(s);
		System.out.println(p + " erhaelt " + name(karte) + " (Wert="
				+ wert(karte) + ")");

		return wert(karte);
	}

	public static void main(String[] args) throws ParseException {

		int packs = IOTools
				.readInteger("Wie viele Paeckchen Karten sollen im Schlitten sein? ");

		int g = 100;

		System.out.println("Der aktuelle Kontostand des Spielers beträgt " + g
				+ " Euro");

		Schlitten schlitten = new Schlitten();
		schlitten.karten = schlitten(packs);
		schlitten.pos = 0;

		boolean nochEinSpiel = true;

		while (nochEinSpiel) {

			int einsatz = IOTools
					.readInteger("Wieviel Euro moechten Sie setzen?");
			if (einsatz > g) {
				System.out.println("Soviel Geld haben Sie nicht!");
				continue;
			} else
				g = g - einsatz;

			int sblatt = 0;
			int cblatt = 0;

			sblatt = ausgabe("Spieler", schlitten);
			cblatt = ausgabe("Croupier", schlitten);
			sblatt += ausgabe("Spieler", schlitten);

			if (sblatt == 21) {
				System.out.println("\n! B-L-A-C-K-J-A-C-K !\n");
				cblatt += ausgabe("Croupier", schlitten);
				if (cblatt < 21 || cblatt > 22) {
					System.out.println("Spieler gewinnt und erhält "
							+ (einsatz / 2) + " als Gewinn!\n");
					g = g + einsatz + (einsatz / 2);
				} else {
					System.out
							.println("EGALITE (Unentschieden, Der Spieler erhält seinen Einsatz zurück)");
					g = g + einsatz;
				}
			} else {

				while (true) {
					System.out.println();
					if (sblatt == 21) {
						System.out.println("Spieler hat 21 Punkte!!!");
						break;
					}
					if (sblatt > 21) {
						System.out.println("Spieler liegt ueber 21 Punkten!!!");
						break;
					}
					char antwort = ' ';
					while (antwort != 'J' && antwort != 'N')
						antwort = IOTools
								.readChar("Noch eine Karte (J/N)? Punktestand bisher "
										+ sblatt);
					if (antwort == 'N') {
						System.out.println("Spieler sagt: Nein");
						break;
					}
					System.out.println("Spieler sagt: Ja");
					sblatt += ausgabe("Spieler", schlitten);
				}

				if (sblatt <= 21) {
					System.out.println("\nCroupier ist am Zug:");
					while (cblatt <= 16)
						cblatt += ausgabe("Croupier", schlitten);

				}
				System.out.println("Croupier hat " + cblatt + " Punkte.");
				if (sblatt > 21 || (cblatt <= 21 && cblatt > sblatt))
					System.out
							.println(".... Spieler verliert kompletten Einsatz ....");
				else if (cblatt > 21 || cblatt < sblatt) {
					System.out
							.println("!!!! Spieler Gewinnt und verdoppelt seinen Einsatz !!!!");
					g = g + einsatz * 2;
				} else {
					System.out
							.println("EGALITE (Unentschieden, Der Spieler erhält seinen Einsatz zurück)");
					g = g + einsatz;
				}
			}
			System.out.println("Der aktuelle Kontostand des Spielers beträgt "
					+ g + " Euro");
			if (g <= 0) {
				System.out.println("Der Spieler hat das Spiel verloren!");
				break;
			}

			char antwort = ' ';
			while (antwort != 'J' && antwort != 'N')
				antwort = IOTools.readChar("Noch ein Spiel (J/N)?");
			nochEinSpiel = (antwort == 'J');
			System.out.println();

		}
	}
}
