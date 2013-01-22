package client.lib.basics;

public class ShipComponent {
//likes shields or turbines
    
    protected String name;
    protected int animCounter;
    protected int x;
    protected int y;
    protected int roomId;
    protected int maxEnergy;
    protected int actEnergy;
        
    public ShipComponent(String name, int x, int y, int roomId) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.roomId = roomId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAnimCounter() {
        return animCounter;
    }
    public void setAnimCounter(int animCounter) {
        this.animCounter = animCounter;
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
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public int getMaxEnergy() {
        return maxEnergy;
    }
    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }
    public int getActEnergy() {
        return actEnergy;
    }
    public void setActEnergy(int actEnergy) {
        this.actEnergy = actEnergy;
    }    
    
    
}
