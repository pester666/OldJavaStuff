package allsrc.ch11src.src.com.brackeen.javagamebook.shooter3D;

import allsrc.ch11src.src.com.brackeen.javagamebook.math3D.PolygonGroup;
import allsrc.ch11src.src.com.brackeen.javagamebook.math3D.PolygonGroupBounds;

/**
    A Player object.
*/
public class Player extends JumpingGameObject {

    public static final float DEFAULT_PLAYER_RADIUS = 32;
    public static final float DEFAULT_PLAYER_HEIGHT = 128;

    public Player() {
        this(new PolygonGroup("Player"));

        // set up player bounds
        PolygonGroupBounds playerBounds = getBounds();
        playerBounds.setTopHeight(DEFAULT_PLAYER_HEIGHT);
        playerBounds.setRadius(DEFAULT_PLAYER_RADIUS);
    }

    public Player(PolygonGroup group) {
        super(group);
    }

}
