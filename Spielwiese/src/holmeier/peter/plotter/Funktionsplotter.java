package holmeier.peter.plotter;

import holmeier.peter.IOTools;

import java.text.ParseException;

import Prog1Tools.Plottable;
import Prog1Tools.Plotter;

public class Funktionsplotter implements Plottable {

	static double tlinks, trechts;

	public double inf() {
		return tlinks;
	}

	public double sup() {
		return trechts;
	}

	public double x(double t) {
		return t;
	}

	public double y(double t) {
		return Math.sin(t);
	}

	public static void main(String[] args) throws ParseException {
		Plotter plotter;

		tlinks = IOTools.readDouble("tlinks > ");
		trechts = IOTools.readDouble("trechts > ");

		plotter = new Plotter(new Funktionsplotter(), "sin-Plot");
		plotter.setNumOfPoints(300);
		plotter.showGrid(true);
		plotter.adjustGrid(0.2, 0.2);
		plotter.setVisible(true);

		System.out.println("Zum Beenden bitte Fenster schlieﬂen druecken");

	}

}
