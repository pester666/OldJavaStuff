package client.multiplayer.game;

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
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import client.menu.TileScreenFrame;
import client.multiplayer.chat.ChatClientGame;
import database.UnitDatabaseFantasy;
import lib.Constants;
import lib.Environment;
import lib.Player;
import lib.Unit;

@SuppressWarnings("serial")
public class MultiplayerDeployPanel extends JPanel {
    private int xBox = 0;
    private int yBox = 0;
    private int x = 0;
    private int y = 0;
    private int xTrue = 0;
    private int yTrue = 0;
    private int clickedTileNrX = 0;
    private int clickedTileNrY = 0;
    private int oldX = 0;
    private int oldY = 0;
    private Unit[][] hashMap = new Unit[32][19];
    private Unit[][] boughtUnits = null;
    private Environment[][] envMap = new Environment[32][19];
    private Unit selectedUnitBox;
    private Unit selectedUnitField;
    private Unit showInfoUnit;
    private int age = Constants.FANTASY;
    private String ageString = "Fantasy";
    private String[] messages = new String[32];
    public static Player[] players = new Player[5];
    
    public boolean yourTurn = true;
    
    private boolean isHost;
    private String ipAdress;

    Polygon polyBox = null;
    Polygon polyField = null;
    Polygon polyNext = null;

    private boolean overNext = false;
    
    private ChatClientGame chat = new ChatClientGame(isHost, ipAdress);

