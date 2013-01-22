package holmeier.peter.waehrungen;

import holmeier.peter.IOTools;

import java.text.ParseException;

public class Waehrungsrechner {

	public static void main(String[] args) throws ParseException {

		double dmBetrag;
		Euro betrag;
		Lire lire;
		Franc franc;

		dmBetrag = IOTools.readDouble("DM Betrag > ");
		betrag = new DM(dmBetrag);

		lire = new Lire(betrag);
		franc = new Franc(betrag);

		System.out.println("In Euro " + betrag.euroBetrag());
		System.out.println("In Lire " + lire.waehrungsBetrag());
		System.out.println("In Franc " + franc.waehrungsBetrag());

	}
}
