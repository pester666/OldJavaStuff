package lib.base;

import java.util.ArrayList;
import java.util.List;

public class Unit {

    protected int hp;
    protected int str;
    protected int dex;
    protected String name;
    protected int lvl;
    protected List<Effect> effects;
    
    public Unit(int hp, int str, int dex, String name, int lvl) {
        super();
        this.hp = hp;
        this.str = str;
        this.dex = dex;
        this.name = name;
        this.lvl = lvl;
        this.effects = new ArrayList<Effect>();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }    
}