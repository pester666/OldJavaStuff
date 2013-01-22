package holmeier.peter.spiele;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class MastermindKandidat {

	public static class Versuch {
		public int[] ziffern;
		public int direkt;
		public int indirekt;
	}

	public static boolean passt(int[] ziffern, Versuch v) {

		int direkt = 0;
		int indirekt = 0;

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				if (ziffern[i] == v.ziffern[j]) {
					if (i == j)
						direkt++;
					else
						indirekt++;
				}
		return (direkt == v.direkt && indirekt == v.indirekt);
	}

	public static void main(String[] args) throws ParseException {

		Versuch[] versuche = new Versuch[10];
		for (int i = 0; i < 10; i++)
			versuche[i] = new Versuch();
		int nr = 0;

		for (int z = 0; z <= 9999 && nr < 10; z++) {
			int[] ziffern = Mastermind.bereiteAuf(z);
			if (ziffern == null)
				continue;

			boolean moeglich = true;
			for (int i = 0; i < nr; i++)
				moeglich = moeglich & passt(ziffern, versuche[i]);
			if (!moeglich)
				continue;
			System.out.println("Versuch Nr. " + (nr + 1) + ": "
					+ Mastermind.zahl(ziffern));
			versuche[nr].ziffern = ziffern;
			versuche[nr].direkt = IOTools.readInteger(" direkte Treffer: ");
			versuche[nr].indirekt = IOTools.readInteger("indirekte Treffer: ");

			if (versuche[nr].direkt == 4) {
				System.out.println("Das war's");
				break;
			}

		}

	}

}
