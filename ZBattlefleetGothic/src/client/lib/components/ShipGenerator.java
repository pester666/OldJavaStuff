package client.lib.components;

import client.lib.basics.ShipComponent;

public class ShipGenerator extends ShipComponent {

    private int maxEngerySupply;
    
    public ShipGenerator(String name, int maxEngerySupply, int x, int y, int roomId) {
        super(name, x, y, roomId);
        this.maxEngerySupply = maxEngerySupply;
    }

    public int getMaxEngerySupply() {
        return maxEngerySupply;
    }

    public void setMaxEngerySupply(int maxEngery) {
        this.maxEngerySupply = maxEngery;
    }
    
    

}
