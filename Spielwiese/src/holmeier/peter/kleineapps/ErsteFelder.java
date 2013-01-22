package holmeier.peter.kleineapps;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class ErsteFelder {
	public static void main(String[] args) throws ParseException {

		int n = IOTools.readInteger("Wie viele Werte in Reihe 1?");
		int[] werte1 = new int[n];
		for (int i = 0; i < werte1.length; i++)
			werte1[i] = IOTools.readInteger("Wert Nr. " + i + ": ");
		n = IOTools.readInteger("Wie viele Werte in Reihe 2?");
		int[] werte2 = new int[n];
		for (int i = 0; i < werte2.length; i++)
			werte2[i] = IOTools.readInteger("Wert Nr. " + i + ": ");
		System.out.println("Reihe 1 verkehrt herum");
		for (int i = 0; i < werte1.length; i++)
			System.out.println("Wert Nr. " + i + ": "
					+ werte1[werte1.length - 1 - i]);
		System.out.println("Reihe 2 verkehrt herum");
		for (int i = 0; i < werte2.length; i++)
			System.out.println("Wert Nr. " + i + ": "
					+ werte2[werte2.length - 1 - i]);

	}
}
