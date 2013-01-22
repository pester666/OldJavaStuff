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

import client.multiplayer.JoinPanel;
import client.multiplayer.LobbyPanelHost;
import lib.Constants;

@SuppressWarnings("serial")
public class MultiplayerPanel extends JPanel {
    
    Polygon polyHost = null;
    Polygon polyJoin = null;
    Polygon polyBack = null;
    boolean overHost = false;
    boolean overJoin = false;
    boolean overBack = false;
    public static boolean isVisible = false;
    public LobbyPanelHost pnlLobby = new LobbyPanelHost();
    public JoinPanel pnlJoin = new JoinPanel();
    
    ClassLoader cl = MultiplayerPanel.class.getClassLoader();

    public MultiplayerPanel(){
        this.setName("Multiplayer");
        this.setDoubleBuffered(true);
        init();
        this.setOpaque(false);
        this.setVisible(false);
        this.setLayout(new BorderLayout());
        
        this.addMouseListener(mouseListener);
        
        this.addMouseMotionListener(new MouseMotionListener() {
            
            @Override
            public void mouseMoved(MouseEvent e) {
                overHost = false;
                overJoin = false;
                overBack = false;
                if(polyHost.contains(e.getPoint())){
                    overHost = true;
                    overJoin = false;
                    overBack = false;
                }else
                if(polyJoin.contains(e.getPoint())){
                    overHost = false;
                    overJoin = true;
                    overBack = false;
                }else
                if(polyBack.contains(e.getPoint())){
                    overHost = false;
                    overJoin = false;
                    overBack = true;
                }
                repaint();
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                
            }
        });
    }
    
    private void init(){
        int[] xCoords = new int[]{
                50, 60, 210, 220, 220, 210, 60, 50
            };
            int[] yCoords = new int[]{
                    250, 240, 240, 250, 270, 280, 280, 270
            };
        polyHost = new Polygon(xCoords, yCoords, 8);
                
        xCoords = new int[]{
                50, 60, 210, 220, 220, 210, 60, 50
            };
        yCoords = new int[]{
                    300, 290, 290, 300, 320, 330, 330, 320
            };
        polyJoin = new Polygon(xCoords, yCoords, 8);
                
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
            g.drawImage(getImage("images/symbols/menu/multi/label_header.png"), 20, 190, null);
            if(!overHost){
                g.drawImage(getImage("images/symbols/menu/multi/button_host_unmarked.png"), 50, 240, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/button_host_marked.png"), 50, 240, null);
            }
            if(!overJoin){
                g.drawImage(getImage("images/symbols/menu/multi/button_join_unmarked.png"), 50, 290, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/button_join_marked.png"), 50, 290, null);
            }
            if(!overBack){
                g.drawImage(getImage("images/symbols/menu/multi/button_back_unmarked.png"), 50, 440, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/button_back_marked.png"), 50, 440, null);
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
                if(polyHost.contains(e.getPoint())){
                    makeInvisible();
                    changePanel(Constants.MENU_LOBBY);
                    LobbyPanelHost.isVisible = true;
                    pnlLobby.startServer();
                    pnlLobby.setVisible(true);
                    pnlLobby.setOpaque(true);
                    pnlLobby.updateChat();
                }else
                if(polyJoin.contains(e.getPoint())){
                    makeInvisible();
                    changePanel(Constants.MENU_JOIN);
                    JoinPanel.isVisible = true;
                    pnlJoin.setVisible(true);
                    pnlJoin.setOpaque(true);
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
    
    private void makeInvisible(){
        isVisible = false;
    }
    
    private void changePanel(int panel){
        if(panel == Constants.MENU_LOBBY){
            this.remove(pnlJoin);
            this.add(pnlLobby, BorderLayout.CENTER);
        }else if(panel == Constants.MENU_JOIN){
            this.remove(pnlLobby);
            this.add(pnlJoin, BorderLayout.CENTER);
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
