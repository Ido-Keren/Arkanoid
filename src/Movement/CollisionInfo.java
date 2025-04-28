package Movement;

import Shapes.*;

/**
 * Class stores the closest collision point and object.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Returns the collision point.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collision object.
     *
     * @return the collision object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * Sets collision point to inputted collision point.
     *
     * @param collisionPoint the collision point to set ours to.
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * Sets collision object to inputted collision object.
     *
     * @param collisionObject the collision object to set ours to.
     */
    public void setCollisionObject(Collidable collisionObject) {
        this.collisionObject = collisionObject;
    }

    /**
     * A constructor for our collision info.
     */
    public CollisionInfo() {
        this.collisionObject = null;
        this.collisionPoint = null;
    }
}
