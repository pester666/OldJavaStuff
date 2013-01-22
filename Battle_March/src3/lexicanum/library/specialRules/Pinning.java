package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Pinning extends SpecialRule {

    public Pinning() {
        super("Niederhalten", "Pinning", "Falls mindestens 1 Verlust muss eine MW-Test gemacht werden." +
        		                    "\nMisslingt der sucht die Einheit Schutz." +
        		                    "\nKann auch mehrmals pro Zug zum testen gezwungen werden.");
    } 
}
