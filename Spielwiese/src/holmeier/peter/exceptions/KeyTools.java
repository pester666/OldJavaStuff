package holmeier.peter.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyTools {

	private KeyTools() {
	}

	private static BufferedReader in = new BufferedReader(
			new InputStreamReader(System.in));

	private static String readLine() {
		try {
			return in.readLine();
		} catch (IOException ex) {
			InputException ex2 = new InputException();
			throw ex2;
		}
	}

	public static double readDouble() {
		while (true) {
			try {
				return (new Double(readLine())).doubleValue();
			} catch (NumberFormatException ex) {
			}
		}
	}
}
