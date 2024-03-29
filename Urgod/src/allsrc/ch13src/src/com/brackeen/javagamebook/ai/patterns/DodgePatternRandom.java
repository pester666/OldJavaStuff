package allsrc.ch13src.src.com.brackeen.javagamebook.ai.patterns;

import java.util.Collections;
import java.util.Iterator;

import allsrc.ch13src.src.com.brackeen.javagamebook.bsp2D.BSPTree;
import allsrc.ch13src.src.com.brackeen.javagamebook.game.GameObject;
import allsrc.ch13src.src.com.brackeen.javagamebook.math3D.Vector3D;
import allsrc.ch13src.src.com.brackeen.javagamebook.util.MoreMath;

/**
    An "dodge" pattern that puts the bot in a random location
    in a half-circle around the player.
*/
public class DodgePatternRandom extends AIPattern {

    private float radiusSq;

    public DodgePatternRandom(BSPTree tree) {
        this(tree, 500);
    }

    public DodgePatternRandom(BSPTree tree, float radius) {
        super(tree);
        this.radiusSq = radius * radius;
    }


    public Iterator find(GameObject bot, GameObject player) {

        Vector3D goal = getLocationFromPlayer(bot, player,
            radiusSq);

        // pick a random location on this half-circle
        // (-90 to 90 degrees from current location)
        float maxAngle = (float)Math.PI/2;
        float angle = MoreMath.random(-maxAngle, maxAngle);
        goal.subtract(player.getLocation());
        goal.rotateY(angle);
        goal.add(player.getLocation());
        calcFloorHeight(goal, bot.getFloorHeight());

        return Collections.singleton(goal).iterator();
    }

}

