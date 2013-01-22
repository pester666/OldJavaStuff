package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import lib.Race;

@SuppressWarnings("serial")
public class TileScreenFrame extends JFrame {
    
    public Race[] races = null;
    private MainMenuPanel pnlMain = new MainMenuPanel();
    
    public TileScreenFrame(){
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Sanacorp Tabletop");
        this.add(pnlMain);
        this.setVisible(true);
        this.setResizable(false);
        this.addKeyListener(new KeyListener(){

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }

            @Override
            public void keyTyped(KeyEvent e) {
                //TODO key events
            }
            
        });
    }
}
