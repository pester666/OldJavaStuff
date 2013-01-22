package lib;

public class Gear {
    
    public String name;
    public boolean isMeleeWeapon = false;
    public int strength = 1;
    public int armorPenetration = 0;
    public int rateOfFire = 1;
    public int range = 1;
    
    /**
     * Create ur own weapeonzzz
     * @param isMeleeWeapon
     * @param strength
     * @param armorPenetration
     * @param rateOfFire
     * @param range
     */
    public Gear(String name, boolean isMeleeWeapon, int strength, int armorPenetration, int rateOfFire, int range) {
        this.name = name;
        this.isMeleeWeapon = isMeleeWeapon;
        this.strength = strength;
        this.armorPenetration = armorPenetration;
        this.rateOfFire = rateOfFire;
        this.range = range;
    }
}
