package allsrc.ch13src.src.com.brackeen.javagamebook.ai.patterns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import allsrc.ch13src.src.com.brackeen.javagamebook.bsp2D.BSPTree;
import allsrc.ch13src.src.com.brackeen.javagamebook.game.GameObject;
import allsrc.ch13src.src.com.brackeen.javagamebook.math3D.Vector3D;
import allsrc.ch13src.src.com.brackeen.javagamebook.util.MoreMath;

/**
    An "dodge" pattern that makes the bot zig perpindicular to
    the player, the zag back to the starting location.
*/
public class DodgePatternZigZag extends AIPattern {

    private float dodgeDist;

    public DodgePatternZigZag(BSPTree tree) {
        this(tree, 200);
    }

    public DodgePatternZigZag(BSPTree tree, float dodgeDist) {
        super(tree);
        this.dodgeDist = dodgeDist;
    }


    public Iterator find(GameObject bot, GameObject player) {

        // create the vector to the dodge location
        Vector3D zig = new Vector3D(bot.getLocation());
        zig.subtract(player.getLocation());
        zig.normalize();
        zig.multiply(dodgeDist);
        zig.rotateY((float)Math.PI/2);

        // 50% chance - dodge one way or the other
        if (MoreMath.chance(.5f)) {
            zig.multiply(-1);
        }

        // convert vector to absolute location
        zig.add(bot.getLocation());
        calcFloorHeight(zig, bot.getFloorHeight());

        Vector3D zag = new Vector3D(bot.getLocation());
        calcFloorHeight(zag, bot.getFloorHeight());

        List path = new ArrayList();
        path.add(zig);
        path.add(zag);

        return path.iterator();
    }
}

