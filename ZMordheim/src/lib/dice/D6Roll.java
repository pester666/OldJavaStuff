package lib.dice;

import java.util.Random;

public class D6Roll {

    public static int rollOneD6(){
        Random ran = new Random();
        return ran.nextInt(6) + 1;
    }
    
    public static int[] rollSeveralD6(int amount){
        int[] throwed = new int[amount];
        Random ran = new Random();
        for (int i = 0; i < amount; i++) {
            throwed[i] = ran.nextInt(6) + 1;
        }
        return throwed;
    }
}
