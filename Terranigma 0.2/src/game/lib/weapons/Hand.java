package game.lib.weapons;

public class Hand extends Weapon {

    private static int cooldown = 500;
    private static int dmgMin = 5;
    private static int dmgMax = 10;
    private static int range = 20;
    private static String name = "Hand";    
    
    public Hand() {
        super(cooldown, dmgMin, dmgMax, name, range, true, 0);
    }
}
