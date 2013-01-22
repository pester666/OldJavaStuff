package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class RapidFire extends SpecialRule {

    public RapidFire() {
        super("Schnellfeuer", "Rapid Fire", "2 Schuss auf halbe Reichweite." +
        		"\n1 Schuss auf Maximalreichweite." +
        		"\nKann nicht in der Nahkampfphase nicht angreifen wenn geschossen.");
    }
}
