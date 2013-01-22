package client.lib;

public class Building {
    
    private int level = 0;
    private int credits = 0;
    private int gas = 0;
    private int steel = 0;
    private int food = 0;
    private boolean empty = true;
    private String name = "";
    private String description = "";
    private boolean providesRessources = false;   
    private int creditsPerMinute = 0;
    private int gasPerMinute = 0;
    private int steelPerMinute = 0;
    private int foodPerMinute = 0;    
    private int populationPerMinute = 0;

    public Building(int level, int credits, int gas, int steel, int food, boolean empty, String name, String description, boolean providesRessources, int creditsPerMinute, int gasPerMinute, int steelPerMinute, int foodPerMinute, int populationPerMinute) {
        super();
        this.level = level;
        this.credits = credits;
        this.gas = gas;
        this.steel = steel;
        this.food = food;
        this.empty = empty;
        this.name = name;
        this.description = description;
        this.providesRessources = providesRessources;
        this.creditsPerMinute = creditsPerMinute;
        this.gasPerMinute = gasPerMinute;
        this.steelPerMinute = steelPerMinute;
        this.foodPerMinute = foodPerMinute;
        this.populationPerMinute = populationPerMinute;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isProvidesRessources() {
        return providesRessources;
    }

    public void setProvidesRessources(boolean providesRessources) {
        this.providesRessources = providesRessources;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getGas() {
        return gas;
    }

    public void setGas(int gas) {
        this.gas = gas;
    }

    public int getSteel() {
        return steel;
    }

    public void setSteel(int steel) {
        this.steel = steel;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

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

    public int getPopulationPerMinute() {
        return populationPerMinute;
    }

    public void setPopulationPerMinute(int populationPerMinute) {
        this.populationPerMinute = populationPerMinute;
    }
}
