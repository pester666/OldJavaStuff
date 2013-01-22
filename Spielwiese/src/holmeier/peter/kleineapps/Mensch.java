package holmeier.peter.kleineapps;

public class Mensch {

	private int nummer;
	private String vorname;
	private String nachname;
	private int alter;
	private boolean geschlecht;
	private int gesamt = 0;

	public Mensch(int alter, boolean geschlecht, String vorname, String nachname) {
		gesamt = gesamt++;
		this.alter = alter;
		this.geschlecht = geschlecht;
		this.vorname = vorname;
		this.nachname = nachname;

	}

	public int getAlter() {
		return this.alter;
	}

	public void setAlter(int neuesAlter) {
		this.alter = neuesAlter;
	}

	public boolean getGeschlecht() {
		return geschlecht;
	}

	public boolean aelterAls(Mensch m) {
		return (alter > m.alter);
	}

	public String toString() {
		String text = vorname + " " + nachname + ", " + alter + " Jahre, ";
		if (geschlecht)
			text = text + "maennlich,";
		else
			text = text + "weiblich,";
		text = text + "Laufende Nummer: " + nummer;
		return text;
	}

}
