package base.lib;

public class Weapon {

    protected String name;
    protected int range;
    protected int strength;
    protected int ap;
    protected boolean isAOE;
    protected int size;
    protected int attacks;
    protected boolean mayMoveAndShoot;
    protected boolean canScatter;
    protected int scatterRange;
    protected boolean hasFired;
    protected boolean getsMovementMalus;
    protected boolean mayShootRepeadetly;
    protected int shotsMade;
    protected int maxRepeatingShoots;
    public Weapon(String name, int range, int strength, int ap, boolean isAOE, int size, int attacks, boolean mayMoveAndShoot, boolean canScatter, int scatterRange, boolean hasFired, boolean getsMovementMalus, boolean mayShootRepeadetly,
        int shotsMade, int maxRepeatingShoots) {
        super();
        this.name = name;
        this.range = range;
        this.strength = strength;
        this.ap = ap;
        this.isAOE = isAOE;
        this.size = size;
        this.attacks = attacks;
        this.mayMoveAndShoot = mayMoveAndShoot;
        this.canScatter = canScatter;
        this.scatterRange = scatterRange;
        this.hasFired = hasFired;
        this.getsMovementMalus = getsMovementMalus;
        this.mayShootRepeadetly = mayShootRepeadetly;
        this.shotsMade = shotsMade;
        this.maxRepeatingShoots = maxRepeatingShoots;
    }
    

    
    
}
