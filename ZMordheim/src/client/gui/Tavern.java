package client.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Tavern extends MenuPanel {

    private Image imgEmpty1 = new ImageIcon("res/img/tavern/tavernEmpty1.png").getImage();
    private Image imgEmpty2 = new ImageIcon("res/img/tavern/tavernEmpty2.png").getImage();
    private Image imgEmpty3 = new ImageIcon("res/img/tavern/tavernEmpty3.png").getImage();
    
    private Image imgDoor1 = new ImageIcon("res/img/tavern/tavernDoor1.png").getImage();
    private Image imgDoor2 = new ImageIcon("res/img/tavern/tavernDoor2.png").getImage();
    private Image imgDoor3 = new ImageIcon("res/img/tavern/tavernDoor3.png").getImage();
    
    private Image imgBar1 = new ImageIcon("res/img/tavern/tavernBar1.png").getImage();
    private Image imgBar2 = new ImageIcon("res/img/tavern/tavernBar2.png").getImage();
    private Image imgBar3 = new ImageIcon("res/img/tavern/tavernBar3.png").getImage();
    
    private Image imgTable1 = new ImageIcon("res/img/tavern/tavernTable1.png").getImage();
    private Image imgTable2 = new ImageIcon("res/img/tavern/tavernTable2.png").getImage();
    private Image imgTable3 = new ImageIcon("res/img/tavern/tavernTable3.png").getImage();
    private int animCounter = 1;
    private boolean doorOpen = false;
    private boolean barOpen = false;
    private boolean tableOpen = false;
    private Polygon polyDoor;
    private Polygon polyBar;
    private Polygon polyTable;
    private Thread t;
    public static boolean isActive = true;
    private int panelIdToActivate = 0;

    //goto foreign lands, wird später als feature eingebaut ;D

    public Tavern(boolean active) {
        this.setSize(500, 500);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        t = new Thread(anim);
        if (active) {
            t.start();
        }
        int[] x = new int[] { 110, 180, 180, 110 };

        int[] y = new int[] { 85, 85, 200, 200 };

        polyDoor = new Polygon(x, y, 4);

        x = new int[] { 200, 380, 380, 200 };

        y = new int[] { 80, 80, 155, 155 };

        polyBar = new Polygon(x, y, 4);

        x = new int[] { 270, 425, 425, 270 };

        y = new int[] { 300, 300, 380, 380 };

        polyTable = new Polygon(x, y, 4);

        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (isActive) {
                    if (polyDoor.contains(e.getPoint())) {
                        doorOpen = true;
                        barOpen = false;
                        tableOpen = false;
                    } else if (polyBar.contains(e.getPoint())) {
                        doorOpen = false;
                        barOpen = true;
                        tableOpen = false;
                    } else if (polyTable.contains(e.getPoint())) {
                        doorOpen = false;
                        barOpen = false;
                        tableOpen = true;
                    } else {
                        doorOpen = false;
                        barOpen = false;
                        tableOpen = false;
                    }
                }
            }
        });

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isActive) {
                    if (polyDoor.contains(e.getPoint())) {
                        Load.isActive = true;
                        setActive(false);
                    } else if (polyBar.contains(e.getPoint())) {
                        System.exit(JFrame.EXIT_ON_CLOSE);
                    } else if (polyTable.contains(e.getPoint())) {
                        Hire.isActive = true;
                        setActive(false);
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
    
    private Runnable anim = new Runnable() {

        @Override
        public void run() {
            while (isActive) {
                animate();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    };

    public void setActive(boolean active) {
        this.setVisible(active);
        this.setOpaque(active);
        isActive = active;
        if (active && !t.isAlive()) {
            this.t.start();
        }
    }

    private void animate() {
        if (animCounter == 3) {
            animCounter = 0;
        }
        animCounter++;
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        if (isActive) {
            if (doorOpen) {
                if (animCounter == 1) {
                    g.drawImage(imgDoor1, 0, 0, null);
                } else if (animCounter == 2) {
                    g.drawImage(imgDoor2, 0, 0, null);
                } else if (animCounter == 3) {
                    g.drawImage(imgDoor3, 0, 0, null);
                }
            } else if (barOpen) {
                if (animCounter == 1) {
                    g.drawImage(imgBar1, 0, 0, null);
                } else if (animCounter == 2) {
                    g.drawImage(imgBar2, 0, 0, null);
                } else if (animCounter == 3) {
                    g.drawImage(imgBar3, 0, 0, null);
                }
            } else if (tableOpen) {
                if (animCounter == 1) {
                    g.drawImage(imgTable1, 0, 0, null);
                } else if (animCounter == 2) {
                    g.drawImage(imgTable2, 0, 0, null);
                } else if (animCounter == 3) {
                    g.drawImage(imgTable3, 0, 0, null);
                }
            } else {
                if (animCounter == 1) {
                    g.drawImage(imgEmpty1, 0, 0, null);
                } else if (animCounter == 2) {
                    g.drawImage(imgEmpty2, 0, 0, null);
                } else if (animCounter == 3) {
                    g.drawImage(imgEmpty3, 0, 0, null);
                }
            }
        }
    }

    @Override
    public int getPanelToActive() {
        return panelIdToActivate;
    }

    @Override
    public int getPanelId() {
        return PanelIds.TAVERN;
    }
}
