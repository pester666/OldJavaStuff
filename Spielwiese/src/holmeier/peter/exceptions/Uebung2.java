package holmeier.peter.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Uebung2 {

	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));

	public static int readInteger() {
		String str = "";
		int erg = 0;
		boolean nochmal;

		do {
			System.out.print("Bitte int-Zahl eingeben > ");
			try {
				nochmal = false;
				str = in.readLine();
				erg = Integer.parseInt(str);
			} catch (IOException ex) {
				throw new InputException();
			} catch (NumberFormatException ex) {
				System.out.println("Das war keine ganze Zahl - nochmal!");
				nochmal = true;
			}
		} while (nochmal);

		return erg;
	}

	public static double readDouble() {
		String str = "";
		double erg = 0.0;
		boolean nochmal;

		do {
			System.out.print("Bitte double-Zahl eingeben > ");
			try {
				nochmal = false;
				str = in.readLine();
				erg = Double.parseDouble(str);
			} catch (IOException ex) {
				throw new InputException();
			} catch (NumberFormatException ex) {
				System.out
						.println("Das war keine zulaessige double-Zahl - nochmal!");
				nochmal = true;
			}
		} while (nochmal);

		return erg;

	}

	public static void main(String[] args) {
		System.out.println(readInteger());
		System.out.println(readDouble());
	}
}
