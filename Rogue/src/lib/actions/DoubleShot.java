package lib.actions;

import java.util.Random;

import lib.Battleturn;

import lib.base.Action;
import lib.base.Unit;

public class DoubleShot extends Action {

    /**
     * Normal Swordattack, balanced with crit and dmg
     */
    public DoubleShot() {
        super("Doubleshot", "Shoots two times with lower hitchance.");
    }

    @Override
    public Battleturn action(Unit a, Unit b, Unit dummyA, Unit dummyB) {
        Random ran = new Random();
        System.out.println(a.getName() + " used Slash.");
        if(a.getDex()-b.getDex()+30 >= ran.nextInt(100)){
            //HIT
            if(a.getDex() >= ran.nextInt(100)){
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
        if(a.getDex()-b.getDex()+30 >= ran.nextInt(100)){
            //HIT
            if(a.getDex() >= ran.nextInt(100)){
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
