package client.lib;

import java.awt.Polygon;

public class Button {

    private Polygon poly;
    private int x;
    private int y;
    private String name;
    private boolean active;

    public Button(int x, int y, String name) {
        super();
        this.poly = new Polygon(new int[]{x, x+150, x+150, x}, new int[]{y, y, y+50, y+50}, 4);
        this.x = x;
        this.y = y;
        this.name = name;
    }
        
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Polygon getPoly() {
        return poly;
    }
    public void setPoly(Polygon poly) {
        this.poly = poly;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
