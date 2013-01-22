package lib.effects;

import lib.Battleturn;
import lib.base.Effect;
import lib.base.Unit;

public class Block extends Effect{

    public Block() {
        super("Block", 1, "Blocks incoming damage.");
    }
    
    @Override
    public Battleturn affect(Unit a, Unit b, Unit aDummy, Unit bDummy){
        if(b.getHp() == bDummy.getHp()){
                //MAY BLOCK
                System.out.println(b.getName() + " blocks!");
                a.setStr(a.getStr()-b.getStr());
                if(a.getStr() < 0){
                    a.setStr(0);
                }
                System.out.println(a.getName() + " causes " + a.getStr() + " Damage.");
        }else{
            //Damage was done but will be healed
                //MAY BLOCK
                System.out.println(b.getName() + " blocks!");
                int i = b.getStr()-a.getStr();
                if(i < 0){
                    i = 0;
                }
                b.setHp(bDummy.getHp()-i);
                System.out.println(b.getName() + "s Damage lowered to " + i);
        }
        return new Battleturn(a, b, aDummy, bDummy);
    }
}
