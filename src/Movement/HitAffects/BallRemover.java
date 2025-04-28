package Movement.HitAffects;

import Calculations.Counter;
import Games.Animations.GameLevel;
import Sprites.Ball;
import Sprites.Block;

/**
 * Class Represents the listener that will remove a ball.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * A builder of a ball remover.
     *
     * @param game the game to remove from.
     * @param removedBalls the counter of the amount of balls.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * The ball hitter hit the block beingHit, remove the ball from the game and update remaining balls accordingly.
     *
     * @param beingHit the object that is being hit.
     * @param hitter the ball hitting the object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        game.removeSprite(hitter);
    }
}
