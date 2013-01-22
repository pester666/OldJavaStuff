package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Bulky extends SpecialRule {

    public Bulky() {
        super("Massig", "Bulky", "Zählt bei Transporten als 2 Modelle." +
        		            "\nSehr Massig zählt als 3 Modelle." +
        		            "\nExtrem Massig zählt als 5 Modelle.");
    } 
}
