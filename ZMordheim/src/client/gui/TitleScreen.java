package client.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

public class TitleScreen extends MenuPanel {

    private Image screenClosed1 = new ImageIcon("res/img/title/screenClosed1.png").getImage();
    private Image screenClosed2 = new ImageIcon("res/img/title/screenClosed2.png").getImage();
    private Image screenClosed3 = new ImageIcon("res/img/title/screenClosed3.png").getImage();
    private Image screenOpen1 = new ImageIcon("res/img/title/screenOpen1.png").getImage();
    private Image screenOpen2 = new ImageIcon("res/img/title/screenOpen2.png").getImage();
    private Image screenOpen3 = new ImageIcon("res/img/title/screenOpen3.png").getImage();
    private int animCounter = 1;
    private boolean doorOpen = false;
    private Polygon polyDoor;
    private Thread t;
    public static boolean isActive = true;
    private int panelIdToActivate = PanelIds.TITLE;
    
    public TitleScreen(boolean active){
        this.setSize(500, 500);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        t = new Thread(anim);
        t.start();
        int[] x = new int[]{
              260, 440, 440, 260  
        };
        
        int[] y = new int[]{
                210, 210, 450, 450
        };
        
        polyDoor = new Polygon(x, y, 4);
        
        this.addMouseMotionListener(new MouseMotionListener(){

            @Override
            public void mouseDragged(MouseEvent e) {
                
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (isActive) {
                    if (polyDoor.contains(e.getPoint())) {
                        doorOpen = true;
                    } else {
                        doorOpen = false;
                    }
                }
            }
            
        });
        
        this.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                if(isActive){
                    if(polyDoor.contains(e.getPoint())){
                        Tavern.isActive = true;
                        panelIdToActivate = 2;
                        setActive(false);
                    }                    
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
    }
    
    private Runnable anim = new Runnable(){

        @Override
        public void run() {
            while(isActive){
                animate();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    };
    
    public void setActive(boolean active){
        this.setVisible(active);
        this.setOpaque(active);
        isActive = active;
        if(active && !t.isAlive()){
            this.t.start();
        }
    }
    
    private void animate(){
        if(animCounter == 3){
            animCounter = 0;
        }
        animCounter++;
        this.repaint();
    }
    
    public void paintComponent(Graphics g){
        if(isActive){
            if(doorOpen){
                if(animCounter == 1){
                    g.drawImage(screenOpen1, 0, 0, null);
                }else if(animCounter == 2){
                    g.drawImage(screenOpen2, 0, 0, null);
                }else if(animCounter == 3){
                    g.drawImage(screenOpen3, 0, 0, null);
                }
            }else{
                if(animCounter == 1){
                    g.drawImage(screenClosed1, 0, 0, null);
                }else if(animCounter == 2){
                    g.drawImage(screenClosed2, 0, 0, null);
                }else if(animCounter == 3){
                    g.drawImage(screenClosed3, 0, 0, null);
                }
            } 
        }
    }
    
    @Override
    public int getPanelToActive() {
        return panelIdToActivate;
    }

    @Override
    public int getPanelId() {
        return PanelIds.TITLE;
    }
}
