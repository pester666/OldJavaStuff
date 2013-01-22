package allsrc.ch11src.src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import allsrc.ch11src.src.com.brackeen.javagamebook.bsp2D.BSPRenderer;
import allsrc.ch11src.src.com.brackeen.javagamebook.bsp2D.BSPTree;
import allsrc.ch11src.src.com.brackeen.javagamebook.bsp2D.MapLoader;
import allsrc.ch11src.src.com.brackeen.javagamebook.game.CollisionDetection;
import allsrc.ch11src.src.com.brackeen.javagamebook.game.GameObject;
import allsrc.ch11src.src.com.brackeen.javagamebook.game.GameObjectRenderer;
import allsrc.ch11src.src.com.brackeen.javagamebook.game.GridGameObjectManager;
import allsrc.ch11src.src.com.brackeen.javagamebook.math3D.PointLight3D;
import allsrc.ch11src.src.com.brackeen.javagamebook.math3D.PolygonGroup;
import allsrc.ch11src.src.com.brackeen.javagamebook.math3D.PolygonGroupBounds;
import allsrc.ch11src.src.com.brackeen.javagamebook.math3D.Transform3D;
import allsrc.ch11src.src.com.brackeen.javagamebook.shooter3D.Bot;
import allsrc.ch11src.src.com.brackeen.javagamebook.shooter3D.Player;
import allsrc.ch11src.src.com.brackeen.javagamebook.shooter3D.ShooterCore;

public class CollisionTest extends ShooterCore {

    public static void main(String[] args) {
        new CollisionTest(args).run();
    }

    protected BSPTree bspTree;
    protected String mapFile;

    public CollisionTest(String[] args) {
        super(args);
        for (int i=0; mapFile == null && i<args.length; i++) {
            if (mapFile == null && !args[i].startsWith("-")) {
                mapFile = args[i];
            }
        }
        if (mapFile == null) {
            mapFile = "src/allsrc/ch11src/images/sample.map";
        }
    }

    public void createPolygons() {
        Graphics2D g = screen.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, screen.getWidth(), screen.getHeight());
        g.setColor(Color.WHITE);
        g.drawString("Loading...", 5, screen.getHeight() - 5);
        screen.update();

        float ambientLightIntensity = .2f;
        List lights = new LinkedList();
        lights.add(new PointLight3D(-100,100,100, .3f, -1));
        lights.add(new PointLight3D(100,100,0, .3f, -1));

        MapLoader loader = new MapLoader();
        loader.setObjectLights(lights, ambientLightIntensity);

        try {
            bspTree = loader.loadMap(mapFile);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        CollisionDetection collisionDetection =
            new CollisionDetection(bspTree);
        gameObjectManager = new GridGameObjectManager(
            bspTree.calcBounds(), collisionDetection);
        gameObjectManager.addPlayer(new GameObject(
            new PolygonGroup("Player")));

        // set up player bounds
        PolygonGroupBounds playerBounds =
            gameObjectManager.getPlayer().getBounds();
        playerBounds.setTopHeight(
            Player.DEFAULT_PLAYER_HEIGHT);
        playerBounds.setRadius(
                Player.DEFAULT_PLAYER_RADIUS);

        ((BSPRenderer)polygonRenderer).setGameObjectManager(
            gameObjectManager);


        createGameObjects(loader.getObjectsInMap());
        Transform3D start = loader.getPlayerStartLocation();
        gameObjectManager.getPlayer().getTransform().setTo(start);
    }

    private void createGameObjects(List mapObjects) {
        Iterator i= mapObjects.iterator();
        while (i.hasNext()) {
            PolygonGroup group = (PolygonGroup)i.next();
            String filename = group.getFilename();
            if ("robot.obj".equals(filename)) {
                gameObjectManager.add(new Bot(group));
            }
            else {
                // static object
                gameObjectManager.add(new GameObject(group));
            }
        }
    }



    public void drawPolygons(Graphics2D g) {

        polygonRenderer.startFrame(g);

        // draw polygons in bsp tree (set z buffer)
        ((BSPRenderer)polygonRenderer).draw(g, bspTree);

        // draw game object polygons (check and set z buffer)
        gameObjectManager.draw(g,
            (GameObjectRenderer)polygonRenderer);

        polygonRenderer.endFrame(g);

    }
}
