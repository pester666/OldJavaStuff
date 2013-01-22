package client.multiplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import client.menu.MultiplayerPanel;
import client.multiplayer.chat.ChatClientLobby;

@SuppressWarnings("serial")
public class JoinPanel extends JPanel {

    public static boolean isVisible = false;
    
    ClassLoader cl = JoinPanel.class.getClassLoader();
    
    Polygon polyJoin = null;
    Polygon polyBack = null;
    boolean overJoin = false;
    boolean overBack = false;
    public String ipAdress = null;
    public ChatClientLobby chat = null;
    
    public LobbyPanelJoin pnlLobbyJoin = new LobbyPanelJoin();
    
    boolean isHostAvailible = true;
    
    public JoinPanel(){
        this.setName("Join");
        this.setDoubleBuffered(true);
        init();
        this.setOpaque(false);
        this.setVisible(false);
        ipAdress = "";
        this.setLayout(new BorderLayout());
        
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(motionListener);
    }
    
    private void init(){
        int[] xCoords = new int[]{
                50, 60, 210, 220, 220, 210, 60, 50
            };
            int[] yCoords = new int[]{
                    400, 390, 390, 400, 420, 430, 430, 420
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
            g.drawImage(getImage("images/symbols/menu/multi/join/label_header.png"), 20, 190, null);
            g.drawImage(getImage("images/symbols/menu/multi/join/field_ip.png"), 20, 290, null);
            
            if(isHostAvailible){
                Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);
                g.setFont(f);
                g.setColor(Color.YELLOW);
                g.drawString(ipAdress, 115, 315);
            }else{
                Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);
                g.setFont(f);
                g.setColor(Color.RED);
                g.drawString(ipAdress, 115, 315);
                
                g.drawString("Host not availible!", 115, 365);
            }

            if(!overJoin){
                g.drawImage(getImage("images/symbols/menu/multi/join/button_join_unmarked.png"), 50, 390, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/join/button_join_marked.png"), 50, 390, null);
            }
            
            if(!overBack){
                g.drawImage(getImage("images/symbols/menu/multi/join/button_back_unmarked.png"), 50, 440, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/join/button_back_marked.png"), 50, 440, null);
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
                if(polyJoin.contains(e.getPoint())){
                    pressJoin();
                }else
                if(polyBack.contains(e.getPoint())){
                      setOpaque(false);
                      setVisible(false);
                      isVisible = false;
                      ipAdress = "";
                      MultiplayerPanel.isVisible = true;
                } 
            }
        }
    };
    
    private MouseMotionListener motionListener = new MouseMotionListener() {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            overJoin = false;
            overBack = false;
            if(polyJoin.contains(e.getPoint())){
                overJoin = true;
                overBack = false;
            }else
            if(polyBack.contains(e.getPoint())){
                overJoin = false;
                overBack = true;
            }
            repaint();
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            
        }
    };
    
    public void pressJoin(){
        chat = new ChatClientLobby(false, ipAdress);
        try {
            chat.pingHost();
        } catch (IOException e) {
            isHostAvailible = false;
            repaint();
        }
        if(isHostAvailible){
            pnlLobbyJoin.connectToLobby(ipAdress);
            isVisible = false;
            add(pnlLobbyJoin, BorderLayout.CENTER);
            LobbyPanelJoin.isVisible = true;
            pnlLobbyJoin.setVisible(true);
            pnlLobbyJoin.setOpaque(true);
            pnlLobbyJoin.updateChat();
        }
    }
    
    public void updateShownIp(){
        isHostAvailible = true;
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
