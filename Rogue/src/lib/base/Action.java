package lib.base;

import lib.Battleturn;

public abstract class Action {

    protected String name;
    protected String description;
    
    public Action(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract Battleturn action(Unit a, Unit b, Unit dummyA, Unit dummyB);    
    
}
