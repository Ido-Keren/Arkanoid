package Movement.HitAffects;

import Games.Animations.GameLevel;
import Calculations.*;
import Sprites.*;

/**
 * Class Represents the listener that will remove a block.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * A builder of a block remover.
     *
     * @param game the game to remove from.
     * @param removedBlocks the counter of the amount of blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * The ball hitter hit the block beingHit, remove the block from the game and update remaining blocks accordingly.
     *
     * @param beingHit the object that is being hit.
     * @param hitter the ball hitting the object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
        beingHit.removeFromGame(game);
    }
}
