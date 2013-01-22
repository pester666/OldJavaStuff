package holmeier.peter.kleineapps;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class Punkt {

	private double x, y;

	public Punkt(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void read() throws ParseException {
		x = IOTools.readInteger("x-Koordinate:");
		y = IOTools.readInteger("y-Koordinate:");
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}

}
