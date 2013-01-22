package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Jink extends SpecialRule {

    public Jink() {
        super("Ausweichmanöver", "Jink", "Hat sich das Modell bewegt, erhält es einen Deckungswurft von 5+ bis zum nächsten Zug." +
        		                "\nBei Vollgas oder Turboboost erhöht sich dieser auf 4+.");
    }
}
