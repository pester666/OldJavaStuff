package lexicanum.library.units.imp;

import lexicanum.library.race.Races;

import lexicanum.data.Infantry;

public class IGInfantry {

    public static final Infantry guardsman = new Infantry("Soldat", 6, 3, 10, null, 3, 3, 1, 7, 5, 1, 3, 3, Races.IMP);
    
    public static String[] getUnits(){
        return new String[]{guardsman.getName()};
    }
}
