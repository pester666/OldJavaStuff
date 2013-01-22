package allsrc.ch13src.src.com.brackeen.javagamebook.ai;

import allsrc.ch13src.src.com.brackeen.javagamebook.game.CollisionDetection;
import allsrc.ch13src.src.com.brackeen.javagamebook.math3D.PolygonGroup;

public class EvolutionBot extends AIBot {

    private EvolutionGenePool genePool;
    private long damagedCaused;

    public EvolutionBot(PolygonGroup polygonGroup,
        CollisionDetection collisionDetection,
        EvolutionGenePool genePool, PolygonGroup blastModel)
    {
        super(polygonGroup, collisionDetection,
            genePool.getNewBrain(), blastModel);
        this.genePool = genePool;
        setRegenerating(true);
    }

    public void regenerate() {
        genePool.notifyDead(brain, damagedCaused);
        brain = genePool.getNewBrain();
        damagedCaused = 0;
        super.regenerate();
    }

    public void notifyHitPlayer(long damage) {
        damagedCaused+=damage;
    }
}
