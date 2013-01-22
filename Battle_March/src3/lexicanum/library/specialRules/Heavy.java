package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Heavy extends SpecialRule {

    public Heavy(int a) {
        super("Schwer", "Heavy", a + " Schüsse." +
        		            "\nHat sich die Einheit bewegt, nur Schnellschüsse." +
        		            "\nDarf in der Nahkampfphase nicht angreifen.");
    }
}
