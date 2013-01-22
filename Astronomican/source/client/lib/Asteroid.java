package client.lib;

import java.awt.Polygon;

public class Asteroid {
    
    public int x;
    public int y;
    public int size;
    public int speed;
    public int hp;
    public Polygon p;
    
    public Asteroid(int x, int y, int size, int speed, int hp) {
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.hp = hp;
        int[] xp = new int[]{
                x, x+size, x+size, x
        };
        int[] yp = new int[]{
                y, y, y+size, y+size
        };
        p = new Polygon(xp, yp, 4);
    }
}