    public MultiplayerDeployPanel(boolean isHost, String ipAdress) {
        this.isHost = isHost;
        this.ipAdress = ipAdress;
        Unit[] payed = new Unit[10];
        for (int i = 0; i < payed.length; i++) {
            payed[i] = new UnitDatabaseFantasy().BLACKORK;
        }
        TileScreenFrame.myself.setUnits(payed);
        int k = TileScreenFrame.myself.getUnits().length;
        boughtUnits = new Unit[k][3];
        Unit[] units = TileScreenFrame.myself.getUnits();
        int counter = 0;
        for (int i = 0; i < boughtUnits.length; i++) {
            for (int j = 0; j < boughtUnits[i].length; j++) {
                if (counter >= units.length) {
                    break;
                }
                boughtUnits[i][j] = units[counter];
                counter++;
            }
        }
        UnitDatabaseFantasy d = new UnitDatabaseFantasy();
        hashMap[12][12] = d.CHAOSKRIEGER;
        hashMap[12][12].playerName = "Grumplbert";
        init();
        if(isHost){
            createMapTerrain();    
        }
        this.setDoubleBuffered(true);
        this.setBackground(Color.green);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(motionListener);
        try {
            chat.openConnection();
            chat.getPlayers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        int[] xCoords = new int[] { 0, 1030, 1030, 0 };

        int[] yCoords = new int[] { 610, 610, 800, 800 };

        polyBox = new Polygon(xCoords, yCoords, 4);

        xCoords = new int[] { 33, 1030, 1030, 33 };

        yCoords = new int[] { 33, 33, 606, 606 };

        polyField = new Polygon(xCoords, yCoords, 4);

        xCoords = new int[] { 1050, 1205, 1205, 1050 };

        yCoords = new int[] { 580, 580, 725, 725 };

        polyNext = new Polygon(xCoords, yCoords, 4);
    }

    private void boxClicked(int x, int y, boolean isRightclick) {

        if (isRightclick) {
            clickedTileNrX = (x - 13) / 32;
            clickedTileNrY = (y - 622) / 32;
            xBox = clickedTileNrX;
            yBox = clickedTileNrY;
            showUnitInfo(boughtUnits[clickedTileNrX][clickedTileNrY]);
        } else if (yourTurn) {
            if (selectedUnitField != null) {
                removeUnit();
                repaint();
                return;
            } else {
                clickedTileNrX = (x - 13) / 32;
                clickedTileNrY = (y - 622) / 32;
                xBox = clickedTileNrX;
                yBox = clickedTileNrY;
                selectedUnitBox = boughtUnits[clickedTileNrX][clickedTileNrY];
                repaint();
            }
        }
    }

    private void giveMouseCoordinatesAndMakeAction(int x, int y, boolean isRightclick) {
        this.x = x;
        this.y = y;
        clickedTileNrX = this.x / 32;
        clickedTileNrY = this.y / 32;
        xTrue = clickedTileNrX * 32;
        yTrue = clickedTileNrY * 32;

        try {
            if (hashMap[clickedTileNrX][clickedTileNrY] != null) {
                if(isRightclick){
                    showUnitInfo(hashMap[clickedTileNrX][clickedTileNrY]);
                }else if(yourTurn){
                    selectUnit();    
                }
            } else if(yourTurn){
                if (selectedUnitBox != null) {
                    createUnit(clickedTileNrX, clickedTileNrY);
                } else if (selectedUnitField != null) {
                    makeUnitMove(clickedTileNrX + 1, clickedTileNrY + 1);
                    freeSelectionFromUnit();
                } else {
                    freeSelectionFromUnit();
                }
            }
        } catch (NullPointerException e) {
            setLog("There is no HashMap loaded at this time!");
        }
    }
    
    private void selectUnit(){
        if (hashMap[clickedTileNrX][clickedTileNrY].playerName.equals(TileScreenFrame.myself.getName())) {
            if (!hashMap[clickedTileNrX][clickedTileNrY].equals(selectedUnitField)) {
                selectedUnitField = hashMap[clickedTileNrX][clickedTileNrY];
                oldX = xTrue;
                oldY = yTrue;
                repaint();
                return;
            } else {
                freeSelectionFromUnit();
            }
        } else {
            if (selectedUnitField != null) {
                if (!hashMap[clickedTileNrX][clickedTileNrY].playerName.equals("Environment")) {

                } else {
                    makeUnitMove(clickedTileNrX + 1, clickedTileNrY + 1);
                    freeSelectionFromUnit();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < envMap.length; i++) {
            for (int j = 0; j < envMap[i].length; j++) {
                g.drawImage(getImage(envMap[i][j].name + ".PNG"), i * 32, j * 32, null);
            }
        }
        for (int i = 0; i < hashMap.length; i++) {
            for (int j = 0; j < hashMap[i].length; j++) {
                if (hashMap[i][j] != null) {
                    if (!hashMap[i][j].equals(selectedUnitField)) {
                        g.drawImage(getImage("images/units/" + ageString + "/" + hashMap[i][j].name + ".PNG"), i * 32, j * 32, null);
                    } else {
                        g.drawImage(getImage("images/units/" + ageString + "/selected/" + hashMap[i][j].name + ".PNG"), i * 32, j * 32, null);
                    }
                }
            }
        }

        g.drawImage(getImage("images/symbols/game/label_box.png"), 0, 610, null);

        for (int i = 0; i < boughtUnits.length; i++) {
            for (int j = 0; j < boughtUnits[i].length; j++) {
                if (boughtUnits[i][j] != null) {
                    if (clickedTileNrX == i && clickedTileNrY == j) {
                        g.drawImage(getImage("images/units/" + ageString + "/selected/" + boughtUnits[i][j].name + ".png"), (13 + i * 32) + i * 2, (622 + j * 32) + j * 2, null);
                    } else {
                        g.drawImage(getImage("images/units/" + ageString + "/" + boughtUnits[i][j].name + ".png"), (13 + i * 32) + i * 2, (622 + j * 32) + j * 2, null);
                    }
                }
            }
        }
        if (!overNext) {
            g.drawImage(getImage("images/symbols/game/button_next_unmarked.png"), 1050, 580, null);
        } else {
            g.drawImage(getImage("images/symbols/game/button_next_marked.png"), 1050, 580, null);
        }

        g.drawImage(getImage("images/symbols/game/label_log.png"), 1050, 20, null);

        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 10);
        g.setFont(f);
        g.setColor(Color.YELLOW);
        for (int i = 0; i < messages.length; i++) {
            if (messages[i] != null) {
                g.drawString(messages[i], 1065, 40 + (i * 5) + (i * 10));
            }
        }
        
        f = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        g.setFont(f);
        g.setColor(Color.YELLOW);

        if(showInfoUnit != null){
            g.drawImage(getImage("images/symbols/game/label_stats.png"), 138, 226, null);
            g.drawImage(getImage("images/units/" + ageString + "/" + showInfoUnit.name + ".png"), 170, 127, 96, 96, null);
            g.drawString("Name: " + showInfoUnit.name, 148, 245);
            g.drawString("Costs: " + showInfoUnit.costs, 148, 260);
            g.drawString("HP: " + showInfoUnit.hp, 148, 275);
            g.drawString("Attacks: " + showInfoUnit.attacks, 148, 290);
            g.drawString("Armoursave: " + showInfoUnit.armorsave, 148, 305);
            g.drawString("Range: " + showInfoUnit.range, 148, 320);
            g.drawString("Ranged Skill: " + showInfoUnit.rangedSkill, 148, 335);
            g.drawString("Strength: " + showInfoUnit.strength, 148, 350);
            g.drawString("Toughness: " + showInfoUnit.toughness, 148, 365);
            g.drawString("Movement: " + showInfoUnit.movement, 148, 380);
            g.drawString("Melee Skill: " + showInfoUnit.melee, 148, 395);
            g.drawString("GEAR: ", 148, 420);
            g.drawString("Name: " + showInfoUnit.gear.name, 148, 435);
            g.drawString("Range: " + showInfoUnit.gear.range, 148, 450);
            g.drawString("Rate of Fire: " + showInfoUnit.gear.rateOfFire, 148, 465);
            g.drawString("Strength: " + showInfoUnit.gear.strength, 148, 480);
            g.drawString("Armour Penetration: " + showInfoUnit.gear.armorPenetration, 148, 495);
        }
    }

    private void makeUnitMove(int x, int y) {
        if (x < 32 && x > 0 && y < 24 && y > 0) {
            if (envMap[x - 1][y - 1].walkable && !areEnemiesAround(x, y)) {
                hashMap[oldX / 32][oldY / 32] = null;
                hashMap[x - 1][y - 1] = selectedUnitField;
                chat.moveUnit(oldX / 32, oldY / 32, x-1, y-1);
            } else {
                setLog("This tile is not walkable!");
            }
        }
    }

    private void removeUnit() {
        if (hashMap[clickedTileNrX][clickedTileNrY] != null) {
            for (int i = 0; i < boughtUnits.length; i++) {
                for (int j = 0; j < boughtUnits[i].length; j++) {
                    if (boughtUnits[i][j] == null) {
                        boughtUnits[i][j] = hashMap[clickedTileNrX][clickedTileNrY];
                        hashMap[clickedTileNrX][clickedTileNrY] = null;
                        setLog(selectedUnitField.name + " removed.");
                        freeSelectionFromUnit();
                        chat.removeUnit(clickedTileNrX, clickedTileNrY);
                        return;
                    }
                }
            }
        } else if (envMap[clickedTileNrX][clickedTileNrY].isStructure) {
            createRandomTileAtLocation(clickedTileNrX, clickedTileNrY);
        }
    }

    private void freeSelectionFromUnit() {
        selectedUnitField = null;
        selectedUnitBox = null;
        repaint();
    }

    private void createUnit(int x, int y) {
        if (envMap[x][y].walkable) {
            if (!areEnemiesAround(x, y)) {
                boughtUnits[xBox][yBox] = null;
                hashMap[x][y] = new Unit(selectedUnitBox, TileScreenFrame.myself.getName());
                setLog(selectedUnitBox.name + " placed.");
                freeSelectionFromUnit();
                chat.createUnit(hashMap[x][y]);
            } else {
                setLog("Enemy too close!");
            }
        } else {
            setLog("You may not place a unit here!");
        }
    }

    private void createMapTerrain() {
        Random ran = new Random();

        for (int i = 0; i < hashMap.length; i++) {
            for (int j = 0; j < hashMap[i].length; j++) {
                if (i == 0 || j == 0) {
                    if (j == 0 && i == 0) {
                        envMap[i][j] = new Environment("images/symbols/rowsncols/" + 0, 0, false, false, false);
                    } else if (i == 0) {
                        envMap[i][j] = new Environment("images/symbols/rowsncols/" + j, 0, false, false, false);
                    } else {
                        envMap[i][0] = new Environment("images/symbols/rowsncols/" + i, 0, false, false, false);
                    }
                } else {
                    int bla = ran.nextInt(13);
                    if (bla == 0 || bla == 1 || bla == 2) {
                        envMap[i][j] = new Environment("images/environment/grass", 0, true, true, false);
                    } else if (bla == 3 || bla == 4 || bla == 5) {
                        envMap[i][j] = new Environment("images/environment/grass2", 0, true, true, false);
                    } else if (bla == 6 || bla == 7 || bla == 8 || bla == 9) {
                        envMap[i][j] = new Environment("images/environment/grass3", 0, true, true, false);
                    } else if (bla == 10 || bla == 11) {
                        envMap[i][j] = new Environment("images/environment/tree", 5, true, true, false);
                    } else if (bla == 12) {
                        if (age == Constants.WW2) {
                            envMap[i][j] = new Environment("images/environment/fence", 6, false, true, false);
                        } else if (age == Constants.FANTASY) {
                            envMap[i][j] = new Environment("images/environment/stone", 6, false, true, false);
                        }
                    }
                }
            }
        }
        chat.sendTerrain(envMap);
    }

    private void createRandomTileAtLocation(int i, int j) {
        Random ran = new Random();
        int bla = ran.nextInt(10);
        if (bla == 0 || bla == 1 || bla == 2) {
            envMap[i][j] = new Environment("images/environment/grass", 0, true, true, false);
        } else if (bla == 3 || bla == 4 || bla == 5) {
            envMap[i][j] = new Environment("images/environment/grass2", 0, true, true, false);
        } else if (bla == 6 || bla == 7 || bla == 8 || bla == 9) {
            envMap[i][j] = new Environment("images/environment/grass3", 0, true, true, false);
        }
    }

    public void nextPlayer() {
        freeSelectionFromUnit();
        yourTurn = false;
        overNext = false;
        //TODO next player
    }

    public void setAge(int age) {
        this.age = age;
        if (age == Constants.FANTASY) {
            this.ageString = "Fantasy";
        } else if (age == Constants.SCIFI) {
            this.ageString = "Science Fiction";
        } else if (age == Constants.WW2) {
            this.ageString = "WW2";
        }if(isHost){
            createMapTerrain();    
        }
    }

    private Image getImage(String path) {
        String imgLocation = null;
        ClassLoader cl = this.getClass().getClassLoader();
        URL imageURL = null;

        imgLocation = path;
        imageURL = cl.getResource(imgLocation);
        if (imageURL != null) {
            return new ImageIcon(imageURL).getImage();
        } else {
            return new ImageIcon(imgLocation).getImage();
        }
    }

    private void setLog(String message) {
        for (int i = 0; i < messages.length; i++) {
            if (i + 1 < messages.length) {
                messages[i] = messages[i + 1];
            } else {
                if (message.length() > 23) {
                    messages[i] = message.substring(0, 23) + "-";
                    message = message.substring(23);
                    i = 0;
                } else {
                    messages[i] = message;
                    break;
                }
            }
        }
    }

    private boolean areEnemiesAround(int x, int y) {
        for (int i = -4; i <= 4; i++) {
            for (int j = -4; j <= 4; j++) {
                if (x + i < hashMap.length && y + j < hashMap[0].length && x + i > 0 && y + j > 0) {
                    if (hashMap[x + i][y + j] != null && !hashMap[x + i][y + j].playerName.equals(TileScreenFrame.myself.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private void showUnitInfo(Unit unit){
        showInfoUnit = unit;
    }

    private MouseMotionListener motionListener = new MouseMotionListener() {

        @Override
        public void mouseMoved(MouseEvent e) {
            if(yourTurn){
                overNext = false;
                if (polyNext.contains(e.getPoint())) {
                    overNext = true;
                }
                repaint();   
            }            
        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }
    };

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
            showInfoUnit = null;
            if (polyField.contains(arg0.getPoint())) {
                giveMouseCoordinatesAndMakeAction(arg0.getX(), arg0.getY(), arg0.getButton() == MouseEvent.BUTTON3);
            }else
            if (polyNext.contains(arg0.getPoint()) && yourTurn) {
                nextPlayer();
            }else
            if (polyBox.contains(arg0.getPoint())) {
                boxClicked(arg0.getX(), arg0.getY(), arg0.getButton() == MouseEvent.BUTTON3);
            }
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {

        }

        @Override
        public void mouseExited(MouseEvent arg0) {

        }

        @Override
        public void mousePressed(MouseEvent arg0) {

        }

        @Override
        public void mouseReleased(MouseEvent arg0) {

        }
    };
}
