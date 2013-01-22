package lib;

public class Weapon {

    protected String name = "";
    protected int str = 0;
    protected int rng = 0;
    protected int rof = 0;
    protected int aoe = 0;
    protected boolean isThrown = false;
    
    public Weapon(String name, int str, int rng, int rof, int aoe, boolean isThrown) {
        super();
        this.name = name;
        this.str = str;
        this.rng = rng;
        this.rof = rof;
        this.aoe = aoe;
        this.isThrown = isThrown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getRng() {
        return rng;
    }

    public void setRng(int rng) {
        this.rng = rng;
    }

    public int getRof() {
        return rof;
    }

    public void setRof(int rof) {
        this.rof = rof;
    }

    public int getAoe() {
        return aoe;
    }

    public void setAoe(int aoe) {
        this.aoe = aoe;
    }

    public boolean isThrown() {
        return isThrown;
    }

    public void setThrown(boolean isThrown) {
        this.isThrown = isThrown;
    }
}
