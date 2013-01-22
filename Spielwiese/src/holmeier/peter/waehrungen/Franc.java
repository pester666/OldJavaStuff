package holmeier.peter.waehrungen;

public class Franc extends Euro {

	public Franc(double dm) {
		super(dm / 1.95583);
	}

	public Franc(Euro euro) {
		super(euro.euroBetrag());
	}

	public double waehrungsBetrag() {
		return euroBetrag() * 6.55957;
	}

}
