package client.view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Mainframe extends JFrame {

    private Mainpanel p;
    
    public Mainframe(){
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new Mainpanel();
        this.setLayout(new BorderLayout());
        this.add(p, BorderLayout.CENTER);
        this.setVisible(true);
        this.addKeyListener(new heyListen());
        Thread t = new Thread(p);
        t.start();
    }
    
    private class heyListen implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                p.getShip().makeMovingDown(true);
            }else if(e.getKeyCode() == KeyEvent.VK_UP){
                p.getShip().makeMovingUp(true);
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                p.getShip().makeMovingLeft(true);
            }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                p.getShip().makeMovingRight(true);
            }
            if(e.getKeyCode() == KeyEvent.VK_A){
                p.getShip().shoot();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                p.getShip().makeMovingDown(false);
            }else if(e.getKeyCode() == KeyEvent.VK_UP){
                p.getShip().makeMovingUp(false);
            }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                p.getShip().makeMovingLeft(false);
            }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                p.getShip().makeMovingRight(false);
            }
        }
    };
}
