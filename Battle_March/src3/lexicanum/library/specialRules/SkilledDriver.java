package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class SkilledDriver extends SpecialRule {

    public SkilledDriver() {
        super("Geschickter Fahrer", "Skilled Driver", "Besteht automatisch Tests für gefährliches Gelände." +
        		                                "\nErhält +1 auf den Deckungswurf für Ausweichmanöver.");
    }
}
