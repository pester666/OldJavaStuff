package client.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import client.gui.hire.Heroes;
import client.gui.hire.Warbands;
import database.warbands.undead.heroes.Vampire;
import lib.Hero;
import lib.Warband;

public class Hire extends MenuPanel {

    private Image imgBook = new ImageIcon("res/img/hire/book.png").getImage();
    private Image imgPen = new ImageIcon("res/img/hire/bookPen.png").getImage();
    private Image imgMarker = new ImageIcon("res/img/hire/bookMarker.png").getImage();
    private Image imgPage = new ImageIcon("res/img/hire/bookPage.png").getImage();
    
    private boolean pageOpen = false;
    private boolean markerOpen = false;
    private boolean penOpen = false;
    private boolean isChoosingWarband = true;
    private Polygon polyPage;
    private Polygon polyPen;
    private Polygon polyMarker;
    private Thread t;
    private Warbands warbands;
    private Heroes heroes;
    private int warbandCounter = 0;
    private int heroCounter = 0;
    private String warbandName;
    
    public static boolean isActive = false;

    public Hire(boolean active) {
        this.setSize(500, 500);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        warbands = new Warbands();
        heroes = new Heroes();
        t = new Thread(anim);
        
        if (active && !t.isAlive()) {
            this.t.start();
        }
        
        int[] x = new int[] { 245, 295, 295, 245 };

        int[] y = new int[] { 400, 400, 450, 450 };
        
        polyPage = new Polygon(x, y, 4);
        
        x = new int[] { 5, 60, 60, 5 };

        y = new int[] { 415, 415, 475, 475 };
        
        polyMarker = new Polygon(x, y, 4);
                
        x = new int[] { 350, 465, 465, 350 };

        y = new int[] { 230, 230, 365, 365 };
        
        polyPen = new Polygon(x, y, 4);
        
        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (isActive) {
                    if (polyPage.contains(e.getPoint())) {
                        pageOpen = true;
                        markerOpen = false;
                        penOpen = false;
                    } else if (polyPen.contains(e.getPoint())) {
                        pageOpen = false;
                        markerOpen = false;
                        penOpen = true;
                    } else if (polyMarker.contains(e.getPoint())) {
                        pageOpen = false;
                        markerOpen = true;
                        penOpen = false;
                    } else {
                        pageOpen = false;
                        markerOpen = false;
                        penOpen = false;
                    }
                }
            }
        });

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isActive) {
                    System.out.println(e.getX() + " " + e.getY());
                    if (polyPage.contains(e.getPoint())) {
                        if (isChoosingWarband) {
                            if (warbandCounter + 1 < warbands.getDescription().length) {
                                warbandCounter++;
                            }
                        }else{
                            if (heroCounter + 1 < heroes.getDescription().length) {
                                heroCounter++;
                            }
                        }
                    } else if (polyPen.contains(e.getPoint())) {
                        if(isChoosingWarband){
                            warbandName = getWarbandName();
                        }else{
                            saveWarband(warbandName);
                        }
                    } else if (polyMarker.contains(e.getPoint())) {
                        if (isChoosingWarband) {
                            if (warbandCounter > 0) {
                                warbandCounter--;
                            }
                        }else{
                            if (heroCounter > 0) {
                                heroCounter--;
                            } 
                        }
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
    
    private String getWarbandName(){
        String s = (String)JOptionPane.showInputDialog(
                            this,
                            "What is ur warband called?",
                            "Havn't heard of ye yet...",
                            JOptionPane.PLAIN_MESSAGE,
                            null, null, "");

        if ((s != null) && (s.length() > 0)) {
            isChoosingWarband = false;
            heroes.setHeroes(warbandCounter);
            return s;
        }
        return "U Failed Warband";
    }
    
    private String getHeroName(){
        String s = (String)JOptionPane.showInputDialog(
                            this,
                            "Who art thy anywhay?",
                            "And by the way...",
                            JOptionPane.PLAIN_MESSAGE,
                            null, null, "");

        if ((s != null) && (s.length() > 0)) {
            return s;
        }
        return "Chopped Tongue";
    }
    
    private Hero getHero(){
        Hero h = null;
        if(warbandCounter == 0){
            h = new Vampire(getHeroName(), null);
        }else if(warbandCounter == 1){
            
        }
        return h;
    }
    
    private void saveWarband(String name){
        try {
            Warband wb = new Warband(name, 500, getHero(), heroes.getHeraldikLocation(heroCounter));
            wb.setMaxUnits(15);
//            Warband wb = new Warband(name, 500, new ImageIcon(heroes.getHeraldik(heroCounter)), getHero());
            HireTroops.isActive = true;
            HireTroops.warbandName = warbandName;
            setActive(false);
            FileOutputStream fout = new FileOutputStream("res/warbands/" + name);
            ObjectOutputStream oos = new ObjectOutputStream(fout);   
            oos.writeObject(wb);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setActive(boolean active) {
        this.setVisible(active);
        this.setOpaque(active);
        isActive = active;
        if (active && !t.isAlive()) {
            this.t.start();
        }
    }

    public void paintComponent(Graphics g) {
        if (pageOpen) {
            g.drawImage(imgPage, 0, 0, null);
        } else if (penOpen) {
            g.drawImage(imgPen, 0, 0, null);
        } else if (markerOpen) {
            g.drawImage(imgMarker, 0, 0, null);
        } else {
            g.drawImage(imgBook, 0, 0, null);
        }
        if(isChoosingWarband){
            g.drawImage(warbands.getHeader(), 50, 50, null);
            g.drawImage(warbands.getHeraldik(warbandCounter), 30, 120, null);
            g.drawImage(warbands.getDescription(warbandCounter), 30, 245, null);
        }else{
            g.drawImage(heroes.getHeader(), 50, 50, null);
            g.drawImage(heroes.getHeraldik(heroCounter), 50, 50, null);
            g.drawImage(heroes.getDescription(heroCounter), 50, 50, null); 
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
    
    @Override
    public int getPanelToActive() {
        return 0;
    }

    @Override
    public int getPanelId() {
        return PanelIds.HIRE;
    }
}
