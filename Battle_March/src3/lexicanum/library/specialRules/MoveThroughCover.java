package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class MoveThroughCover extends SpecialRule {

    public MoveThroughCover() {
        super("Durch Deckung bewegen", "Move Through Cover", "Bei schwierigem Gel�nde zus�tzlichen W6 werfen und h�chsten nehmen." +
        		                "\nBesteht automatisch Tests f�r gef�hrliches Gel�nde." +
        		                "\nBeeinflusst nicht Aufschlagstests oder das Ausw�rfeln der Angriffsreichweite.");
    }
}
