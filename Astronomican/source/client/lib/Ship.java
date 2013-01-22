package client.lib;

import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Ship {
    
    public int x;
    public int y;
    public int speed;
    public int anim = 0;
    public boolean right;
    public boolean left;
    public boolean down;
    public boolean up;
    public Polygon p;
    private List<Laser> laser;
    
    public Ship (int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        laser = new ArrayList<Laser>();
        int[] xp = new int[]{
                50, 142, 142, 50
        };
        int[] yp = new int[]{
                50, 50, 129, 129 
        };
        p = new Polygon(xp, yp, 4);
    }
    
    public void moveShip(){
        if(this.left){
            this.x = this.x + this.speed*-1;
            for (int i = 0; i < this.p.xpoints.length; i++) {
                this.p.xpoints[i] -= this.speed;
            }
        }else if(this.right){
            this.x = this.x + this.speed;
            for (int i = 0; i < this.p.xpoints.length; i++) {
                this.p.xpoints[i] += this.speed;
            }
        }
        if(this.up){
            this.y = this.y + this.speed*-1;
            for (int i = 0; i < this.p.ypoints.length; i++) {
                this.p.ypoints[i] -= this.speed;
            }
        }else if(this.down){
            this.y = this.y + this.speed;
            for (int i = 0; i < this.p.ypoints.length; i++) {
                this.p.ypoints[i] += this.speed;
            }
        }
    }
    
    public void makeMovingLeft(boolean moving){
        this.left = moving;
    }
    public void makeMovingRight(boolean moving){
        this.right = moving;
    }
    public void makeMovingUp(boolean moving){
        this.up = moving;
    }
    public void makeMovingDown(boolean moving){
        this.down = moving;
    }
    
    public List<Laser> getLaser(){
        return this.laser;
    }
    
    public void shoot(){
        laser.add(new Laser(this.x+51, this.y+6, 10, 1, Color.GREEN));
        laser.add(new Laser(this.x+51, this.y+70, 10, 1, Color.GREEN));
    }
}
