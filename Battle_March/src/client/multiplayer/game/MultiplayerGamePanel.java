package client.multiplayer.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lib.Constants;
import lib.Environment;
import lib.LOSCheck;
import lib.Unit;

@SuppressWarnings("serial")
public class MultiplayerGamePanel extends JPanel {
    private int x = 0;
    private int y = 0;
    private int xTrue = 0;
    private int yTrue = 0;
    private int clickedTileNrX = 0;
    private int clickedTileNrY = 0;
    private int oldX = 0;
    private int oldY = 0;
    private int pointLimit = 2000;
    int targetTileX = 0;
    int targetTileY = 0;
    private Unit[][] hashMap = new Unit[32][24];
    private Unit explosion = new Unit(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Explosion", "Environment", null, true, true, 0, 0, Constants.RACE_ENVIRONMENT);
    private Unit catapultRock = new Unit(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Catapult-Rock", "Environment", null, true, true, 0, 0, Constants.RACE_ENVIRONMENT);
    private Unit hailOfBullets = new Unit(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Hail of Bullets", "Environment", null, true, true, 0, 0, Constants.RACE_ENVIRONMENT);
    private Environment[][] envMap = new Environment[32][24];
    private Unit currentSelection;
    private Unit listSelection;
    private boolean isSelected = false;
    private boolean isAttacking = false;
    private boolean isDeploying = false;
    private boolean isMoving = false;
    private boolean isDeleting = false;
    private String selectedPlayer = null;
//    private JLabel name = new JLabel(" ");
//    private JLabel space = new JLabel(" ");
//    private JLabel strenght = new JLabel("S: ");
//    private JLabel toughness = new JLabel("T: ");
//    private JLabel melee = new JLabel("WS: ");
//    private JLabel attacks = new JLabel("A: ");
//    private JLabel movement = new JLabel("MV: ");
//    private JLabel range = new JLabel("R: ");
//    private JLabel armor = new JLabel("AS: ");
//    private JLabel gear = new JLabel("G: ");
//    private JLabel owner = new JLabel("O: ");
//    private JLabel rangedSkill = new JLabel("BS: ");
//    private JLabel hp = new JLabel("HP: ");
//    private JLabel type = new JLabel("TYPE: ");
//    private JLabel alreadyMoved = new JLabel("Moved: ");
//    private JLabel alreadyAttacked = new JLabel("Attacked: ");
    private List<PlayerRessources> playerRessources = new ArrayList<PlayerRessources>();
    private int age = Constants.FANTASY;
//    private UnitTypes ut = new UnitTypes();
    public Combat c = new Combat();
    private String txt;

    public MultiplayerGamePanel() {

        createMapTerrain();

        this.setBackground(Color.green);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                giveMouseCoordinatesAndMakeAction(arg0.getX(), arg0.getY());

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
        });
    }

    private void giveMouseCoordinatesAndMakeAction(int x, int y) {
        this.x = x;
        this.y = y;
        clickedTileNrX = this.x / 32;
        clickedTileNrY = this.y / 32;
        xTrue = clickedTileNrX * 32;
        yTrue = clickedTileNrY * 32;

        if (isDeleting) {
            removeUnit();
            repaint();
            return;
        }
        try {
            if (hashMap[clickedTileNrX][clickedTileNrY] != null) {
                if (hashMap[clickedTileNrX][clickedTileNrY].playerName.equals(selectedPlayer)) {
                    if (!hashMap[clickedTileNrX][clickedTileNrY].equals(currentSelection)) {
                        currentSelection = hashMap[clickedTileNrX][clickedTileNrY];
                        oldX = xTrue;
                        oldY = yTrue;
                        changeAttributePanel(hashMap[clickedTileNrX][clickedTileNrY]);
                        setSelected(true);
                        repaint();
                        return;
                    } else {
                        freeSelectionFromUnit();
                    }

                } else {
                    if (isSelected) {
                        if (!hashMap[clickedTileNrX][clickedTileNrY].playerName.equals("Environment")) {
                            if (isAttacking) {
                                makeUnitAttack(hashMap[oldX / 32][oldY / 32], hashMap[clickedTileNrX][clickedTileNrY]);
                                freeSelectionFromUnit();
                            }
                        } else {
                            if (isMoving()) {
                                makeUnitMove(clickedTileNrX + 1, clickedTileNrY + 1);
                                repaint();
                                freeSelectionFromUnit();
                            }
                        }
                    } else {
                        changeAttributePanel(hashMap[clickedTileNrX][clickedTileNrY]);
                    }
                }
            } else if (isDeploying()) {
                createUnit(clickedTileNrX, clickedTileNrY);
                repaint();
            } else if (isSelected) {
                if (isMoving()) {
                    makeUnitMove(clickedTileNrX + 1, clickedTileNrY + 1);
                    repaint();
                    freeSelectionFromUnit();
                }
            } else {
                freeSelectionFromUnit();
            }
        } catch (NullPointerException e) {
            if (isDeploying()) {
                createUnit(clickedTileNrX, clickedTileNrY);
                repaint();
            }
            txt = txt + "There is no HashMap loaded at this time!\n";
            txt = txt + "\n";
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
                    if (!hashMap[i][j].equals(currentSelection)) {
                        g.drawImage(getImage("images/units/" + age + "/" + hashMap[i][j].name + ".PNG"), i * 32, j * 32, null);
                    } else {
                        g.drawImage(getImage("images/units/" + age + "/selected/" + hashMap[i][j].name + ".PNG"), i * 32, j * 32, null);
                    }
                }
            }
        }
    }

    private void makeUnitMove(int x, int y) {
        if (hashMap[oldX / 32][oldY / 32].alreadyMoved) {
            txt = txt + "You already moved that unit!\n";
            txt = txt + "\n";
            return;
        }
        if(isUnitInCloseCombat(oldX/32, oldY/32)){
            txt = txt + "Your unit may not move out of close combat!\n";
            txt = txt + "\n";
            return;
        }

        int ap = oldX / 32 - clickedTileNrX;
        if (ap < 0) {
            ap = ap * -1;
        }
        int dp = oldY / 32 - clickedTileNrY;
        if (dp < 0) {
            dp = dp * -1;
        }
        int distance = ap + dp;
        if (distance < 0) {
            distance = distance * -1;
        }

        if (x < 32 && x > 0 && y < 24 && y > 0) {
            if (envMap[x - 1][y - 1].walkable) {
                if (distance <= currentSelection.movement) {
                    if (currentSelection.unitType == Constants.UNIT_ARTILLERY || currentSelection.unitType == Constants.UNIT_MACHINEGUN) {
                        currentSelection.alreadyAttacked = true;
                    }
                    hashMap[oldX / 32][oldY / 32] = null;
                    hashMap[x - 1][y - 1] = currentSelection;
                    hashMap[x - 1][y - 1].alreadyMoved = true;
                } else {
                    txt = txt + "That is too far for ur unit to move!\n";
                    txt = txt + "\n";
                }
            } else {
                txt = txt + "This tile is not walkable!\n";
                txt = txt + "\n";
            }
        }
    }

    private boolean isUnitInCloseCombat(int i, int j) {
        if(hashMap[i-1][j] != null && !hashMap[i-1][j].playerName.equals(selectedPlayer)){
            return true;
        }
        if(hashMap[i+1][j] != null && !hashMap[i+1][j].playerName.equals(selectedPlayer)){
            return true;
        }
        if(hashMap[i][j-1] != null && !hashMap[i][j-1].playerName.equals(selectedPlayer)){
            return true;
        }
        if(hashMap[i][j+1] != null && !hashMap[i][j+1].playerName.equals(selectedPlayer)){
            return true;
        }
        return false;
    }

    private void makeUnitAttack(Unit attacker, Unit defender) {
        if (attacker.alreadyAttacked) {
            txt = txt + "You already attacked with this unit!\n";
            txt = txt + "\n";
            return;
        }
        int ap = oldX / 32 - clickedTileNrX;
        if (ap < 0) {
            ap = ap * -1;
        }
        int dp = oldY / 32 - clickedTileNrY;
        if (dp < 0) {
            dp = dp * -1;
        }
        int distance = ap + dp;
        if (distance < 0) {
            distance = distance * -1;
        }
        if (distance == 0) {
            distance = 2;
        }

        LOSCheck los = new LOSCheck(clickedTileNrY, clickedTileNrX, oldY/32, oldX/32, envMap);
        if(!los.hasUnitLOS()){
            txt = txt + "The target is out of sight!\n";
            txt = txt + "\n";
            hashMap[oldX / 32][oldY / 32].alreadyAttacked = true;
        }
        
        if (distance == 1) {
            int attacks = 0;
            if (attacker.gear.isMeleeWeapon) {
                attacks = attacker.attacks + attacker.gear.rateOfFire;
            } else {
                attacks = attacker.attacks;
            }
            for (int i = 0; i < attacks; i++) {
                if (c.meleeAttack(attacker, defender)) {
                    if (c.wound(attacker, defender, true)) {
                        if (c.coverSaveFails(attacker, defender, getDefendersCover(), true)) {
                            targetGetsDamaged();
                        }
                    }
                }
            }
        } else if (distance <= attacker.range || (attacker.gear.isMeleeWeapon && distance <= attacker.gear.range)) {
            int attacks = 0;
            if (attacker.gear.isMeleeWeapon) {
                attacks = attacker.attacks + attacker.gear.rateOfFire;
            } else {
                attacks = attacker.gear.rateOfFire;
            }
            for (int i = 0; i < attacks; i++) {
                if (c.meleeAttack(attacker, defender)) {
                    if (c.wound(attacker, defender, true)) {
                        if (c.coverSaveFails(attacker, defender, getDefendersCover(), true)) {
                            targetGetsDamaged();
                        }
                    }
                }
            }
        } else if (distance <= attacker.gear.range) {
            targetTileX = clickedTileNrX;
            targetTileY = clickedTileNrY;
            for (int i = 0; i < attacker.gear.rateOfFire; i++) {
                if (attacker.unitType == Constants.UNIT_INFANTRY || attacker.unitType == Constants.UNIT_ANTITANK) {
                    if (c.shoot(attacker, defender)) {
                        if (c.wound(attacker, defender, false)) {
                            if (c.coverSaveFails(attacker, defender, getDefendersCover(), false)) {
                                targetGetsDamaged();
                            }
                        }
                    }
                } else if (attacker.unitType == Constants.UNIT_ARTILLERY) {
                    int abberation = c.getArtilleryTarget(attacker);
                    if (abberation == 0) {
                        if (c.wound(attacker, defender, false)) {
                            if (c.coverSaveFails(attacker, defender, getDefendersCover(), false)) {
                                targetGetsDamaged();
                                if (age == Constants.WW2) {
                                    hashMap[clickedTileNrX][clickedTileNrY] = explosion;
                                } else if (age == Constants.FANTASY) {
                                    hashMap[clickedTileNrX][clickedTileNrY] = catapultRock;
                                }
                            }
                        }
                    } else {
                        aquireNewArtilleryTarget(abberation);
                        if (hashMap[clickedTileNrX][clickedTileNrY] != null) {
                            txt = txt + "Your artillery has hitted " + hashMap[clickedTileNrX][clickedTileNrY].name + " owned by " + hashMap[clickedTileNrX][clickedTileNrY].playerName + "\n";
                            if (c.wound(attacker, hashMap[clickedTileNrX][clickedTileNrY], false)) {
                                if (c.coverSaveFails(attacker, hashMap[clickedTileNrX][clickedTileNrY], getDefendersCover(), false)) {
                                    targetGetsDamaged();
                                    if (age == Constants.WW2) {
                                        hashMap[clickedTileNrX][clickedTileNrY] = explosion;
                                    } else if (age == Constants.FANTASY) {
                                        hashMap[clickedTileNrX][clickedTileNrY] = catapultRock;
                                    }
                                }
                            }
                        } else {
                            if (age == Constants.WW2) {
                                hashMap[clickedTileNrX][clickedTileNrY] = explosion;
                            } else if (age == Constants.FANTASY) {
                                hashMap[clickedTileNrX][clickedTileNrY] = catapultRock;
                            }
                            txt = txt + "Your artillery has hitted an empty tile!\n";
                            txt = txt + "\n";
                        }
                    }
                } else if (attacker.unitType == Constants.UNIT_VEHICLE) {
                    txt = txt + "Not yet implemented!\n";
                    txt = txt + "\n";
                } else if (attacker.unitType == Constants.UNIT_MACHINEGUN) {
                    makeMGSpread(attacker);
                    for (int j = 0; j < attacker.attacks; j++) {
                        if (hashMap[clickedTileNrX][clickedTileNrY] != null && !hashMap[clickedTileNrX][clickedTileNrY].playerName.equals("Environment")) {
                            txt = txt + "Your machinegun has hitted " + hashMap[clickedTileNrX][clickedTileNrY].name + " owned by " + hashMap[clickedTileNrX][clickedTileNrY].playerName + "\n";
                            if (c.wound(attacker, hashMap[clickedTileNrX][clickedTileNrY], false)) {
                                if (c.coverSaveFails(attacker, hashMap[clickedTileNrX][clickedTileNrY], getDefendersCover(), false)) {
                                    hashMap[clickedTileNrX][clickedTileNrY] = hailOfBullets;
                                    targetGetsDamaged();
                                }
                            }
                        } else {
                            hashMap[clickedTileNrX][clickedTileNrY] = hailOfBullets;
                        }
                    }
                }
            }
        } else {
            txt = txt + "The target is out of range!\n";
            txt = txt + "\n";
        }
        hashMap[oldX / 32][oldY / 32].alreadyAttacked = true;
    }

    private void targetGetsDamaged() {
        hashMap[clickedTileNrX][clickedTileNrY].hp = hashMap[clickedTileNrX][clickedTileNrY].hp-1;
        if(hashMap[clickedTileNrX][clickedTileNrY].hp <= 0){
            removeUnit();
        }else{
            txt = txt + "The target unit has lost one Hitpoint!\n";
            txt = txt + "\n";
        } 
    }

    private void removeUnit() {
        if(hashMap[clickedTileNrX][clickedTileNrY] != null){
            if (isDeleting) {
                for (int i = 0; i < playerRessources.size(); i++) {
                    if (playerRessources.get(i).nameOfThePlayer.equals(selectedPlayer)) {
                        playerRessources.get(i).pointLimitForPlayer = playerRessources.get(i).pointLimitForPlayer + hashMap[clickedTileNrX][clickedTileNrY].costs;
                        txt = txt + listSelection.name + " sold.\n";
                        txt = txt + "You have " + playerRessources.get(i).pointLimitForPlayer + " Points left to spend!\n";
                        txt = txt + "\n";
                    }
                }
            } else {
                for (int i = 0; i < playerRessources.size(); i++) {
                    if (playerRessources.get(i).nameOfThePlayer.equals(hashMap[clickedTileNrX][clickedTileNrY].playerName)) {
                        playerRessources.get(i).unitCount -= 1;
                        if (playerRessources.get(i).unitCount <= 0) {
                            JOptionPane.showMessageDialog(null, "Defeat!", hashMap[clickedTileNrX][clickedTileNrY].playerName + " has lost the game!", 2);
                        }
                    }
                }
            }
            hashMap[clickedTileNrX][clickedTileNrY] = null;
            repaint();
        }else if(envMap[clickedTileNrX][clickedTileNrY].isStructure){
            createRandomTileAtLocation(clickedTileNrX, clickedTileNrY);
        }
    }

    private void freeSelectionFromUnit() {
        setSelected(false);
        currentSelection = null;
        changeAttributePanel(currentSelection);
        repaint();
    }

    private void createUnit(int x, int y) {
        if (listSelection != null) {
            if (envMap[x][y].walkable) {
                if (listSelection.race != Constants.RACE_ENVIRONMENT) {
                    for (int i = 0; i < playerRessources.size(); i++) {
                        if (playerRessources.get(i).nameOfThePlayer.equals(selectedPlayer)) {
                            if (playerRessources.get(i).pointLimitForPlayer >= listSelection.costs) {
                                playerRessources.get(i).unitCount += 1;
                                hashMap[x][y] = new Unit(listSelection, selectedPlayer);
                                playerRessources.get(i).pointLimitForPlayer = playerRessources.get(i).pointLimitForPlayer - listSelection.costs;
                                txt = txt + listSelection.name + " bought.\n";
                                txt = txt + playerRessources.get(i).nameOfThePlayer + " has " + playerRessources.get(i).pointLimitForPlayer + " Points left to spend!\n";
                                txt = txt + "\n";

                            } else {
                                txt = txt + playerRessources.get(i).nameOfThePlayer + " has insufficient founds!\n";
                                txt = txt + "\n";
                            }
                        }
                    }
                    freeSelectionFromUnit();
                } else {
                    envMap[x][y] = new Environment("images/environment/" + listSelection.name, 4, listSelection.alreadyMoved, listSelection.alreadyAttacked, true);
                }
            } else {
                txt = txt + "You may not place a unit there!\n";
                txt = txt + "\n";
            }
        } else {
            txt = txt + "You have not selected a unit to deploy!\n";
            txt = txt + "\n";
        }
    }

    private void changeAttributePanel(Unit unit) {
        if (unit != null) {
//            name.setText(unit.name);
//            movement.setText("MV: " + unit.movement);
//            strenght.setText("S: " + unit.strength);
//            toughness.setText("T: " + unit.toughness);
//            melee.setText("WS: " + unit.melee);
//            attacks.setText("A: " + unit.attacks);
//            range.setText("R: " + unit.range);
//            armor.setText("AS: " + unit.armorsave);
//            gear.setText("G: " + unit.gear.name + "(" + unit.gear.range + "/" + unit.gear.strength + "/" + unit.gear.armorPenetration + "/" + unit.gear.rateOfFire + ")");
//            owner.setText("O: " + unit.playerName);
//            rangedSkill.setText("BS: " + unit.rangedSkill);
//            hp.setText("HP: " + unit.hp + "/" + unit.maxHp);
//            type.setText("TYPE: " + ut.getUnitType(unit.unitType));
//            alreadyMoved.setText("Moved: " + unit.alreadyMoved);
//            alreadyAttacked.setText("Attacked: " + unit.alreadyAttacked);
        } else {
//            name.setText("");
//            movement.setText("MV: ");
//            strenght.setText("S: ");
//            toughness.setText("T: ");
//            melee.setText("WS: ");
//            attacks.setText("A: ");
//            range.setText("R: ");
//            armor.setText("AS: ");
//            gear.setText("G: ");
//            owner.setText("O: ");
//            rangedSkill.setText("BS: ");
//            hp.setText("HP: ");
//            type.setText("TYPE: ");
//            alreadyMoved.setText("Moved: ");
//            alreadyAttacked.setText("Attacked: ");
        }
    }

    public JPanel attributePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        panel.setBackground(Color.black);

