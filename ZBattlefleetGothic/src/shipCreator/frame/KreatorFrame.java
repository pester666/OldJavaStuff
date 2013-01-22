package shipCreator.frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import shipCreator.board.Mainboard;
import shipCreator.sidebars.Entitypanel;
import shipCreator.sidebars.Infopanel;
import shipCreator.sidebars.KreatorMenu;
import shipCreator.sidebars.Toolpanel;

public class KreatorFrame extends JFrame {
    
    Mainboard mb;
    Entitypanel ep;
    Infopanel ip;
    KreatorMenu km;
    Toolpanel tp;
    
    public KreatorFrame(){
        this.setSize(1024, 800);
        this.setTitle("ShipKreator");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        mb = new Mainboard();
        ep = new Entitypanel();
        ip = new Infopanel();
        km = new KreatorMenu();
        tp = new Toolpanel();
        this.add(mb, BorderLayout.CENTER);
        JPanel eastern = new JPanel();
        eastern.setLayout(new BorderLayout());
        eastern.add(ep, BorderLayout.CENTER);
        eastern.add(tp, BorderLayout.SOUTH);
        this.add(eastern, BorderLayout.EAST);
        this.add(ip, BorderLayout.SOUTH);
        this.setJMenuBar(km);
        Thread t = new Thread(new Repainter());
        t.start();
        this.setVisible(true);
    }
    
    private class Repainter implements Runnable{

        @Override
        public void run() {
            while(true){
                if( mb.getShip() != null && km.getShip() != null && ! mb.getShip().equals(km.getShip())){
                    mb.setShip(km.getShip());   
                }
                if( ep.getTile() != null){
                    mb.setTile(ep.getTile());
                }
                mb.repaint();
                ip.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } 
            }
        }
    }
}
