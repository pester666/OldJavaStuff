package lib;

import java.io.Serializable;

public class Equipment implements Serializable{

    protected String name;
    
    protected int cost;

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Equipment(String name, int cost) {
        super();
        this.name = name;
        this.cost = cost;
    }
}
