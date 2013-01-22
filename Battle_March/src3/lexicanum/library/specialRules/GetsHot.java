package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class GetsHot extends SpecialRule {

    public GetsHot() {
        super("Überhitzen", "Gets Hot", "Bei einem Trefferwurf von 1 erleidet das Modell eine Verwundung." +
        		                "\nFahrzeuge verlieren bei einem weiteren Wurf von 1,2,3 einen RP.");
    } 
}
