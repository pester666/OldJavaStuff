package holmeier.peter.zellen;

public class Petrischale {

	private Zelle[][] inhalt;

	public int getBreite() {
		return inhalt.length;
	}

	public int getLaenge() {
		return inhalt[0].length;
	}

	private static void mischen(Object[] feld) {
		for (int i = 0; i < feld.length; i++) {
			int j = (int) (feld.length * Math.random());

			Object dummy = feld[i];
			feld[i] = feld[j];
			feld[j] = dummy;
		}
	}

	public Petrischale(int breite, int laenge, int zahlDerLebenden) {
		Zelle[] zellen = new Zelle[breite * laenge];

		java.util.Arrays.fill(zellen, 0, zahlDerLebenden, new Zelle(true));
		java.util.Arrays.fill(zellen, zahlDerLebenden, zellen.length,
				new Zelle(false));

		mischen(zellen);
		inhalt = new Zelle[breite][laenge];
		for (int i = 0; i < breite; i++) {
			System.arraycopy(zellen, i * laenge, inhalt[i], 0, laenge);
		}
	}

	// public void fuellen(Object[] feld, int von, int bis, Object
	// fuellHiermit){
	// for (int i = von; i < bis; i++) {
	// feld[i] = fuellHiermit;
	// }
	// }

	public Zelle getInhalt(int x, int y) {
		if (x < 0 || x >= inhalt.length || y < 0 || y >= inhalt[0].length)
			return new Zelle(false);
		return inhalt[x][y];
	}

	private int zahlDerNachbarn(int x, int y) {
		int res = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				res += (getInhalt(i, j).istLebendig()) ? 1 : 0;
			}
		}
		if (getInhalt(x, y).istLebendig())
			res--;
		return res;
	}

	public Petrischale(Petrischale alt) {
		inhalt = new Zelle[alt.getBreite()][alt.getLaenge()];
		for (int i = 0; i < inhalt.length; i++)
			for (int j = 0; j < inhalt[i].length; j++)
				inhalt[i][j] = new Zelle(alt.getInhalt(i, j), alt
						.zahlDerNachbarn(i, j));
	}

	public void schalteUm(int x, int y) {
		inhalt[x][y] = new Zelle(!inhalt[x][y].istLebendig());
	}

}
