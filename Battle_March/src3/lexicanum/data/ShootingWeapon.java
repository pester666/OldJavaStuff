package lexicanum.data;

public class ShootingWeapon extends Weapon {

    protected final String range;

    public ShootingWeapon(String nameD, String nameE, int strength, int ds, SpecialRule[] specialRules, String range) {
        super(nameD, nameE, strength, ds, specialRules);
        this.range = range;
    }
}
