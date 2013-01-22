package holmeier.peter.plotter;

import Prog1Tools.Plottable;
import Prog1Tools.Plotter;

public class KreisPlot implements Plottable {

	public double inf() { // parameterbereich beginnt bei 0
		return 0;
	}

	public double sup() { // parameterbereich endet bei 2*pi
		return 2 * Math.PI;
	}

	public double x(double t) { // von 0 bis 2 alle x -werte
		return Math.sin(t);
	}

	public double y(double t) { // von 0 bis 2 alle y -werte
		return Math.cos(t);
	}

	public static void main(String[] args) {
		Plotter p = new Plotter(new KreisPlot(), "Kreisplot");
		p.adjustGrid(0.2, 0.2); // abstand der einheiten des feldes
		p.showGrid(true); // feld anzeigen
		p.setNumOfPoints(100); // !!! Change !!!
		p.setVisible(true); // punkte anzeigen
		System.out.print("zum Beenden bitte das ");
		System.out.println("Grafikfenster schliessen.");
	}

}
