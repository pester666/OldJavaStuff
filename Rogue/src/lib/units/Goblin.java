package lib.units;

import lib.Monster;
import lib.actions.Dash;
import lib.actions.Slash;
import lib.base.Action;

public class Goblin extends Monster {

    public Goblin() {
        super(20, 5, 7, "Goblin", 1, new Action[]{new Slash(), new Slash(), new Slash(), new Dash()});
    }
}
