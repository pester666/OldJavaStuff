package lexicanum.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Mainframe extends JFrame {

    private Mainpanel p;
    
    public Mainframe(){
        p = new Mainpanel();
        this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Lexicanum");
        this.setLayout(new BorderLayout());
        this.add(p, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
