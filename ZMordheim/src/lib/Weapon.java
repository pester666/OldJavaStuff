package lib;

public abstract class Weapon extends Equipment {

    protected int range;
    protected int strengthModifier;
    protected int armorModifier;
    protected boolean isMeeleeWeapon;
    protected Skill[] specialRules;
    
    public int getRange() {
        return range;
    }
    public int getStrengthModifier() {
        return strengthModifier;
    }
    public int getArmorModifier() {
        return armorModifier;
    }
    public boolean isMeeleeWeapon() {
        return isMeeleeWeapon;
    }
    public Skill[] getSpecialRule() {
        return specialRules;
    }
    public Weapon(String name, int cost, int range, int strengthModifier, int armorModifier, boolean isMeeleeWeapon, Skill[] specialRules) {
        super(name, cost);
        this.range = range;
        this.strengthModifier = strengthModifier;
        this.armorModifier = armorModifier;
        this.isMeeleeWeapon = isMeeleeWeapon;
        this.specialRules = specialRules;
    }
    
    protected abstract Unit attackTarget(Unit attacker, Unit target);
    
}
