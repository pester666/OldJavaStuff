package lib;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Ressource {

    private String name = "";
    private int amountPerRound = 0;
    private int tier = 1;
    private Image img = null;
    private int costs = 0;
    private int divider = 1;
    private int xCoord = 0;
    private int yCoord = 0;
    
    public Ressource(String name){
        this.name = name;
        setStats();
    }
    
    private void setStats(){
        if(this.name.equals(C.GOLD)){
            this.amountPerRound = 100;
            this.img = new ImageIcon("img/ressources/goldmine.png").getImage();
            this.costs = this.amountPerRound*this.tier;
            this.divider = 1;
        }else
        if(this.name.equals(C.LUMBER)){
            this.amountPerRound = 250;
            this.img = new ImageIcon("img/ressources/sawmill.png").getImage();
            this.costs = this.amountPerRound/3*this.tier;
            this.divider = 3;
        }else
        if(this.name.equals(C.STONE)){
            this.amountPerRound = 175;
            this.img = new ImageIcon("img/ressources/quarry.png").getImage();
            this.costs = this.amountPerRound/2*this.tier;
            this.divider = 2;
        }
    } 
    
    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getAmountPerRound() {
        return amountPerRound;
    }

    public int getTier() {
        return tier;
    }

    public void upgradeRessource() {
        this.tier++;
        this.amountPerRound = this.amountPerRound + this.divider*10*this.tier;
        this.costs = this.amountPerRound/this.divider*this.tier;
    }

    public String getName() {
        return name;
    }

    public Image getImg() {
        return img;
    }

    public int getCosts() {
        return costs;
    }
}
