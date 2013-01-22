package holmeier.peter.spiele;

public class Achtdamenproblem {

	// int[] brett = new int[8];

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

	public static void ausgabe(int[] brett) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				System.out.print("|" + ((i == brett[j]) ? 'D' : ' '));
			System.out.println("|");
		}
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
			boolean success = setze(brett, spalte + 1);
			if (success)
				return true;
		}
		return false;

	}

	public static void main(String[] args) {

		int[] feld = { 0, 0, 0, 0, 0, 0, 0, 0 };
		setze(feld, 0);
	}

}
