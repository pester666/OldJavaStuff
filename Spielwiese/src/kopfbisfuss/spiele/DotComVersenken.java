package kopfbisfuss.spiele;

import java.util.ArrayList;

public class DotComVersenken {

	private SpielHelfer helfer = new SpielHelfer();
	private ArrayList<DotCom> dotComListe = new ArrayList<DotCom>();
	private int anzahlVersuche = 0;

	private void spielEinreichten() {
		DotCom eins = new DotCom();
		eins.setName("Victory");
		DotCom zwei = new DotCom();
		zwei.setName("Flying Dutchman");
		DotCom drei = new DotCom();
		drei.setName("Black Pearl");
		dotComListe.add(eins);
		dotComListe.add(zwei);
		dotComListe.add(drei);

		System.out
				.println("Ihre Aufgabe ist es, drei Kriegsschiffe zu versenken.");
		System.out
				.println("Die Victory, die Flying Dutchman und die Black Pearl");
		System.out
				.println("Versuche Sie, sie mit so wenig Versuchen wie möglich zu vernichten!");

		for (DotCom aktuellesDotCom : dotComListe) {

			ArrayList<String> neuerOrt = helfer.platziereDotCom(3);

			aktuellesDotCom.setZellorte(neuerOrt);
		}

	}

	private void beginneSpiel() {
		while (!dotComListe.isEmpty()) {

			String rateversuch = helfer
					.getBenutereingabe("Geben Sie einen Rateversuch ein.");
			prüfeRateversuch(rateversuch);
		}
		beendeSpiel();
	}

	private void prüfeRateversuch(String rateversuch) {

		anzahlVersuche++;

		String ergebnis = "Vorbei";

		for (DotCom aktuellesDotCom : dotComListe) {
			ergebnis = aktuellesDotCom.prüfDich(rateversuch);

			if (ergebnis.equals("Treffer"))
				break;
			if (ergebnis.equals("Versenkt")) {
				dotComListe.remove(aktuellesDotCom);
				break;
			}
		}

		System.out.println(ergebnis);
	}

	private void beendeSpiel() {
		System.out
				.println("Alle Kriegsschiffe sind versenkt! Ihre Flotte ist sicher!");
		if (anzahlVersuche <= 18)
			System.out.println("Sie haben nur " + anzahlVersuche
					+ " Versuche benötigt. Sie erhalten eine Ehrenmedaille!");
		else {
			System.out.println("Hat ja lang gedauert. " + anzahlVersuche
					+ " Versuche.");
			System.out
					.println("Wollen Sie lieber das Deck schurbben anstatt die Kanone bedienen?");
		}
	}

	public static void main(String[] args) {
		DotComVersenken spiel = new DotComVersenken();
		spiel.spielEinreichten();
		spiel.beginneSpiel();
	}
}
