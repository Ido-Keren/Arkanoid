package Movement;

import Calculations.DoubleComparing;
import Shapes.*;
import java.util.LinkedList;

/**
 * Class represents the environment in our game.
 */
public class GameEnvironment {
    private java.util.List<Collidable> environment;

    // constant used.
    static final int NONE = -1;

    /**
     * A constructor of our environment.
     */
    public GameEnvironment(){
        environment = new LinkedList<Collidable>();
    }

    /**
     * Returns out environment.
     *
     * @return our environment.
     */
    public java.util.List<Collidable> getEnvironment() {
        return this.environment;
    }

    /**
     * Method adds a collidable into our environment.
     *
     * @param c the collidable to add into our environment
     */
    public void addCollidable(Collidable c){
        environment.add(c);
    }

    /**
     * Method removes inputted collidable from our collection.
     *
     * @param s the collidable to remove from our collection.
     */
    public void removeCollidable (Collidable s) {
        this.environment.remove(s);
    }

    /**
     * Method finds the closest collision point with a collidable in our environment from the
     * start of the inputted line (returns null if there's no collision points).
     *
     * @param trajectory the inputted line to check.
     * @return the closest collision point (returns null if there's no collision points).
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        DoubleComparing d = new DoubleComparing();
        CollisionInfo cInfo = new CollisionInfo();
        double dictance = NONE;
        for (Collidable c : this.environment) {
             Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
             if (p != null) {
                 if (p.distance(trajectory.start()) < dictance || dictance == NONE) {
                     dictance = p.distance(trajectory.start());
                     cInfo.setCollisionPoint(p);
                     cInfo.setCollisionObject(c);
                 }
             }
        }
        if (cInfo.collisionObject() == null){
            return null;
        }
        return cInfo;
    }

}
