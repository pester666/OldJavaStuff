package terraria;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Mainframe extends JFrame{

    Mainpanel mp = null;

    public Mainframe() {
        mp = new Mainpanel();
        this.setSize(480, 480);
        this.add(mp);
        this.addKeyListener(keyListener);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private KeyListener keyListener = new KeyListener(){

        @Override
        public void keyPressed(KeyEvent key) {
            if (key.getKeyCode() == KeyEvent.VK_LEFT) {
                mp.player.playerWalkLeft(true);
            } else if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
                mp.player.playerWalkRight(true);
            } else if (key.getKeyCode() == KeyEvent.VK_UP) {
                mp.player.playerJump(true);
            }
        }

        @Override
        public void keyReleased(KeyEvent key) {
            if (key.getKeyCode() == KeyEvent.VK_LEFT) {
                mp.player.playerWalkLeft(false);
            } else if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
                mp.player.playerWalkRight(false);
            } else if (key.getKeyCode() == KeyEvent.VK_UP) {
                mp.player.playerJump(false);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            
        }
        
    };
}
