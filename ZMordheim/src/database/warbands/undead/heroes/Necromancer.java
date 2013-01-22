package database.warbands.undead.heroes;

import lib.Constants;
import lib.Equipment;
import lib.Hero;
import lib.Skill;
import lib.Spell;

public class Necromancer extends Hero {

    public Necromancer(String name, Equipment[] equipment){
        super(4, 3, 3, 3, 3, 1, 3, 1, 7, 35, name, 0, new Skill[1], equipment, 4, 6, 6, 4, 4, 3, 6, 4, 9, true, Constants.MEDIUM_UNIT, Constants.WARBAND_UNDEAD, new Spell[1], 0, "res/img/units/heroes/Necromancer.png", "Necromancer");   
    }
}
