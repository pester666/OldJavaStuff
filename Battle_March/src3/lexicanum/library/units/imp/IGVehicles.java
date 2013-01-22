package lexicanum.library.units.imp;

import lexicanum.data.Vehicle;
import lexicanum.library.race.Races;

public class IGVehicles {

    public static final Vehicle chimera = new Vehicle("Chimäre", 6, 3, 55, null, 12, 10, 10, 3, Races.IMP);
    
    public static String[] getUnits(){
        return new String[]{chimera.getName()};
    }
}
