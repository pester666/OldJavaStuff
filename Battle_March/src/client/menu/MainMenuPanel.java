package client.menu;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import lib.Constants;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {
    
    Polygon polySp = null;
    Polygon polyMp = null;
    Polygon polyOptions = null;
    Polygon polyExit = null;
    boolean overSP = false;
    boolean overMP = false;
    boolean overOptions = false;
    boolean overExit = false;
    static boolean isVisible = true;
    public SingleplayerPanel pnlSingle = new SingleplayerPanel();
    public MultiplayerPanel pnlMulti = new MultiplayerPanel();   
    public OptionsPanel pnlOpti = new OptionsPanel();
    
//    ClassLoader cl = MainMenuPanel.class.getClassLoader();

    public MainMenuPanel(){
        this.setDoubleBuffered(true);
        init();
        this.setName("Main");
        this.addMouseMotionListener(motionListener);        
        this.addMouseListener(mouseListener);
        this.setLayout(new BorderLayout());
    }
    
    private void makeInvisible(){
        isVisible = false;
    }
    
    private void init(){
        int[] xCoords = new int[] { 50, 60, 210, 220, 220, 210, 60, 50 };

        int[] yCoords = new int[] { 250, 240, 240, 250, 270, 280, 280, 270 };

        polySp = new Polygon(xCoords, yCoords, 8);

        xCoords = new int[] { 50, 60, 210, 220, 220, 210, 60, 50 };
        
        yCoords = new int[] { 300, 290, 290, 300, 320, 330, 330, 320 };
        
        polyMp = new Polygon(xCoords, yCoords, 8);

        xCoords = new int[] { 50, 60, 210, 220, 220, 210, 60, 50 };
        
        yCoords = new int[] { 350, 340, 340, 350, 370, 380, 380, 370 };
        
        polyOptions = new Polygon(xCoords, yCoords, 8);

        xCoords = new int[] { 50, 60, 210, 220, 220, 210, 60, 50 };
        
        yCoords = new int[] { 450, 440, 440, 450, 470, 480, 480, 470 };
        
        polyExit = new Polygon(xCoords, yCoords, 8);
        
    }
    
    public void paintComponent(Graphics g){
        g.drawImage(getImage("images/symbols/menu/background.png"), 0, 0, null);
        g.drawImage(getImage("images/symbols/menu/label_header.png"), 20, 190, null);
        if (!overSP) {
            g.drawImage(getImage("images/symbols/menu/main/button_sp_unmarked.png"), 50, 240, null);
        } else {
            g.drawImage(getImage("images/symbols/menu/main/button_sp_marked.png"), 50, 240, null);
        }
        if (!overMP) {
            g.drawImage(getImage("images/symbols/menu/main/button_mp_unmarked.png"), 50, 290, null);
        } else {
            g.drawImage(getImage("images/symbols/menu/main/button_mp_marked.png"), 50, 290, null);
        }
        if (!overOptions) {
            g.drawImage(getImage("images/symbols/menu/main/button_options_unmarked.png"), 50, 340, null);
        } else {
            g.drawImage(getImage("images/symbols/menu/main/button_options_marked.png"), 50, 340, null);
        }
        if (!overExit) {
            g.drawImage(getImage("images/symbols/menu/main/button_exit_unmarked.png"), 50, 440, null);
        } else {
            g.drawImage(getImage("images/symbols/menu/main/button_exit_marked.png"), 50, 440, null);
        }
    }
    
    private MouseMotionListener motionListener = new MouseMotionListener(){

        @Override
        public void mouseMoved(MouseEvent e) {
//            if(isVisible){
                overSP = false;
                overMP = false;
                overOptions = false;
                overExit = false;
                if(polySp.contains(e.getPoint())){
                    overSP = true;
                    overMP = false;
                    overOptions = false;
                    overExit = false;
                }else
                if(polyMp.contains(e.getPoint())){
                    overSP = false;
                    overMP = true;
                    overOptions = false;
                    overExit = false;
                }else
                if(polyOptions.contains(e.getPoint())){
                    overSP = false;
                    overMP = false;
                    overOptions = true;
                    overExit = false;
                }else
                if(polyExit.contains(e.getPoint())){
                    overSP = false;
                    overMP = false;
                    overOptions = false;
                    overExit = true;
                }
                repaint();
//            }
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            
        }
    };
    
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
                if(polySp.contains(e.getPoint())){
                    makeInvisible();
                    changePanel(Constants.MENU_SINGEPLAYER);
                    SingleplayerPanel.isVisible = true;
                    pnlSingle.setVisible(true);
                    pnlSingle.setOpaque(true);
                }else
                if(polyMp.contains(e.getPoint())){
                    changePanel(Constants.MENU_MULTIPLAYER); 
                    makeInvisible();
                    MultiplayerPanel.isVisible = true;
                    pnlMulti.setVisible(true);
                    pnlMulti.setOpaque(true);
                }else
                if(polyOptions.contains(e.getPoint())){
                    changePanel(Constants.MENU_OPTIONS); 
                    makeInvisible();
                    OptionsPanel.isVisible = true;
                    pnlOpti.setVisible(true);
                    pnlOpti.setOpaque(true);
                }else
                if(polyExit.contains(e.getPoint())){
                      System.exit(1);      
                } 
            }
        }
    };
    
    private void changePanel(int panel){
        if(panel == Constants.MENU_SINGEPLAYER){
            this.remove(pnlMulti);
            this.remove(pnlOpti);
            this.add(pnlSingle, BorderLayout.CENTER);
        }else if(panel == Constants.MENU_MULTIPLAYER){
            this.remove(pnlSingle);
            this.remove(pnlOpti);
            this.add(pnlMulti, BorderLayout.CENTER);
        }else if(panel == Constants.MENU_OPTIONS){
            this.remove(pnlSingle);
            this.remove(pnlMulti);
            this.add(pnlOpti, BorderLayout.CENTER);
        }
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
