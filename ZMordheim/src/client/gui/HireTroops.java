package client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import client.gui.hire.Troops;
import lib.Unit;
import lib.Warband;

public class HireTroops extends MenuPanel {

    public static boolean isActive = false;
    public static String warbandName = "";
    private Image imgPlain = new ImageIcon("res/img/hire/alley.png").getImage();
    private Image imgBuy = new ImageIcon("res/img/hire/alleyBuy.png").getImage();
    private Image imgSell = new ImageIcon("res/img/hire/alleySell.png").getImage();
    private Image imgNext = new ImageIcon("res/img/hire/alleyRight.png").getImage();
    private Image imgPrevious= new ImageIcon("res/img/hire/alleyLeft.png").getImage();
    private Image imgReady = new ImageIcon("res/img/hire/alleyReady.png").getImage();
    private Image imgCancel = new ImageIcon("res/img/hire/alleyCancel.png").getImage();
    private Image imgSelectedUnit;
    
    private boolean isOverSell = false;
    private boolean isOverBuy = false;
    private boolean isOverPrevious = false;
    private boolean isOverNext = false;
    private boolean isOverCancel = false;
    private boolean isOverReady = false;
    private boolean boxClicked = false;
    
    private Polygon polyBuy;
    private Polygon polySell;
    private Polygon polyNext;
    private Polygon polyPrevious;
    private Polygon polyHired;
    private Polygon polyReady;
    private Polygon polyCancel;
    
    private Thread t;
    private int troopCount = 0;
    private Warband warband;
    private Troops troops;
    private Unit selectedUnit;

