package lib.weapons;

import java.util.Random;

import lib.Unit;
import lib.Weapon;

public class Greataxe extends Weapon {

    public Greataxe() {
        super(WC.GREATAXE, 5, 2, 1, 1, false);
    }
    
    public int attack(Unit attacker, Unit defender){
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        if(dice + attacker.getMelee() >= defender.getDef()){
            dice = ran.nextInt(6) + 1;
            int difference = defender.getArm() - (dice + attacker.getStr() + this.str);
            if(dice + attacker.getStr() + this.str >= defender.getArm()){
                return difference;
            }
        }
        return 0;
    }
}
