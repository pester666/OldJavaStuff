package game.lib.units;

import game.lib.weapons.Weapon;

public abstract class Unit {

    private int xCoord;
    private int yCoord;
    private int width;
    private int hight;
    
    private String name;
    
    private int hp;
    private int lvl;
    
    private Weapon equippedWeapon;
    
    private int direction = 0;
        
    public Unit(int xCoord, int yCoord, int width, int hight, String name, int hp, int lvl, Weapon equippedWeapon, int standsLeft) {
        super();
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.width = width;
        this.hight = hight;
        this.name = name;
        this.hp = hp;
        this.lvl = lvl;
        this.equippedWeapon = equippedWeapon;
        this.direction = standsLeft;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getWidth() {
        return width;
    }

    public int getHight() {
        return hight;
    }

    public String getName() {
        return name;
    }

    public int getLvl() {
        return lvl;
    }
    
    public void raiseLvl(){
        this.lvl = this.lvl + 1;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
