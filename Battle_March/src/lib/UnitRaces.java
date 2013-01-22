package lib;

import java.util.ArrayList;
import java.util.List;

import database.UnitDatabaseWW2;

import database.UnitDatabaseFuture;

import database.UnitDatabaseFantasy;

public class UnitRaces {

    public String[] returnFantasyRaces() {
        String[] array = new String[7];
        array[0] = Constants.RACE_ORKS;
        array[1] = Constants.RACE_HUMAN;
        array[2] = Constants.RACE_WOODELVES;
        array[3] = Constants.RACE_DWARF;
        array[4] = Constants.RACE_CHAOS;
        array[5] = Constants.RACE_UNDEAD;
        array[6] = Constants.RACE_ENVIRONMENT;
        //        array[6] = ENVIRONMENT;
        return array;
    }

    public String[] returnFutureRaces() {
        String[] array = new String[5];
        array[0] = Constants.RACE_CHAOS;
        array[1] = Constants.RACE_HUMAN;
        array[2] = Constants.RACE_ORKS;
        array[3] = Constants.RACE_TYRANIDS;
        array[4] = Constants.RACE_ENVIRONMENT;
        //        array[3] = ENVIRONMENT;
        return array;
    }

    public String[] returnWW2Races() {
        String[] array = new String[3];
        array[0] = Constants.RACE_AXIS;
        array[1] = Constants.RACE_ALLIED;
        array[2] = Constants.RACE_ENVIRONMENT;
        //        array[2] = ENVIRONMENT;
        return array;
    }

    public Unit[] getUnitsOfRaceAndAge(String race, int age) {
        List<Unit> units = new ArrayList<Unit>();
        if (age == Constants.FANTASY) {
            UnitDatabaseFantasy ud = new UnitDatabaseFantasy();
            Unit[] dummy = ud.returnUnits();
            for (int i = 0; i < dummy.length; i++) {
                if (dummy[i].race == race) {
                    units.add(dummy[i]);
                }
            }
        } else if (age == Constants.SCIFI) {
            UnitDatabaseFuture ud = new UnitDatabaseFuture();
            Unit[] dummy = ud.returnUnits();
            for (int i = 0; i < dummy.length; i++) {
                if (dummy[i].race == race) {
                    units.add(dummy[i]);
                }
            }
        } else if (age == Constants.WW2) {
            UnitDatabaseWW2 ud = new UnitDatabaseWW2();
            Unit[] dummy = ud.returnUnits();
            for (int i = 0; i < dummy.length; i++) {
                if (dummy[i].race == race) {
                    units.add(dummy[i]);
                }
            }
        }
        return units.toArray(new Unit[units.size()]);
    }
}
