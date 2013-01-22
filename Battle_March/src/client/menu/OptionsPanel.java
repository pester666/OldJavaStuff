package client.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {

    public static boolean isVisible = false;
    public static boolean isNameChanging = false;
    
    Polygon polyChangeName = null;
    //TODO
    //polyChangeServerPort
    Polygon polyBack = null;
    boolean overChangeName = false;
    boolean overBack = false;
    public String actName = null;
    
    ClassLoader cl = OptionsPanel.class.getClassLoader();
    
    public OptionsPanel(){
        this.setName("Options");
        this.setDoubleBuffered(true);
        init();
        this.setOpaque(false);
        this.setVisible(false);
        actName = "Spieler";
        
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
            
        polyChangeName = new Polygon(xCoords, yCoords, 8);
                
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
            g.drawImage(getImage("images/symbols/menu/options/label_header.png"), 20, 190, null);
            g.drawImage(getImage("images/symbols/menu/options/field_name.png"), 265, 240, null);
            g.drawImage(getImage("images/symbols/menu/options/label_pole.png"), 220, 250, null);

            if(!overChangeName){
                g.drawImage(getImage("images/symbols/menu/options/button_change_unmarked.png"), 50, 240, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/options/button_change_marked.png"), 50, 240, null);
            }
                        
            if(isNameChanging){
                Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);
                g.setFont(f);
                g.setColor(Color.GREEN);
                g.drawString(actName, 285, 268);
            }else{
                Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);
                g.setFont(f);
                g.setColor(Color.YELLOW);
                g.drawString(actName, 285, 268);
            }
            
            if(!overBack){
                g.drawImage(getImage("images/symbols/menu/options/button_back_unmarked.png"), 50, 440, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/options/button_back_marked.png"), 50, 440, null);
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
                if(polyChangeName.contains(e.getPoint())){
                    isNameChanging = true;
                    repaint();
                }else
                if(polyBack.contains(e.getPoint())){
                      setOpaque(false);
                      setVisible(false);
                      isVisible = false;
                      isNameChanging = false;
                      actName = TileScreenFrame.myself.getName();
                      MainMenuPanel.isVisible = true;
                } 
            }
        }
    };
    
    private MouseMotionListener motionListener = new MouseMotionListener() {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            overChangeName = false;
            overBack = false;
            if(polyChangeName.contains(e.getPoint())){
                overChangeName = true;
                overBack = false;
            }else
            if(polyBack.contains(e.getPoint())){
                overChangeName = false;
                overBack = true;
            }
            repaint();
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            
        }
    };
    
    public void updatePlayername(){
        this.repaint();
    }
    
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
