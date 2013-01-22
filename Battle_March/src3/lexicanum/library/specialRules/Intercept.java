package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Intercept extends SpecialRule {

    public Intercept() {
        super("Abfangen", "Intercept", "Am Ende der gegnerischen Bewegungsphase darf die Einheit auf eine Einheit schie�en welche sich aus der Reserve bewegt hat." +
        		    "\nDarf w�hrend der n�chsten Schussphase nicht mit dieser Waffe schie�en, jedoch mit anderen, falls verf�gbar schon." +
        		    "\nHat die Einheit noch die Regel 'Flugabwehr' wird die normale BF gegen alles verwendet.");
    }
}
