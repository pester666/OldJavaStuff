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

		System.out.println("Das Volumen beträgt: " + v + "cm³");
		System.out.println("Die Gesamtfläche der Dose beträgt: " + fg + "cm²");
		System.out.println("Die Mantefläche der Dose beträgt: " + fm + "cm²");
		System.out.println("Die Bodenfläche der Dose beträgt: " + fb + "cm²");
		System.out.println("Der Durchmesser des Dosenbodens beträgt: " + d
				+ "cm");

	}
}