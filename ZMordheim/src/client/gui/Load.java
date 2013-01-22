package client.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import lib.Warband;

public class Load extends MenuPanel {

    public static boolean isActive = false;
    private Thread t;
    private Warband warband;
    
    public Load(boolean active){
        t = new Thread();
    }
    
    public void setActive(boolean active) {
        this.setVisible(active);
        this.setOpaque(active);
        isActive = active;
        if (active && !t.isAlive()) {
            loadWarband(getUnitname());
            this.t.start();
            for (int i = 0; i < warband.getUnits().length; i++) {
                System.out.println(warband.getUnits()[i].getName());
            }
        }
    }
    
    private String getUnitname(){
        String s = (String)JOptionPane.showInputDialog(
                            this,
                            "Who art thee called?",
                            "A new one...",
                            JOptionPane.PLAIN_MESSAGE,
                            null, null, "");

        if ((s != null) && (s.length() > 0)) {
            return s;
        }
        return "Chopped Tongue";
    }
    
    public void loadWarband(String warbandName){
        try {
            FileInputStream fout = new FileInputStream("res/warbands/" + warbandName);
            ObjectInputStream oos = new ObjectInputStream(fout);   
            warband = (Warband)oos.readObject();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int getPanelToActive() {
        return 0;
    }

    @Override
    public int getPanelId() {
        return PanelIds.LOAD;
    }
}