//        JLabel[] allLabels = { name, space, movement, range, strenght, toughness, melee, attacks, rangedSkill, gear, armor, owner, alreadyMoved, alreadyAttacked, hp, type };
//
//        for (int i = 0; i < allLabels.length; i++) {
//            allLabels[i].setBackground(Color.black);
//            allLabels[i].setForeground(Color.white);
//        }
//
//        panel.add(name);
//        panel.add(space);
//        panel.add(movement);
//        panel.add(range);
//        panel.add(strenght);
//        panel.add(toughness);
//        panel.add(melee);
//        panel.add(attacks);
//        panel.add(rangedSkill);
//        panel.add(gear);
//        panel.add(armor);
//        panel.add(owner);
//        panel.add(hp);
//        panel.add(type);
//        panel.add(alreadyMoved);
//        panel.add(alreadyAttacked);

        return panel;
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
    }
    private void createRandomTileAtLocation(int i, int j){
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

    public void setListSelection(Unit listSelection) {
        this.listSelection = listSelection;
    }

    private boolean isDeploying() {
        return isDeploying;
    }

    private boolean isMoving() {
        return isMoving;
    }

    public void setSelectedPlayer(String actionCommand) {
        freeSelectionFromUnit();
        this.selectedPlayer = actionCommand;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    private int getDefendersCover() {
        return envMap[clickedTileNrX][clickedTileNrY].cover;
    }

    public void setHashMap(Unit[][] hashMap) {
        this.hashMap = hashMap;
    }
    
    public void setDeploying(boolean isDeploying){
        this.isDeploying = isDeploying;
    }

    public void setAge(int age) {
        this.age = age;
        createMapTerrain();
        setPointLimitAndResetGame(0);
    }

    public void setAction(String ac) {
        if (ac.equals("Move")) {
            isMoving = true;
            isAttacking = false;
            isDeleting = false;
            isDeploying = false;
            //            isDefending = false;
        } else if (ac.equals("Attack")) {
            isMoving = false;
            isAttacking = true;
            isDeleting = false;
            isDeploying = false;
            //          isDefending = false;
        } else if (ac.equals("Defend")) {
            isMoving = false;
            isAttacking = false;
            isDeleting = false;
            isDeploying = false;
            //          isDefending = true;
        } else if (ac.equals("Buy Unit")) {
            isMoving = false;
            isAttacking = false;
            isDeleting = false;
            isDeploying = true;
            //          isDefending = false;
        } else if (ac.equals("Sell Unit")) {
            isMoving = false;
            isAttacking = false;
            isDeleting = true;
            isDeploying = false;
            //          isDefending = false;
        }
    }

    public void setPointLimitAndResetGame(int points) {
        if (points > 0) {
            pointLimit = points;
        }
        currentSelection = null;
        for (int i = 0; i < hashMap.length; i++) {
            for (int j = 0; j < hashMap[i].length; j++) {
                hashMap[i][j] = null;
            }
        }
        repaint();
    }

    public int getPointLimit() {
        return pointLimit;
    }

    public void restoreUnits() {
        for (int i = 0; i < hashMap.length; i++) {
            for (int j = 0; j < hashMap[i].length; j++) {
                if (hashMap[i][j] != null && hashMap[i][j].playerName.equals(selectedPlayer)) {
                    hashMap[i][j].alreadyAttacked = false;
                    hashMap[i][j].alreadyMoved = false;
                } else if (hashMap[i][j] != null && hashMap[i][j].playerName.equals("Environment")) {
                    hashMap[i][j] = null;
                }
            }
        }
    }

    public void setPlayerPoints(String[] name) {
        if (playerRessources.size() > 0) {
            for (int i = 0; i < name.length; i++) {
                playerRessources.remove(i);
            }
        }

        for (int i = 0; i < name.length; i++) {
            playerRessources.add(new PlayerRessources(pointLimit, 0, name[i]));
        }
    }

    private void aquireNewArtilleryTarget(int abberation) {
        Random ran = new Random();
        if (abberation == 1) {
            int dice = ran.nextInt(2) + 1;
            if (dice == 1) {
                dice = ran.nextInt(2) + 1;
                if (dice == 1) {
                    clickedTileNrX = clickedTileNrX - abberation;
                } else {
                    clickedTileNrX = clickedTileNrX + abberation;
                }
            } else {
                dice = ran.nextInt(2) + 1;
                if (dice == 1) {
                    clickedTileNrY = clickedTileNrY - abberation;
                } else {
                    clickedTileNrY = clickedTileNrY + abberation;
                }
            }
        } else {
            int dice = ran.nextInt(4) + 1;
            if (dice == 1) {
                dice = ran.nextInt(2) + 1;
                if (dice == 1) {
                    clickedTileNrX = clickedTileNrX - abberation;
                } else {
                    clickedTileNrX = clickedTileNrX + abberation;
                }
            } else if (dice == 2) {
                dice = ran.nextInt(2) + 1;
                if (dice == 1) {
                    clickedTileNrY = clickedTileNrY - abberation;
                } else {
                    clickedTileNrY = clickedTileNrY + abberation;
                }
            } else if (dice == 3) {
                dice = ran.nextInt(2) + 1;
                if (dice == 1) {
                    clickedTileNrX = clickedTileNrX - 1;
                    clickedTileNrY = clickedTileNrY - 1;
                } else {
                    clickedTileNrX = clickedTileNrX + 1;
                    clickedTileNrY = clickedTileNrY + 1;
                }
            } else if (dice == 4) {
                dice = ran.nextInt(2) + 1;
                if (dice == 1) {
                    clickedTileNrX = clickedTileNrX - 1;
                    clickedTileNrY = clickedTileNrY + 1;
                } else {
                    clickedTileNrX = clickedTileNrX + 1;
                    clickedTileNrY = clickedTileNrY - 1;
                }
            }
        }
    }

    private void makeMGSpread(Unit attacker) {
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        dice = dice - attacker.rangedSkill;
        if (dice > 0) {
            dice = ran.nextInt(2) + 1;
            if (dice == 1) {
                dice = ran.nextInt(2) + 1;
                if (dice == 1) {
                    clickedTileNrX = targetTileX - 1;
                } else {
                    clickedTileNrX = targetTileX + 1;
                }
            } else {
                dice = ran.nextInt(2) + 1;
                if (dice == 1) {
                    clickedTileNrY = targetTileY - 1;
                } else {
                    clickedTileNrY = targetTileY + 1;
                }
            }
        }
    }
    
    private Image getImage(String path) {
        String imgLocation = null;
        ClassLoader cl = this.getClass().getClassLoader();
        URL imageURL = null;

        imgLocation = "images/symbols/move.PNG";
        imgLocation = "images/symbols/attack.PNG";
        imgLocation = "images/symbols/stop.PNG";
        imgLocation = "images/symbols/turn.PNG";
        imgLocation = path;
        imageURL = cl.getResource(imgLocation);
        if (imageURL != null) {
            return new ImageIcon(imageURL).getImage();
        } else {
            return new ImageIcon(imgLocation).getImage();
        }
    }
    
    private class PlayerRessources {
        int pointLimitForPlayer = 0;
        int unitCount = 0;
        String nameOfThePlayer = null;

        public PlayerRessources(int limit, int unitCount, String name) {
            this.pointLimitForPlayer = limit;
            this.unitCount = unitCount;
            this.nameOfThePlayer = name;
        }
    }
}
