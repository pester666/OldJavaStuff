package holmeier.peter.exkursenum;

public class PhysWert {
	private Einheit einheit;
	private int wert;
	private final EinheitenVorsatz vorsatz;

	public PhysWert(Einheit einheit, EinheitenVorsatz vorsatz, int wert) {
		super();
		this.einheit = einheit;
		this.vorsatz = vorsatz;
		this.wert = wert;
	}

	public Einheit getEinheit() {
		return einheit;
	}

	public void setEinheit(Einheit einheit) {
		this.einheit = einheit;
	}

	public int getWert() {
		return wert;
	}

	public double getRechenWert() {
		return wert * vorsatz.getFaktor();
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

	public EinheitenVorsatz getVorsatz() {
		return vorsatz;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(einheit.getGroesse());
		sb.append(" = ");
		sb.append(wert);
		sb.append(" ");
		sb.append(vorsatz.getPrintName());
		sb.append(einheit.getName());
		sb.append(", Rechenwert =" + getRechenWert());
		return sb.toString();
	}

}
