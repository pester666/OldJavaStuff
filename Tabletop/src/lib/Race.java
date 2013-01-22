package lib;

public class Race {

    private int gold = 0;
    private Town[] towns = null;
    private Warlord[] warlords = null;
    private String name = "";
    private String owner = "";
    private Ressource[] ressources = null;
    
    public Race(String owner, String name){
        this.name = name;
        this.owner = owner;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Town[] getTowns() {
        return towns;
    }

    public void addTowns(Town town) {
        Town[] t = new Town[towns.length+1];
        for (int i = 0; i < towns.length; i++) {
            t[i] = towns[i];
        }
        t[towns.length] = town;
        this.towns = new Town[t.length];
        this.towns = t;
    }

    public Warlord[] getWarlords() {
        return warlords;
    }

    public void addWarlords(Warlord warlord) {
        Warlord[] w = new Warlord[warlords.length+1];
        for (int i = 0; i < warlords.length; i++) {
            w[i] = warlords[i];
        }
        w[warlords.length] = warlord;
        this.warlords = new Warlord[w.length];
        this.warlords = w;
    }

    public Ressource[] getRessources() {
        return ressources;
    }

    public void addRessources(Ressource ressource) {
        Ressource[] r = new Ressource[ressources.length+1];
        for (int i = 0; i < ressources.length; i++) {
            r[i] = ressources[i];
        }
        r[ressources.length] = ressource;
        this.ressources = new Ressource[r.length];
        this.ressources = r;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }    
}
