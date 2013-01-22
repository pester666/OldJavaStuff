package client.lib;

import java.awt.Color;
import java.awt.Polygon;

public class Laser {
    
    public int x;
    public int y;
    public int speed;
    public Color color;
    public Polygon p;
    public int strength;
    
    public Laser(int x, int y, int speed, int strength, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.strength = strength;
        this.speed = speed;
        this.color = color;
        int[] xp = new int[]{
                x, x+19, x+19, x
        };
        int[] yp = new int[]{
                y, y, y+2, y+2
        };
        p = new Polygon(xp, yp, 4);
    }
}
