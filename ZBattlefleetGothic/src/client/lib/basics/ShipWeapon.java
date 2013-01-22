package client.lib.basics;

public class ShipWeapon {
//nothing to say here or?
    protected int x;
    protected int y;
    protected int id;
    protected String name;
    protected int maxEnergy;
    protected int actEnergy;
    protected int dmg;
    protected int cooldown;
    protected int actCooldown;
    protected boolean canGoThroughShield;
    protected boolean canLayFire;
    protected int fireChance;
    protected boolean isProjectile;
    protected Room target;
    protected boolean isFiring;
    protected int speed;
    
    public ShipWeapon(int x, int y, int id, String name, int maxEnergy, int actEnergy, int dmg, int cooldown, boolean canGoThroughShield, boolean canLayFire, int fireChance, boolean isProjectile, int speed) {
        super();
        this.x = x;
        this.y = y;
        this.id = id;
        this.name = name;
        this.maxEnergy = maxEnergy;
        this.actEnergy = actEnergy;
        this.dmg = dmg;
        this.cooldown = cooldown;
        this.canGoThroughShield = canGoThroughShield;
        this.canLayFire = canLayFire;
        this.fireChance = fireChance;
        this.isProjectile = isProjectile;
        this.actCooldown = this.cooldown;
        this.speed = speed;
    }
    
    

    public int getSpeed() {
        return speed;
    }



    public void setSpeed(int speed) {
        this.speed = speed;
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getActEnergy() {
        return actEnergy;
    }

    public void setActEnergy(int actEnergy) {
        this.actEnergy = actEnergy;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isCanGoThroughShield() {
        return canGoThroughShield;
    }

    public void setCanGoThroughShield(boolean canGoThroughShield) {
        this.canGoThroughShield = canGoThroughShield;
    }

    public boolean isCanLayFire() {
        return canLayFire;
    }

    public void setCanLayFire(boolean canLayFire) {
        this.canLayFire = canLayFire;
    }

    public int getFireChance() {
        return fireChance;
    }

    public void setFireChance(int fireChance) {
        this.fireChance = fireChance;
    }

    public boolean isProjectile() {
        return isProjectile;
    }

    public void setProjectile(boolean isProjectile) {
        this.isProjectile = isProjectile;
    }

    public int getActCooldown() {
        return actCooldown;
    }

    public void setActCooldown(int actCooldown) {
        this.actCooldown = actCooldown;
    }

    public Room getTarget() {
        return target;
    }

    public void setTarget(Room target) {
        this.target = target;
    }

    public boolean isFiring() {
        return isFiring;
    }

    public void setFiring(boolean isFiring) {
        this.isFiring = isFiring;
    }
    
    
    
}
