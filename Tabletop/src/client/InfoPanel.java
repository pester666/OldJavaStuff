package client;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

    public InfoPanel(){
        this.setPreferredSize(new Dimension(800, 85));
        this.setDoubleBuffered(true);
    }
    
    public void paintComponent(Graphics g){
        
    }
}
