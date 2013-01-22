package client.singleplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.Main;

import lib.Constants;

import database.UnitDatabaseFantasy;
import database.UnitDatabaseFuture;
import database.UnitDatabaseWW2;
import lib.Unit;
import lib.UnitRaces;

public class SingleplayerFrame {

    String[] players = null;
    JComboBox playerList;
    SingleplayerGameBoard gamePanel = null;
    JComboBox raceList = null;
    JComboBox unitList = null;

    final UnitDatabaseFantasy ubfantasy = new UnitDatabaseFantasy();
    final UnitDatabaseWW2 ubww2 = new UnitDatabaseWW2();
    final UnitDatabaseFuture ubfuture = new UnitDatabaseFuture();

    Unit[] unitsFuture = ubfuture.returnUnits();
    Unit[] unitsFantasy = ubfantasy.returnUnits();
    Unit[] unitsWW2 = ubww2.returnUnits();

    String[] races = null;

    Unit[] unitsOfRace = null;

    int actAge = Constants.FANTASY;

    UnitRaces ur = new UnitRaces();

    JRadioButton btnMove = new JRadioButton("Move");
    JRadioButton btnAttack = new JRadioButton("Attack");
    JRadioButton btnDefend = new JRadioButton("Defend");
    JRadioButton btnDelete = new JRadioButton("Sell Unit");
    JRadioButton btnDeploy = new JRadioButton("Buy Unit");
    JRadioButton btnEndTurn = new JRadioButton("Finish");

    JMenuItem menuItemChangePhase;

    final JLabel owningPlayer = new JLabel("Choose a player and a unit:");

    private boolean deploymentPhase = true;
    private boolean changingAge = false;
    
    JFrame frame;

    public SingleplayerFrame() {

    }
    
    private void hide(){
        frame.dispose();
        gamePanel.dlg.dispose();
        gamePanel.c.dlg.dispose();
    }

    public void createFrame(int points, int age) {
        frame = new JFrame("Eisenkreuzkrieger");
        frame.setSize(1030, 800);
        frame.setTitle("Eisenkreuzkrieger");
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                hide();
                Main.showMenu();
            }
        });

        String s = (String)JOptionPane.showInputDialog(null, "Please choose the amount of players participating in this game:\nFor default just press enter.", "Amaount of players", JOptionPane.QUESTION_MESSAGE, null, null, "");
        int count = 0;
        if ((s != null)) {
            if (s.length() <= 0) {
                players = new String[2];
                players[0] = "Player 1";
                players[1] = "Player 2";
            } else {
                try {
                    count = Integer.parseInt(s);
                    players = new String[count];
                    for (int i = 0; i < count; i++) {
                        String name = (String)JOptionPane.showInputDialog(null, "Please enter " + (i + 1) + "'s player name:\n", "Choose playername", JOptionPane.QUESTION_MESSAGE, null, null, "");
                        if ((name != null) && (name.length() > 0)) {
                            players[i] = name;
                        } else {
                            players[i] = "Player " + (i + 1);
                        }
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Playercount set to 2 with default names!", "Invalid number!", JOptionPane.ERROR_MESSAGE);
                    players = new String[2];
                    players[0] = "Player 1";
                    players[1] = "Player 2";
                }
            }
        } else {
            System.exit(0);
        }

        playerList = new JComboBox(players);

        JPanel commandPanel = new JPanel();
        gamePanel = new SingleplayerGameBoard();
        JPanel buttonPanel = new JPanel();

        createImagesForButtons();

        btnMove.setMnemonic(KeyEvent.VK_M);
        btnAttack.setMnemonic(KeyEvent.VK_A);
        btnDefend.setMnemonic(KeyEvent.VK_D);
        btnDeploy.setMnemonic(KeyEvent.VK_B);
        btnDelete.setMnemonic(KeyEvent.VK_S);
        btnEndTurn.setMnemonic(KeyEvent.VK_F);

        btnMove.setToolTipText("Moves a own selected Unit to a field in movementrange");
        btnAttack.setToolTipText("Attacks an Unit in range. Melee or Ranged, doesnt matter.");
        btnDefend.setToolTipText("Makes the Unit stay really really cool at facing attacks!");
        btnDeploy.setToolTipText("Deploys a Unit selected from the Unit-Box at a free field.");
        btnDelete.setToolTipText("Deletes the target Unit from the battlefield!");
        btnEndTurn.setToolTipText("Ends your turn so the other player can make his moves!");

        btnMove.setForeground(Color.white);
        btnAttack.setForeground(Color.white);
        btnDefend.setForeground(Color.white);
        btnDeploy.setForeground(Color.white);
        btnDelete.setForeground(Color.white);
        btnEndTurn.setForeground(Color.white);

        btnDeploy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                gamePanel.setAction(arg0.getActionCommand());
            }
        });

        btnMove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                gamePanel.setAction(arg0.getActionCommand());
            }
        });

        btnAttack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                gamePanel.setAction(arg0.getActionCommand());
            }
        });

        btnDefend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                gamePanel.setAction(arg0.getActionCommand());
            }
        });

        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                gamePanel.setAction(arg0.getActionCommand());
            }
        });

        btnEndTurn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                gamePanel.restoreUnits();
                changePlayer();
                owningPlayer.setText("Its " + players[playerList.getSelectedIndex()] + " turn");
            }
        });

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(btnMove);
        btnGroup.add(btnAttack);
        btnGroup.add(btnDefend);
        btnGroup.add(btnDeploy);
        btnGroup.add(btnDelete);
        btnGroup.add(btnEndTurn);

        commandPanel.setBackground(Color.black);
        gamePanel.setBackground(Color.green);

        frame.setLayout(new BorderLayout());

        buttonPanel.setLayout(new GridLayout(2, 3));
        buttonPanel.setBackground(Color.black);

        buttonPanel.add(btnMove);
        btnMove.setBackground(Color.black);
        buttonPanel.add(btnAttack);
        btnAttack.setBackground(Color.black);
        buttonPanel.add(btnDefend);
        btnDefend.setBackground(Color.black);
        buttonPanel.add(btnEndTurn);
        btnEndTurn.setBackground(Color.black);
        buttonPanel.add(btnDelete);
        btnDelete.setBackground(Color.black);
        buttonPanel.add(btnDeploy);
        btnDeploy.setBackground(Color.black);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuProp = new JMenu("Properties");
        menuProp.setMnemonic(KeyEvent.VK_P);
        JMenu menuHelp = new JMenu("About");
        menuHelp.setMnemonic(KeyEvent.VK_A);

        gamePanel.setPointLimitAndResetGame(points);
