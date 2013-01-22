package holmeier.peter.kleineapps;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class Vielfache {
	public static void main(String[] args) throws ParseException {

		int zahl = IOTools.readInteger("Geben Sie einen Integerwert ein:");

		for (int i = 1; i < 11; i++) {
			System.out.print(zahl * i + " ");

		}

	}
}
