package holmeier.peter.damenjagd;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class DSpiel {

	public static void main(String[] args) throws ParseException {

		DameFigur beute, jaeger;

		int zeilenPos, anzahl;
		char spaltenPos, richtung;
		String farbe;
		String str = "";

		System.out.println("Positionieren Sie die Beute");
		spaltenPos = IOTools.readChar("Spalte (A bis H) Ihrer Figur? ");
		zeilenPos = IOTools.readInteger("Zeile (1 bis 8) Ihrer Figur? ");
		farbe = IOTools.readLine("Farbe Ihrer Figur? ");
		beute = new DameFigur(Character.toUpperCase(spaltenPos), zeilenPos,
				farbe);

		Bildschirm.loeschen();

		System.out.println("Positionieren Sie den Jäger");
		spaltenPos = IOTools.readChar("Spalte (A bis H) Ihrer Figur? ");
		zeilenPos = IOTools.readInteger("Zeile (1 bis 8) Ihrer Figur? ");
		farbe = IOTools.readLine("Farbe Ihrer Figur? ");
		jaeger = new DameFigur(Character.toUpperCase(spaltenPos), zeilenPos,
				farbe);

		if (jaeger.trifft(beute))
			System.out.println("Treffer! Sie (als Jäger) haben gewonnen");
		else {
			System.out.println("Die Beute-Figur steht woanders!");
			System.out
					.println("Sie haben nun 10 Züge, um die Beute-Figur zu treffen.");
			for (int i = 1; i <= 10; i++) {
				System.out.println("Bewegen Sie Ihre " + jaeger);
				richtung = IOTools
						.readChar("Wollen Sie waagrecht (-), senkrecht (|) "
								+ "oder diagonal (/, \\) ziehen? ");
				switch (richtung) {
				case '-':
					str = "(< 0 nach links, > 0 nach rechts) ";
					break;
				case '|':
					str = "(< 0 nach oben,  > 0 nach unten ) ";
					break;
				case '/':
					str = "(< 0 nach links unten, > 0 nach rechts oben ) ";
					break;
				case '\\':
					str = "(< 0 nach links oben,  > 0 nach rechts unten) ";
					break;
				}

				anzahl = IOTools.readInteger("Wieviele Felder ziehen? " + str);

				jaeger.ziehe(richtung, anzahl);

				if (jaeger.trifft(beute)) {
					System.out
							.println("Treffer! Sie (als Jaeger) haben gewonnen");
					break;
				} else if (i < 10)
					System.out.println("Leider kein Treffer!");
				else {
					System.out.println("Auch zuletzt kein Treffer!");
					System.out.println("Die Beute: " + beute);
					System.out.println("hat gewonnen!");
				}
			}
		}
	}

}
