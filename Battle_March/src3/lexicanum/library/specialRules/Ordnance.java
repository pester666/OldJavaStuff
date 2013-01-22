package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Ordnance extends SpecialRule {
    
    public Ordnance(int a) {
        super("Gesch�tz", "Ordnance", a + " Sch�sse." +
        		                "\nWenn kein Fahrzeug, dann nur wenn sich Einheit nicht bewegt hat." +
        		                "\nNichtfahrzeuge d�rfen keine anderen Waffen abfeuern." +
        		                "\nDarf in der Nahkampfphase nicht angreifen." +
        		                "\nZus�tzlicher W�rfel f�r Panzerungsdurchschlag, und h�heres Ergebnis benutzen.");
    }
}
