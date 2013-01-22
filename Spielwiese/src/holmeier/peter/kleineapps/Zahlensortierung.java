package holmeier.peter.kleineapps;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class Zahlensortierung {
	public static void main(String[] args) throws ParseException {

		int count = IOTools.readInteger(" ");

		int[] zahl = new int[count];

		for (int i = 0; i < zahl.length; i++)
			zahl[i] = IOTools.readInteger(" ");

		System.out.println("Wie viele Zahlen willst du sortieren? " + count);

		for (int i = 0; i < zahl.length; i++)
			System.out.println((i + 1) + ". Zahl: " + zahl[i]);

		java.util.Arrays.sort(zahl);
		for (int i = 0; i < zahl.length; i++) {
			System.out.print(zahl[i] + " ");
		}
	}
}