//        gamePanel.setPlayerPoints(players);
        gamePanel.setAge(age);
        this.actAge = age;
        
        JMenuItem menuItemOpenHelp = new JMenuItem("Help");

        menuItemOpenHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                final JDialog dlg = new JDialog();
                JTextArea txt = new JTextArea();
                JScrollPane scrPane = new JScrollPane(txt);

                dlg.setTitle("Help");
                dlg.setSize(900, 700);
                FileReader fr;
                BufferedReader br;
                try {
                    fr = new FileReader("Manual.txt");
                    br = new BufferedReader(fr);
                    String s;
                    while ((s = br.readLine()) != null) {
                        txt.append(s + "\n");
                    }
                    fr.close();
                    br.close();
                } catch (FileNotFoundException e) {
                    txt.append("File not found!\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dlg.setAlwaysOnTop(true);
                dlg.add(scrPane);
                dlg.setLocation(300, 0);
                dlg.setVisible(true);
            }
        });

        menuItemChangePhase = new JMenuItem("CHANGE TO BATTLE-PHASE!");
        menuItemChangePhase.setToolTipText("Start the game!");

        playerList.setBackground(Color.black);
        playerList.setForeground(Color.white);
        playerList.setSelectedIndex(0);
        gamePanel.setSelectedPlayer(players[0]);
        gamePanel.setPlayerPoints(players);
        playerList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                gamePanel.setSelectedPlayer(players[playerList.getSelectedIndex()]);
            }
        });

        Unit[] units = null;
        System.out.println(age);
        if(age == Constants.FANTASY){
            units = ur.getUnitsOfRaceAndAge(Constants.RACE_ORKS, age);
            races = ur.returnFantasyRaces();
        }else if (age == Constants.SCIFI){
            units = ur.getUnitsOfRaceAndAge(Constants.RACE_ORKS, age);
            races = ur.returnFutureRaces();
        }else if (age == Constants.WW2){
            units = ur.getUnitsOfRaceAndAge(Constants.RACE_AXIS, age);
            races = ur.returnWW2Races();
        }
        String[] unitNames = new String[units.length];
        unitsOfRace = new Unit[units.length];
        for (int i = 0; i < units.length; i++) {
            unitsOfRace[i] = units[i];
        }
        
        raceList = new JComboBox(races);
        raceList.setBackground(Color.black);
        raceList.setForeground(Color.white);
        raceList.setSelectedIndex(0);
        raceList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                changeRace();
            }
        });
        
        for (int i = 0; i < units.length; i++) {
            if (units[i].race == races[raceList.getSelectedIndex()]) {
                unitNames[i] = units[i].name + "(" + unitsOfRace[i].costs + " Pts.)";
            }
        }

        unitList = new JComboBox(unitNames);
        unitList.setBackground(Color.black);
        unitList.setForeground(Color.white);
        unitList.setSelectedIndex(0);
        gamePanel.setListSelection(units[0]);
        unitList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                setGamePanelListSelection();
            }
        });

        menuItemChangePhase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                changePhase();
            }
        });
        
        menuProp.add(menuItemChangePhase);
        menuHelp.add(menuItemOpenHelp);
        menuBar.add(menuProp);
        menuBar.add(menuHelp);

        commandPanel.setLayout(new GridLayout(1, 3));

        JPanel deployPanel = new JPanel();
        deployPanel.setLayout(new GridLayout(4, 1));
        deployPanel.setBackground(Color.black);
        deployPanel.setForeground(Color.white);
        owningPlayer.setBackground(Color.black);
        owningPlayer.setForeground(Color.white);
        raceList.setBackground(Color.black);
        raceList.setForeground(Color.white);
        deployPanel.add(owningPlayer);
        deployPanel.add(playerList);
        deployPanel.add(raceList);
        deployPanel.add(unitList);
        commandPanel.add(gamePanel.attributePanel());
        commandPanel.add(deployPanel);
        commandPanel.add(buttonPanel);

        frame.setJMenuBar(menuBar);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(commandPanel, BorderLayout.SOUTH);

        btnMove.setEnabled(false);
        btnAttack.setEnabled(false);
        btnDefend.setEnabled(false);
        btnDeploy.setEnabled(true);
        btnDelete.setEnabled(true);
        btnEndTurn.setEnabled(false);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void setGamePanelListSelection() {
        if (unitList.getItemCount() > 0) {
            if (unitList.getSelectedIndex() < 0) {
                unitList.setSelectedIndex(0);
            }
            //            if (unitList.getItemCount() < 0) {
            gamePanel.setListSelection(unitsOfRace[unitList.getSelectedIndex()]);
            //            }
        }
    }

    private void changePlayer() {
        if (playerList.getSelectedIndex() == players.length - 1) {
            playerList.setSelectedIndex(0);
            gamePanel.setSelectedPlayer(players[0]);
        } else {
            playerList.setSelectedIndex(playerList.getSelectedIndex() + 1);
            gamePanel.setSelectedPlayer(players[playerList.getSelectedIndex()]);
        }
    }

