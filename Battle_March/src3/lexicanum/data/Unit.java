package lexicanum.data;

public class Unit {

    protected final String name;
    protected final int movement;
    protected final int bf;
    protected final int pts;
    protected final SpecialRule[] specialRules;
    protected final Race race;
    
    public Unit(String name, int movement, int bf, int pts, SpecialRule[] specialRules, Race race) {
        super();
        this.name = name;
        this.movement = movement;
        this.bf = bf;
        this.pts = pts;
        this.specialRules = specialRules;
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public int getMovement() {
        return movement;
    }

    public int getBf() {
        return bf;
    }

    public int getPts() {
        return pts;
    }

    public SpecialRule[] getSpecialRules() {
        return specialRules;
    }

    public Race getRace() {
        return race;
    }
}
