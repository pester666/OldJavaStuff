package lib;

import lib.buildings.Barracks;
import lib.buildings.Tavern;
import lib.buildings.University;

public class Building {

    protected String name = "";
    protected int tier = 0;
    protected int costs = 0;

    public Building(String name, int tier, int costs) {
        super();
        this.name = name;
        this.tier = tier;
        this.costs = costs;
    }
    
    public Building(){
        
    }

    public int getTier() {
        return tier;
    }

    public void upgradeBuilding() {
        this.tier++;
        //TODO costs
    }

    public String getName() {
        return name;
    }

    public int getCosts() {
        return costs;
    }
    
    public Building[] getBuildings(){
        return new Building[]{new Barracks(), new Tavern(), new University()};
    }
}

