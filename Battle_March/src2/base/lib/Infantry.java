package base.lib;

public class Infantry extends Unit {

    protected Weapon weapon;
    protected int toughness;
    protected int armorsave;
    
    public Infantry(String name, boolean hasMoved, int toughness, int armorsave, int morale, int movement, int wounds, int points, Weapon weapon, int bf, int width, int heigth) {
        super(name, hasMoved, morale, movement, wounds, points, width, heigth, bf, new Weapon[]{weapon});
        this.toughness = toughness;
        this.armorsave = armorsave;
    }
}
