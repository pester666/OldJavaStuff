package client.actions;

import java.util.Random;

import lib.Unit;
import lib.Weapon;

public class Attack {

    public int getMeleeDamage(Unit attacker, Weapon attackerWeapon, Unit defender){
        
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        if(dice + attacker.getMelee() >= defender.getDef()){
            dice = ran.nextInt(6) + 1;
            int difference = defender.getArm() - (dice + attacker.getStr() + attackerWeapon.getStr());
            if(dice + attacker.getStr() + attackerWeapon.getStr() >= defender.getArm()){
                return difference;
            }
        }
        
        return 0;
    }
    
    public int getRangeDamage(Unit attacker, Weapon attackerWeapon, Unit defender){
        
        Random ran = new Random();
        int dice = ran.nextInt(6) + 1;
        if(dice + attacker.getRng() >= defender.getDef()){
            dice = ran.nextInt(6) + 1;
            int difference;
            if(attackerWeapon.isThrown()){
                difference = defender.getArm() - (dice + attacker.getStr() + attackerWeapon.getStr());
            }else{
                difference = defender.getArm() - (dice + attackerWeapon.getStr()); 
            }
            if(difference >= defender.getArm()){
                return difference;
            }
        }
        
        return 0;
    }
}
