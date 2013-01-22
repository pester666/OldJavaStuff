package base.data.units;

import base.data.equip.M1Garand;
import base.lib.Infantry;

public class GI extends Infantry {

    public GI() {
        super("GI", false, 3, 6, 7, 4, 1, 10, new M1Garand(), 3, 25, 25);
    }
}
