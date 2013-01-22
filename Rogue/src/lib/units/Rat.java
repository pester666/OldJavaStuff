package lib.units;

import lib.Monster;
import lib.actions.Dash;
import lib.base.Action;

public class Rat extends Monster {

    public Rat() {
        super(10, 2, 9, "Rat", 1, new Action[]{new Dash(), new Dash(), new Dash(), new Dash()});
    }
}
