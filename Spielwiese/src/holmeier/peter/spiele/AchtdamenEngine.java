package holmeier.peter.spiele;

public class AchtdamenEngine {

	public static boolean[][][] loesung = new boolean[92][][];
	public static int anzLoesungen = 0;

	public static boolean bedroht(int[] brett, int spalte) {

		for (int i = 0; i < spalte; i++)
			if (brett[i] == brett[spalte])
				return true;

		for (int i = spalte - 1, j = brett[spalte] - 1; i >= 0; i--, j--)
			if (brett[i] == j)
				return true;

		for (int i = spalte - 1, j = brett[spalte] + 1; i >= 0; i--, j++)
			if (brett[i] == j)
				return true;

		return false;
	}

	public static boolean setze(int[] brett, int spalte) {

		if (spalte == 8) {
			ausgabe(brett);
			return true;
		}

		for (int i = 0; i < 8; i++) {
			brett[spalte] = i;
			if (bedroht(brett, spalte))
				continue;
			// boolean success = setze(brett, spalte + 1);
		}
		return false;
	}

	public static void ausgabe(int[] brett) {
		boolean[][] feld;

		feld = new boolean[8][8];

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (i == brett[j])
					feld[i][j] = true;
				else
					feld[i][j] = false;

		loesung[anzLoesungen] = feld;

		anzLoesungen++;
	}

	public static void invoke() {
		int[] feld = { 0, 0, 0, 0, 0, 0, 0, 0 };
		setze(feld, 0);
	}

}
