package client.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Controllpanel extends JPanel{

    private JButton townList;
    
    public Controllpanel(){
        this.setBackground(Color.black);
        townList = new JButton("Townlist");
        this.add(townList);
    }
}
