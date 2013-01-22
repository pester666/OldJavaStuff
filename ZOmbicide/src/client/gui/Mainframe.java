package client.gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import client.lib.Constants;
import client.runtime.Logic;

public class Mainframe extends JFrame {

    Mainpanel p;
    
    public Mainframe(){
        this.setSize(Constants.WIDTH, Constants.HEIGTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Zombicide");
        p = new Mainpanel();
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(p, BorderLayout.CENTER);
        Thread t = new Thread(p);
        t.start();
        Logic l = new Logic();
        this.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == 'w'){
                    p.setUp(true);
                }else if(e.getKeyChar() == 'a'){
                    p.setLeft(true);
                }else if(e.getKeyChar() == 's'){
                    p.setDown(true);
                }else if(e.getKeyChar() == 'd'){
                    p.setRight(true);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == 'w'){
                    p.setUp(false);
                }else if(e.getKeyChar() == 'a'){
                    p.setLeft(false);
                }else if(e.getKeyChar() == 's'){
                    p.setDown(false);
                }else if(e.getKeyChar() == 'd'){
                    p.setRight(false);
                }
            }
        });
    }
}
