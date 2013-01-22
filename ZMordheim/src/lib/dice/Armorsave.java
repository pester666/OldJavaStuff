package lib.dice;

import lib.Unit;


public class Armorsave {

    public static boolean hasFailedArmorsave(Unit attacker, Unit target, int modifier){
        int throwed = D6Roll.rollOneD6() + modifier;
        int arm = target.getArmorsave();
        int s = attacker.getStrength();

        if(arm > 0){
            if(s > 3){
                arm = arm + (s-3);
            }
            if(throwed >= arm){
                return true;
            }
        }
        
        return false;
    }
}
