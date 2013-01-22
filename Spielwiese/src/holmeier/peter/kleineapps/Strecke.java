package holmeier.peter.kleineapps;

import java.text.ParseException;

public class Strecke {

	private Punkt p, q;

	public Strecke(Punkt p, Punkt q) {
		this.p = p;
		this.q = q;
	}

	public void read() throws ParseException {
		System.out.println("Punkt P der Strecke eingeben:");
		p.read();
		System.out.println("Punkt Q der Strecke eingeben:");
		q.read();
	}

	public double getLaenge() {
		double xdiff = p.getX() - q.getX();
		double ydiff = p.getY() - q.getY();
		return Math.sqrt(xdiff * xdiff + ydiff * ydiff);
	}

	public String toString() {
		return p + "_" + q;
	}

}
