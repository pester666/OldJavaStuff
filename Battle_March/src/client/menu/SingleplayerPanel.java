package client.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import client.Main;
import lib.Constants;
import lib.Player;

@SuppressWarnings("serial")
public class SingleplayerPanel extends JPanel {

    Polygon polyStart = null;
    Polygon polyChangeTime = null;
    Polygon polyChangePoints = null;
    Polygon polyBack = null;
    boolean overStart = false;
    boolean overChangeTime = false;
    boolean overChangePoints = false;
    boolean overBack = false;
    static boolean isVisible = false;
    
    ClassLoader cl = SingleplayerPanel.class.getClassLoader();
    
    private int[] points = new int[]{
      500, 1000, 2000, 3000      
    };
    private int actPoints = 500;
    
    private int[] ages = new int[]{
      Constants.FANTASY, Constants.SCIFI, Constants.WW2      
    };
    private int actAge = Constants.FANTASY;
    
    public SingleplayerPanel(){
        this.setName("Singleplayer");
        this.setDoubleBuffered(true);
        init();
        this.setOpaque(false);
        this.setVisible(false);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(motionListener);
    }
    
    private void init(){
        int[] xCoords = new int[]{
                50, 60, 210, 220, 220, 210, 60, 50
            };
            int[] yCoords = new int[]{
                    250, 240, 240, 250, 270, 280, 280, 270
            };
        polyStart = new Polygon(xCoords, yCoords, 8);
                
        xCoords = new int[]{
                50, 60, 210, 220, 220, 210, 60, 50
            };
        yCoords = new int[]{
                    300, 290, 290, 300, 320, 330, 330, 320
            };
        polyChangeTime = new Polygon(xCoords, yCoords, 8);
                
        xCoords = new int[]{
                50, 60, 210, 220, 220, 210, 60, 50
            };
        yCoords = new int[]{
                    350, 340, 340, 350, 370, 380, 380, 370
            };
        polyChangePoints = new Polygon(xCoords, yCoords, 8);
                
        xCoords = new int[]{
                50, 60, 210, 220, 220, 210, 60, 50
            };
        yCoords = new int[]{
                    450, 440, 440, 450, 470, 480, 480, 470
            };
        polyBack = new Polygon(xCoords, yCoords, 8);
    }
    
    public void paintComponent(Graphics g){
        if(isVisible){
            g.drawImage(getImage("images/symbols/menu/background.png"), 0, 0, null);
            g.drawImage(getImage("images/symbols/menu/single/label_header.png"), 20, 190, null);
            if(!overStart){
                g.drawImage(getImage("images/symbols/menu/single/button_start_unmarked.png"), 50, 240, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/single/button_start_marked.png"), 50, 240, null);
            }
            
            if(!overChangeTime){
                g.drawImage(getImage("images/symbols/menu/single/button_time_unmarked.png"), 50, 290, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/single/button_time_marked.png"), 50, 290, null);
            }
            
            g.drawImage(getImage("images/symbols/menu/single/label_age_" + actAge + ".png"), 240, 290, null);
            
            if(!overChangePoints){
                g.drawImage(getImage("images/symbols/menu/single/button_points_unmarked.png"), 50, 340, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/single/button_points_marked.png"), 50, 340, null);
            }
            
            g.drawImage(getImage("images/symbols/menu/single/label_" + actPoints + ".png"), 240, 340, null);
            
            if(!overBack){
                g.drawImage(getImage("images/symbols/menu/single/button_back_unmarked.png"), 50, 440, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/single/button_back_marked.png"), 50, 440, null);
            }
        }
    }   
    
    private MouseListener mouseListener = new MouseListener(){

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if(isVisible){
                if(polyStart.contains(e.getPoint())){
                    Main.launchSingleplayer(actPoints, actAge);
                    Player[] players = new Player[2];
                    for (int i = 0; i < players.length; i++) {
                        players[i] = new Player(null, "Open", 0, Constants.RACE_ORKS, false);
                    }
//                    Main.launchMultiplayer(players, actAge, true, null);
                }else
                if(polyChangeTime.contains(e.getPoint())){
                    for (int i = 0; i < ages.length; i++) {
                        if(actAge == ages[i]){
                            if(i+1 == ages.length){
                                actAge = ages[0];
                            }else{
                                actAge = ages[i+1];
                                break;
                            }
                        }
                    }
                    repaint();
                }else
                if(polyChangePoints.contains(e.getPoint())){
                    for (int i = 0; i < points.length; i++) {
                        if(actPoints == points[i]){
                            if(i+1 == points.length){
                                actPoints = points[0];
                            }else{
                                actPoints = points[i+1];
                                break;
                            }
                        }
                    }
                    repaint();
                }else
                if(polyBack.contains(e.getPoint())){
                      setOpaque(false);
                      setVisible(false);
                      isVisible = false;
                      MainMenuPanel.isVisible = true;
                } 
            }
        }
    };

    private MouseMotionListener motionListener = new MouseMotionListener(){
        
        @Override
        public void mouseMoved(MouseEvent e) {
            overStart = false;
            overChangeTime = false;
            overChangePoints = false;
            overBack = false;
            if(polyStart.contains(e.getPoint())){
                overStart = true;
                overChangeTime = false;
                overChangePoints = false;
                overBack = false;
            }else
            if(polyChangeTime.contains(e.getPoint())){
                overStart = false;
                overChangeTime = true;
                overChangePoints = false;
                overBack = false;
            }else
            if(polyChangePoints.contains(e.getPoint())){
                overStart = false;
                overChangeTime = false;
                overChangePoints = true;
                overBack = false;
            }else
            if(polyBack.contains(e.getPoint())){
                overStart = false;
                overChangeTime = false;
                overChangePoints = false;
                overBack = true;
            }
            repaint();
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            
        }        
    };
    
    private Image getImage(String path){
        URL url = null;
        url = this.getClass().getClassLoader().getResource(path);
        if(url != null){
            return new ImageIcon(url).getImage();
        }else{
            return new ImageIcon(path).getImage();
        }
    }
}
