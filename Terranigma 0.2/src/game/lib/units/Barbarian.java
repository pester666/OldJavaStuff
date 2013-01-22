package game.lib.units;

import game.lib.weapons.Weapon;

public class Barbarian extends Unit {
    
    public Barbarian(int xCoord, int yCoord, int width, int hight, String name, int hp, int lvl, Weapon equippedWeapon, int direction) {
        super(xCoord, yCoord, width, hight, name, hp, lvl, equippedWeapon, direction);
    }
}
