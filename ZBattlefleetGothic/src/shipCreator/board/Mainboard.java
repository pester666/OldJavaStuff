package shipCreator.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import client.lib.basics.Tile;

public class Mainboard extends JPanel {

    private Image ship;
    private Tile tile;
    private int x;
    private int y;
    
    public Mainboard(){
        this.setBackground(Color.BLACK);
        this.setSize(800, 700);
        this.setPreferredSize(new Dimension(800, 700));
        this.addMouseMotionListener(new Listen());
        this.setDoubleBuffered(true);
    }

    public Image getShip() {
        return ship;
    }

    public void setShip(Image ship) {
        this.ship = ship;
    }
    
    public void paintComponent(Graphics g){
        g.fillRect(0, 0, 800, 700);
        if(ship != null){
            g.drawImage(ship, 0, 0, null);    
        }
        if(tile != null){
            if(x < 800 || x > 0 || y < 700 || y > 0){
                g.drawImage(new ImageIcon("res/img/room/tile.png").getImage(), x, y, null);             
            }
        }
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
    
    private class Listen implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }
    }
}
