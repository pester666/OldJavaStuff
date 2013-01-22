package database;

import java.util.ArrayList;
import java.util.List;

import lib.Constants;
import lib.Unit;



public class UnitDatabaseFantasy {

    private GearDatabaseFantasy gd = new GearDatabaseFantasy();
//                                          range, as,mv,bf,kg, t, s, a,hp, name,     player, gear,    moved,attacked, costs, unitType
    public final Unit STONE =           new Unit(1, 3, 2, 2, 2, 5, 4, 1,5, 5, "Stone", null, gd.rockSling, false, false, 50, Constants.UNIT_INFANTRY, Constants.RACE_ENVIRONMENT);

//ORKS
//                                          range, as,mv,bf,kg, t, s, a, name,     player, gear,    moved,attacked, costs, unitType
    public final Unit GOBLIN =          new Unit(1, 6, 4, 0, 3, 3, 2, 1, 1, 1, "Goblin", null, gd.dagger, false, false, 25, Constants.UNIT_INFANTRY, Constants.RACE_ORKS);
    public final Unit ORKWARRIOR =      new Unit(1, 5, 3, 0, 3, 4, 3, 1, 1, 1, "Ork-Warrior", null, gd.chopper, false, false, 60, Constants.UNIT_INFANTRY, Constants.RACE_ORKS);
    public final Unit ORKARCHER =       new Unit(1, 6, 3, 3, 3, 4, 3, 1, 1, 1, "Ork-Archer", null, gd.bow, false, false, 60, Constants.UNIT_INFANTRY, Constants.RACE_ORKS);
    public final Unit BLACKORK =        new Unit(1, 5, 3, 0, 4, 4, 4, 1, 1, 1, "Blackork", null, gd.greatsword, false, false, 130, Constants.UNIT_INFANTRY, Constants.RACE_ORKS);
    public final Unit ROCKLOBBER =      new Unit(0, 4, 2, 3, 2, 5, 2, 0, 1, 1, "Rock Lobber", null, gd.rockLobber, true, false, 200, Constants.UNIT_ARTILLERY, Constants.RACE_ORKS);
    
//IMPERIUM
//                                          range, as,mv,bf,kg, t, s, a, name,     player, gear,    moved,attacked, costs, unitType
    public final Unit ARCHER =          new Unit(1, 6, 3, 3, 2, 3, 3, 1, 1, 1, "Archer", null, gd.bow, false, false, 60, Constants.UNIT_INFANTRY, Constants.RACE_HUMAN);
    public final Unit SWORDSMAN =       new Unit(1, 4, 3, 0, 4, 3, 3, 1, 1, 1, "Swordsman", null, gd.sword, false, false, 80, Constants.UNIT_INFANTRY, Constants.RACE_HUMAN);
    public final Unit SPEARMAN =        new Unit(1, 5, 3, 0, 3, 3, 3, 1, 1, 1, "Spearman", null, gd.spear, false, false, 70, Constants.UNIT_INFANTRY, Constants.RACE_HUMAN);
    public final Unit CROSSBOWMAN =     new Unit(1, 6, 3, 3, 3, 3, 3, 1, 1, 1, "Crossbowman", null, gd.crossbow, false, false, 90, Constants.UNIT_INFANTRY, Constants.RACE_HUMAN);
    public final Unit RIFLEMAN =        new Unit(1, 6, 3, 3, 3, 3, 3, 1, 1, 1, "Rifleman", null, gd.rifle, false, false, 90, Constants.UNIT_INFANTRY, Constants.RACE_HUMAN);
    
//CHAOS
//                                          range, as,mv,bf,kg, t, s, a, name,     player, gear,    moved,attacked, costs, unitType
    public final Unit CHAOSKRIEGER =    new Unit(1, 3, 3, 0, 4, 4, 4, 2, 1, 1, "Chaoskrieger", null, gd.halberd, false, false, 100, Constants.UNIT_INFANTRY, Constants.RACE_CHAOS);
   
//UNTOTE
//                                          range, as,mv,bf,kg, t, s, a, name,     player, gear,    moved,attacked, costs, unitType
    public final Unit SKELETON =        new Unit(1, 4, 3, 0, 2, 3, 3, 1, 1, 1, "Skelett", null, gd.spear, false, false, 60, Constants.UNIT_INFANTRY, Constants.RACE_UNDEAD);
    
//ZWERGE
//                                          range, as,mv,bf,kg, t, s, a, name,     player, gear,    moved,attacked, costs, unitType
    public final Unit CLANWARRIOR =     new Unit(1, 3, 2, 0, 3, 4, 3, 1, 1, 1, "Clanwarrior", null, gd.axe, false, false, 80, Constants.UNIT_INFANTRY, Constants.RACE_DWARF);
    public final Unit MUSKETEER =       new Unit(1, 5, 2, 3, 3, 4, 3, 1, 1, 1, "Musketeer", null, gd.rifle, false, false, 90, Constants.UNIT_INFANTRY, Constants.RACE_DWARF);

//ELVES
//                                          range, as,mv,bf,kg, t, s, a, name,     player, gear,    moved,attacked, costs, unitType
    public final Unit WOODELFWARRIOR =  new Unit(1, 6, 5, 4, 3, 3, 3, 1, 1, 1, "Woodelfwarrior", null, gd.longbow, false, false, 70, Constants.UNIT_INFANTRY, Constants.RACE_WOODELVES);
    
