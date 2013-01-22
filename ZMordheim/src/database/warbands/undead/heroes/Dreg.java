package database.warbands.undead.heroes;

import lib.Constants;
import lib.Equipment;
import lib.Hero;
import lib.Skill;

public class Dreg extends Hero {

    public Dreg(String name, Equipment[] equipment){
        super(4, 2, 2, 3, 3, 1, 3, 1, 7, 20, name, 0, new Skill[1], equipment, 4, 6, 6, 4, 4, 3, 6, 4, 9, false, Constants.MEDIUM_UNIT, Constants.WARBAND_UNDEAD, null, 0, "res/img/units/heroes/Dreg.png", "Dreg");    
    }
    
}
