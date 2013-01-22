package database.warbands.undead.henchmen;

import database.stuff.SpecialAbilities;
import lib.Constants;
import lib.Equipment;
import lib.Henchmen;
import lib.Skill;

public class Zombie extends Henchmen {

    public Zombie(String name){
        super(4, 2, 0, 3, 3, 1, 1, 1, 5, 15, name, 0, new Skill[]{SpecialAbilities.FEAR, SpecialAbilities.IMMUNE_TO_POISON, SpecialAbilities.IMMUNE_TO_PSYCHO, SpecialAbilities.NO_PAIN, SpecialAbilities.NO_EXP, SpecialAbilities.MAY_NOT_RUN}, new Equipment[1], Constants.MEDIUM_UNIT, Constants.WARBAND_UNDEAD, 0, "res/img/units/henchmen/Zombie.png", "Zombie");    
    }
}
