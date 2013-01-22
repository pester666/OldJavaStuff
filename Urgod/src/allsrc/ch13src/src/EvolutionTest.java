package allsrc.ch13src.src;

import java.util.Iterator;
import java.util.List;

import allsrc.ch13src.src.com.brackeen.javagamebook.ai.EvolutionBot;
import allsrc.ch13src.src.com.brackeen.javagamebook.ai.EvolutionGenePool;
import allsrc.ch13src.src.com.brackeen.javagamebook.game.GameObject;
import allsrc.ch13src.src.com.brackeen.javagamebook.math3D.PolygonGroup;
import allsrc.ch13src.src.com.brackeen.javagamebook.shooter3D.HeadsUpDisplay;
import allsrc.ch13src.src.com.brackeen.javagamebook.shooter3D.MessageQueue;
import allsrc.ch13src.src.com.brackeen.javagamebook.shooter3D.Player;

public class EvolutionTest extends PathFindingTest {

    private EvolutionGenePool genePool;

    public static void main(String[] args) {
        new EvolutionTest(args, "src/allsrc/ch13src/images/sample3.map").run();
    }

    public EvolutionTest(String[] args, String defaultMap) {
        super(args, defaultMap);
    }

    public void stop() {
        super.stop();

        // print information about the "brains" in the gene pool.
        System.out.println(genePool);
    }

    protected void createGameObjects(List mapObjects) {

        drawInstructions = false;
        MessageQueue queue = MessageQueue.getInstance();
        addOverlay(queue);
        addOverlay(new HeadsUpDisplay(
            (Player)gameObjectManager.getPlayer()));
        queue.setDebug(true);
        queue.add("Use the mouse/arrow keys to move.");
        queue.add("Press Esc to exit.");

        genePool = new EvolutionGenePool(bspTree);

        Iterator i= mapObjects.iterator();
        while (i.hasNext()) {
            PolygonGroup group = (PolygonGroup)i.next();
            String filename = group.getFilename();
            if (filename != null && filename.endsWith("bot.obj")) {

                EvolutionBot bot = new EvolutionBot(group,
                    collisionDetection, genePool,
                    botProjectileModel);
                bot.setRegenerating(true);
                gameObjectManager.add(bot);
            }
            else {
                // static object
                gameObjectManager.add(new GameObject(group));
            }
        }
    }

}
