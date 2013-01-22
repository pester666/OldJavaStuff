package client.lib.ships;

import java.util.ArrayList;

import client.lib.actions.ShipactionWarpjump;
import client.lib.basics.Action;
import client.lib.basics.Room;
import client.lib.basics.Ship;
import client.lib.basics.ShipComponent;
import client.lib.basics.ShipWeapon;
import client.lib.basics.Unit;
import client.lib.components.ShipBridge;
import client.lib.components.ShipGenerator;
import client.lib.components.ShipLarzarett;
import client.lib.components.ShipOxygen;
import client.lib.components.ShipShield;
import client.lib.components.ShipTurbines;
import client.lib.components.ShipWeapons;
import client.lib.units.Human;
import client.lib.weapons.IonCannon;
import client.lib.weapons.Laser;
import client.lib.weapons.Torpedo;

public class Cruiser extends Ship {

    private final static int SHIP_X = 100;
    private final static int SHIP_Y = 257;
    
    public Cruiser(){
        
        super(5, "Cruiser", 10, 20, 500, SHIP_X, SHIP_Y, new ArrayList<Room>(), new ArrayList<ShipComponent>() , new ArrayList<ShipWeapon>(), new ArrayList<Unit>(), new ArrayList<Action>());
        this.rooms.add(new Room(50, 50, 5, SHIP_X+25, SHIP_Y+93, 1, false, false));
        this.rooms.add(new Room(50, 50, 5, SHIP_X+75, SHIP_Y+93, 2, false, false));
        this.rooms.add(new Room(50, 50, 7, SHIP_X+75, SHIP_Y+43, 3, false, false));
        this.rooms.add(new Room(50, 50, 7, SHIP_X+75, SHIP_Y+143, 4, false, false));
        this.rooms.add(new Room(50, 50, 7, SHIP_X+225, SHIP_Y+93, 5, false, false));
        this.rooms.add(new Room(50, 50, 5, SHIP_X+175, SHIP_Y+93, 6, false, false));
        this.rooms.add(new Room(50, 50, 5, SHIP_X+125, SHIP_Y+93, 7, false, false));
        this.rooms.add(new Room(50, 50, 5, SHIP_X+225, SHIP_Y+93, 8, false, false));
        this.rooms.add(new Room(50, 50, 5, SHIP_X+175, SHIP_Y+93, 9, false, false));
        
        this.components.add(new ShipGenerator("Generator", 10, SHIP_X+25, SHIP_Y+93, 1));
        this.components.get(0).setActEnergy(10);
        this.components.add(new ShipShield("Shields", SHIP_X+225, SHIP_Y+93, 8));
        this.components.get(1).setMaxEnergy(2);
        this.components.get(1).setActEnergy(1);
        this.components.add(new ShipOxygen("Oxygen", SHIP_X+75, SHIP_Y+143, 4));
        this.components.get(2).setMaxEnergy(1);
        this.components.get(2).setActEnergy(1);
        this.components.add(new ShipBridge("Bridge", SHIP_X+75, SHIP_Y+93, 2));
        this.components.get(3).setMaxEnergy(1);
        this.components.get(3).setActEnergy(1);
        this.components.add(new ShipLarzarett("Lazarett", SHIP_X+125, SHIP_Y+93, 7));
        this.components.get(4).setMaxEnergy(1);
        this.components.get(4).setActEnergy(1);
        this.components.add(new ShipWeapons("Weapon", SHIP_X+75, SHIP_Y+43, 3));
        this.components.add(new ShipTurbines("TurbineBig", SHIP_X-44, SHIP_Y+93, true, 1));
        this.components.get(6).setMaxEnergy(1);
        this.components.get(6).setActEnergy(1);
        
        this.weapons.add(new Laser(SHIP_X+145, SHIP_Y+33, 1));
        this.weapons.add(new IonCannon(SHIP_X+145, SHIP_Y+188, 2));
        this.weapons.add(new Torpedo(SHIP_X+370, SHIP_Y+93, 3));
        
        for (int i = 0; i < this.weapons.size(); i++) {
            this.components.get(5).setMaxEnergy(this.components.get(5).getMaxEnergy()+this.weapons.get(i).getMaxEnergy());
            this.components.get(5).setActEnergy(this.components.get(5).getActEnergy()+this.weapons.get(i).getActEnergy());
        }
        
        this.actions.add(new ShipactionWarpjump());
        
        this.units.add(new Human());
        this.units.add(new Human());
        this.units.add(new Human());
        this.units.add(new Human());
        this.units.add(new Human());
        this.units.add(new Human());
        this.units.add(new Human());
        this.units.add(new Human());
        this.units.add(new Human());
        this.units.add(new Human());
    }
}
