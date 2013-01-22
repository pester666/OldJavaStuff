package client.lib.basics;

public class Particle {
    
    protected String name;
    protected int x;
    protected int y;
    protected int id;
    protected int speed;
    public Particle(String name, int x, int y, int id, int speed) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
        this.id = id;
        this.speed = speed;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    
    
}
