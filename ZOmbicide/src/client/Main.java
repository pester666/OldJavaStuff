package client;

import javax.swing.ImageIcon;

import client.gui.Mainframe;
import client.lib.Constants;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Constants.OBSTICLES.put(1, new ImageIcon("res/img/rock.png").getImage());
        Constants.OBSTICLES.put(2, new ImageIcon("res/img/plant.png").getImage());
        Constants.OBSTICLES.put(3, new ImageIcon("res/img/crate.png").getImage());
        Mainframe mf = new Mainframe();
        mf.setVisible(true);
    }
}
