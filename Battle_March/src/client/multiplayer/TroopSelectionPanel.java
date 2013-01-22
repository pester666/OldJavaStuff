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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import client.menu.TileScreenFrame;

import lib.Constants;
import lib.Unit;
import lib.UnitRaces;

@SuppressWarnings("serial")
public class TroopSelectionPanel extends JPanel {

    public static boolean isVisible = false;
    
    public String race = Constants.RACE_ORKS;
    public int age = Constants.FANTASY;
    public int actPoints = 500;
    public int points = 500;
    private Unit[] troopsOfRace = null;
    private Unit[][] box = new Unit[5][4];
    private UnitRaces ur = new UnitRaces();
    private Polygon polyUnits = null;
    private Unit selectedUnit = null;
    private String ageString = "Fantasy";
    
    boolean isHost = false;
    private int x = 9999;
    private int y = 9999;
    
    private boolean overCancel = false;
    private boolean overSell = false;
    private boolean toMuchCosts = false;
    private boolean overBuy = false;
    
    Polygon polySell = null;
    Polygon polyCancel = null;
    Polygon polyBuy = null;
    Polygon polyBox = null;
    
    public TroopSelectionPanel(boolean isHost){
        this.setName("TroopPanel");
        this.setDoubleBuffered(true);
        this.setOpaque(false);
        this.setVisible(false);
        this.setLayout(new BorderLayout());
        troopsOfRace = ur.getUnitsOfRaceAndAge(race, age);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(motionListener);
        this.isHost = isHost;
        init();
        
    }
    
    private void init(){
        
        int[] xCoords = new int[] { 10, 119, 119, 10};
        int[] yCoords = new int[] { 130, 130, 550, 550};

        polyUnits = new Polygon(xCoords, yCoords, 4);
        
        xCoords = new int[] { 315, 325, 475, 485, 485, 475, 325, 315 };

        yCoords = new int[] { 80, 70, 70, 80, 100, 110, 110, 100 };

        polySell = new Polygon(xCoords, yCoords, 8);
        
        xCoords = new int[] { 505, 515, 665, 675, 675, 665, 515, 505 };
        
        polyCancel = new Polygon(xCoords, yCoords, 8);
        
        xCoords = new int[] { 130, 140, 290, 300, 300, 290, 140, 130};
        
        yCoords = new int[] { 530, 520, 520, 530, 550, 560, 560, 550};

        polyBuy = new Polygon(xCoords, yCoords, 8);
        
        xCoords = new int[] { 315, 770, 770, 315};
        
        yCoords = new int[] { 130, 130, 420, 420};

        polyBox = new Polygon(xCoords, yCoords, 4);
        
    }
    