//    private void changeAge(int age) {
//        this.age = age;
//        changingAge = true;
//        gamePanel.setAge(age);
//        unitList.removeAllItems();
//
//        String[] raceHolder = ur.returnFantasyRaces();
//        if (age == "Fantasy") {
//            races = new String[raceHolder.length];
//            races = ur.returnFantasyRaces();
//        } else if (age == "Science Fiction") {
//            races = new String[raceHolder.length];
//            races = ur.returnFutureRaces();
//        } else if (age == "WW2") {
//            races = new String[raceHolder.length];
//            races = ur.returnWW2Races();
//        }
//        raceList.removeAllItems();
//        for (int i = 0; i < races.length; i++) {
//            raceList.addItem(races[i]);
//        }
//        raceList.setSelectedIndex(0);
//        for (int i = 0; i < unitsOfRace.length; i++) {
//            if (unitsOfRace[i] != null) {
//                unitList.addItem(unitsOfRace[i].name + "(" + unitsOfRace[i].costs + " Pts.)");
//            }
//        }
//        gamePanel.setListSelection(unitsOfRace[0]);
//        if (!deploymentPhase) {
//            gamePanel.setPointLimitAndResetGame(0);
//            gamePanel.setPlayerPoints(players);
//        }
//        changingAge = false;
//    }

    private void changePhase() {
        if (deploymentPhase) {
            btnMove.setEnabled(true);
            btnAttack.setEnabled(true);
            btnDefend.setEnabled(true);
            btnDeploy.setEnabled(false);
            btnDelete.setEnabled(false);
            btnEndTurn.setEnabled(true);
            btnMove.setSelected(true);
            owningPlayer.setText("Its " + players[0] + "'s turn");
            unitList.setEnabled(false);
            playerList.setEnabled(false);
            raceList.setEnabled(false);
            gamePanel.setSelectedPlayer(players[0]);
            gamePanel.setDeploying(false);
            playerList.setSelectedIndex(0);
            deploymentPhase = false;
            menuItemChangePhase.setText("CHANGE TO DEPLOYMENT-PHASE!");
            menuItemChangePhase.setToolTipText("Create a new game!");
        } else {
            btnMove.setEnabled(false);
            btnAttack.setEnabled(false);
            btnDefend.setEnabled(false);
            btnDeploy.setEnabled(true);
            btnDelete.setEnabled(true);
            btnEndTurn.setEnabled(false);
            btnDeploy.setSelected(true);
            owningPlayer.setText("Owner of Unit to be bought:");
            unitList.setEnabled(true);
            playerList.setEnabled(true);
            raceList.setEnabled(true);
            gamePanel.setSelectedPlayer(players[0]);
            playerList.setSelectedIndex(0);
            deploymentPhase = true;
            menuItemChangePhase.setText("CHANGE TO BATTLE-PHASE!");
            menuItemChangePhase.setToolTipText("Start the game!");
        }
    }

    private void changeRace() {
        UnitRaces ur = new UnitRaces();
        Unit[] units = null;
        int selectedIndex = 0;
        if (raceList.getItemCount() <= 0) {
            selectedIndex = 0;
            units = ur.getUnitsOfRaceAndAge(races[selectedIndex], actAge);
        } else {
            selectedIndex = raceList.getSelectedIndex();
            units = ur.getUnitsOfRaceAndAge(races[selectedIndex], actAge);
        }
        String[] unitNames = new String[units.length];
        unitsOfRace = new Unit[units.length];
        for (int i = 0; i < units.length; i++) {
            unitsOfRace[i] = units[i];
        }
        for (int i = 0; i < units.length; i++) {
            if (units[i].race == races[selectedIndex]) {
                unitNames[i] = units[i].name + "(" + unitsOfRace[i].costs + " Pts.)";
            }
        }
        unitList.removeAllItems();
        if (!changingAge) {
            for (int i = 0; i < unitsOfRace.length; i++) {
                if (unitsFantasy[i] != null) {
                    unitList.addItem(unitsOfRace[i].name + "(" + unitsOfRace[i].costs + " Pts.)");
                }
            }
        }
        gamePanel.setListSelection(unitsOfRace[0]);
    }

    private void createImagesForButtons() {
        String imgLocation = null;
        ClassLoader cl = SingleplayerFrame.class.getClassLoader();
        URL imageURL = null;

        imgLocation = "images/symbols/move.PNG";
        imageURL = cl.getResource(imgLocation);
        if (imageURL != null) {
            btnMove.setIcon(new ImageIcon(imageURL));
        } else {
            btnMove.setIcon(new ImageIcon(imgLocation));
        }

        imgLocation = "images/symbols/attack.PNG";
        imageURL = cl.getResource(imgLocation);
        if (imageURL != null) {
            btnAttack.setIcon(new ImageIcon(imageURL));
        } else {
            btnAttack.setIcon(new ImageIcon(imgLocation));
        }

        imgLocation = "images/symbols/stop.PNG";
        imageURL = cl.getResource(imgLocation);
        if (imageURL != null) {
            btnDefend.setIcon(new ImageIcon(imageURL));
        } else {
            btnDefend.setIcon(new ImageIcon(imgLocation));
        }

        imgLocation = "images/symbols/deploy.PNG";
        imageURL = cl.getResource(imgLocation);
        if (imageURL != null) {
            btnDeploy.setIcon(new ImageIcon(imageURL));
        } else {
            btnDeploy.setIcon(new ImageIcon(imgLocation));
        }

        imgLocation = "images/symbols/delete.PNG";
        imageURL = cl.getResource(imgLocation);
        if (imageURL != null) {
            btnDelete.setIcon(new ImageIcon(imageURL));
        } else {
            btnDelete.setIcon(new ImageIcon(imgLocation));
        }

        imgLocation = "images/symbols/turn.PNG";
        imageURL = cl.getResource(imgLocation);
        if (imageURL != null) {
            btnEndTurn.setIcon(new ImageIcon(imageURL));
        } else {
            btnEndTurn.setIcon(new ImageIcon(imgLocation));
        }
    }
}
