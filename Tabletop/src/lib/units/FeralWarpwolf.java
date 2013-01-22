package lib.units;

import lib.Ability;
import lib.Unit;
import lib.Weapon;
import lib.abilities.Regeneration;
import lib.weapons.Bite_3;
import lib.weapons.Claw_4;

public class FeralWarpwolf extends Unit {

    public FeralWarpwolf(){
        super(UC.FERAL_WARPWOLF,
                28, //HP
                7, //MELEE
                14, //DEFENSE
                3, //RANGE
                11, //STRENGTH
                16, //ARMOR
                new Weapon[]{new Claw_4(), new Claw_4(), new Bite_3()},
                new Ability[]{new Regeneration()},
                6, //SPEED
                7, //COMMAND
                0, // X
                0, //Y
                9);    //COSTS    
    }
}
