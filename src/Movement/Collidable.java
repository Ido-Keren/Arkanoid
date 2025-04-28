package Movement;

import Sprites.Ball;
import Shapes.Rectangle;

/**
 * Interface for all in game collidables.
 */
public interface Collidable {

    /**
     * Returns the rectangle of our collidable.
     *
     * @return the rectangle of our collidable.
     */
    Rectangle getCollisionRectangle();

    /**
     * Method updates the given velocity based on the collision point.
     *
     * @param collisionPoint the point a collision occurred.
     * @param currentVelocity the current velocity to update.
     * @param hitter the bal that hit our collidable
     * @return the updated velocity.
     */
    Velocity hit(Ball hitter, Shapes.Point collisionPoint, Velocity currentVelocity);
}
