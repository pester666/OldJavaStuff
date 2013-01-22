package client.lib.components;

import client.lib.basics.ShipComponent;

public class ShipTurbines extends ShipComponent {
    
    private boolean bigTurbines;    

    public ShipTurbines(String name, int x, int y, boolean bigTurbines, int roomId) {
        super(name, x, y, roomId);
        this.bigTurbines = bigTurbines;
    }

    public boolean isBigTurbines() {
        return bigTurbines;
    }

    public void setBigTurbines(boolean bigTurbines) {
        this.bigTurbines = bigTurbines;
    }
}
