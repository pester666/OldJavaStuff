package lexicanum.data;

public class Walker extends Vehicle {

    protected final int ws;
    protected final int a;
    protected final int i;
    protected final int s;
    
    public Walker(String name, int movement, int bf, int pts, SpecialRule[] specialRules, int front, int side, int back, int hp, int ws, int a, int i, int s, Race race) {
        super(name, movement, bf, pts, specialRules, front, side, back, hp, race);
        this.ws = ws;
        this.a = a;
        this.i = i;
        this.s = s;
    }
}
