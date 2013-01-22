package shipCreator.sidebars;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class KreatorMenu extends JMenuBar {

    private Image ship;
    
    public KreatorMenu(){
        JMenu m = new JMenu("Options");
        JMenu newShip = new JMenu("New Ship");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        
        JMenuItem cruiser = new JMenuItem("Cruiser");
        cruiser.addActionListener(new NewCruiser());
        
        newShip.add(cruiser);
        m.add(newShip);
        m.add(save);
        m.add(load);
        this.add(m);
    }
    
    private class NewCruiser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ship = new ImageIcon("res/img/ship/Cruiser.png").getImage();
        }
    }

    public Image getShip() {
        return ship;
    }

    public void setShip(Image ship) {
        this.ship = ship;
    }
    
    
}
