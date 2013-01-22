package lib.actions;

import java.util.Random;

import lib.Battleturn;

import lib.base.Unit;

import lib.base.Action;

public class Block extends Action {

    public Block() {
        super("Shieldblock", "Tries to block off an attack");
    }
    
    @Override
    public Battleturn action(Unit a, Unit b, Unit dummyA, Unit dummyB) {
        Random ran = new Random();
        System.out.println(a.getName() + " used Shieldblock.");
        if(a.getHp() == dummyA.getHp()){
            if(a.getDex()-b.getDex()+50 >= ran.nextInt(100)){
                //MAY BLOCK
                System.out.println(a.getName() + " blocks!");
                b.setStr(b.getStr()-a.getStr());
                if(b.getStr() < 0){
                    b.setStr(0);
                }
                System.out.println(b.getName() + " causes " + b.getStr() + " Damage.");
            }
        }else{
            //Damage was done but will be healed
            if(a.getDex()-b.getDex()+50 >= ran.nextInt(100)){
                //MAY BLOCK
                System.out.println(a.getName() + " blocks!");
                int i = b.getStr()-a.getStr();
                if(i < 0){
                    i = 0;
                }
                a.setHp(dummyA.getHp()-i);
                System.out.println(b.getName() + "s Damage lowered to " + i);
            }
        }
        return new Battleturn(a, b, dummyA, dummyB);
    }
}
