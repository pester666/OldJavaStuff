package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class MassiveHits extends SpecialRule {

    public MassiveHits() {
        super("Wuchtige Hiebe", "Heavy Hits", "Alle Attacken haben DS 2, außer es ist als DS 1 markiert." +
        		                        "\nDarf Attackenzahl halbieren und dafür Stärke verdoppeln." +
        		                        "\nBei diesen Attacken dürfen Panzerungsdurchschläge wiederholt werden.");
    } 
}
