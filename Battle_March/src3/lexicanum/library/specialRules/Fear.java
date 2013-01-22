package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Fear extends SpecialRule {

    public Fear() {
        super("Angst", "Fear", "In jeder Nahkampfunterphase muss eine MW-Test von Einheiten abgelegt werden, die gegen Angstverursachende Feinde k�mpfen." +
        		        "\nSchl�gt der Fehl, wird das Kampfgeschick f�r diese Phase auf 1 reduziert." +
        		        "\nIst nicht selbst immun gegen Angst.");
    }
}
