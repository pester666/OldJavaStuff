package client.gui.hire;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Heroes extends HireBook{

    public Heroes() {
        this.header = new ImageIcon("res/img/hire/headers/heroes.png").getImage();

    }
    
    public void setHeroes(int warband){
        if(warband == 0){ //Undead
            this.heraldikLocation = new String[]{
              "res/img/hire/heraldik/heroes/undead1.png",
              "res/img/hire/heraldik/heroes/undead2.png",
              "res/img/hire/heraldik/heroes/undead3.png"
            };
            this.heraldik = new Image[]{
                    new ImageIcon(this.heraldikLocation[0]).getImage(),
                    new ImageIcon(this.heraldikLocation[1]).getImage(),
                    new ImageIcon(this.heraldikLocation[2]).getImage()
            };
            this.description = new Image[]{
                    new ImageIcon("res/img/hire/description/heroes/undead.png").getImage(),
                    new ImageIcon("res/img/hire/description/heroes/undead.png").getImage(),
                    new ImageIcon("res/img/hire/description/heroes/undead.png").getImage(),
                    new ImageIcon("res/img/hire/description/heroes/undead.png").getImage(),
                    new ImageIcon("res/img/hire/description/heroes/undead.png").getImage(),
            };
        }else if(warband == 1){ //Dwarf
            
        }
    }
}
