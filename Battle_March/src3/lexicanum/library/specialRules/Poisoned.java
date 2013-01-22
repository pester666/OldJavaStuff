package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Poisoned extends SpecialRule {

    public Poisoned(int p) {
        super("Gift", "Poisoned", "Verwundet Modelle mit Wiederstand auf " + p +"+." +
        		            "\nIst die Stärke des Trägers oder der Waffe gleich bzw. höher als der Wiederstand des Opfers" +
        		            ", wiederholt der Träger verpatzte Verwundungswürfe im Nahkampf.");
    }
}
