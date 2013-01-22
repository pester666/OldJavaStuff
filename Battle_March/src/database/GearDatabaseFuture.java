package database;

import lib.Gear;

public class GearDatabaseFuture {
 
//                      FANTASY    
//MELEE WEAPONS
//                                        name,  meele, s,ap,rof, range         -> rof = rate of fire // s = strength
    public Gear claw =         new Gear("Claw", true, 0, 4, 1, 1);


  
//RANGED WEAPONS
//                                       name, meele,  s,ap,rof, range         -> rof = rate of fire // s = strength
    public Gear lasgun =       new Gear("Lasgun",false, 3, 7, 1, 8);
    public Gear pistl =        new Gear("Pistl",false, 3, 7, 2, 3);
    public Gear shoota =       new Gear("Shoota",false, 4, 6, 3, 10);
    public Gear bolter =       new Gear("Bolter",false, 4, 5, 1, 8);
    public Gear mortar =       new Gear("Mortar", false, 4, 5, 1, 15);
    public Gear heavybolter =  new Gear("Heavy Bolter", false, 5, 4, 3, 8);
       
}
