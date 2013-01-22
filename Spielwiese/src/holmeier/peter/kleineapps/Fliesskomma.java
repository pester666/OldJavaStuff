package holmeier.peter.kleineapps;

public class Fliesskomma {
	public static void main(String[] args) {
		long x = 192119201;
		// long y=35675640;
		// double bruch=1.0/107751d;
		// long term=1682*x*y*y*y*y+3*x*x*x+29*x*y*y-2*x*x*x*x*x+832;
		// double ergebnis=bruch*term;
		// System.out.println("Ergebnis: "+ergebnis);
		// double bsp= Math.pow(y, 4);

		// double term2=1682.0*x*Math.pow(y, 4.0)+3.0*Math.pow(x,
		// 3.0)+29.0*x*Math.pow(y, 2.0)-2.0*Math.pow(x, 5.0)+832.0;
		// System.out.println("Ergebnis term2 : "+bruch*term2);

		System.out.println(((long) x * x * x * x * x));
		System.out.println(Math.pow(x, 5));

	}
}
