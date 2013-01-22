package holmeier.peter.kleineapps;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class QuersummeMath {

	public static void main(String[] args) throws ParseException {

		int zahl = IOTools.readInteger("Geben Sie einen Integerwert ein:");
		int dummy = zahl;
		int quer = 0;

		while (dummy != 0) {
			int einer = dummy % 10;
			dummy = dummy / 10;
			if (einer != 0 && zahl % einer == 0)
				System.out.println("Die Zahl " + zahl + " ist durch " + einer
						+ " teilbar!");
			quer = quer + einer;

		}

		System.out.println("Die Quersumme der Zahl ist " + quer);

		int summe = quer;

		while (quer != 0) {
			int zweier = quer % 10;
			quer = quer / 10;
			if (zweier != 0 && quer % zweier == 0)
				System.out.println("Die Quersumme " + summe + " ist durch "
						+ zweier + " teilbar!");

		}

		Integer intObj = new Integer(zahl);
		String strZahl = intObj.toString();

		// in Einzel-Strings der Länge 1 zerlegen

		strZahl.length();
		int quersumme = 0;
		for (int i = 0; i < strZahl.length(); i++) {
			// int indexend = 1, indexstart = 0;
			String einzel = strZahl.substring(i, i + 1);
			int teilQuersum = Integer.parseInt(einzel);
			quersumme = quersumme + teilQuersum;
			// mit anderem Operator:1234 quersumme += teilQuersum;
		}

		System.out.println(quersumme);

	}
}
