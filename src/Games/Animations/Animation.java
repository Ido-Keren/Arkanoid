package Games.Animations;

import biuoop.DrawSurface;

/**
 * Interface for all game animations.
 */
public interface Animation {

    /**
     * moves one frame our animation.
     *
     * @param d the surface to animate on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Return true if animation should stop.
     *
     * @return true if animation should stop.
     */
    boolean shouldStop();
}
