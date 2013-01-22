package database;

import java.util.ArrayList;
import java.util.List;

import lib.Constants;
import lib.Unit;

public class UnitDatabaseWW2 {

    private GearDatabaseWW2 gd = new GearDatabaseWW2();
//AXXIS
//                                          range, as,mv,bf,kg, t, s, a, name,         player, gear,       moved,attacked,costs, unitType
    public final Unit VOLKSGRENADIER =  new Unit(1, 5, 3, 3, 3, 3, 3, 1, 1, 1, "Volksgrenadier", null, gd.kar98, false, false, 40, Constants.UNIT_INFANTRY, Constants.RACE_AXIS);
    public final Unit PANZERGRENADIER = new Unit(1, 5, 3, 4, 4, 3, 4, 2, 1, 1, "Panzergrenadier", null, gd.kar98, false, false, 60, Constants.UNIT_INFANTRY, Constants.RACE_AXIS);
    public final Unit MP40SCHUETZE =    new Unit(1, 5, 3, 3, 3, 3, 3, 1, 1, 1, "Volksgrenadier mit MP40", null, gd.mp40, false, false, 60, Constants.UNIT_INFANTRY, Constants.RACE_AXIS);
    public final Unit MP44SCHUETZE =    new Unit(1, 5, 3, 3, 4, 3, 4, 1, 1, 1, "Panzergrenadier mit MP44", null, gd.mp44, false, false, 60, Constants.UNIT_INFANTRY, Constants.RACE_AXIS);
    public final Unit MG42SCHUETZE =    new Unit(1, 5, 2, 3, 3, 3, 3, 2, 2, 2, "MG42 Trupp", null, gd.mg42, false, false, 60, Constants.UNIT_MACHINEGUN, Constants.RACE_AXIS);
    
    
//ALLIES
//                                          range, as,mv,bf,kg, t, s, a, name,     player, gear,       moved,attacked,costs, unitType
    public final Unit MORTAR =          new Unit(1, 4, 2, 3, 2, 3, 3, 1, 2, 2, "Mortar", null, gd.mortar, true, false, 120, Constants.UNIT_ARTILLERY, Constants.RACE_ALLIED);
    public final Unit BAZOOKA =         new Unit(1, 5, 3, 3, 2, 3, 3, 1, 1, 1, "Bazooka", null, gd.bazooka, false, false, 120, Constants.UNIT_ANTITANK, Constants.RACE_ALLIED);
    public final Unit GI =              new Unit(1, 5, 3, 3, 3, 3, 3, 1, 1, 1, "GI", null, gd.garand, false, false, 40, Constants.UNIT_INFANTRY, Constants.RACE_ALLIED);
    public final Unit SUBMACHINEGUNNER =new Unit(1, 5, 3, 3, 3, 3, 3, 1, 1, 1, "GI with Thompson", null, gd.thompson, false, false, 40, Constants.UNIT_INFANTRY, Constants.RACE_ALLIED);
    
    public final Unit ARTILLERY        =new Unit(1, 3, 1, 3, 3, 4, 2, 2, 3, 3, "Artillery", null, gd.artillery, false, false, 200, Constants.UNIT_ARTILLERY, Constants.RACE_ALLIED);
    
    public Unit[] returnUnits(){
        Unit[] array = new Unit[10];
        List<Unit> units = new ArrayList<Unit>();
        units.add(VOLKSGRENADIER);
        units.add(PANZERGRENADIER);
        units.add(MP40SCHUETZE);
        units.add(MP44SCHUETZE);
        units.add(MG42SCHUETZE);
        units.add(MORTAR);
        units.add(BAZOOKA);
        units.add(GI);
        units.add(SUBMACHINEGUNNER);
        units.add(ARTILLERY);
        units.toArray(array);
        return array;
    }
}
