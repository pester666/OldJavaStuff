package lib.dice;

import lib.Unit;


public class RollToWound {

    /**
     * Proofs if Attacker has wounded the Target
     * @param ws1 Attackers Weaponskill
     * @param ws2 Targets Weaponskill
     * @return
     */
    public static boolean hasWounded(Unit attacker, Unit target, int modifier){
        int diff = attacker.getStrength() - target.getToughness();
        if(diff > 2){
            diff = 2;
        }
        int throwed = D6Roll.rollOneD6();
        if(throwed == 6 && diff > -2){
            //TODO sinnvoll unterbringen
            CriticalRollStandard.roll(modifier);
        }
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
            case 2:
                if(throwed >= 2){
                    return true;
                }
                break;
            case -1:
                if(throwed >= 5){
                    return true;
                }
                break;
            case -2:
                if(throwed >= 6){
                    return true;
                }
                break;
            case -3:
                if(throwed >= 6){
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
}
