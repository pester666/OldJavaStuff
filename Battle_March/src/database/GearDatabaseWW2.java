package database;

import lib.Gear;

public class GearDatabaseWW2 {
 
//                      FANTASY    
//MELEE WEAPONS
//                                        name,  meele,  s,ap,rof, range         -> rof = rate of fire // s = strength
    public Gear mp40 =          new Gear("MP40", true,  3, 6, 2, 4);
    public Gear mp44 =          new Gear("MP44", true,  3, 5, 2, 6);
    public Gear thompson =          new Gear("Thompson", true,  3, 6, 3, 3);


  
//RANGED WEAPONS
//                                        name,  meele,  s,ap,rof, range         -> rof = rate of fire // s = strength
    public Gear mg42 =          new Gear("MG42", false, 5, 4, 4, 8);
    public Gear kar98 =         new Gear("Kar98", false, 4, 5, 1, 9);
    public Gear mortar =        new Gear("Mortar", false, 4, 5, 1, 15);
    public Gear bazooka =        new Gear("Bazooka", false, 7, 4, 1, 6);
    public Gear garand =        new Gear("M1Garand", false, 3, 6, 2, 7);
    public Gear artillery =        new Gear("Artillery", false, 7, 3, 1, 25);

      
}
