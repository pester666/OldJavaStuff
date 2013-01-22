package base.lib;

public class Tank extends Unit {

    protected int armorFront;
    protected int armorSide;
    protected int armorBack;
    
    public Tank(String name, boolean hasMoved, int morale, int movement, int wounds, int points, Weapon[] weapon, int armorFront, int armorSide, int armorBack, int width, int heigth, int bf) {
        super(name, hasMoved, morale, movement, wounds, points, width, heigth, bf, weapon);
        this.weapon = weapon;
        this.armorFront = armorFront;
        this.armorSide = armorSide;
        this.armorBack = armorBack;
    } 
}
