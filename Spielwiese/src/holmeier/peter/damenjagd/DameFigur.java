package holmeier.peter.damenjagd;

public class DameFigur extends SpielFigur {

	@SuppressWarnings("unused")
	private final String NAME = "Dame";

	public DameFigur(char x, int y, String f) {
		super(x, y, f);
	}

	public void ziehe(char richtung, int anzahl) {

		switch (richtung) {
		case '-':
			ziehe(anzahl, 0);
			break;

		case '|':
			ziehe(0, anzahl);
			break;

		case '/':
			ziehe(anzahl, anzahl);
			break;

		case '\\':
			ziehe(anzahl, -anzahl);
			break;

		default:
			System.out.println("Warnung: unzulaessigs Zeichen!");
		}
	}

	public boolean trifft(DameFigur andereFigur) {
		return getXpos() == andereFigur.getXpos()
				&& getYpos() == andereFigur.getYpos();
	}

	public String toString() {
		return getFarbe() + "e Dame auf Feld " + getXpos() + getYpos();
	}

}
