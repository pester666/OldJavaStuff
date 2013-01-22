package holmeier.peter.kleineapps;

public class Werteberechnung {// Aufgabe 4.14

	public static void main(String args[]) {
		int x = 192119201, y = 35675640;
		// double a = (1.0/107751.0); // Bruch vor der Klammer

		// double b = (1682.0*x*(y*y*y*y)), // Berechnung der
		// c = (3.0*(x*x*x)), //jeweiligen
		// d = (29.0*x*(y*y)), //Terme
		// e = -(2.0*(x*x*x*x*x)),
		double f = 832.0;
		// ab = a*b,
		// ac = a*c,
		// ad = a*d,
		// ae = a*e,
		// af = a*f;
		int g, h;

		double ca = ((x * (y * y)) / 107751.0), cb = ((1682.0 * (y * y)) + 29.0), cc = ((x
				* x * x) / 107751.0), cd = (3.0 - (2.0 * (x * x))), ce = (832.0 / 107751.0);
		System.out.println(ca * cb + cc * cd + ce);

		if (f == 832) {
			System.out.println("F ist 832");
		} else {
			System.out.println("F ist nicht 832");
		}
		h = 7;
		switch (h) {
		case 1:
			g = 10;
			break;
		case 2:
			g = 15;
			break;
		case 3:
			g = 20;
			break;
		case 4:
			g = 30;
			break;
		default:
			g = 40;
		}

		System.out.println(g);

		// System.out.println(a);
		// System.out.println(b);
		// System.out.println(c);
		// System.out.println(d);
		// System.out.println(e);
		// System.out.println(f);
		// System.out.println((a*b));
		// System.out.println((a*c));
		// System.out.println((a*d));
		// System.out.println((a*e));
		// System.out.println((a*f));
		// System.out.println(b+c);
		// System.out.println(a*b);
		// System.out.println(a*(b+c+d+e+f));
		System.out
				.println((1.0 / 107751.0)
						* ((1682.0 * x * ((y * y * y * y))
								+ (3.0 * (x * x * x)) + (29.0 * x * (y * y))
								- (2.0 * (x * x * x * x * x)) + 832)));

	}
}
