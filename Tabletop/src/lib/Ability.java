package lib;

public class Ability {

    protected String name = "";
    protected int costs = 0;
    protected int rng = 0;
    protected int str = 0;
    protected boolean up = false;
       
    public Ability(String name, int costs, int rng, int str, boolean up) {
        super();
        this.name = name;
        this.costs = costs;
        this.rng = rng;
        this.str = str;
        this.up = up;
    }
    
    public String getName() {
        return name;
    }
    public int getCosts() {
        return costs;
    }
    public int getRng() {
        return rng;
    }
    public int getStr() {
        return str;
    }
    public boolean isUp() {
        return up;
    }
}
