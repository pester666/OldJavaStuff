package lib.dice;


public class CriticalRollStandard {

    public static String roll(int modifier){
        int throwed = D6Roll.rollOneD6() + modifier;
        String s = throwed + ": ";
        if(throwed <= 2){
            s = s + "Deep Wound\n" +
            		"The target suffers two wounds if he fails his armor save."; 
         }else
         if(throwed <= 4){
             s = s + "Weakspot\n" +
             		"The target suffers two wounds and ignores amor saves."; 
         }else
         if(throwed <= 6){
             s = s + "Masterhit!\n" +
             		"The target suffers two wounds and ignores armor saves. " +
             		"In addition you get +2 on your throw on the Injury Roll.";
         }
         return s;
    }     
}
