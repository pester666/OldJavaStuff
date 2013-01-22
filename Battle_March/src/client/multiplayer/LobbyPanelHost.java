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

import client.Main;

import client.menu.MultiplayerPanel;
import client.menu.TileScreenFrame;
import client.multiplayer.chat.ChatClientLobby;
import client.multiplayer.server.ChatServerLobby;
import lib.Constants;
import lib.Player;
import lib.UnitRaces;

@SuppressWarnings("serial")
public class LobbyPanelHost extends JPanel {

    public static boolean isVisible = false;
    
    ClassLoader cl = LobbyPanelHost.class.getClassLoader();

    Polygon polyStart = null;
    Polygon polyCancel = null;
    Polygon polyChangeTime = null;
    Polygon polyChangePoints = null;
    Polygon polyRace = null;
    Polygon polyTroops = null;
    boolean overStart = false;
    boolean overCancel = false;
    boolean overChangeTime = false;
    boolean overChangePoints = false;
    boolean overRace = false;
    boolean overTroops = false;
    public String chatTxt = null;
    public ChatClientLobby chat = new ChatClientLobby(true, null);
    ChatServerLobby server;
    public static Player[] p = new Player[5];
//    public static String[] players = new String[5];
//    public static String[] playerRaces = new String[5];
    public static boolean allPlayersReady = false;
    private String[] messages = new String[9];
    
    private int[] points = new int[] { 500, 1000, 2000, 3000 };
    private int actPoints = 500;

    private int[] ages = new int[] { Constants.FANTASY, Constants.SCIFI, Constants.WW2 };
    private int actAge = Constants.FANTASY;
    
    private String[] races = null;
//    private String actRace = null;
    
    UnitRaces ur = new UnitRaces();
    
    public TroopSelectionPanel pnlTroops = new TroopSelectionPanel(true);
    
    public LobbyPanelHost(){
        this.setName("Lobby");
        this.setDoubleBuffered(true);
        init();
        this.setOpaque(false);
        this.setVisible(false);
        this.setLayout(new BorderLayout());
        chatTxt = "";
        
        races = ur.returnFantasyRaces();
        TileScreenFrame.myself.setRace(races[0]);
        for (int i = 0; i < p.length; i++) {
            p[i] = new Player(null, "Open", 0, Constants.RACE_ORKS, false);
//            players[i] = "Open";
//            playerRaces[i] = TileScreenFrame.myself.getRace();
        }
                
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(motionListener);
    }
    
    private void init(){
        int[] xCoords = new int[] { 50, 60, 210, 220, 220, 210, 60, 50 };
        int[] yCoords = new int[] { 470, 460, 460, 470, 490, 500, 500, 490 };

        polyChangeTime = new Polygon(xCoords, yCoords, 8);

        yCoords = new int[] { 520, 510, 510, 520, 540, 550, 550, 540 };

        polyChangePoints = new Polygon(xCoords, yCoords, 8);

        xCoords = new int[] { 590, 600, 750, 760, 760, 750, 600, 590 };

        yCoords = new int[] { 470, 460, 460, 470, 490, 500, 500, 490 };

        polyStart = new Polygon(xCoords, yCoords, 8);

        yCoords = new int[] { 520, 510, 510, 520, 540, 550, 550, 540 };

        polyCancel = new Polygon(xCoords, yCoords, 8);
        
        yCoords = new int[] { 420, 410, 410, 420, 440, 450, 450, 440 };
        
        polyTroops = new Polygon(xCoords, yCoords, 8);
        
        xCoords = new int[] { 430, 440, 590, 600, 600, 590, 440, 430 };

        polyRace = new Polygon(xCoords, yCoords, 8);
        
    }    
    
