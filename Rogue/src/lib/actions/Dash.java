package lib.actions;

import java.util.Random;

import lib.Battleturn;

import lib.base.Unit;

import lib.base.Action;

public class Dash extends Action {

    public Dash() {
        super("Dash", "Higher chance to hit with lower Critchance");
    }
    
    @Override
    public Battleturn action(Unit a, Unit b, Unit dummyA, Unit dummyB) {
        Random ran = new Random();
        System.out.println(a.getName() + " used Dash.");
        if(a.getDex()-b.getDex()+55 >= ran.nextInt(100)){
            //HIT
            if(a.getDex()-5 >= ran.nextInt(100)){
                //CRIT
                b.setHp(b.getHp()-(a.getStr()*2));
                System.out.println(a.getName() + " landed a critical blow! " + (a.getStr()*2) + " Damage!");
            }else{
                //NORMAL BLOW
                b.setHp(b.getHp()-a.getStr());
                System.out.println(a.getName() + " has hit the enemy! " + a.getStr() + " Damage.");
            }
        }else{
            System.out.println(a.getName() + " missed ...");
        }
        return new Battleturn(a, b, dummyA, dummyB);
    }
}
