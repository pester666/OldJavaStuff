package client.lib.basics;

public class Tile {

    protected int x;
    protected int y;
    protected int id;
    protected boolean isOnFire;
    protected boolean isWalkable;
    
    public Tile(int x, int y, int id){
        this.x = x;
        this.id = id;
        this.y = y;
    }
    
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isOnFire() {
        return isOnFire;
    }
    public void setOnFire(boolean isOnFire) {
        this.isOnFire = isOnFire;
    }
    public boolean isWalkable() {
        return isWalkable;
    }
    public void setWalkable(boolean isWalkable) {
        this.isWalkable = isWalkable;
    }
    
    
}
