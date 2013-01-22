package holmeier.peter.waehrungen;

public class Yen extends Waehrung {

	private static double kurs;

	private double wert;

	public Yen(double wert) {
		this.wert = wert;
	}

	public double dollarBetrag() {
		return wert * kurs;
	}

	public static void setKurs(double Kurs) {
		kurs = Kurs;
	}
}
