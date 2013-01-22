package lib.dice;

public class Parry {

    public static boolean hasParried(int throwedToHit, int sa, int sd, int modifier){
        int throwed = D6Roll.rollOneD6() + modifier;
        if(sa < sd*2){
            if(throwed > throwedToHit){
                return true;
            }
        }
        
        return false;
    }
}