    public void paintComponent(Graphics g){
        if(isVisible){
            g.drawImage(getImage("images/symbols/menu/background.png"), 0, 0, null);
            g.drawImage(getImage("images/symbols/menu/multi/lobby/label_header.png"), 20, 190, null);
            g.drawImage(getImage("images/symbols/menu/multi/lobby/label_players.png"), 40, 255, null);
            g.drawImage(getImage("images/symbols/menu/multi/lobby/field_chat.png"), 40, 410, null);
            g.drawImage(getImage("images/symbols/menu/multi/lobby/label_pole.png"), 340, 270, null);
            g.drawImage(getImage("images/symbols/menu/multi/lobby/label_pole.png"), 340, 330, null);
            g.drawImage(getImage("images/symbols/menu/multi/lobby/label_chat.png"), 385, 255, null);
            Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);
            g.setFont(f);
            g.setColor(Color.YELLOW);
            g.drawString(chatTxt, 150, 440);
            f = new Font(Font.SANS_SERIF, Font.BOLD, 10);
            g.setFont(f);
            g.setColor(Color.GREEN);
            for (int i = 0; i < p.length; i++) {
                if(p[i] != null){
                    g.drawString(p[i].getName(), 60, 300+(i*5)+(i*10));
                    g.drawString(p[i].getRace(), 140, 300+(i*5)+(i*10));
                }
            }
            f = new Font(Font.SANS_SERIF, Font.BOLD, 10);
            g.setFont(f);
            g.setColor(Color.YELLOW);
            for (int i = 0; i < messages.length; i++) {
                if(messages[i] != null){
                    g.drawString(messages[i], 395, 273+(i*5)+(i*10));
                }
            }
            
            if(!overChangeTime){
                g.drawImage(getImage("images/symbols/menu/multi/lobby/button_time_unmarked.png"), 50, 460, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/lobby/button_time_marked.png"), 50, 460, null);
            }
            
            g.drawImage(getImage("images/symbols/menu/multi/lobby/label_age_" + actAge + ".png"), 240, 460, null);
            
            if(!overChangePoints){
                g.drawImage(getImage("images/symbols/menu/multi/lobby/button_points_unmarked.png"), 50, 510, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/lobby/button_points_marked.png"), 50, 510, null);
            }
            
            g.drawImage(getImage("images/symbols/menu/multi/lobby/label_" + actPoints + ".png"), 240, 510, null);
            
            if(!overRace){
                g.drawImage(getImage("images/symbols/menu/multi/lobby/age_" + actAge + "/label_" + TileScreenFrame.myself.getRace() + "_unmarked.png"), 430, 410, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/lobby/age_" + actAge + "/label_" + TileScreenFrame.myself.getRace() + "_marked.png"), 430, 410, null);
            }
            
            if(!overTroops){
                g.drawImage(getImage("images/symbols/menu/multi/lobby/button_troops_unmarked.png"), 620, 410, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/lobby/button_troops_marked.png"), 620, 410, null);
            }
            
            if(!overStart || !allPlayersReady){
                g.drawImage(getImage("images/symbols/menu/multi/lobby/button_start_unmarked.png"), 620, 460, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/lobby/button_start_marked.png"), 620, 460, null);
            }
            
            if(!overCancel){
                g.drawImage(getImage("images/symbols/menu/multi/lobby/button_cancel_unmarked.png"), 620, 510, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/lobby/button_cancel_marked.png"), 620, 510, null);
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
                if(polyChangeTime.contains(e.getPoint())){
                    for (int i = 0; i < ages.length; i++) {
                        if(actAge == ages[i]){
                            if(i+1 == ages.length){
                                actAge = ages[0];
                            }else{
                                actAge = ages[i+1];
                            }
                            if(actAge == Constants.FANTASY){
                                races = ur.returnFantasyRaces();
                            }else if(actAge == Constants.SCIFI){
                                races = ur.returnFutureRaces();
                            }else if(actAge == Constants.WW2){
                                races = ur.returnWW2Races();
                            }
                            TileScreenFrame.myself.setRace(races[0]);
                            chat.changeAge(actAge);
                            chat.changeRace(TileScreenFrame.myself.getRace());
                            pnlTroops.changeAge(actAge);
                            pnlTroops.changeRace(TileScreenFrame.myself.getRace());
                            break;
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
                            }
                            chat.changePoints(actPoints);
                            pnlTroops.changePoints(actPoints);
                            break;
                        }
                    }
                    repaint();
                }else
                if(polyStart.contains(e.getPoint())){
                    chat.startTheGame();
                    server.shouldRun = false;
                    server.interrupt();
                    for (int i = 0; i < p.length; i++) {
                        if(p[i].getName().equals(TileScreenFrame.myself.getName())){
                            p[i].setUnits(TileScreenFrame.myself.getUnits());
                        }
                    }
                    Main.launchMultiplayer(p, actAge, true, null);
                }else
                if(polyCancel.contains(e.getPoint())){
                    chat.closeConnection();
                    server.shouldRun = false;
                    server.interrupt();
                    setOpaque(false);
                    setVisible(false);
                    isVisible = false;
                    MultiplayerPanel.isVisible = true;
                }else
                if(polyRace.contains(e.getPoint())){
                    for (int i = 0; i < races.length; i++) {
                        if(TileScreenFrame.myself.getRace() == races[i]){
                            //+2 because of Environment
                            if(i+2 == races.length){
                                TileScreenFrame.myself.setRace(races[0]);
                            }else{
                                TileScreenFrame.myself.setRace(races[i+1]);
                            }
                            chat.changeRace(TileScreenFrame.myself.getRace());
                            pnlTroops.changeRace(TileScreenFrame.myself.getRace());
                            break;
                        }
                    }
                    repaint();
                }else
                if(polyTroops.contains(e.getPoint())){
                    add(pnlTroops, BorderLayout.CENTER);
                    isVisible = false;
                    TroopSelectionPanel.isVisible = true;
                    pnlTroops.setVisible(true);
                    pnlTroops.setOpaque(true);
                }
            }
        }
    };
    
    private MouseMotionListener motionListener = new MouseMotionListener() {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            overStart = false;
            overCancel = false;
            overChangeTime = false;
            overChangePoints = false;
            overRace = false;
            overTroops = false;
            if(polyStart.contains(e.getPoint())){
                overStart = true;
                overChangeTime = false;
                overChangePoints = false;
                overCancel = false;
                overRace = false;
                overTroops = false;
            }else
            if(polyCancel.contains(e.getPoint())){
                overStart = false;
                overChangeTime = false;
                overChangePoints = false;
                overCancel = true;
                overRace = false;
                overTroops = false;
            }else 
            if(polyChangeTime.contains(e.getPoint())){
                overStart = false;
                overChangeTime = true;
                overChangePoints = false;
                overCancel = false;
                overRace = false;
                overTroops = false;
            }else
            if(polyChangePoints.contains(e.getPoint())){
                overStart = false;
                overChangeTime = false;
                overChangePoints = true;
                overCancel = false;
                overRace = false;
                overTroops = false;
            }else
            if(polyRace.contains(e.getPoint())){
                overStart = false;
                overChangeTime = false;
                overChangePoints = false;
                overCancel = false;
                overRace = true;
                overTroops = false;
            }else 
            if(polyTroops.contains(e.getPoint())){
                overStart = false;
                overChangeTime = false;
                overChangePoints = false;
                overCancel = false;
                overRace = false;
                overTroops = true;
            }
            repaint();
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            
        }
    };
    
    public void startServer(){
        server = new ChatServerLobby();
        server.p = p;
//        server.players = players;
        server.start();
        try {
            chat.openConnection();
            Thread t = new repainter();
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.repaint();
    }
    
    public void updateChat(){
        messages = chat.messages;
        this.repaint();
    }
    
    public void sendMessage(String message){
        chatTxt = "";
        chat.printMessage(Constants.MSG_CHAT + Constants.MSG_SPLITTER + TileScreenFrame.myself.getName() + Constants.MSG_SPLITTER + message);
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
    
    private class repainter extends Thread{
        public void run(){
            while(true){
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
