package Games;

import Movement.Velocity;
import Sprites.Block;
import Sprites.Sprite;
import java.util.List;

/**
 * Interface for level's information.
 */
public interface LevelInformation {

    /**
     * Method returns the number of balls.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * Method returns the initial velocity of each ball.
     *
     * @return the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Method returns the paddle's speed.
     *
     * @return the paddle's speed.
     */
    int paddleSpeed();

    /**
     * Method returns the paddle's width.
     *
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * Method returns the level's name.
     *
     * @return the level's name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.

    /**
     * Method returns the Blocks that make up this level.
     *
     * @return the Blocks that make up this level
     */
    List<Block> blocks();

    /**
     * Method returns the number of blocks that should be removed.
     *
     * @return the number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
