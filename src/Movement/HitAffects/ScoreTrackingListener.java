package Movement.HitAffects;

import Games.Animations.GameLevel;
import Sprites.*;
import Calculations.Counter;

/**
 * Class Represents the listener that will update the score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private GameLevel game;

    /**
     * A builder of a score updater.
     *
     * @param game the game to update score for.
     * @param scoreCounter the counter of the amount of points.
     */
    public ScoreTrackingListener(GameLevel game, Counter scoreCounter) {
        this.currentScore = scoreCounter;
        this.game = game;
    }

    /**
     * The ball hitter hit the block beingHit, update currentScore accordingly (bonus points if last block).
     *
     * @param beingHit the object that is being hit.
     * @param hitter the ball hitting the object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(10);
        beingHit.removeHitListener(this);
        if (game.getBlocksAmount().getValue() == 0) {
            this.currentScore.increase(100);
        }
    }

}

