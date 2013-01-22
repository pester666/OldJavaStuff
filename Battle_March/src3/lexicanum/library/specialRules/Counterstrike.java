package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Counterstrike extends SpecialRule {

    public Counterstrike() {
        super("Gegenschlag", "Counterstrike", "Einheiten mit dieser Regel machen einen MW-Test wenn sie angegriffen werden." +
        		                        "\nIst der Erfolgreich, erhalten alle mit der Regel +1 Attacke." +
        		                        "\nZählt nicht wenn bereits im Nahkampf.");
    }
}
