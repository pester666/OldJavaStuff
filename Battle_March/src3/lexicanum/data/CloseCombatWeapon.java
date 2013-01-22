package lexicanum.data;

public class CloseCombatWeapon extends Weapon {

    protected final int strengthModifier;

    public CloseCombatWeapon(String nameD, String nameE, int strength, int ds, SpecialRule[] specialRules, int strengthModifier) {
        super(nameD, nameE, strength, ds, specialRules);
        this.strengthModifier = strengthModifier;
    }
}
