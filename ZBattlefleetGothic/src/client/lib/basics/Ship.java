package client.lib.basics;

import java.util.List;

public class Ship {
//good ol betsy#
    
    protected int skill;
    protected String name;
    protected int speed;
    protected int hp;
    protected int cost;
    protected List<Room> rooms;
    protected List<ShipComponent> components;
    protected List<ShipWeapon> weapons;
    protected List<Unit> units;
    protected List<Action> actions;
    protected int x;
    protected int y;
    protected boolean showInterior;
    
    public Ship(int skill, String name, int speed, int hp, int cost, int x, int y, List<Room> rooms, List<ShipComponent> components, List<ShipWeapon> weapons, List<Unit> units, List<Action> actions) {
        super();
        this.skill = skill;
        this.name = name;
        this.speed = speed;
        this.hp = hp;
        this.cost = cost;
        this.x = x;
        this.y = y;
        this.rooms = rooms;
        this.components = components;
        this.weapons = weapons;
        this.units = units;
        this.actions = actions;
    }

    public int getArmor() {
        return skill;
    }

    public void setArmor(int armor) {
        this.skill = armor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<ShipComponent> getComponents() {
        return components;
    }

    public void setComponents(List<ShipComponent> components) {
        this.components = components;
    }

    public List<ShipWeapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<ShipWeapon> weapons) {
        this.weapons = weapons;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isShowInterior() {
        return showInterior;
    }

    public void setShowInterior(boolean showInterior) {
        this.showInterior = showInterior;
    }
}
