package lib.dice;

import lib.Unit;


public class RollToHit {

    /**
     * Proofs if Attacker has hitted the Target
     * @param ws1 Attackers Weaponskill
     * @param ws2 Targets Weaponskill
     * @return
     */
    public static boolean hasHitted(Unit attacker, Unit target, int modifier){
        int diff = attacker.getWeaponSkill() - target.getWeaponSkill();
        if(diff < -1){
            diff = -1;
        }
        if(diff > 1){
            diff = 1;
        }
        int throwed = D6Roll.rollOneD6();
        switch (diff) {
            case 0:
                if(throwed >= 4){
                    return true;
                }
                break;
            case 1:
                if(throwed >= 3){
                    return true;
                }
                break;
            case -1:
                if(throwed >= 5){
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
}
