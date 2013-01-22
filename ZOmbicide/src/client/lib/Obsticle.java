package client.lib;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Obsticle {

    private int xPos;
    private int yPos;
    private int id;
    private boolean inSight;
    private boolean walkable;
    private Polygon p;
    public Obsticle(int x, int y, int id, boolean inSight, boolean walkable) {
        super();
        this.xPos = x;
        this.yPos = y;
        this.id = id;
        this.inSight = inSight;
        this.walkable = walkable;
        this.p = new Polygon();
        this.p.xpoints = new int[]{x, x+Constants.SIZE, x+Constants.SIZE, x};
        this.p.ypoints = new int[]{y, y, y+Constants.SIZE, y+Constants.SIZE};
        this.p.npoints = 4;
    }
    public int getX() {
        return xPos;
    }
    public void setX(int x) {
        this.xPos = x;
    }
    public int getY() {
        return yPos;
    }
    public void setY(int y) {
        this.yPos = y;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isInSight() {
        return inSight;
    }
    public void setInSight(boolean inSight) {
        this.inSight = inSight;
    }
    
    public void scrollElementLeft(int speed){
        this.xPos += speed;
        this.inSight = checkVisible();
        this.p.xpoints[0] = xPos;
        this.p.xpoints[1] = xPos+Constants.SIZE;
        this.p.xpoints[2] = xPos+Constants.SIZE;
        this.p.xpoints[3] = xPos;
    }
    
    public void scrollElementRight(int speed){
        this.xPos -= speed;
        this.inSight = checkVisible();
        this.p.xpoints[0] = xPos;
        this.p.xpoints[1] = xPos+Constants.SIZE;
        this.p.xpoints[2] = xPos+Constants.SIZE;
        this.p.xpoints[3] = xPos;
    }
    
    public void scrollElementUp(int speed){
        this.yPos += speed;
        this.inSight = checkVisible();
        this.p.ypoints[0] = yPos;
        this.p.ypoints[1] = yPos;
        this.p.ypoints[2] = yPos+Constants.SIZE;
        this.p.ypoints[3] = yPos+Constants.SIZE;
    }
    
    public void scrollElementDown(int speed){
        this.yPos -= speed;
        this.inSight = checkVisible();
        this.p.ypoints[0] = yPos;
        this.p.ypoints[1] = yPos;
        this.p.ypoints[2] = yPos+Constants.SIZE;
        this.p.ypoints[3] = yPos+Constants.SIZE;
    }
    
    public void paintElement(Graphics g){
        if(inSight){
//            g.drawImage(Constants.OBSTICLES.get(new Integer(this.getId())), xPos, yPos, Constants.SIZE, Constants.SIZE, null);
            if(isWalkable()){
                g.setColor(Color.YELLOW);
                g.fillRect(xPos, yPos, Constants.SIZE, Constants.SIZE);
            }else{
                g.setColor(Color.BLACK);
                g.fillRect(xPos, yPos, Constants.SIZE, Constants.SIZE);
            }
        }
    }
    
    private boolean checkVisible(){
        if(xPos <= Constants.WIDTH && yPos <= Constants.HEIGTH){
            return true;
        }
        return false;
    }
    
    public boolean isWalkable(){
        return this.walkable;
    }
    public void setWalkable(boolean b) {
        this.walkable = b;
    }
    
    public Polygon getPolygon(){
        return this.p;
    }
}
