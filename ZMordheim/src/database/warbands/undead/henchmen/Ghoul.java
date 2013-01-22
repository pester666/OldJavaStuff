package database.warbands.undead.henchmen;

import database.stuff.SpecialAbilities;
import lib.Constants;
import lib.Equipment;
import lib.Henchmen;
import lib.Skill;

public class Ghoul extends Henchmen {

    public Ghoul(String name){
        super(4, 2, 2, 3, 4, 1, 3, 2, 5, 40, name, 0, new Skill[]{SpecialAbilities.FEAR}, new Equipment[1], Constants.MEDIUM_UNIT, Constants.WARBAND_UNDEAD, 0, "res/img/units/henchmen/Ghoul.png", "Ghoul"); 
    }
}
