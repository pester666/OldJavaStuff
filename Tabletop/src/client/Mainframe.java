package client;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import land.LandPanel;
import map.Map;


public class Mainframe extends JFrame {
    LandPanel lp = null;
    public Mainframe(){
        lp = new LandPanel();
        Map map = new Map();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.add(lp, BorderLayout.CENTER);
        this.add(map, BorderLayout.CENTER);
        this.setResizable(false);
        this.setVisible(true);
        this.addKeyListener(keyListener);
    }   
    
    private KeyListener keyListener = new KeyListener(){

        @Override
        public void keyTyped(KeyEvent e) {
            if(lp.isVisible && e.getKeyChar() == KeyEvent.VK_ESCAPE){
                lp.showMenu();  
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    };
}
