package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Intercept extends SpecialRule {

    public Intercept() {
        super("Abfangen", "Intercept", "Am Ende der gegnerischen Bewegungsphase darf die Einheit auf eine Einheit schießen welche sich aus der Reserve bewegt hat." +
        		    "\nDarf während der nächsten Schussphase nicht mit dieser Waffe schießen, jedoch mit anderen, falls verfügbar schon." +
        		    "\nHat die Einheit noch die Regel 'Flugabwehr' wird die normale BF gegen alles verwendet.");
    }
}
