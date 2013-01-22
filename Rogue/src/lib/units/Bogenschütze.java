package lib.units;

import lib.actions.Arrowshot;
import lib.actions.Concentrate;
import lib.actions.DoubleShot;
import lib.actions.Sniper;
import lib.base.Action;
import lib.base.Klasse;

public class Bogensch�tze extends Klasse {

    public Bogensch�tze() {
        super(2, "Archer", -10, -1, +5, new Action[]{new Arrowshot(), new DoubleShot(), new Sniper(), new Concentrate()});
    }
}
