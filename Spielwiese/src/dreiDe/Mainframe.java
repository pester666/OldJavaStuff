package dreiDe;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Mainframe extends JFrame {

    Panel f = new Panel();
    
    public Mainframe(){
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(f, BorderLayout.CENTER);
        f.init();
        this.setVisible(true);
    }
}
