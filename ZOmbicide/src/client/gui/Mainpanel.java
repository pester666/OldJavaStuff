package client.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import client.lib.Constants;
import client.lib.Map;
import client.lib.Player;

public class Mainpanel extends JPanel implements Runnable{

    private boolean left;
    private boolean right;
    private boolean down;
    private boolean up;
    private Player p;
    private Map m;
    
    public Mainpanel(){
        p = new Player(3, 5);
        m = new Map(Constants.WIDTH, Constants.HEIGTH);
    }
    
    public void paintComponent(Graphics g){
        if(left){
            p.setX(p.getX()-p.getSpeed());
            if(checkHorizontal(true)){
                p.setX(p.getX()+p.getSpeed());
            }
        }else if(right){
            p.setX(p.getX()+p.getSpeed());
            if(checkHorizontal(false)){
                p.setX(p.getX()-p.getSpeed());
            }
        }
        if(up){
            p.setY(p.getY()-p.getSpeed());
            if(checkVertical(true)){
                p.setY(p.getY()+p.getSpeed());
            }
        }else if(down){
            p.setY(p.getY()+p.getSpeed());
            if(checkVertical(false)){
                p.setY(p.getY()-p.getSpeed());
            }
        }
        if(p.getX() < Constants.SCROLL_DISTANCE){
            p.setX(Constants.SCROLL_DISTANCE);
            m.scrollLevelLeft(p.getSpeed());
        }else if(p.getX() > Constants.WIDTH-Constants.SCROLL_DISTANCE){
            p.setX(Constants.WIDTH-Constants.SCROLL_DISTANCE);
            m.scrollLevelRight(p.getSpeed());
        }
        if(p.getY() < Constants.SCROLL_DISTANCE){ 
            p.setY(Constants.SCROLL_DISTANCE);
            m.scrollLevelUp(p.getSpeed());
        }else if(p.getY() > Constants.HEIGTH - Constants.SCROLL_DISTANCE){
            p.setY(Constants.HEIGTH-Constants.SCROLL_DISTANCE);
            m.scrollLevelDown(p.getSpeed());
        }
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGTH);
        for (int i = 0; i < m.getFields().length; i++) {
            for (int j = 0; j < m.getFields()[i].length; j++) {
                m.getFields()[i][j].paintElement(g);
            }
        }
        g.setColor(Color.BLACK);
        g.drawString("Time left: " + Constants.TIMELEFT, 0, 10);
        g.drawImage(Constants.IMG_PLAYER, p.getX(), p.getY(), Constants.SIZE, Constants.SIZE, null);
    }

    @Override
    public void run() {
        while(true){
            this.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    } 
    
    private boolean checkHorizontal(boolean left){
        for (int i = 0; i < m.getFields().length; i++) {
            for (int j = 0; j < m.getFields()[i].length; j++) {
                if(left){
                    if(! m.getFields()[i][j].isWalkable() && (m.getFields()[i][j].getPolygon().contains(new Point(p.getX(), p.getY()))
                             || m.getFields()[i][j].getPolygon().contains(new Point(p.getX(), p.getY()+Constants.SIZE)))){
                        System.out.println("lefte");
                        return true;
                    }
                }else{
                    if(! m.getFields()[i][j].isWalkable() && (m.getFields()[i][j].getPolygon().contains(new Point(p.getX()+Constants.SIZE, p.getY()))
                            || m.getFields()[i][j].getPolygon().contains(new Point(p.getX()+Constants.SIZE, p.getY()+Constants.SIZE)))){
                        System.out.println("righte");
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean checkVertical(boolean up){
        for (int i = 0; i < m.getFields().length; i++) {
            for (int j = 0; j < m.getFields()[i].length; j++) {
                if(up){
                    if(! m.getFields()[i][j].isWalkable() && (m.getFields()[i][j].getPolygon().contains(new Point(p.getX(), p.getY()))
                            || m.getFields()[i][j].getPolygon().contains(new Point(p.getX()+Constants.SIZE, p.getY())))){
                        System.out.println("upe");
                        return true;
                    }
                }else{
                    if(! m.getFields()[i][j].isWalkable() && (m.getFields()[i][j].getPolygon().contains(new Point(p.getX(), p.getY()+Constants.SIZE))
                            || m.getFields()[i][j].getPolygon().contains(new Point(p.getX()+Constants.SIZE, p.getY()+Constants.SIZE)))){
                        System.out.println("downe");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
