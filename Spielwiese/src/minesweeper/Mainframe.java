package minesweeper;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

public class Mainframe extends JFrame {

    private Panel p;
    private int size = 1;
    
    public Mainframe(){
        p = new Panel();
        this.setSize();
        this.setSize(145*size, 195*size);
        this.setTitle("SOKA");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar mb = new JMenuBar();
        JMenu m = new JMenu("Properties");
        JMenu mD = new JMenu("Difficulties");
        JRadioButtonMenuItem mEasy = new JRadioButtonMenuItem("Easy");
        mEasy.setActionCommand("Easy");
        mEasy.setSelected(true);
        mEasy.addActionListener(new changeDiff());
        JRadioButtonMenuItem mMedium = new JRadioButtonMenuItem("Medium");
        mMedium.setActionCommand("Medium");
        mMedium.addActionListener(new changeDiff());
        JRadioButtonMenuItem mHard = new JRadioButtonMenuItem("Hard");
        mHard.setActionCommand("Hard");
        mHard.addActionListener(new changeDiff());
        ButtonGroup gp = new ButtonGroup();
        gp.add(mHard);
        gp.add(mMedium);
        gp.add(mEasy);
        mD.add(mEasy);
        mD.add(mMedium);
        mD.add(mHard);
        m.add(mD);
        mb.add(m);
        this.setJMenuBar(mb);
        this.setLayout(new BorderLayout());
        this.add(p, BorderLayout.CENTER);
        this.add(new JLabel("  "), BorderLayout.WEST);
        this.add(p.getStatusPanel(), BorderLayout.NORTH);
        Thread t = new Thread(p);
        t.start();
        this.setResizable(false);
        this.setVisible(true);
    }
    
    private class changeDiff implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if(s.equals("Easy")){
                changeDifficulty(1);
            }else if(s.equals("Medium")){
                changeDifficulty(2);
            }else if(s.equals("Hard")){
                changeDifficulty(3);
            }
        }
    }
    
    private void changeDifficulty(int diff){
        if(diff == 1){
            this.setSize(145*size, 195*size);
            p.resetBoard(1);
        }else if(diff == 2){
            this.setSize(280*size, 325*size);
            p.resetBoard(2);
        }else if(diff == 3){
            this.setSize(500*size, 330*size);
            p.resetBoard(3);
        }
    }
    
    private void setSize(){
        size = p.getFieldSize();
        if(size == 16){
            size = 1;
        }else if(size == 32){
            size = 2;
        }
    }
}

