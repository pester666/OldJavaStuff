package holmeier.peter.waehrungen;

public class Goldbarren implements Wertgegenstand {

	public static double preisProGrammInDollar = 60;

	private double gewicht;

	public Goldbarren(double gewichtInGramm) {
		gewicht = gewichtInGramm;
	}

	public Waehrung wert() {
		return new USDollar(gewicht * preisProGrammInDollar);
	}

}
