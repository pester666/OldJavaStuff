package database;

import java.util.ArrayList;
import java.util.List;

import lib.Constants;
import lib.Unit;

public class UnitDatabaseFuture {

    private GearDatabaseFuture gd = new GearDatabaseFuture();
//                                          range, as,mv,bf,kg, t, s, a, hp,maxHp,name,     player, gear,   moved,attacked, costs, unitType
    public final Unit GUARDSMAN =       new Unit(1, 5, 3, 3, 3, 3, 3, 1, 1, 1, "Guardsman", null, gd.lasgun, false, false, 50, Constants.UNIT_INFANTRY, Constants.RACE_HUMAN);
    public final Unit ORKBOY =          new Unit(1, 6, 3, 2, 4, 4, 3, 2, 1, 1, "Ork-Boy", null, gd.lasgun, false, false, 50, Constants.UNIT_INFANTRY, Constants.RACE_ORKS);
    public final Unit BALLABOY =        new Unit(1, 4, 3, 2, 4, 4, 3, 2, 1, 1, "Ballaboy", null, gd.shoota, false, false, 90, Constants.UNIT_INFANTRY, Constants.RACE_ORKS);
    public final Unit CSMMARINE =       new Unit(1, 3, 3, 4, 4, 4, 4, 1, 1, 1, "Chaos Space Marine", null, gd.bolter, false, false, 140, Constants.UNIT_INFANTRY, Constants.RACE_CHAOS);
    public final Unit HORMAGANT =       new Unit(1, 6, 4, 3, 4, 3, 3, 3, 1, 1, "Hormagant", null, gd.claw, false, false, 30, Constants.UNIT_INFANTRY, Constants.RACE_TYRANIDS);
    public final Unit HEAVYBOLTER =     new Unit(1, 4, 3, 3, 3, 3, 3, 2, 1, 1, "Heavy Bolter", null, gd.heavybolter, false, false, 140, Constants.UNIT_MACHINEGUN, Constants.RACE_HUMAN);
    public final Unit MORTAR =          new Unit(1, 4, 3, 3, 3, 3, 3, 1, 1, 1, "Mortar", null, gd.mortar, false, false, 100, Constants.UNIT_ARTILLERY, Constants.RACE_HUMAN);
    
    public Unit[] returnUnits(){
        Unit[] array = new Unit[7];
        List<Unit> units = new ArrayList<Unit>();
        units.add(GUARDSMAN);
        units.add(ORKBOY);
        units.add(BALLABOY);
        units.add(CSMMARINE);
        units.add(HORMAGANT);
        units.add(HEAVYBOLTER);
        units.add(MORTAR);
        units.toArray(array);
        return array;
    }
}
