package lib.base;

public class Klasse {

    protected int id;
    protected String name;
    protected int hpMod;
    protected int strMod;
    protected int dexMod;
    protected Action[] actions = new Action[4];
    
    public Klasse(int id, String name, int hpMod, int strMod, int dexMod, Action[] actions) {
        super();
        this.id = id;
        this.name = name;
        this.hpMod = hpMod;
        this.strMod = strMod;
        this.dexMod = dexMod;
        this.actions = actions;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHpMod() {
        return hpMod;
    }
    public void setHpMod(int hpMod) {
        this.hpMod = hpMod;
    }
    public int getStrMod() {
        return strMod;
    }
    public void setStrMod(int strMod) {
        this.strMod = strMod;
    }
    public int getDexMod() {
        return dexMod;
    }
    public void setDexMod(int dexMod) {
        this.dexMod = dexMod;
    }

    public Action[] getActions() {
        return actions;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }
}
