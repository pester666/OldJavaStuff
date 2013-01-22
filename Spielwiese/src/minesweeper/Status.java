package minesweeper;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Status extends JPanel implements Runnable{

    private int minesLeft = 0;
    private int time = 0;
    private JLabel t;
    private JLabel m;
    
    public Status(int minesLeft, int time){
        this.minesLeft = minesLeft;
        this.time = time;
        t = new JLabel("Time: " + time);
        m = new JLabel("Mines: " + minesLeft);
        this.setLayout(new BorderLayout());
        this.add(m, BorderLayout.WEST);
        this.add(t, BorderLayout.EAST);
    }

    public int getMinesLeft() {
        return minesLeft;
    }

    public void setMinesLeft(int minesLeft) {
        this.minesLeft = minesLeft;
        m.setText("Mines: " + minesLeft);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    public void reset(int minesLeft){
        this.minesLeft = minesLeft;
        this.time = 0;
        m.setText("Mines: " + minesLeft);
        t.setText("Time: " + time);
    }
    
    public void updateTime(){
        time++;
        t.setText("Time: " + time);
    }
    
    @Override
    public void run() {
        while(true){
            updateTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
