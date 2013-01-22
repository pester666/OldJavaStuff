package client.database;

import client.lib.Building;

public class Player {

    private int credits = 0;
    private int gas = 0;
    private int steel = 0;
    private int food = 0;
    private int population = 0;
    private int creditsPerMinute = 0;
    private int gasPerMinute = 0;
    private int steelPerMinute = 0;
    private int foodPerMinute = 0;
    private int populationPerMinute = 0;
    
    public int getCreditsPerMinute() {
        return creditsPerMinute;
    }

    public void setCreditsPerMinute(int creditsPerMinute) {
        this.creditsPerMinute = creditsPerMinute;
    }

    public int getGasPerMinute() {
        return gasPerMinute;
    }

    public void setGasPerMinute(int gasPerMinute) {
        this.gasPerMinute = gasPerMinute;
    }

    public int getSteelPerMinute() {
        return steelPerMinute;
    }

    public void setSteelPerMinute(int steelPerMinute) {
        this.steelPerMinute = steelPerMinute;
    }

    public int getFoodPerMinute() {
        return foodPerMinute;
    }

    public void setFoodPerMinute(int foodPerMinute) {
        this.foodPerMinute = foodPerMinute;
    }

    private Building[] buildings;
        
    public Player(){
        buildings = new Building[16];
    }
    
    public Building getBuilding(int nr){
        return buildings[nr];
    }

    public int getCredits() {
        return credits;
    }

    public int getGas() {
        return gas;
    }

    public int getSteel() {
        return steel;
    }

    public int getFood() {
        return food;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public void setSteel(int steel) {
        this.steel = steel;
    }

    public void setFood(int food) {
        this.food = food;
    }
    
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulationPerMinute() {
        return populationPerMinute;
    }

    public void setPopulationPerMinute(int populationPerMinute) {
        this.populationPerMinute = populationPerMinute;
    }

    public void setBuilding(int count, String building){
        String[] stats = building.split(":");
        boolean empty = false;
        boolean providesRessources = false;
        if(stats[2].equals("true")){
            empty = true;
        }else if(stats[2].equals("false")){
            empty = false;
        }
        if(stats[5].equals("true")){
            providesRessources = true;
        }else if(stats[2].equals("false")){
            providesRessources = false;
        }
        Buildings bi = new Buildings();
        Building b = bi.getBuilding(stats[3]);
        if(b != null){
            int c = b.getCredits();
            int g = b.getGas();
            int s = b.getSteel();
            int f = b.getFood();
            int cm = b.getCreditsPerMinute();
            int gm = b.getGasPerMinute();
            int sm = b.getSteelPerMinute();
            int fm = b.getFoodPerMinute();
            int pm = b.getPopulationPerMinute();
            for (int i = 0; i < Integer.parseInt(stats[1]); i++) {
                c = (int)(c * 1.4);
                g = (int)(g * 1.4);
                s = (int)(s * 1.4);
                f = (int)(f * 1.4);
            }
            for (int i = 1; i < Integer.parseInt(stats[1]); i++) {
                cm = cm + (cm*(100/(i))/100);
                gm = gm + (gm*(100/(i))/100);
                sm = sm + (sm*(100/(i))/100);
                fm = fm + (fm*(100/(i))/100);
                pm = pm + (pm*(100/(i))/100);
            }
            buildings[count] = new Building(Integer.parseInt(stats[1]), c, g, s, f, empty, stats[3], stats[4], providesRessources, cm, gm, sm, fm, pm);
        }else{
            buildings[count] = new Building(Integer.parseInt(stats[1]), 0, 0, 0, 0, empty, stats[3], stats[4], providesRessources, 0, 0, 0, 0, 0);
        }
    }
    
    public void setBuilding(int arrayNr, Building bi){
        buildings[arrayNr] = bi;
    }
}
