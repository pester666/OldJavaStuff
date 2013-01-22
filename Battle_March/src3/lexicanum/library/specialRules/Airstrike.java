package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Airstrike extends SpecialRule {

    public Airstrike() {
        super("Flugabwehr", "Airstrike", "Verwendet normale BF gegen Flieger, Fliegende Monstr�se Kreaturen und Antigravfahrzeuge." +
        		                    "\nWenn sie nicht die Sonderregel 'Abfangen' hat d�rfen gegen alles andere nur Schnellsch�sse (6+) abgegeben werden.");
    }
}
