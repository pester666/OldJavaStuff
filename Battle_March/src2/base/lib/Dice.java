package base.lib;

import java.util.Random;

public class Dice {

    public static boolean hasHitted(Unit shooter){
        Random ran = new Random();
        int thrown = ran.nextInt(6) + 1;
        if(thrown == 1){
            return false;
        }
        if(shooter.weapon[0].getsMovementMalus){
            if(shooter.hasMoved){
                thrown--;
            }
        }    
        if(thrown >= 8-shooter.bs){
            return true;
        }
        return false;
    }
    
    public static int hasHittedTank(Unit shooter){
        
        return 0;
    }
    
    public static boolean hasWounded(Unit shooter, Infantry target) {
        Random ran = new Random();
        int thrown = ran.nextInt(6) + 1;
        if (thrown == 1) {
            return false;
        }
        int needed = 4 + (target.toughness - shooter.weapon[0].strength);
        if (needed > 6) {
            if (needed > 8) {
                needed = 10;
            } else {
                needed = 6;
            }
        }
        if (needed <= 1) {
            needed = 2;
        }
        if (thrown >= needed) {
            return true;
        }
        return false;
    }
    
    public static boolean armorSaveSuccess(Unit shooter, Infantry target){
        Random ran = new Random();
        int thrown = ran.nextInt(6) + 1;
        if (thrown == 1) {
            return false;
        }
        if(shooter.weapon[0].ap <= target.armorsave){
            return false;
        }
        if(thrown >= target.armorsave){
            return true;
        }
        return false;
    }
    
    public static boolean coverSaveSuccess(Unit shooter, Cover cover){
        Random ran = new Random();
        int thrown = ran.nextInt(6) + 1;
        if (thrown == 1) {
            return false;
        }
        if(thrown >= cover.cover){
            return true;
        }
        return false;
    }
}
