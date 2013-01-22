package dreiDe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{
  
    int[] x = new int[]{200, 300, 300, 200};
    int[] y = new int[]{100, 150, 200, 250};
    Polygon pL = new Polygon(x, y, 4);
    int[] x1 = new int[]{450, 350, 350, 450};
    int[] y1 = new int[]{100, 150, 200, 250};
    Polygon pR = new Polygon(x1, y1, 4);
    int[] x2 = new int[]{200, 300, 350, 450};
    int[] y2 = new int[]{100, 150, 150, 100};
    Polygon pU = new Polygon(x2, y2, 4);
    int[] x3 = new int[]{200, 300, 350, 450};
    int[] y3 = new int[]{250, 200, 200, 250};
    Polygon pO = new Polygon(x3, y3, 4);
    
    public Panel(){
        this.setSize(800, 600);
    }
    
    public void init(){
        Thread t = new Thread(this);
        t.start();
    }

    public void paintComponent(Graphics g){
        this.setBackground(Color.BLACK);
        g.setColor(Color.GRAY);
        g.fillPolygon(pL);
        g.fillPolygon(pR);
        g.setColor(Color.DARK_GRAY);
        g.fillPolygon(pU);
        g.fillPolygon(pO);
    }

    @Override
    public void run() {
        while(true){
            this.repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
