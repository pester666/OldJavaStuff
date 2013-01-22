package lib;

import java.io.Serializable;

public class Warband implements Serializable{

    protected int goldCrowns;
    protected int rating;
    protected int shards;
    protected int treasure;
    protected Unit[] units;
    protected String name;
    protected int maxUnits;
    protected int race;
    
    protected String heraldik;
    
    public Warband(String name, int goldCrowns, Hero hero, String heraldik){
        this.units = new Unit[]{hero};
        this.name = name;
        this.goldCrowns = goldCrowns;
        this.heraldik = heraldik;
    }

    public String getHeraldik() {
        return heraldik;
    }
//
    public void setHeraldik(String heraldik) {
        this.heraldik = heraldik;
    }

    public int getGoldCrowns() {
        return goldCrowns;
    }

    public void setGoldCrowns(int goldCrowns) {
        this.goldCrowns = goldCrowns;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getShards() {
        return shards;
    }

    public void setShards(int shards) {
        this.shards = shards;
    }

    public int getTreasure() {
        return treasure;
    }

    public void setTreasure(int treasure) {
        this.treasure = treasure;
    }

    public Unit[] getUnits() {
        return units;
    }

    public void setUnits(Unit[] units) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addUnit(Unit unit) {
        Unit[] os = new Unit[this.units.length+1];
        for (int i = 0; i < this.units.length; i++) {
            os[i] = this.units[i];
        }
        os[this.units.length] = unit;
        this.units = new Unit[os.length];
        this.units = os;
    }
    
    public void removeUnit(Unit unit) {
        //TODO auf ID filtern
        for (int i = 0; i < this.units.length; i++) {
            if (this.units[i].getName().equals(unit.getName()) && this.units[i].getType().equals(unit.getType())) {
                this.units[i] = null;
            }
        }
        Unit[] newUnits = new Unit[this.units.length - 1];
        int counter = 0;
        for (int i = 0; i < this.units.length; i++) {
            if (this.units[i] != null) {
                newUnits[i] = this.units[counter];
            }
            counter++;
        }
        this.units = new Unit[newUnits.length];
        this.units = newUnits;
    }

    public int getMaxUnits() {
        return maxUnits;
    }

    public void setMaxUnits(int maxUnits) {
        this.maxUnits = maxUnits;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }
}
