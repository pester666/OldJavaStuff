package lib.dice;


public class SeriousInjuryRoll {

    public static String roll(int modifier){
        int thrown = modifier;
        for (int i = 0; i < 11; i++) {
            thrown = thrown + D6Roll.rollOneD6();
        }
        String s = thrown + ": ";
        
        if(thrown <= 15){
            s = s + "DEAD\n" +
            		"The warrior is dead and his body is abandoned in the dark alleys of Mordheim, never to be found again. " +
            		"All the weapons and equipment he carried are lost. " +
            		"Remove him from the warband rooster.";
        }else
        if(thrown <= 21){
            int throwAgain = D6Roll.rollOneD6();
            s = s + "MULTIPLE INJURIES\n" +
            		"The warrior is not dead but has suffered a lot of wounds. " +
            		"Roll " + throwAgain + " times on this table. " +
            		"Re-roll any 'Dead', 'Captured' and furter 'Multiple Injuries' results.";
        }else
        if(thrown == 22){
            
        }else
        if(thrown == 23){
            
        }else
        if(thrown == 24){
            
        }else
        if(thrown == 25){
            
        }else
        if(thrown == 26){
            
        }else
        if(thrown == 31){
            
        }else
        if(thrown == 32){
            
        }else
        if(thrown == 33){
            
        }else
        if(thrown == 34){
            
        }else
        if(thrown == 35){
            
        }else
        if(thrown == 36){
            
        }else
        if(thrown <= 55){
            
        }else
        if(thrown == 56){

        }else
        if(thrown == 61){
            
        }else
        if(thrown <= 63){
            
        }else
        if(thrown == 64){
            
        }else
        if(thrown == 65){
            
        }else
        if(thrown == 66){
            
        }
        
        return s;
    }
}
