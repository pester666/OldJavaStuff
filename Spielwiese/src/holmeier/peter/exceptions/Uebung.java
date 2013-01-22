package holmeier.peter.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Uebung {

	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));

	public static int readInteger() {
		String str = "";
		int erg;

		System.out.print("Bitte int-Zahl eingeben > ");
		try {
			str = in.readLine();
		} catch (IOException ex) {
			throw new InputException();
		}
		;
		erg = Integer.parseInt(str);
		return erg;
	}

	public static double readDouble() {
		String str = "";
		double erg;

		System.out.print("Bitte double-Zahl eingeben > ");
		try {
			str = in.readLine();
		} catch (IOException ex) {
			throw new InputException();
		}
		;
		erg = Double.parseDouble(str);
		return erg;

	}

	public static void main(String[] args) {
		System.out.println(readInteger());
		System.out.println(readDouble());
	}
}
