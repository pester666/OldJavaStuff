package holmeier.peter.kleineapps;

public class Alter {// Aufgabe 4.11

	public static void main(String args[]) {

		double x1 = 1.0E20, x2 = 1223.0, x3 = 1.0E18, x4 = 1.0E15, x5 = 3.0, x6 = -1.0E12, y1 = 1.0E20, y2 = 2.0, y3 = -1.0E22, y4 = 1.0E13, y5 = 2111.0, y6 = 1.0E16;

		double a = x1 * y1, b = x2 * y2, c = x3 * y3, d = x4 * y4, e = x5 * y5, f = x6
				* y6;

		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);

		System.out.println((a + b) + (c + d) + (e + f));

		System.out.println((x1 * y1) + (x2 * y2) + (x3 * y3) + (x4 * y4)
				+ (x5 * y5) + (x6 * y6));

	}
}
