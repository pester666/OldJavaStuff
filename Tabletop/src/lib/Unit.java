package lib;

public class Unit {

    protected String name = "";
    protected int hp = 0;
    protected int melee = 0;
    protected int def = 0;
    protected int rng = 0;
    protected int str = 0;
    protected int arm = 0;
    protected Weapon[] wp = null;
    protected Ability[] ab = null;
    protected int speed = 0;
    protected int command = 0;
    protected int xCoord = 0;
    protected int yCoord = 0;
    protected int costs = 0;
    
    public Unit(String name, int hp, int melee, int def, int rng, int str, int arm, Weapon[] wp, Ability[] ab, int speed, int command, int xCoord, int yCoord, int costs) {
        super();
        this.name = name;
        this.hp = hp;
        this.melee = melee;
        this.def = def;
        this.rng = rng;
        this.str = str;
        this.arm = arm;
        this.wp = wp;
        this.ab = ab;
        this.speed = speed;
        this.command = command;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.costs = costs;
    }

    public int getCosts() {
        return costs;
    }

    public void setCosts(int costs) {
        this.costs = costs;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }


    public Ability[] getAb() {
        return ab;
    }

    public void addAbility(Ability ability) {
        Ability[] abi = new Ability[ab.length + 1];
        for (int i = 0; i < ab.length; i++) {
            abi[i] = ab[i];
        }
        abi[ab.length] = ability;
        this.ab = new Ability[abi.length];
        this.ab = abi;
    }
    
    public void removeAbility(Ability ability){
        for (int i = 0; i < ab.length; i++) {
            if(ab[i].name.equals(ability.name)){
                ab[i] = null;
            }
        }
        Ability[] abi = new Ability[ab.length - 1];
        int c = 0;
        for (int i = 0; i < ab.length; i++) {
            if(ab != null){
                abi[c] = ab[i];
                c++;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMelee() {
        return melee;
    }

    public void setMelee(int melee) {
        this.melee = melee;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getRng() {
        return rng;
    }

    public void setRng(int rng) {
        this.rng = rng;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getArm() {
        return arm;
    }

    public void setArm(int arm) {
        this.arm = arm;
    }

    public Weapon[] getWeapon() {
        return wp;
    }

    public void addWeapon(Weapon weapon) {
        Weapon[] wep = new Weapon[wp.length + 1];
        for (int i = 0; i < wp.length; i++) {
            wep[i] = wp[i];
        }
        wep[wp.length] = weapon;
        this.wp = new Weapon[wep.length];
        this.wp = wep;
    }
    
    public void removeWeapon(Weapon weapon){
        for (int i = 0; i < wp.length; i++) {
            if(wp[i].name.equals(weapon.name)){
                wp[i] = null;
            }
        }
        Weapon[] wep = new Weapon[wp.length - 1];
        int c = 0;
        for (int i = 0; i < wp.length; i++) {
            if(wp != null){
                wep[c] = wp[i];
                c++;
            }
        }
    }
}
