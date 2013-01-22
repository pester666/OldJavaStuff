package client.gui.hire;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Warbands extends HireBook{

    public Warbands() {
        this.header = new ImageIcon("res/img/hire/headers/warband.png").getImage();
        this.heraldik = new Image[]{
                new ImageIcon("res/img/hire/heraldik/warbands/undead.png").getImage(),
                new ImageIcon("res/img/hire/heraldik/warbands/dwarf.png").getImage(),
        };
        this.description = new Image[]{
                new ImageIcon("res/img/hire/description/warbands/undead.png").getImage(),
                new ImageIcon("res/img/hire/description/warbands/dwarf.png").getImage(),
        };
    }
}
