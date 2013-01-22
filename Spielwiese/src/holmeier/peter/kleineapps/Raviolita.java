package holmeier.peter.kleineapps;

public class Raviolita {
	public static void main(String[] args) {

		final double PI = 3.141592;
		double u = 3.0, h = 2.0;
		double d, fb, fm, fg, v;

		d = u / PI;
		fb = PI * ((d / 2) * (d / 2));
		fm = u * h;
		fg = (2 * fb) + fm;
		v = fb * h;

		System.out.println("Das Volumen betr�gt: " + v + "cm�");
		System.out.println("Die Gesamtfl�che der Dose betr�gt: " + fg + "cm�");
		System.out.println("Die Mantefl�che der Dose betr�gt: " + fm + "cm�");
		System.out.println("Die Bodenfl�che der Dose betr�gt: " + fb + "cm�");
		System.out.println("Der Durchmesser des Dosenbodens betr�gt: " + d
				+ "cm");

	}
}