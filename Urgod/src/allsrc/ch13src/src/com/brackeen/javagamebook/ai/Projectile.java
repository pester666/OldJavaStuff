package allsrc.ch13src.src.com.brackeen.javagamebook.ai;

import allsrc.ch13src.src.com.brackeen.javagamebook.game.GameObject;
import allsrc.ch13src.src.com.brackeen.javagamebook.math3D.MovingTransform3D;
import allsrc.ch13src.src.com.brackeen.javagamebook.math3D.PolygonGroup;
import allsrc.ch13src.src.com.brackeen.javagamebook.math3D.Vector3D;
import allsrc.ch13src.src.com.brackeen.javagamebook.shooter3D.Player;
import allsrc.ch13src.src.com.brackeen.javagamebook.util.MoreMath;

/**
    The Blast GameObject is a projectile, designed to travel
    in a straight line for five seconds, then die. Blasts
    destroy Bots instantly.
*/
public class Projectile extends GameObject {

    private static final long DIE_TIME = 5000;
    private static final float SPEED = 1.5f;
    private static final float ROT_SPEED = .008f;

    private MovingTransform3D transform;
    private long aliveTime;
    private AIBot sourceBot;
    private int minDamage;
    private int maxDamage;

    /**
        Create a new Blast with the specified PolygonGroup
        and normalized vector direction.
    */
    public Projectile(PolygonGroup polygonGroup,
        Vector3D direction, AIBot sourceBot, int minDamage,
        int maxDamage)
    {
        super(polygonGroup);
        this.sourceBot = sourceBot;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;

        transform = getTransform();
        Vector3D velocity = transform.getVelocity();
        velocity.setTo(direction);
        velocity.multiply(SPEED);
        transform.setVelocity(velocity);
        //transform.setAngleVelocityX(ROT_SPEED);
        transform.setAngleVelocityY(ROT_SPEED);
        transform.setAngleVelocityZ(ROT_SPEED);
        setState(STATE_ACTIVE);
    }


    public void update(GameObject player, long elapsedTime) {
        aliveTime+=elapsedTime;
        if (aliveTime >= DIE_TIME) {
            setState(STATE_DESTROYED);
        }
        else {
            super.update(player, elapsedTime);
        }
    }


    public boolean isFlying() {
        return true;
    }


    public void notifyObjectCollision(GameObject object) {
        // destroy bots and itself
        if (object instanceof Player) {
            int healthLost = MoreMath.random(minDamage, maxDamage);
            ((Player)object).addHealth(-healthLost);
            if (sourceBot != null) {
                sourceBot.notifyHitPlayer(healthLost);
            }
        }
        else if (object instanceof AIBot) {
            int healthLost = MoreMath.random(minDamage, maxDamage);
            ((AIBot)object).addHealth(-healthLost);
        }
        setState(STATE_DESTROYED);
    }


    public void notifyWallCollision() {
        getTransform().stop();
        setState(STATE_DESTROYED);
    }


    public void notifyFloorCollision() {
        notifyWallCollision();
    }


    public void notifyCeilingCollision() {
        notifyWallCollision();
    }
}
