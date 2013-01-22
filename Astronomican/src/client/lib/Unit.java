package client.lib;

public class Unit {

    private int attack = 0;
    private int defense = 0;
    private int creditCost = 0;
    private int gasCost = 0;
    private int steelCost = 0;
    private int foodCost = 0;
    private int buildingTime = 0;
    private String type = "";
    private int requiresSpace = 0;
    private String attackType = "";
    
    public Unit(int attack, int defense, int creditCost, int gasCost, int steelCost, int foodCost, int buildingTime, String type, int requiresSpace, String attackType) {
        super();
        this.attack = attack;
        this.defense = defense;
        this.creditCost = creditCost;
        this.gasCost = gasCost;
        this.steelCost = steelCost;
        this.foodCost = foodCost;
        this.buildingTime = buildingTime;
        this.type = type;
        this.requiresSpace = requiresSpace;
        this.attackType = attackType;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getCreditCost() {
        return creditCost;
    }

    public void setCreditCost(int creditCost) {
        this.creditCost = creditCost;
    }

    public int getGasCost() {
        return gasCost;
    }

    public void setGasCost(int gasCost) {
        this.gasCost = gasCost;
    }

    public int getSteelCost() {
        return steelCost;
    }

    public void setSteelCost(int steelCost) {
        this.steelCost = steelCost;
    }

    public int getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(int foodCost) {
        this.foodCost = foodCost;
    }

    public int getBuildingTime() {
        return buildingTime;
    }

    public void setBuildingTime(int buildingTime) {
        this.buildingTime = buildingTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRequiresSpace() {
        return requiresSpace;
    }

    public void setRequiresSpace(int requiresSpace) {
        this.requiresSpace = requiresSpace;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }
}
