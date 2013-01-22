package holmeier.peter.kleineapps;

public class Zinsberechnung {
public static void main(String[] args) {
	
	int geldbetrag = 165;
	int laufzeit = 2;
	double zins = 3.5;
	
	
	double zinssatz = zins / 100;
	
	System.out.println("Anzulegender Geldbetrag in Euro: " + geldbetrag);
	System.out.println("Jahreszins: " + geldbetrag*zinssatz*(laufzeit*365.0)/36500.0);
	double jahreszins = (geldbetrag*zinssatz*(laufzeit*365.0)/36500.0);
	System.out.println("Laufzeit: " + laufzeit);
	//System.out.println("Wert nach einem Jahr " + (geldbetrag+(geldbetrag*jahreszins))/1);
	
	for (int jahre = 1; jahre <= laufzeit; jahre++) {
		System.out.println("Wert nach " + jahre + " Jahr " + (geldbetrag+(geldbetrag*jahreszins)));
		geldbetrag += (geldbetrag*jahreszins);
	}
}
}
