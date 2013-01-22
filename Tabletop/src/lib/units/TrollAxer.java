package lib.units;

import lib.Ability;
import lib.Unit;
import lib.Weapon;
import lib.abilities.Regeneration;
import lib.weapons.Greataxe;

public class TrollAxer extends Unit {

    public TrollAxer(){
        super(UC.TROLL_AXER, 22, 6, 12, 4, 9, 18,
                new Weapon[]{new Greataxe()},
                new Ability[]{new Regeneration()},
                5, 6, 0, 0, 6);        
    }
}
