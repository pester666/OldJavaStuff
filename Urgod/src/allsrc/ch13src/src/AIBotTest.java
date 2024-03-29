package allsrc.ch13src.src;

import java.util.Iterator;
import java.util.List;

import allsrc.ch13src.src.com.brackeen.javagamebook.ai.AIBot;
import allsrc.ch13src.src.com.brackeen.javagamebook.ai.Brain;
import allsrc.ch13src.src.com.brackeen.javagamebook.ai.patterns.AimPattern;
import allsrc.ch13src.src.com.brackeen.javagamebook.ai.patterns.AttackPatternRush;
import allsrc.ch13src.src.com.brackeen.javagamebook.ai.patterns.AttackPatternStrafe;
import allsrc.ch13src.src.com.brackeen.javagamebook.ai.patterns.DodgePatternRandom;
import allsrc.ch13src.src.com.brackeen.javagamebook.ai.patterns.DodgePatternZigZag;
import allsrc.ch13src.src.com.brackeen.javagamebook.ai.patterns.RunAwayPattern;
import allsrc.ch13src.src.com.brackeen.javagamebook.game.GameObject;
import allsrc.ch13src.src.com.brackeen.javagamebook.math3D.PolygonGroup;
import allsrc.ch13src.src.com.brackeen.javagamebook.path.AStarSearchWithBSP;
import allsrc.ch13src.src.com.brackeen.javagamebook.shooter3D.Bot;
import allsrc.ch13src.src.com.brackeen.javagamebook.shooter3D.HeadsUpDisplay;
import allsrc.ch13src.src.com.brackeen.javagamebook.shooter3D.MessageQueue;
import allsrc.ch13src.src.com.brackeen.javagamebook.shooter3D.Player;

public class AIBotTest extends PathFindingTest {

    private Brain averageBrain;
    private Brain aggressiveBrain;
    private Brain scaredBrain;

    public static void main(String[] args) {
        new AIBotTest(args, "src/allsrc/ch13src/images/sample3.map").run();
    }

    public AIBotTest(String[] args, String defaultMap) {
        super(args, defaultMap);
    }

    protected void createBrains() {

        averageBrain = new Brain();
        averageBrain.attackPathFinder =
            new AttackPatternRush(bspTree);
        averageBrain.aimPathFinder =
            new AimPattern(bspTree);
        averageBrain.dodgePathFinder =
            new DodgePatternRandom(bspTree);
        averageBrain.idlePathFinder = null;
        averageBrain.chasePathFinder =
            new AStarSearchWithBSP(bspTree);
        averageBrain.runAwayPathFinder =
            new RunAwayPattern(bspTree);

        averageBrain.attackProbability = 0.50f;
        averageBrain.dodgeProbability = 0.40f;
        averageBrain.runAwayProbability = 0.10f;

        averageBrain.decisionTime = 4000;
        averageBrain.aimTime = 1000;
        averageBrain.hearDistance = 1000;

        // aggresive brain
        aggressiveBrain = new Brain();
        aggressiveBrain.attackPathFinder =
            new AttackPatternStrafe(bspTree);
        aggressiveBrain.aimPathFinder =
            new AimPattern(bspTree);
        aggressiveBrain.dodgePathFinder =
            new DodgePatternZigZag(bspTree);
        aggressiveBrain.idlePathFinder = null;
        aggressiveBrain.chasePathFinder =
            new AStarSearchWithBSP(bspTree);
        aggressiveBrain.runAwayPathFinder = null;

        aggressiveBrain.attackProbability = 0.8f;
        aggressiveBrain.dodgeProbability = 0.2f;
        aggressiveBrain.runAwayProbability = 0;

        aggressiveBrain.decisionTime = 2000;
        aggressiveBrain.aimTime = 300;
        aggressiveBrain.hearDistance = 1000;


        // scaredy brain
        scaredBrain = new Brain();
        scaredBrain.attackPathFinder =
            new AttackPatternRush(bspTree);
        scaredBrain.aimPathFinder =
            new AimPattern(bspTree);
        scaredBrain.dodgePathFinder =
            new DodgePatternZigZag(bspTree);
        scaredBrain.idlePathFinder = null;
        scaredBrain.chasePathFinder =
            new AStarSearchWithBSP(bspTree);
        scaredBrain.runAwayPathFinder =
            new RunAwayPattern(bspTree);

        scaredBrain.attackProbability = 0.20f;
        scaredBrain.dodgeProbability = 0.40f;
        scaredBrain.runAwayProbability = 0.40f;

        scaredBrain.decisionTime = 4000;
        scaredBrain.aimTime = 1000;
        scaredBrain.hearDistance = 2000;

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

        createBrains();

        Iterator i= mapObjects.iterator();
        while (i.hasNext()) {
            PolygonGroup group = (PolygonGroup)i.next();
            String filename = group.getFilename();
            if ("robot.obj".equals(filename)) {
                gameObjectManager.add(new Bot(group));
            }
            else if ("averagebot.obj".equals(filename)) {
                AIBot bot = new AIBot(group, collisionDetection,
                    averageBrain, botProjectileModel);
                gameObjectManager.add(bot);
            }
            else if ("aggressivebot.obj".equals(filename)) {
                AIBot bot = new AIBot(group, collisionDetection,
                    aggressiveBrain, botProjectileModel);
                gameObjectManager.add(bot);
            }
            else if ("scaredybot.obj".equals(filename)) {
                AIBot bot = new AIBot(group, collisionDetection,
                    scaredBrain, botProjectileModel);
                gameObjectManager.add(bot);
            }
            else {
                // static object
                gameObjectManager.add(new GameObject(group));
            }
        }
    }

}
