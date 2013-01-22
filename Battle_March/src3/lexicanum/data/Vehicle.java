package lexicanum.data;

public class Vehicle extends Unit {

    protected final int front;
    protected final int side;
    protected final int back;
    protected final int hp;
    
    public Vehicle(String name, int movement, int bf, int pts, SpecialRule[] specialRules, int front, int side, int back, int hp, Race race) {
        super(name, movement, bf, pts, specialRules, race);
        this.front = front;
        this.side = side;
        this.back = back;
        this.hp = hp;
    }
}
