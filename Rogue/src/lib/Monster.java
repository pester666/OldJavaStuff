package lib;

import lib.base.Action;

import lib.base.Unit;

public class Monster extends Unit{

    private Action[] actions = new Action[4];
    
    public Monster(int hp, int str, int dex, String name, int lvl, Action[] actions) {
        super(hp, str, dex, name, lvl);
        this.actions = actions;
    }
    
    public Action getAction(int i){
        return this.actions[i];
    }
    
    public Action[] getActions(){
        return this.actions;
    }
}
