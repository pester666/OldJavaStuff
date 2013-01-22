package client.gui.onceCreated;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

import client.gui.MenuPanel;

public class Hideout extends MenuPanel{

    public static boolean isActive = false;
    private Image imgPage = new ImageIcon("res/img/hire/bookPage.png").getImage();
    private Polygon polyPen;
    private Thread t;

    public Hideout(boolean active) {
        this.setSize(500, 500);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        t = new Thread(anim);

        if (active && !t.isAlive()) {
            this.t.start();
        }

        int[] x = new int[] { 350, 465, 465, 350 };

        int[] y = new int[] { 230, 230, 365, 365 };

        polyPen = new Polygon(x, y, 4);

        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (isActive) {
                  if(polyPen.contains(e.getPoint())){
                        
                    }
                }
            }
        });

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isActive) {

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

    public void paintComponent(Graphics g) {
        g.drawImage(imgPage, 0, 0, null);
    }

    public void setActive(boolean active) {
        this.setVisible(active);
        this.setOpaque(active);
        isActive = active;
        if (active && !t.isAlive()) {
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

    @Override
    public int getPanelToActive() {
        return 0;
    }

    @Override
    public int getPanelId() {
        return 0;
    }
}
