package lexicanum.library.weapons.imp;

import lexicanum.library.specialRules.Tankscourger;

import lexicanum.library.specialRules.Sniper;

import lexicanum.library.specialRules.GetsHot;

import lexicanum.library.specialRules.Barrage;

import lexicanum.library.specialRules.Melta;

import lexicanum.library.specialRules.Gush;

import lexicanum.library.specialRules.Assault;

import lexicanum.library.specialRules.Synchronized;

import lexicanum.library.specialRules.Blast;
import lexicanum.library.specialRules.IgnoreCover;

import lexicanum.library.specialRules.Poisoned;

import lexicanum.library.specialRules.Pistol;

import lexicanum.library.specialRules.RapidFire;

import lexicanum.data.SpecialRule;
import lexicanum.library.specialRules.Heavy;

import lexicanum.data.ShootingWeapon;

public class IGWeapons {

    public static final ShootingWeapon autocannon = new ShootingWeapon("Maschinenkanone", "Autocannon", 7, 4, new SpecialRule[]{new Heavy(2)}, "48");
    public static final ShootingWeapon boltgun = new ShootingWeapon("Bolter", "Boltgun", 4, 5, new SpecialRule[]{new RapidFire()}, "24");
    public static final ShootingWeapon boltpistol = new ShootingWeapon("Boltpistole", "Boltpistol", 4, 5, new SpecialRule[]{new Pistol()}, "12");
    public static final ShootingWeapon chemCannon = new ShootingWeapon("Chemokanone", "Chem Cannon", 1, 3, new SpecialRule[]{new Heavy(1), new Poisoned(2)}, "Flammen");
    public static final ShootingWeapon eradicatorNovaCannon = new ShootingWeapon("Eradicator Nova Kanone", "Eradicator Nova Cannon", 6, 4, new SpecialRule[]{new Heavy(1), new Blast(5), new IgnoreCover()}, "36");
    public static final ShootingWeapon executionerPlasmaCannon = new ShootingWeapon("Executor Plasmakanone", "Executioner Plasma Cannon", 7, 2, new SpecialRule[]{new Heavy(3), new Blast(3)}, "36");
    public static final ShootingWeapon exterminatorAutocannon = new ShootingWeapon("Exterminator Maschinenkanone", "Exterminator Autocannon", 7, 4, new SpecialRule[]{new Heavy(4), new Synchronized()}, "48");
    public static final ShootingWeapon flamer = new ShootingWeapon("Flammenwerfer", "Flamer", 4, 5, new SpecialRule[]{new Assault(1), new IgnoreCover()}, "Flammenschablone");
    public static final ShootingWeapon heavyBolter = new ShootingWeapon("Schwerer Bolter", "Hevy Bolter", 5, 4, new SpecialRule[]{new Heavy(3)}, "36");
    public static final ShootingWeapon heavyFlamer = new ShootingWeapon("Schwerer Flammenwerfer", "Heavy Flamer", 5, 4, new SpecialRule[]{new Assault(1), new IgnoreCover()}, "Flammenschablone");
    public static final ShootingWeapon heavyStubber = new ShootingWeapon("Maschinengewehr", "Heavy Stubber", 4, 6, new SpecialRule[]{new Heavy(3)}, "36");
    public static final ShootingWeapon hotShotLasgun = new ShootingWeapon("Hochenergie-Lasergewehr", "Hot-shot Lasgun", 3, 3, new SpecialRule[]{new RapidFire()}, "18");
    public static final ShootingWeapon hotShotLaspistol = new ShootingWeapon("Hochenergie Laserpistole", "Hot-shot Laspistol", 3, 3, new SpecialRule[]{new Pistol()}, "6");
    public static final ShootingWeapon hydraAutocannon = new ShootingWeapon("Hydra Maschinenkanone", "Hydra Autocannon", 7, 4, new SpecialRule[]{new Heavy(2)}, "72");
    public static final ShootingWeapon infernoCannon = new ShootingWeapon("Inferno Kanone", "Inferno Cannon", 6, 4, new SpecialRule[]{new Heavy(1), new IgnoreCover(), new Gush()}, "Flammenschablone");
    public static final ShootingWeapon lascannon = new ShootingWeapon("Laserkanone", "Lascannon", 9, 2, new SpecialRule[]{new Heavy(1)}, "48");
    public static final ShootingWeapon lasgun = new ShootingWeapon("Lasergewehr", "Lasgun", 3, 0, new SpecialRule[]{new RapidFire()}, "24");
    public static final ShootingWeapon laspistol = new ShootingWeapon("Laserpistole", "Laspistol", 3, 0, new SpecialRule[]{new Pistol()}, "12");
    public static final ShootingWeapon meltaCannon = new ShootingWeapon("Melterkanone", "Melta Cannon", 8, 1, new SpecialRule[]{new Heavy(1), new Melta(), new Blast(3)}, "24");
    public static final ShootingWeapon meltagun = new ShootingWeapon("Melter", "Meltagun", 8, 1, new SpecialRule[]{new Assault(1), new Melta()}, "12");
    public static final ShootingWeapon mortar = new ShootingWeapon("Mörser", "Mortar", 4, 6, new SpecialRule[]{new Heavy(1), new Blast(3), new Barrage()}, "48");
    public static final ShootingWeapon multilaser = new ShootingWeapon("Multilaser", "Multilaser", 6, 6, new SpecialRule[]{new RapidFire()}, "24");
    public static final ShootingWeapon multimelta = new ShootingWeapon("Multimelter", "Multimelta", 8, 1, new SpecialRule[]{new Heavy(1), new Melta()}, "24");
    public static final ShootingWeapon multipleRocketPod = new ShootingWeapon("Salvenraketenwerfer", "Multiple Rocket Pod", 4, 6, new SpecialRule[]{new Heavy(1), new Blast(5)}, "24");
    public static final ShootingWeapon plasmaCannon = new ShootingWeapon("Plasmakanone", "Plasma Cannon", 7, 2, new SpecialRule[]{new Heavy(1), new Blast(3), new GetsHot()}, "36");
    public static final ShootingWeapon plasmagun = new ShootingWeapon("Plasmawerfer", "Plasma Gun", 7, 2, new SpecialRule[]{new RapidFire(), new GetsHot()}, "24");
    public static final ShootingWeapon plasmaPistol = new ShootingWeapon("Plasmapistole", "Plasma Pistol", 7, 2, new SpecialRule[]{new Pistol(), new GetsHot()}, "12");
    public static final ShootingWeapon punisherGatlingCannon = new ShootingWeapon("Punisher Gatlingkanone", "Punisher Gatling Cannon", 5, 0, new SpecialRule[]{new Heavy(20)}, "24");
    public static final ShootingWeapon ripperGun = new ShootingWeapon("Ogryn Knarre", "Ripper Gun", 5, 0, new SpecialRule[]{new Assault(3)}, "12");
    public static final ShootingWeapon ripperPistol = new ShootingWeapon("Ogryn Pistole", "Ripper Pistol", 0, 0, new SpecialRule[]{new Pistol(), new Sniper()}, "12");
    public static final ShootingWeapon sniperRifle = new ShootingWeapon("Scharfschützengewehr", "Sniper Rifle", 0, 6, new SpecialRule[]{new Heavy(1), new Sniper()}, "36");
    public static final ShootingWeapon stormbolter = new ShootingWeapon("Sturmbolter", "Stormbolter", 4, 5, new SpecialRule[]{new Assault(2)}, "24");
    public static final ShootingWeapon vanquisherBattleCannon = new ShootingWeapon("Vanquisher Kampfgeschütz", "Vanquisher Battle Cannon", 8, 2, new SpecialRule[]{new Heavy(1), new Tankscourger()}, "72");
}
