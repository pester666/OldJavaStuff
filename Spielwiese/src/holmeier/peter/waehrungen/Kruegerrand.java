package holmeier.peter.waehrungen;

public class Kruegerrand extends Waehrung implements Wertgegenstand {

	private static double kurs;

	private double wert;

	public Kruegerrand(double wert) {
		this.wert = wert;
	}

	public double dollarBetrag() {
		return wert * kurs;
	}

	public static void SetKurs(double Kurs) {
		kurs = Kurs;
	}

	public Waehrung wert() {
		return this;
	}
}
