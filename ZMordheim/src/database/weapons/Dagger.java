package database.weapons;

import lib.dice.InjuryRoll;

import lib.dice.Armorsave;

import lib.dice.RollToWound;

import lib.dice.RollToHit;

import lib.Unit;
import lib.Weapon;

public class Dagger extends Weapon {

    public Dagger() {
        super("Dagger", 2, 0, 0, 1, true, null);
    }

    @Override
    protected Unit attackTarget(Unit attacker, Unit target) {
        if(RollToHit.hasHitted(attacker, target, 0)){
            if(RollToWound.hasWounded(attacker, target, 0)){
                if(Armorsave.hasFailedArmorsave(attacker, target, 0)){
                    target = InjuryRoll.roll(target, 0);
                }
            }
        }
        return target;
    }

}
