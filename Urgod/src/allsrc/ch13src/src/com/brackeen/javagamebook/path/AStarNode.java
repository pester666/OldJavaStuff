package allsrc.ch13src.src.com.brackeen.javagamebook.path;

import java.util.List;

import allsrc.ch13src.src.com.brackeen.javagamebook.util.MoreMath;

/**
    The AStarNode class, along with the AStarSearch class,
    implements a generic A* search algorthim. The AStarNode
    class should be subclassed to provide searching capability.
*/
public abstract class AStarNode implements Comparable {

    AStarNode pathParent;
    float costFromStart;
    float estimatedCostToGoal;


    public float getCost() {
        return costFromStart + estimatedCostToGoal;
    }


    public int compareTo(Object other) {
        float otherValue = ((AStarNode)other).getCost();
        float thisValue = this.getCost();

        return MoreMath.sign(thisValue - otherValue);
    }


    /**
        Gets the cost between this node and the specified
        adjacent (aka "neighbor" or "child") node.
    */
    public abstract float getCost(AStarNode node);


    /**
        Gets the estimated cost between this node and the
        specified node. The estimated cost should never exceed
        the true cost. The better the estimate, the more
        effecient the search.
    */
    public abstract float getEstimatedCost(AStarNode node);


    /**
        Gets the children (aka "neighbors" or "adjacent nodes")
        of this node.
    */
    public abstract List getNeighbors();
}

