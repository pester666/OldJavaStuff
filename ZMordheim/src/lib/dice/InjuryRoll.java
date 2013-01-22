package lib.dice;

import lib.State;
import lib.Unit;

public class InjuryRoll {

    public static Unit roll(Unit unit, int modifier){
        int throwed = D6Roll.rollOneD6() + modifier;
        String s = throwed + ": ";
        if(throwed <= 2){
           s = s + "Got Knocked Down"; 
           unit.setState(State.KNOCKED_DOWN);
        }else
        if(throwed <= 4){
            s = s + "Got Stunned";
            unit.setState(State.STUNNED);
        }else
        if(throwed <= 6){
            s = s + "Went out of action";
            unit.setState(State.OUT_OF_ACTION);
        }
        unit.addInfo(s);
        return unit;
    }
}
