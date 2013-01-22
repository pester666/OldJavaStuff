package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Fear extends SpecialRule {

    public Fear() {
        super("Angst", "Fear", "In jeder Nahkampfunterphase muss eine MW-Test von Einheiten abgelegt werden, die gegen Angstverursachende Feinde kämpfen." +
        		        "\nSchlägt der Fehl, wird das Kampfgeschick für diese Phase auf 1 reduziert." +
        		        "\nIst nicht selbst immun gegen Angst.");
    }
}
