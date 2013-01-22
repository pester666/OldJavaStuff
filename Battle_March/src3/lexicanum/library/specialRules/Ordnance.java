package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Ordnance extends SpecialRule {
    
    public Ordnance(int a) {
        super("Geschütz", "Ordnance", a + " Schüsse." +
        		                "\nWenn kein Fahrzeug, dann nur wenn sich Einheit nicht bewegt hat." +
        		                "\nNichtfahrzeuge dürfen keine anderen Waffen abfeuern." +
        		                "\nDarf in der Nahkampfphase nicht angreifen." +
        		                "\nZusätzlicher Würfel für Panzerungsdurchschlag, und höheres Ergebnis benutzen.");
    }
}
