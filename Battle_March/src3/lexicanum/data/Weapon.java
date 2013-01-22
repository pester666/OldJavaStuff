package lexicanum.data;

public class Weapon {

    protected final String nameD;
    protected final String nameE;
    protected final int strength;
    protected final int ds;
    protected final SpecialRule[] specialRules;
    
    public Weapon(String nameD, String nameE, int strength, int ds, SpecialRule[] specialRules) {
        super();
        this.nameD = nameD;
        this.nameE = nameE;
        this.strength = strength;
        this.ds = ds;
        this.specialRules = specialRules;
    }
}
