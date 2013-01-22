package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Mainframe extends JFrame implements Runnable {

    Mainpanel mp;
    Ressourcepanel rp;
    Controllpanel cp;
    Statpanel sp;
    Thread t;

    public Mainframe() {
        this.setTitle("Astronomican");
        this.setSize(500, 595);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        mp = new Mainpanel();
        rp = new Ressourcepanel();
        cp = new Controllpanel();
        sp = new Statpanel();
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout());
        this.add(rp, BorderLayout.NORTH);
        this.add(mp, BorderLayout.CENTER);
        this.add(cp, BorderLayout.SOUTH);
        this.add(sp, BorderLayout.EAST);
        t = new Thread(this);
        t.start();
        this.setResizable(false);
        this.setVisible(true);
    }

    @SuppressWarnings("static-access")
    @Override
    public void run() {
        int refreshRate = 1000;
        FileReader fr = null;
        BufferedReader br = null;
        while (true) {
            try {
                fr = new FileReader("database/PlayerRessources.txt");
                br = new BufferedReader(fr);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            try {
                String line;
                int count = 0;
                while ((line = br.readLine()) != null) {
                    rp.updateRessources(count, line, refreshRate/1000);
                    mp.updateRessources(count, line, refreshRate/1000);
                    count++;
                }
                fr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                t.sleep(refreshRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
