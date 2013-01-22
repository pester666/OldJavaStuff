package holmeier.peter.kalender;

public class Jahreskalender {
	public static void main(String[] args) {

		String[][][][] termine;

		termine = new String[9][12][][];
		termine[0] = new String[12][][];
		termine[1] = new String[12][][];
		termine[2] = new String[12][][];
		termine[3] = new String[12][][];
		termine[4] = new String[12][][];
		termine[5] = new String[12][][];
		termine[6] = new String[12][][];
		termine[7] = new String[12][][];
		termine[8] = new String[12][][];

		// termine = new String[12][][];
		// termine[0] = new String [31][24]; //January
		// termine[1] = new String [28][24]; //February
		// termine[2] = new String [31][24]; //March
		// termine[3] = new String [30][24]; //April
		// termine[4] = new String [31][24]; //May
		// termine[5] = new String [30][24]; //June
		// termine[6] = new String [31][24]; //July
		// termine[7] = new String [31][24]; //August
		// termine[8] = new String [30][24]; //September
		// termine[9] = new String [31][24]; //October
		// termine[10] = new String [30][24]; //November
		// termine[11] = new String [31][24]; //December

		for (int i = 0; i < termine.length; i++)
			for (int j = 0; j < termine[i].length; j++)
				for (int k = 0; k < termine[i][j].length; k++)
					for (int h = 0; h < termine[i][j][k].length; h++)
						termine[i][j][k][h] = "";

		{
			int[][][][] Feld = new int[6][][][];
			for (int d1 = 0; d1 < Feld.length; d1++) {
				Feld[d1] = new int[10][][];
				for (int d2 = 0; d2 < Feld[d1].length; d2++) {
					Feld[d1][d2] = new int[8][];
				}
			}
		}

	}
}
