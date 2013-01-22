package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Berserk extends SpecialRule {

    public Berserk() {
        super("Berserker", "Berserk", "Beim Angriff erhält das Modell +2 anstatt +1 Attacke." +
        		                "\nBei einem ungeordneten Angriff gilt dies nicht (mehr als eine Einheit wird angegriffen).");
    }
}
