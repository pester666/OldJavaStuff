package client.multiplayer.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.Main;
import client.multiplayer.server.ChatServerGame;
import lib.Constants;
import lib.Player;
import lib.UnitRaces;

public class MultiplayerFrame {

    MultiplayerDeployPanel gamePanel = null;

    int actAge = Constants.FANTASY;

    private boolean isHost;
    
    UnitRaces ur = new UnitRaces();
    
    Polygon polyMove = null;
    Polygon polyAttack = null;
    Polygon polyDefend = null;
    Polygon polyEndTurn = null;
    Polygon polyTroopOverview = null;
    Polygon polyNextPlayer = null;

    JMenuItem menuItemChangePhase;
    
    ChatServerGame server = null;
    
    private String ipAdress;

    private boolean deploymentPhase = true;
    
    static Player[] p = new Player[5];
    
    JFrame frame;
    
    public MultiplayerFrame(Player[] players, boolean isHost, String ipAdress) {
        this.isHost = isHost;
        this.ipAdress = ipAdress;
        p = players;
    }
    
    private void hide(){
        frame.dispose();
    }
    
    private void init(){
        int[] xCoords = new int[] { 50, 60, 210, 220, 220, 210, 60, 50 };

        int[] yCoords = new int[] { 250, 240, 240, 250, 270, 280, 280, 270 };

        polyMove = new Polygon(xCoords, yCoords, 8);
    }

    public void createFrame(int age) {
        if(isHost){
            server = new ChatServerGame();
            server.start();
        }
        frame = new JFrame("Eisenkreuzkrieger");
        frame.setBackground(Color.black);
        frame.setSize(1230, 800);
        frame.setTitle("Eisenkreuzkrieger");
        this.init();
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                hide();
                Main.showMenu();
            }
        });
        String[] pl = new String[p.length];
        for (int i = 0; i < p.length; i++) {
            pl[i] = p[i].getName();
        }
        
        JPanel commandPanel = new JPanel();
        gamePanel = new MultiplayerDeployPanel(isHost, ipAdress);

        commandPanel.setBackground(Color.black);
        gamePanel.setBackground(Color.green);

        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuProp = new JMenu("Properties");
        menuProp.setMnemonic(KeyEvent.VK_P);
        JMenu menuHelp = new JMenu("About");
        menuHelp.setMnemonic(KeyEvent.VK_A);

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
               
        menuItemChangePhase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                //TODO if host nur dann menü anzeigen
                changePhase();
            }
        });
        
        menuProp.add(menuItemChangePhase);
        menuHelp.add(menuItemOpenHelp);
        menuBar.add(menuProp);
        menuBar.add(menuHelp);

        commandPanel.setLayout(new GridLayout(1, 3));

        JPanel deployPanel = new JPanel();
        deployPanel.setLayout(new GridLayout(2, 1));
        deployPanel.setBackground(Color.black);
        deployPanel.setForeground(Color.white);
        commandPanel.add(deployPanel);

        frame.setJMenuBar(menuBar);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(commandPanel, BorderLayout.SOUTH);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void changePhase() {
        if (deploymentPhase) {
            deploymentPhase = false;
            menuItemChangePhase.setText("CHANGE TO DEPLOYMENT-PHASE!");
            menuItemChangePhase.setToolTipText("Create a new game!");
        } else {
            deploymentPhase = true;
            menuItemChangePhase.setText("CHANGE TO BATTLE-PHASE!");
            menuItemChangePhase.setToolTipText("Start the game!");
        }
    }
}
