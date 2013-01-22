package de.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BolterPanel extends JPanel implements Runnable{

    Image csm = new ImageIcon("icon/Chaos Space Marine.png").getImage();
    int range = 24;
    int bf = 4;
    boolean rapidFire = true;
    int kadenz = 2;
    List<Bolt> bolts = new ArrayList<Bolt>();
    Random ran = new Random();
    
    public BolterPanel(){
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(512, 128));

    }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 512, 128);
        g.drawImage(csm, 10, 16, null);
        for (int i = 0; i < bolts.size(); i++) {
            if(bolts.get(i).x >= bolts.get(i).ex){
                bolts.remove(i);
                continue;
            }
            bolts.get(i).move(moveBolt(bolts.get(i)));
            g.setColor(Color.BLACK);
            g.drawLine(bolts.get(i).x, bolts.get(i).y, bolts.get(i).ax, bolts.get(i).by);
        }
    }

    @Override
    public void run() {
        while(true){
            this.repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }    
        }
    }
    
    private class Bolt {
        public int x;
        public int y;
        public int ax;
        public int by;
        public int ex;
        public int ey;
        
        public Bolt(int x, int y, int ax, int by) {
            super();
            this.x = x;
            this.y = y;
            this.ax = ax;
            this.by = by;
            ex = 42 + range*100; 
            if(ran.nextInt(6)+1 >= 7-bf){
                ey = 32 + (ran.nextInt(12));
            }else{
                ey = 32 + (ran.nextInt(21)+13);
            }
        }
        
        public void move(Bolt b){
            x = b.x;
            y = b.y;
            ax = b.ax;
            by = b.by;
            ex = b.ex;
            ey = b.ey;
        }
    }
    
    public void addBolt(){
        bolts.add(new Bolt(42, 32, 47, 32));
    }
    private Bolt moveBolt(Bolt b){
        int lGK = 32 - b.ey;
        int lAK = 42 + range;
        int lHYP = lGK*lGK + lAK*lAK;
//        double winkelMündung = lGK/lHYP;

        b.x += 5;
        b.ax += 5;

        
        return b;
    }
}