    public HireTroops(boolean active) {
        this.setSize(500, 500);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        t = new Thread(anim);
        if (active && !t.isAlive()) {
            troops = new Troops(warband.getRace());
            selectedUnit = troops.getUnit(0);
            imgSelectedUnit = new ImageIcon(selectedUnit.getImg()).getImage();
            this.t.start();
        }

        int[] x = new int[] { 375, 440, 440, 375 };

        int[] y = new int[] { 230, 230, 260, 260 };

        polyBuy = new Polygon(x, y, 4);
        
        x = new int[] { 440, 480, 480, 440 };

        y = new int[] { 280, 280, 310, 310 };
        
        polySell = new Polygon(x, y, 4);
        
        x = new int[] { 80, 125, 125, 80 };

        y = new int[] { 170, 170, 290, 290 };
        
        polyCancel = new Polygon(x, y, 4);
        
        x = new int[] { 325, 430, 430, 325 };

        y = new int[] { 375, 375, 460, 460 };
        
        polyReady = new Polygon(x, y, 4);
        
        x = new int[] { 285, 330, 330, 285 };

        y = new int[] { 130, 130, 185, 185 };
        
        polyNext = new Polygon(x, y, 4);
        
        x = new int[] { 140, 185, 185, 140 };

        y = new int[] { 130, 130, 185, 185 };
        
        polyPrevious = new Polygon(x, y, 4);
        
        x = new int[] { 105, 245, 245, 105 };

        y = new int[] { 315, 315, 490, 490 };
        
        polyHired = new Polygon(x, y, 4);

        this.addMouseMotionListener(motionL);

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isActive) {
                    System.out.println(e.getX() + " " + e.getY());
                    boxClicked = false;
                    if(polyBuy.contains(e.getPoint())){
                        buyUnit();
                    }else if(polyCancel.contains(e.getPoint())){
                        
                    }else if(polySell.contains(e.getPoint()) && boxClicked){
                        sellUnit();
                    }else if(polyReady.contains(e.getPoint())){
                        
                    }else if(polyPrevious.contains(e.getPoint())){
                        if(troopCount > 0){
                            troopCount--;
                            selectedUnit = troops.getUnit(troopCount);
                            imgSelectedUnit = new ImageIcon(selectedUnit.getImg()).getImage();
                        }
                    }else if(polyNext.contains(e.getPoint())){
                        if(troopCount + 1 < troops.getUnits().length ){
                            troopCount++;    
                            selectedUnit = troops.getUnit(troopCount);
                            imgSelectedUnit = new ImageIcon(selectedUnit.getImg()).getImage();
                        }
                    }else if(polyHired.contains(e.getPoint())){
                        boxClick(e.getX(), e.getY());
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
    
    public void loadWarband(){
        try {
            FileInputStream fout = new FileInputStream("res/warbands/" + warbandName);
            ObjectInputStream oos = new ObjectInputStream(fout);   
            warband = (Warband)oos.readObject();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void saveWarband(){
        try {
            FileOutputStream fout = new FileOutputStream("res/warbands/" + warband.getName());
            ObjectOutputStream oos = new ObjectOutputStream(fout);   
            oos.writeObject(warband);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void buyUnit(){
        if(warband.getUnits().length < warband.getMaxUnits()){
            warband.setGoldCrowns(warband.getGoldCrowns()-selectedUnit.getCost());
            selectedUnit.setName(this.getUnitname());
            warband.addUnit(selectedUnit);
        }
        this.saveWarband();
    }
    
    private String getUnitname(){
        String s = (String)JOptionPane.showInputDialog(
                            this,
                            "Who art thee called?",
                            "A new one...",
                            JOptionPane.PLAIN_MESSAGE,
                            null, null, "");

        if ((s != null) && (s.length() > 0)) {
            return s;
        }
        return "Chopped Tongue";
    }
    
    private void sellUnit(){
        warband.removeUnit(selectedUnit);
        warband.setGoldCrowns(warband.getGoldCrowns()+selectedUnit.getCost());
        this.saveWarband();
    }
    
    private void boxClick(int x, int y){
        this.boxClicked = true;
    }

    public void paintComponent(Graphics g) {
        
        if(isOverBuy){
            g.drawImage(imgBuy, 0, 0, null);    
        }else
        if(isOverNext){
            g.drawImage(imgNext, 0, 0, null);
        }else
        if(isOverPrevious){
            g.drawImage(imgPrevious, 0, 0, null);
        }else
        if(isOverSell){
            g.drawImage(imgSell, 0, 0, null);
        }else
        if(isOverReady){
            g.drawImage(imgReady, 0, 0, null);
        }else   
        if(isOverCancel){
            g.drawImage(imgCancel, 0, 0, null);
        }else{
            g.drawImage(imgPlain, 0, 0, null);
        }
        
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        g.setFont(f);
        g.setColor(Color.YELLOW);
        if(selectedUnit != null){
            g.drawString("Type: " + selectedUnit.getType(), 180, 260);
            
            g.drawString("Costs: " + selectedUnit.getCost(), 358, 65);            
            g.drawString("Movement: " + selectedUnit.getMovement(), 358, 80);
            g.drawString("Weaponkill: " + selectedUnit.getWeaponSkill(), 358, 95);
            g.drawString("Rangedskill: " + selectedUnit.getBalisticSkill(), 358, 110);
            g.drawString("Strength: " + selectedUnit.getStrength(), 358, 125);
            g.drawString("Toughness: " + selectedUnit.getToughness(), 358, 140);
            g.drawString("Wounds: " + selectedUnit.getWounds(), 358, 155);
            g.drawString("Attacks: " + selectedUnit.getAttacks(), 358, 170);
            g.drawString("Initiative: " + selectedUnit.getInitiative(), 358, 185);
            g.drawString("Leadership: " + selectedUnit.getLeadership(), 358, 200);
        }
        if(selectedUnit != null){
            g.drawImage(imgSelectedUnit, 195, 110, null);    
        }
    }

    public void setActive(boolean active) {
        this.setVisible(active);
        this.setOpaque(active);
        isActive = active;
        if (active && !t.isAlive()) {
            this.loadWarband();
            troops = new Troops(warband.getRace());
            selectedUnit = troops.getUnit(0);
            imgSelectedUnit = new ImageIcon(selectedUnit.getImg()).getImage();
            this.t.start();
        }
    }

    private Runnable anim = new Runnable() {
        @Override
        public void run() {
            while (isActive) {
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    
    private MouseMotionListener motionL = new MouseMotionListener(){
        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (isActive) {
                if(polyBuy.contains(e.getPoint())){
                    isOverSell = false;
                    isOverBuy = true;
                    isOverPrevious = false;
                    isOverNext = false;
                    isOverCancel = false;
                    isOverReady = false;
                }else if(polyCancel.contains(e.getPoint())){
                    isOverSell = false;
                    isOverBuy = false;
                    isOverPrevious = false;
                    isOverNext = false;
                    isOverCancel = true;
                    isOverReady = false;
                }else if(polySell.contains(e.getPoint())){
                    isOverSell = true;
                    isOverBuy = false;
                    isOverPrevious = false;
                    isOverNext = false;
                    isOverCancel = false;
                    isOverReady = false;
                }else if(polyReady.contains(e.getPoint())){
                    isOverSell = false;
                    isOverBuy = false;
                    isOverPrevious = false;
                    isOverNext = false;
                    isOverCancel = false;
                    isOverReady = true;
                }else if(polyPrevious.contains(e.getPoint())){
                    isOverSell = false;
                    isOverBuy = false;
                    isOverPrevious = true;
                    isOverNext = false;
                    isOverCancel = false;
                    isOverReady = false;
                }else if(polyNext.contains(e.getPoint())){
                    isOverSell = false;
                    isOverBuy = false;
                    isOverPrevious = false;
                    isOverNext = true;
                    isOverCancel = false;
                    isOverReady = false;
                }else {
                    isOverSell = false;
                    isOverBuy = false;
                    isOverPrevious = false;
                    isOverNext = false;
                    isOverCancel = false;
                    isOverReady = false;
                }
            }
        }
    };
    
    @Override
    public int getPanelToActive() {
        return 0;
    }

    @Override
    public int getPanelId() {
        return PanelIds.HIRE_TROOPS;
    }
}