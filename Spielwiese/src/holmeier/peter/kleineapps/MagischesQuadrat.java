package holmeier.peter.kleineapps;

import java.util.Formatter;

public class MagischesQuadrat {
	public static final String CRLF = System.getProperty("line.separator");

	public static void main(String[] args) {
		int n = 3;
		int zeile = n / 2;
		int spalte = n / 2 + 1;
		int[][] quad = new int[n][n];

		// if (n > 0 && n < 10 && n % 10 == 1) {
		for (int i = 1; i <= n * n; i++) {
			quad[zeile][spalte] = i;

			spalte += 1;

			zeile -= 1;

			if (zeile < 0)
				zeile = n - 1;

			if (spalte == n)
				spalte = 0;

			if (quad[zeile][spalte] != 0) {
				zeile += 1;
				spalte += 1;
				if (zeile == n)
					zeile = 0;
				if (spalte == n)
					spalte = 0;
			}
			//
			// System.out.println("quad[" + zeile + "][" + spalte + "]="
			// + quad[zeile][spalte]);

		}
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// quad[i][j]=i*n+j;

				Formatter formatter = new Formatter(sb);
				formatter.format("%1$5d ", quad[i][j]);
				// System.out.println(sb);

				// System.out.print(quad[i][j]);
				// System.out.print(" ");
			}
			sb.append("\r\n");
			// System.out.println();
		}
		System.out.print(sb);

		System.out.println("Ungerade Zahl zwischen 1 und 10: " + n);

		System.out.print("sepp\rmaier");
		System.out.print("A" + CRLF + "B");

	}
}
