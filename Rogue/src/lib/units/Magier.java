package lib.units;

import lib.actions.Slash;
import lib.base.Action;

import lib.base.Klasse;

public class Magier extends Klasse {

    public Magier() {
        super(3, "Mage", -10, -4, -2, new Action[]{new Slash()});
    }
}
