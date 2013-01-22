package allsrc.ch13src.src.com.brackeen.javagamebook.ai.patterns;

import java.util.Collections;
import java.util.Iterator;

import allsrc.ch13src.src.com.brackeen.javagamebook.bsp2D.BSPTree;
import allsrc.ch13src.src.com.brackeen.javagamebook.game.GameObject;
import allsrc.ch13src.src.com.brackeen.javagamebook.math3D.Vector3D;

/**
    Direct aim at the player. Aim patterns return a direction
    to fire, rather than a location.
*/
public class RunAwayPattern extends AIPattern {

    public RunAwayPattern(BSPTree tree) {
        super(tree);
    }

    public Iterator find(GameObject bot, GameObject player) {
        // dumb move: run in the *opposite* direction of the
        // player (will cause bots to run into walls!)

        Vector3D goal = new Vector3D(player.getLocation());
        goal.subtract(bot.getLocation());

        // opposite direction
        goal.multiply(-1);

        // far, far away
        goal.multiply(100000);
        calcFloorHeight(goal, bot.getFloorHeight());

        // return an iterator
        return Collections.singleton(goal).iterator();
    }
}

