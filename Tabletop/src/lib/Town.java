package lib;

import java.util.Random;

public class Town {

    private Building[] buildings = null;
    private String name = "";
    private Building wall = null;
    private Unit[] units = null;
    private int goldPerRound = 500;
    private int population = 0;
    private int tier = 1;
    private int xCoord = 0;
    private int yCoord = 0;
    private String land = "";
    
    public Town(String name){
        this.name = name;
        setStats();
    }
    
    private void setStats(){
        Random ran = new Random();
        int count = ran.nextInt(2*tier);
        Building b = new Building();
        buildings = b.getBuildings();
        for (int i = 0; i <= count; i++) {
            int n = ran.nextInt(buildings.length);
            if(buildings[n].getTier() == 0){
                buildings[n].upgradeBuilding();
            }
        }
    }
  
    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public String getLand() {
        return land;
    }

    public Building getWall() {
        return wall;
    }

    public void setWall(Building wall) {
        this.wall = wall;
    }

    public int getPopulation() {
        //TODO if pop over count then upgradeTown()
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getTier() {
        return tier;
    }

    public void upgradTown() {
        this.tier++;
        //TODO costs usw;
    }

    public Building[] getBuildings() {
        return buildings;
    }

    public String getName() {
        return name;
    }

    public Unit[] getUnits() {
        return units;
    }

    public int getGoldPerRound() {
        return goldPerRound;
    }
}
