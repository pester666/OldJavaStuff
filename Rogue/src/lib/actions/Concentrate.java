package lib.actions;

import lib.Battleturn;
import lib.base.Action;
import lib.base.Unit;

public class Concentrate extends Action {

    /**
     * Normal Swordattack, balanced with crit and dmg
     */
    public Concentrate() {
        super("Concentrate", "Pushes your Dexterity.");
    }

    @Override
    public Battleturn action(Unit a, Unit b, Unit dummyA, Unit dummyB) {
        System.out.println(a.getName() + " used Concentrate.");
        a.setDex(a.getDex()+2);
        return new Battleturn(a, b, dummyA, dummyB);
    }
}
