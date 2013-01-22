package base.data.units;

import base.data.equip.LightMgTank;
import base.data.equip.StuartCannon;
import base.lib.Tank;
import base.lib.Weapon;

public class Stuart extends Tank {

    public Stuart() {
        super("Stuart Tank", false, 8, 6, 1, 70, new Weapon[]{new StuartCannon(), new LightMgTank()}, 12, 11, 10, 25, 50, 3);
    }

}
