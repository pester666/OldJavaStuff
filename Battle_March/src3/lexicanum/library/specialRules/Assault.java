package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class Assault extends SpecialRule {
 
    public Assault(int a) {
        super("Sturmwaffe", "Assaultweapon", a + " Sch�sse, egal ob bewegt." +
        		                        "\nDarf in der Nahkampfphase angreifen, jedoch nur selbes Ziel.");
    }

}
