package lib.dice;

import database.stuff.Spells;

import lib.Spell;
import lib.Unit;

public class Magic {
    
    public static boolean canBeCast(Spell spell){
        return true;
    }
    
    public static Spell getNewSpell(Unit unit, int warband){
        int throwed = D6Roll.rollOneD6();
        Spell[] spells = Spells.getSpellsOfWarband(warband);
        boolean hasSpellAlready = false;
        if(unit.getSpells() != null && unit.getSpells().length <6){
            for (int i = 0; i < unit.getSpells().length; i++) {
                if(unit.getSpells()[i] == spells[throwed]){
                    hasSpellAlready = true;
                    break;
                }
            }        
            if(hasSpellAlready){
                return getNewSpell(unit, warband);
            }else{
                return spells[throwed];
            }
        }else{
            return null;
        }
    }
}
