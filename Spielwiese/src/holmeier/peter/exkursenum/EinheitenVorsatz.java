package holmeier.peter.exkursenum;

public enum EinheitenVorsatz {
	MICRO("mikro", 1e-6), MILLI("milli", 1e-3), CENTI("zenti", 1e-2), DEZI(
			"dezi", 1e-1), EINS("", 1.0), DEKA("deka", 10d), HEKTO("hekto",
			100d), KILO("kilo", 1000d), MEGA("mega", 1e6d);
	private final String printName;
	private final double faktor;

	private EinheitenVorsatz(String printName, double faktor) {
		this.printName = printName;
		this.faktor = faktor;
	}

	public String getPrintName() {
		return printName;
	}

	public double getFaktor() {
		return faktor;
	}

}
