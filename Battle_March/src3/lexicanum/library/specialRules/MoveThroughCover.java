package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class MoveThroughCover extends SpecialRule {

    public MoveThroughCover() {
        super("Durch Deckung bewegen", "Move Through Cover", "Bei schwierigem Gelände zusätzlichen W6 werfen und höchsten nehmen." +
        		                "\nBesteht automatisch Tests für gefährliches Gelände." +
        		                "\nBeeinflusst nicht Aufschlagstests oder das Auswürfeln der Angriffsreichweite.");
    }
}
