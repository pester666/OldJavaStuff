package lib;

public class Warlord {

    private String name = "";
    private int hp = 0;
    private Weapon[] wp = null;
    private Unit[] units = null;
    private Ability ab = null;
    private int melee = 0;
    private int def = 0;
    private int rng = 0;
    private int str = 0;
    private int arm = 0;
    private int speed = 0;
    private int command = 0;
    
    public Warlord(String name){
        this.name = name;
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

    public Weapon[] getWp() {
        return wp;
    }

    public void setWp(Weapon[] wp) {
        this.wp = wp;
    }

    public Unit[] getUnits() {
        return units;
    }

    public void setUnits(Unit[] units) {
        this.units = units;
    }

    public Ability getAb() {
        return ab;
    }

    public void setAb(Ability ab) {
        this.ab = ab;
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
}
