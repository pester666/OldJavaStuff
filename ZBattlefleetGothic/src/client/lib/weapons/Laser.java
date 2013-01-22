package client.lib.weapons;

import client.lib.basics.ShipWeapon;

public class Laser extends ShipWeapon {

    public Laser(int x, int y, int id) {
        super(x, y, id, "Laser", 2, 1, 1, 50, false, true, 15, false, 25);
    }
}
