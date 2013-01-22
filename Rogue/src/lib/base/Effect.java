package lib.base;

import lib.Battleturn;

public abstract class Effect {

    protected String name;
    protected int duration;
    protected String description;
    public Effect(String name, int duration, String description) {
        super();
        this.name = name;
        this.duration = duration;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean durationEnds(){
        this.duration--;
        if(this.duration <= 0){
            return true;
        }
        return false;
    }
    
    public abstract Battleturn affect(Unit a, Unit b, Unit aDummy, Unit bDummy);
}
