package game.lib;


public class ActualTile {

    private int id = 0;
    private boolean walkable = true;
    public ActualTile(int id, boolean walkable) {
        super();
        this.id = id;
        this.walkable = walkable;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isWalkable() {
        return walkable;
    }
    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }
}
