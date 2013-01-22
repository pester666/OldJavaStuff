package game.lib.weapons;

public class Club extends Weapon {

    private static int cooldown = 700;
    private static int dmgMin = 9;
    private static int dmgMax = 17;
    private static int range = 20;
    private static String name = "Club";
    
    public Club() {
        super(cooldown, dmgMin, dmgMax, name, range, true, 0);
    }
}
