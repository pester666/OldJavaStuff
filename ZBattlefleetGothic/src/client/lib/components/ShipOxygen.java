package client.lib.components;

import client.lib.basics.ShipComponent;

public class ShipOxygen extends ShipComponent {

    private int maxSupply;
    
    public ShipOxygen(String name, int x, int y, int roomId) {
        super(name, x, y, roomId);
    }

    public int getMaxEngery() {
        return maxSupply;
    }

    public void setMaxEngery(int maxEngery) {
        this.maxSupply = maxEngery;
    }
    
    

}
