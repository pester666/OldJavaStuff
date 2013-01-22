package lib;

public class Environment {

    public String name = null;
    public int cover = 0;
    public boolean walkable = true;
    public boolean los = true;
    public boolean isStructure = false;
    
    public Environment(){
        
    }
    
    /**
     * 
     * @param name
     * @param cover
     * @param walkable
     * @param los
     */
    public Environment(String name, int cover, boolean walkable, boolean los, boolean isStructure) {
        this.name = name;
        this.cover = cover;
        this.walkable = walkable;
        this.los = los;
        this.isStructure = isStructure;
    }
}
