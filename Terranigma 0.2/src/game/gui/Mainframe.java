package game.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Mainframe extends JFrame{
    
    DPanel p;
    
    public Mainframe(){
        this.setSize(600, 600);
        try {
            p = new DPanel();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        Thread t = new Thread(p);
        t.start();
        this.add(p);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    DPanel.up = false;
                    System.out.println("hinfort");
                }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    DPanel.down = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    DPanel.left = false;
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    DPanel.right = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_A){
                    DPanel.isAttacking = false;
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    DPanel.up = true;
                    System.out.println("voran");
                }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    DPanel.down = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    DPanel.left = true;
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    DPanel.right = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_A){
                    DPanel.isAttacking = true;
                }
            }
        });
    }
}
