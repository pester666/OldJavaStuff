package holmeier.peter.exkursenum;

public class EnumTest {

	public static final int GESCHLECHT_WEIBLICH = 0;
	public static final int GESCHLECHT_MAENNLICH = 1;

	public static boolean isWeiblich(int geschlecht) {
		if (geschlecht != GESCHLECHT_WEIBLICH
				&& geschlecht != GESCHLECHT_MAENNLICH)
			throw new IllegalArgumentException("value must be 0 or 1");
		return geschlecht == GESCHLECHT_WEIBLICH;
	}

	public static boolean isWeiblich(Geschlecht geschlecht) {
		return geschlecht == Geschlecht.female;
	}

	public static void main(String[] args) {
		PhysWert vierKilometer = new PhysWert(new Meter(),
				EinheitenVorsatz.KILO, 4);
		System.out.println(vierKilometer);
		EinheitenVorsatz kilo = EinheitenVorsatz.KILO;
		// Gramm g= Gramm.GRAMM;
		System.out.println(kilo);
		System.out.println("Ordinalzahl von männlich: "
				+ Geschlecht.male.ordinal());
		System.out.println("Ordinalzahl von weiblich: "
				+ Geschlecht.female.ordinal());
	}

}
