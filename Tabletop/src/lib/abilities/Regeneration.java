package lib.abilities;

import java.util.Random;

import lib.Ability;

public class Regeneration extends Ability {

    public Regeneration() {
        super(AC.REGENERATION, 0, 0, 0, false);
    }
    
    public int doRegenerateD3(){
        Random ran = new Random();
        return ran.nextInt(3)+1;
    }
}
