package database;

import lib.Gear;

public class GearDatabaseFantasy {
 
//MELEE WEAPONS
//                                        name,         meele,s,ap,rof, range         -> rof = rate of fire // s = strength
    public Gear spear =         new Gear("Spear",       true, 0, 7, 0, 2);
    public Gear halberd =       new Gear("Halberd",     true, 1, 7, 0, 1);
    public Gear dagger =        new Gear("Dagger",      true, 0, 7, 1, 1);
    public Gear sword =         new Gear("Sword",       true, 0, 6, 1, 1);
    public Gear chopper =       new Gear("Chopper",     true, 1, 6, 0, 1);
    public Gear greatsword =    new Gear("Greatsword",  true, 2, 5, 0, 1);
    public Gear axe =           new Gear("Axe",         true, 0, 5, 0, 1);

  
//RANGED WEAPONS
//                                        name,         meele, s,ap,rof, range         -> rof = rate of fire // s = strength
    public Gear bow =           new Gear("Bow",         false, 3, 5, 1, 7);
    public Gear longbow =       new Gear("Longbow",     false, 3, 5, 1, 10);
    public Gear rockSling =     new Gear("Sling",       false, 2, 7, 2, 4);
    public Gear crossbow =      new Gear("Crossbow",    false, 4, 5, 1, 9);
    public Gear rifle =         new Gear("Rifle",       false, 4, 4, 1, 7);
    public Gear rockLobber =      new Gear("Catapult",    false, 8, 3, 1, 15); 
    
}
