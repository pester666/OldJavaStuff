package database.warbands.undead.henchmen;

import database.stuff.SpecialAbilities;
import lib.Constants;
import lib.Equipment;
import lib.Henchmen;
import lib.Skill;

public class Direwolf extends Henchmen {

    public Direwolf(String name){
        super(9, 3, 0, 4, 3, 1, 2, 1, 4, 50, name, 0, new Skill[]{SpecialAbilities.FEAR, SpecialAbilities.IMMUNE_TO_POISON, SpecialAbilities.IMMUNE_TO_PSYCHO, SpecialAbilities.NO_PAIN, SpecialAbilities.NO_EXP, SpecialAbilities.MAY_NOT_RUN, SpecialAbilities.CHARGE}, new Equipment[1], Constants.MEDIUM_UNIT, Constants.WARBAND_UNDEAD, 0, "res/img/units/henchmen/Direwolf.png", "Direwolf");    
    }
}
