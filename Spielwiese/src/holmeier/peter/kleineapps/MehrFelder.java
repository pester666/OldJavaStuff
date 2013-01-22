package holmeier.peter.kleineapps;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class MehrFelder {
	public static void main(String[] args) throws ParseException {

		int n = IOTools.readInteger("Wie viele Zahlen?");
		int[] werte = new int[n];
		int[] werte2 = new int[werte.length];
		System.arraycopy(werte, 0, werte2, 0, werte.length);
		for (int i = 0; i < werte.length; i++)
			werte2[i] = werte[i];

		for (int i = 0; i < werte.length; i++)
			werte[i] = IOTools.readInteger("Zahl Nr. " + i + ": ");

		for (int i = 0; i < werte2.length; i++)
			werte2[i] = (2 * werte2[i]);

		for (int i = 0; i < n; i++)
			System.out.println("Die Zahl " + werte[i] + " ergibt verdoppelt "
					+ werte2[i] + ".");

	}
}
