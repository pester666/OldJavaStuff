package holmeier.peter.waehrungen;

public class DM extends Euro {

	public DM(double dm) {
		super(dm / 1.95583);
	}

	public DM(Euro euro) {
		super(euro.euroBetrag());
	}

	public double waehrungsBetrag() {
		return euroBetrag() * 1.95583;
	}

}
