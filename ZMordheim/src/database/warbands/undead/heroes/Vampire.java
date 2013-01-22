package database.warbands.undead.heroes;

import database.stuff.SpecialAbilities;
import lib.Constants;
import lib.Equipment;
import lib.Hero;
import lib.Skill;

public class Vampire extends Hero {
        
    public Vampire(String name, Equipment[] equipment){
        super(6, 4, 4, 4, 4, 2, 5, 2, 8, 110, name, 20, new Skill[]{SpecialAbilities.FEAR, SpecialAbilities.LEADER, SpecialAbilities.IMMUNE_TO_PSYCHO, SpecialAbilities.IMMUNE_TO_POISON, SpecialAbilities.NO_PAIN}, equipment, 6, 8, 6, 7, 6, 6, 9, 4, 10, false, Constants.MEDIUM_UNIT, Constants.WARBAND_UNDEAD, null, 0, "res/img/units/heroes/Vampire.png", "Vampire");
    }

}
