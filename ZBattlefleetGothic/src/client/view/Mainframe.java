package client.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import client.lib.Constants;

public class Mainframe extends JFrame {

    private Mainpanel p;
    
    public Mainframe(){
        this.setSize(Constants.WINDOWWIDTH, Constants.WINDOWHEIGTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new Mainpanel();
        this.setLayout(new BorderLayout());
        this.add(p, BorderLayout.CENTER);
        this.setVisible(true);
        Thread t = new Thread(p);
        t.start();
    }
}
