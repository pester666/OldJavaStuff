package holmeier.peter.exceptions;

public class Exueb8 {

	public static double gibZufallszahlBisEinhalb() throws Exception {

		double res = Math.random();
		if (res > 0.5)
			throw new Exception("Zahl zu gross");
		return res;
	}

	public static void main(String[] args) {

		double zahl;

		try {
			zahl = gibZufallszahlBisEinhalb();
		} catch (Exception e) {
			zahl = 0.5;
		}

		System.out.println(zahl);
	}

}
