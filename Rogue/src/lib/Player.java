package lib;

import lib.base.Action;

import lib.base.Klasse;
import lib.base.Unit;

public class Player extends Unit {

    private int xp;
    private Klasse klasse;
    
    public Player(int hp, int str, int dex, String name) {
        super(hp, str, dex, name, 1);
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
        this.dex = this.dex + this.klasse.getDexMod();
        this.hp = this.hp + this.klasse.getHpMod();
        this.str = this.str + this.klasse.getStrMod();
    }
    
    public Action getAction(int i){
        return this.klasse.getActions()[i];
    }
    
    public Action[] getActions(){
        return this.klasse.getActions();
    }
}
