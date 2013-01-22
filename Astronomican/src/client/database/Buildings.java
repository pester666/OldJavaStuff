package client.database;

import client.lib.Building;

public class Buildings {

    public final Building REFINERY = new Building(0, 0, 0, 75, 125, false, "Refinery", "Creates Gas", true, 0, 68, 0, 0, 0);
    public final Building MINE = new Building(0, 0, 70, 0, 130, false, "Mine", "Provides Steel", true, 0, 0, 68, 0, 0);
    public final Building HARVESTER = new Building(0, 0, 100, 100, 0, false, "Harvester", "Provides Food", true, 0, 0, 0, 68, 0);
    public final Building BARRACKS = new Building(0, 120, 50, 100, 0, false, "Barracks", "Troop training", false, 0 ,0 ,0 ,0, 0);
    public final Building PALACE = new Building(0, 0, 95, 105, 100, false, "Palace", "Your House, provides Credits and Population", true, 68 ,0 ,0 ,0, 68);
    
    public Building getBuilding(String name){
        if(name.equals("Refinery")){
            return REFINERY;
        }else if(name.equals("Mine")){
            return MINE;
        }else if(name.equals("Harvester")){
            return HARVESTER;
        }else if(name.equals("Barracks")){
            return BARRACKS;
        }else if(name.equals("Palace")){
            return PALACE;
        }
        return null;
    }
}
