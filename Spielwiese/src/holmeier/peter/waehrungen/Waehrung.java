package holmeier.peter.waehrungen;

public abstract class Waehrung {

	public abstract double dollarBetrag();

	public String toString() {
		return "$" + dollarBetrag();
	}

	public boolean equals(Object obj) {
		if (obj instanceof Waehrung)
			return this.dollarBetrag() == ((Waehrung) obj).dollarBetrag();
		else
			return super.equals(obj);
	}

	public int hashCode() {
		return (int) (dollarBetrag() * 100);
	}

	public static Waehrung gesamtwert(Wertgegenstand[] objekte) {
		double summe = 0;
		for (int i = 0; i < objekte.length; i++)
			summe += objekte[i].wert().dollarBetrag();
		return new USDollar(summe);
	}

}
