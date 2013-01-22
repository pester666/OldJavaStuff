package de.test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class BolterFrame extends JFrame {

    public BolterFrame(){
        this.setSize(512, 128);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final BolterPanel p = new BolterPanel();
        this.add(p);
        Thread t = new Thread(p);
        t.start();
        this.addKeyListener(new KeyListener(){

            @Override
            public void keyPressed(KeyEvent arg0) {
                
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                
            }

            @Override
            public void keyTyped(KeyEvent arg0) {
                System.out.println("typed");
                p.addBolt();
            }
            
        });
        this.setVisible(true);
    }
}
