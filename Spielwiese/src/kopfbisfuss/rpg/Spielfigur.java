package kopfbisfuss.rpg;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Spielfigur implements Serializable {

	int st�rke;
	String typ;
	String[] waffen;

	public Spielfigur(int s, String t, String[] w) {
		st�rke = s;
		typ = t;
		waffen = w;
	}

	public int getSt�rke() {
		return st�rke;
	}

	public String getTyp() {
		return typ;
	}

	public String getWaffen() {
		String waffenListe = "";

		for (int i = 0; i < waffen.length; i++)
			waffenListe += waffen[i] + " ";
		return waffenListe;
	}

}
