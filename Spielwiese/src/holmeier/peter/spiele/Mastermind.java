package holmeier.peter.spiele;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class Mastermind {

	public static int[] ziffern(int z) {

		int[] res = new int[4];

		for (int i = 0; i < 4; i++) {
			res[i] = z % 10;
			z = z / 10;
		}
		return res;
	}

	public static int[] bereiteAuf(int z) {

		if (z < 0 || z > 9999)
			return null;
		int[] res = ziffern(z);

		for (int i = 0; i < 4; i++)
			for (int j = i + 1; j < 4; j++)
				if (res[i] == res[j])
					return null;
		return res;
	}

	public static int[] findeZahl() {

		int[] res = null;

		while (res == null) {
			int zahl = (int) (9999 * Math.random());
			res = bereiteAuf(zahl);
		}
		return res;
	}

	public static boolean treffer(int[] original, int[] versuch) {
		for (int i = 0; i < 4; i++)
			if (original[i] != versuch[i])
				return false;
		return true;
	}

	public static String auswerten(int[] original, int[] versuch) {

		int direkt = 0;
		int indirekt = 0;

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				if (original[i] == versuch[j]) {
					if (i == j)
						direkt++;
					else
						indirekt++;
				}
		return "Direkte Treffer: " + direkt + "	" + "Indirekte Treffer: "
				+ indirekt;
	}

	public static int[] liesZahl(int versuch) throws ParseException {
		int n = IOTools.readInteger("Versuch Nr. " + versuch + ": ");
		int[] res = bereiteAuf(n);
		while (res == null) {
			System.out.println("KEINE GUELTIGE ZAHL!");
			n = IOTools.readInteger("Versuch Nr. " + versuch + ": ");
			res = bereiteAuf(n);
		}
		return res;
	}

	public static String zahl(int[] z) {
		String res = "";
		for (int i = 0; i < 4; i++)
			res = z[i] + res;
		return res;
	}

	public static void main(String[] args) throws ParseException {

		int[] original = findeZahl();
		boolean erraten = false;

		System.out.println("M-A-S-T-E-R-M-I-N-D");

		for (int i = 1; i < 10; i++) {
			System.out.println("\nSie haben noch " + (10 - i + 1)
					+ " Versuch(e).");
			int[] versuch = liesZahl(i);

			if (treffer(original, versuch)) {
				System.out.println("Hervorragend! Sie haben die Zahl im " + i
						+ ". Versuch erraten.");
				erraten = true;
				break;
			}
			System.out.println("Leider falsch geraten. \n"
					+ auswerten(original, versuch));
		}

		if (!erraten) {
			System.out.println("\nViel Glueck beim naechsten Mal!");
			System.out.println("Die richtige Zahl ware " + zahl(original)
					+ " gewesen.");
		}

	}

}