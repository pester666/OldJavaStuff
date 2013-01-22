package holmeier.peter.exceptions;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class Excep1 {

	public static void main(String[] args) throws ParseException {
		int a = IOTools.readInteger("a = ");
		int b = IOTools.readInteger("b = ");
		try {
			System.out.println("a/b = " + (a / b));
		} catch (ArithmeticException e) {
			System.out.println("Achtung - Sie haben eine "
					+ "ArithmeticException ausgeloest!");
			System.out.println("Es gab folgendes Problem: " + e.getMessage());
			System.out.println("Seien Sie in Zukunft etwas " + "vorsichtiger!");
		}

	}

}
