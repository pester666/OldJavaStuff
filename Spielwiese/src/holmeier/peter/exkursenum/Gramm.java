package holmeier.peter.exkursenum;

public class Gramm implements Einheit {
	public static final Gramm GRAMM = new Gramm();

	@Override
	public String getGroesse() {
		return "Masse";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "gramm";
	}

}
