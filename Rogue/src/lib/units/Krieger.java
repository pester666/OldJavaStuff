package lib.units;

import lib.actions.Block;
import lib.actions.Dash;
import lib.actions.Slash;
import lib.actions.Uppercut;
import lib.base.Action;
import lib.base.Klasse;

public class Krieger extends Klasse {
  
    public Krieger() {
        super(1, "Warrior", +10, +2, -2, new Action[]{new Slash(), new Dash(), new Uppercut(), new Block()});
    }
}
