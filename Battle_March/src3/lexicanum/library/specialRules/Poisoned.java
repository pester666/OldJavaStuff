package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Poisoned extends SpecialRule {

    public Poisoned(int p) {
        super("Gift", "Poisoned", "Verwundet Modelle mit Wiederstand auf " + p +"+." +
        		            "\nIst die St�rke des Tr�gers oder der Waffe gleich bzw. h�her als der Wiederstand des Opfers" +
        		            ", wiederholt der Tr�ger verpatzte Verwundungsw�rfe im Nahkampf.");
    }
}
