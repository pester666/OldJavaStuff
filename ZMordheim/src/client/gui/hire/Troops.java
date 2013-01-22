package client.gui.hire;

import database.warbands.undead.henchmen.Direwolf;
import database.warbands.undead.henchmen.Ghoul;
import database.warbands.undead.henchmen.Zombie;
import lib.Unit;

public class Troops {

    private Unit[] units;
    
    public Troops(int warband){
        if(warband == 0){ //Undead
            this.units = new Unit[]{
                    new Direwolf(""),
                    new Ghoul(""),
                    new Zombie(""),
            };
        }else if(warband == 1){ //Dwarf
            
        }
    }

    public Unit[] getUnits() {
        return units;
    }

    public void setUnits(Unit[] units) {
        this.units = units;
    }

    public Unit getUnit(int troopCount) {
        return units[troopCount];
    }
}
