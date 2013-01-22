package lib;

public class Unit {

    public int range = 0;
    public int armorsave = 0;
    public int movement = 0;
    public int rangedSkill = 0;
    public int melee = 0;
    public int toughness = 0;
    public int strength = 0;
    public int attacks = 0;
    public int costs = 0;
    public int hp = 0;
    public int maxHp = 0;
    public String name = null;
    public String playerName = null;
    public Gear gear = null;
    public boolean alreadyMoved = false;
    public boolean alreadyAttacked = false;
    public int unitType = 0;
    public String race = Constants.RACE_ENVIRONMENT;
    
    //TODO More Gear
    //TODO vehicle, artillery etc.
    
    public Unit(){
        
    }

    /**
     *     /**
     * Constructor of a single Unit
     * 
     * @param range Range of the Weapon
     * @param armorsave The Armorsave against a hit
     * @param movement The range of the Movement of the Unit
     * @param rangedSkill The skill of the rangedcombat of the Unit
     * @param melee The skill of the meleeattacks of the Unit
     * @param name The Name of the Unit
     * @param toughness How good the unit can resist an incoming attack
     * @param strength How strong the unit attacks in close combat
     * @param attacks How many attacks the unit has
     * @param hp How much hitpoints the unit has left
     * @param maxHp How much hitpoints the unit got
     * @param playerName The name of the owning player
     * @param gear The equipped gear of the unit
     * @param alreadyMoved If the unit has already move
     * @param alreadyAttacked If the unit has already attacked
     * @param costs The gold-cost of this unit
     * @param unitType The Unittype
     */
    public Unit(int range, 
                int armorsave, 
                int movement, 
                int rangedSkill, 
                int melee, 
                int toughness, 
                int strength, 
                int attacks, 
                int hp,
                int maxHp,
                String name, 
                String playerName, 
                Gear gear,
                boolean alreadyMoved,
                boolean alreadyAttacked,
                int costs,
                int unitType,
                String race) {
        super();
        this.range = range;
        this.armorsave = armorsave;
        this.movement = movement;
        this.rangedSkill = rangedSkill;
        this.melee = melee;
        this.toughness = toughness;
        this.strength = strength;
        this.attacks = attacks;
        this.hp = hp;
        this.maxHp = maxHp;
        this.name = name;
        this.playerName = playerName;
        this.gear = gear;
        this.alreadyMoved = alreadyMoved;
        this.alreadyAttacked = alreadyAttacked;
        this.costs = costs;
        this.unitType = unitType;
        this.race = race;
    }

    public Unit(Unit listSelection, String playerName) {
        this.range = listSelection.range;
        this.armorsave = listSelection.armorsave;
        this.movement = listSelection.movement;
        this.rangedSkill = listSelection.rangedSkill;
        this.melee = listSelection.melee;
        this.toughness = listSelection.toughness;
        this.strength = listSelection.strength;
        this.attacks = listSelection.attacks;
        this.hp = listSelection.hp;
        this.maxHp = listSelection.maxHp;
        this.name = listSelection.name;
        this.playerName = playerName;
        this.gear = listSelection.gear;
        this.alreadyMoved = listSelection.alreadyMoved;
        this.alreadyAttacked= listSelection.alreadyAttacked;
        this.costs = listSelection.costs;
        this.unitType = listSelection.unitType;
        this.race = listSelection.race;
    }     
}
