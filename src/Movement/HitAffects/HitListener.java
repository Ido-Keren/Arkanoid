package Movement.HitAffects;

import Sprites.*;

/**
 * Interface for all Listeners of a hit.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the object that is being hit.
     * @param hitter the ball hitting the object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