    public void paintComponent(Graphics g){
        if(isVisible){
            g.drawImage(getImage("images/symbols/menu/multi/troops/background.png"), 0, 0, null);
            g.drawImage(getImage("images/symbols/menu/multi/troops/label_box.png"), 315, 130, null);
            g.drawImage(getImage("images/symbols/menu/multi/troops/label_header.png"), 20, 60, null);
            g.drawImage(getImage("images/symbols/menu/multi/troops/label_units.png"), 10, 130, null);
            g.drawImage(getImage("images/symbols/menu/multi/troops/label_preview.png"), 168, 125, null);
            g.drawImage(getImage("images/symbols/menu/multi/troops/label_stats.png"), 138, 226, null);
            int counter = 0;
            for (int i = 0; i < box.length; i++) {
                for (int j = 0; j < box[i].length; j++) {
                    g.drawImage(getImage("images/symbols/menu/multi/troops/label_empty_grass.png"), 328+i*34, 142+j*34, null);    
                    if(box[i][j] != null){
                        if(i == x && j == y){
                            g.drawImage(getImage("images/symbols/menu/multi/troops/label_unit_marked.png"), 328+i*34, 142+j*34, null); 
                        }
                        g.drawImage(getImage("images/units/" + ageString + "/" + box[i][j].name + ".png"), (328+i*32)+i*2, (142+j*32)+j*2, null);
                        counter++;
                    }
                }
            }    
            Font f = new Font(Font.SANS_SERIF, Font.BOLD, 13);
            g.setFont(f);
            g.setColor(Color.YELLOW);
            for (int i = 0; i < troopsOfRace.length; i++) {
                g.drawString(troopsOfRace[i].name, 25, 177+(i*5)+(i*10));
            }
            if(selectedUnit != null){
                g.drawImage(getImage("images/units/" + ageString + "/" + selectedUnit.name + ".png"), 170, 127, 96, 96, null);
                g.drawString("Name: " + selectedUnit.name, 148, 245);
                g.drawString("Costs: " + selectedUnit.costs, 148, 260);
                g.drawString("HP: " + selectedUnit.hp, 148, 275);
                g.drawString("Attacks: " + selectedUnit.attacks, 148, 290);
                g.drawString("Armoursave: " + selectedUnit.armorsave, 148, 305);
                g.drawString("Range: " + selectedUnit.range, 148, 320);
                g.drawString("Ranged Skill: " + selectedUnit.rangedSkill, 148, 335);
                g.drawString("Strength: " + selectedUnit.strength, 148, 350);
                g.drawString("Toughness: " + selectedUnit.toughness, 148, 365);
                g.drawString("Movement: " + selectedUnit.movement, 148, 380);
                g.drawString("Melee Skill: " + selectedUnit.melee, 148, 395);
                g.drawString("GEAR: ", 148, 420);
                g.drawString("Name: " + selectedUnit.gear.name, 148, 435);
                g.drawString("Range: " + selectedUnit.gear.range, 148, 450);
                g.drawString("Rate of Fire: " + selectedUnit.gear.rateOfFire, 148, 465);
                g.drawString("Strength: " + selectedUnit.gear.strength, 148, 480);
                g.drawString("Armour Penetration: " + selectedUnit.gear.armorPenetration, 148, 495);
            }
            
            g.drawString("Points left: " + actPoints, 650, 25);
            
            if(!overCancel){
                g.drawImage(getImage("images/symbols/menu/multi/troops/button_back_unmarked.png"), 505, 70, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/troops/button_back_marked.png"), 505, 70, null);
            }
            
            if(!overSell){
                g.drawImage(getImage("images/symbols/menu/multi/troops/button_sell_unmarked.png"), 315, 70, null);
            }else{
                g.drawImage(getImage("images/symbols/menu/multi/troops/button_sell_marked.png"), 315, 70, null);
            }
            
            if(!overBuy){
                g.drawImage(getImage("images/symbols/menu/multi/troops/button_buy_unmarked.png"), 130, 520, null);   
            }else{
                if (toMuchCosts) {
                    g.drawImage(getImage("images/symbols/menu/multi/troops/button_buy_error.png"), 130, 520, null);
                } else {
                    g.drawImage(getImage("images/symbols/menu/multi/troops/button_buy_marked.png"), 130, 520, null);
                }
            }
        }
    }
    
    public void changeRace(String race){
        this.race = race;
        resetBox();

    }
    
    private void resetBox(){
        if(points == 500){
            box = new Unit[5][4];
        }else if(points == 1000){
            box = new Unit[6][6];
        }else if(points == 2000){
            box = new Unit[8][8];
        }else if(points == 3000){
            box = new Unit[10][10];
        }
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                box[i][j] = null;
            }
        }
        actPoints = points;
        updateUnitList();
    }
    
    public void changeAge(int age){
        this.age = age;
        if(age == Constants.FANTASY){
            ageString = "Fantasy";
        }else if(age == Constants.SCIFI){
            ageString = "Science Fiction";
        }else if(age == Constants.WW2){
            ageString = "WW2";
        }
        resetBox();
    }
    
    private void updateUnitList(){
        selectedUnit = null;
        troopsOfRace = ur.getUnitsOfRaceAndAge(race, age);
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
                if(polyUnits.contains(e.getPoint())){
                    y = (e.getY()-165)/15;     
                    if(troopsOfRace.length > y){
                        selectedUnit = troopsOfRace[y];
                        toMuchCosts = false;
                        repaint();   
                    }
                }else
                if(polyCancel.contains(e.getPoint())){
                    setOpaque(false);
                    setVisible(false);
                    isVisible = false;
                    toMuchCosts = false;
                    if(isHost){
                        LobbyPanelHost.isVisible = true;    
                    }else{
                        LobbyPanelJoin.isVisible = true;
                    }
                }else
                if(polyBuy.contains(e.getPoint())){
                    if(selectedUnit.costs <= actPoints){
                        for (int i = 0; i < box.length; i++) {
                            for (int j = 0; j < box[i].length; j++) {
                                if(box[i][j] == null){
                                    box[i][j] = selectedUnit;
                                    actPoints = actPoints - selectedUnit.costs;
                                    setYourUnits();
                                    repaint();
                                    return;
                                }
                            }
                        } 
                    }
                }else
                if(polyBox.contains(e.getPoint())){
                    x = (e.getX()-328)/34;
                    y = (e.getY()-142)/34;   
                    if(box.length > x && box[0].length > y){
                        selectedUnit = box[x][y];
                        repaint();
                        toMuchCosts = false;
                    }
                }else
                if(polySell.contains(e.getPoint())){  
                    if(box.length > x && box[0].length > y){
                        if(box[x][y] != null){
                            actPoints = actPoints + box[x][y].costs;
                            box[x][y] = null;
                            toMuchCosts = false;
                            setYourUnits();
                            repaint();
                        }
                    }
                }
            }
        }
    };
    
    private MouseMotionListener motionListener = new MouseMotionListener() {
        
        @Override
        public void mouseMoved(MouseEvent e) {
            overSell = false;
            overCancel = false;
            overBuy = false;
            if(polySell.contains(e.getPoint())){
                overSell = true;
                overCancel = false;
                overBuy = false;
            }else
            if(polyCancel.contains(e.getPoint())){
                overSell = false;
                overCancel = true;
                overBuy = false;
            }else
            if(polyBuy.contains(e.getPoint())){
                if(selectedUnit != null){
                    if(selectedUnit.costs > actPoints){
                        overSell = false;
                        overCancel = false;
                        overBuy = true;   
                        toMuchCosts = true;
                    }else{
                        overSell = false;
                        overCancel = false;
                        overBuy = true;   
                        toMuchCosts = false;
                    }
                }
            }
            repaint();
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            //TODO buy unit by drag
        }
    };

    public void changePoints(int points) {
        this.points = points;
        this.actPoints = points;
        resetBox();
    }
    
    private void setYourUnits(){
        List<Unit> list = new ArrayList<Unit>();
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                if(box[i][j] != null){
                    list.add(box[i][j]);
                }
            }
        }
        Unit[] arr = new Unit[list.size()];
        TileScreenFrame.myself.setUnits(list.toArray(arr));
    }
}
