package client.lib;

import java.awt.Polygon;

public class Player {

    private int hp;
    private int speed;
    private int x;
    private int y;
    private Polygon p;
    
    public Player(int hp, int speed){
        this.hp = hp;
        this.speed = speed;
        this.x = 300;
        this.y = 400;
        this.p = new Polygon();
        this.p.xpoints = new int[]{x, x+Constants.SIZE, x+Constants.SIZE, x};
        this.p.ypoints = new int[]{y, y, y+Constants.SIZE, y+Constants.SIZE};
        this.p.npoints = 4;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.p.xpoints[0] = x;
        this.p.xpoints[1] = x+Constants.SIZE;
        this.p.xpoints[2] = x+Constants.SIZE;
        this.p.xpoints[3] = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.p.ypoints[0] = y;
        this.p.ypoints[1] = y;
        this.p.ypoints[2] = y+Constants.SIZE;
        this.p.ypoints[3] = y+Constants.SIZE;
    }    
    
    public Polygon getPolygon(){
        return this.p;
    }
}
