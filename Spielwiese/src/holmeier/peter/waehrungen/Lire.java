package holmeier.peter.waehrungen;

public class Lire extends Euro {

	public Lire(double dm) {
		super(dm / 1.95583);
	}

	public Lire(Euro euro) {
		super(euro.euroBetrag());
	}

	public double waehrungsBetrag() {
		return euroBetrag() * 1936.27;
	}

}