    public final Unit WALLSECTIONH =     new Unit(0,0,0,0,0,0,0,0,0,0,"Wall Section Horizontal",null,null,true,false,0,Constants.UNIT_STRUCTURE, Constants.RACE_ENVIRONMENT);
    public final Unit WALLSECTIONV =     new Unit(0,0,0,0,0,0,0,0,0,0,"Wall Section Vertical",null,null,true,false,0,Constants.UNIT_STRUCTURE, Constants.RACE_ENVIRONMENT);
    public final Unit WALLCORNERLL =     new Unit(0,0,0,0,0,0,0,0,0,0,"Wall Corner Lower Left",null,null,true,false,0,Constants.UNIT_STRUCTURE, Constants.RACE_ENVIRONMENT);
    public final Unit WALLCORNERUL =     new Unit(0,0,0,0,0,0,0,0,0,0,"Wall Corner Upper Left",null,null,true,false,0,Constants.UNIT_STRUCTURE, Constants.RACE_ENVIRONMENT);
    public final Unit WALLCORNERLR =     new Unit(0,0,0,0,0,0,0,0,0,0,"Wall Corner Lower Right",null,null,true,false,0,Constants.UNIT_STRUCTURE, Constants.RACE_ENVIRONMENT);
    public final Unit WALLCORNERUR =     new Unit(0,0,0,0,0,0,0,0,0,0,"Wall Corner Upper Right",null,null,true,false,0,Constants.UNIT_STRUCTURE, Constants.RACE_ENVIRONMENT);
    
    public Unit[] returnUnits(){
        Unit[] array = new Unit[22];
        List<Unit> units = new ArrayList<Unit>();
        units.add(SKELETON);
        units.add(CHAOSKRIEGER);
        units.add(GOBLIN);
        units.add(ORKWARRIOR);
        units.add(ORKARCHER);
        units.add(BLACKORK);
        units.add(ROCKLOBBER);
        units.add(SWORDSMAN);
        units.add(ARCHER);
        units.add(SPEARMAN);
        units.add(CROSSBOWMAN);
        units.add(RIFLEMAN);
        units.add(CLANWARRIOR);
        units.add(MUSKETEER);
        units.add(WOODELFWARRIOR);
        units.add(WALLSECTIONH);
        units.add(WALLSECTIONV);
        units.add(WALLCORNERLL);
        units.add(WALLCORNERUL);
        units.add(WALLCORNERLR);
        units.add(WALLCORNERUR);
        units.add(STONE);
        units.toArray(array);
        return array;
    }
}
