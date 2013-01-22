package game.lib.weapons;

public abstract class Weapon {

    private int cooldown;
    private int dmgMin;
    private int dmgMax;
    private String name;
    private int range;
    
    private boolean mayAttack = true;
    private int cooldownTimer;
    
    public Weapon(int cooldown, int dmgMin, int dmgMax, String name, int range, boolean mayAttack, int cooldownTimer) {
        super();
        this.cooldown = cooldown;
        this.dmgMin = dmgMin;
        this.dmgMax = dmgMax;
        this.name = name;
        this.range = range;
        this.mayAttack = mayAttack;
        this.cooldownTimer = cooldownTimer;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getDmgMin() {
        return dmgMin;
    }

    public int getDmgMax() {
        return dmgMax;
    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public boolean mayAttack() {
        return mayAttack;
    }

    public void setMayAttack(boolean mayAttack) {
        this.mayAttack = mayAttack;
    }

    public int getCooldownTimer() {
        return cooldownTimer;
    }

    public void setCooldownTimer(int cooldownTimer) {
        this.cooldownTimer = cooldownTimer;
    }
}
