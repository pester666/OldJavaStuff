package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Flank extends SpecialRule {
    
    public Flank() {
        super("Flankenangriff", "Flank", "Wenn zumindest ein Modell enthalten, darf gesamte Einheiten flanken." +
        		                "\nMüssen in Reserve beginnen." +
        		                "\nTrifft die Einheit aus der Reserve ein, einen W6 werfen." +
        		                "\nBei 1-2 links bei 3-4 rechts und bei 5-6 freie Wahl.");
    }
}
