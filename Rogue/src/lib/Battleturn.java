package lib;

import lib.base.Unit;

public class Battleturn {

    private Monster m;
    private Monster mDummy;
    private Player p;
    private Player pDummy;
    public Battleturn(Unit a, Unit b, Unit dummyA, Unit dummyB) {
        super();
        this.m = (Monster)b;
        this.mDummy = (Monster)dummyB;
        this.p = (Player)a;
        this.pDummy = (Player)dummyA;
    }
    public Monster getM() {
        return m;
    }
    public void setM(Monster m) {
        this.m = m;
    }
    public Monster getmDummy() {
        return mDummy;
    }
    public void setmDummy(Monster mDummy) {
        this.mDummy = mDummy;
    }
    public Player getP() {
        return p;
    }
    public void setP(Player p) {
        this.p = p;
    }
    public Player getpDummy() {
        return pDummy;
    }
    public void setpDummy(Player pDummy) {
        this.pDummy = pDummy;
    }
    
    
}
