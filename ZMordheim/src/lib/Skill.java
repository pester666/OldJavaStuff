package lib;

import java.io.Serializable;

public class Skill implements Serializable{

    protected String name;
    protected int id;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Skill(int id, String name){
        this.id = id;
    }
}
