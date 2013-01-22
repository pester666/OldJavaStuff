package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Heavy extends SpecialRule {

    public Heavy(int a) {
        super("Schwer", "Heavy", a + " Sch�sse." +
        		            "\nHat sich die Einheit bewegt, nur Schnellsch�sse." +
        		            "\nDarf in der Nahkampfphase nicht angreifen.");
    }
}
