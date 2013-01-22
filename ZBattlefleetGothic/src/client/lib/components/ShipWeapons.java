package client.lib.components;

import java.util.ArrayList;
import java.util.List;

import client.lib.basics.ShipComponent;
import client.lib.basics.ShipWeapon;

public class ShipWeapons extends ShipComponent {

    private List<ShipWeapon> weapons;
    
    public ShipWeapons(String name, int x, int y, int roomId) {
        super(name, x, y, roomId);
        weapons = new ArrayList<ShipWeapon>();
    }

    public List<ShipWeapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<ShipWeapon> weapons) {
        this.weapons = weapons;
    }
}
