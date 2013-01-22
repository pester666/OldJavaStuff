package lexicanum.library.specialRules;

import lexicanum.data.SpecialRule;

public class SpecialWeapon extends SpecialRule {

    public SpecialWeapon() {
        super("Spezielle Waffe", "Special Weapon", "Falls nur eine solche Waffe benutzt wird gibts keinen Bonus von +1 für 2 Nahkampfwaffen." +
        		                            "\nWenn beide Waffen die Regel haben schon.");
    } 
}
