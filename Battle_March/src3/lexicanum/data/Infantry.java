package lexicanum.data;

public class Infantry extends Unit {

    protected final int s;
    protected final int t;
    protected final int a;
    protected final int mw;
    protected final int as;
    protected final int w;
    protected final int i;
    protected final int ws;
    
    public Infantry(String name, int movement, int bf, int pts, SpecialRule[] specialRules, int s, int t, int a, int mw, int as, int w, int i, int ws, Race race) {
        super(name, movement, bf, pts, specialRules, race);
        this.s = s;
        this.t = t;
        this.a = a;
        this.mw = mw;
        this.as = as;
        this.w = w;
        this.i = i;
        this.ws = ws;
    }

    public int getS() {
        return s;
    }

    public int getT() {
        return t;
    }

    public int getA() {
        return a;
    }

    public int getMw() {
        return mw;
    }

    public int getAs() {
        return as;
    }

    public int getW() {
        return w;
    }

    public int getI() {
        return i;
    }

    public int getWs() {
        return ws;
    }
}
