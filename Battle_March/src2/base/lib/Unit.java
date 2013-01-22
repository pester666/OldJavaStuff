package base.lib;

public class Unit {

    protected String name;
    protected boolean hasMoved;
    protected int morale;
    protected int movement;
    protected int movedSoFar;
    protected int wounds;
    protected int points;
    protected int width;
    protected int heigth;
    protected int bs;
    protected Weapon[] weapon;
    protected int y;
    protected int x;
    
    public Unit(String name, boolean hasMoved, int morale, int movement, int wounds, int points, int width, int heigth, int bs, Weapon[] weapon) {
        super();
        this.name = name;
        this.hasMoved = hasMoved;
        this.morale = morale;
        this.movement = movement;
        this.wounds = wounds;
        this.points = points;
        this.width = width;
        this.heigth = heigth;
        this.bs = bs;
        this.weapon = weapon;
    }
}
