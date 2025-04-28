package Games.Animations;

import Games.LevelInformation;
import biuoop.KeyboardSensor;
import Movement.*;
import Movement.HitAffects.*;
import Sprites.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.*;
import java.util.LinkedList;
import Calculations.Counter;

/**
 * Class stores the information and methods needed to operate our level.
 */
public class GameLevel implements Animation {

    private Counter blocksAmount;
    private Counter ballsAmount;
    private LevelInformation info;
    private GameEnvironment environment;
    private SpriteCollection sprites;
    private GUI gui;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;

    /**
     * A constructor of a new game level.
     */
    public GameLevel(LevelInformation info, GUI gui) {
        this.info = info;
        this.gui = gui;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksAmount = new Counter(this.info.numberOfBlocksToRemove());
        this.ballsAmount = new Counter(this.info.numberOfBalls());
        this.runner = new AnimationRunner(this.gui, 60);
        this.keyboard = this.gui.getKeyboardSensor();
    }

    /**
     * Method returns the amount of blocks in our level.
     *
     * @return the amount of blocks in our game level.
     */
    public Counter getBlocksAmount() {
        return this.blocksAmount;
    }

    /**
     * Method returns the amount of balls in our game level.
     *
     * @return the amount of balls in our game level.
     */
    public Counter getBallsAmount() {
        return this.ballsAmount;
    }

    /**
     * Method adds a collidable to our environment.
     *
     * @param c the colliadable to add to our environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Method adds a sprite to our collection.
     *
     * @param s the sprite to add to our collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Method removes inputted collidable from our game level.
     *
     * @param c the collidable to remove from our game level.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Method removes inputted sprite from our game level.
     *
     * @param s the sprite to remove from our game level.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Method sets the level up- creates all the colliadables and sprites and adds them to our game level.
     */
    public void initialize(Counter score) {
        java.util.LinkedList<Block> blocks = new LinkedList<Block>();
        BlockRemover p1 = new BlockRemover(this, this.getBlocksAmount());
        BallRemover p2 = new BallRemover(this, this.getBallsAmount());
        ScoreIndicator s = new ScoreIndicator(score);
        LevelNameIndicator indicator = new LevelNameIndicator(this.info.levelName());
        ScoreTrackingListener p3 = new ScoreTrackingListener(this, s.getScore());

        // the game borders and background.
        this.info.getBackground().addToGame(this);
        blocks.add(new Block(0, 0, 800, 20, Color.gray));
        blocks.add(new Block(0, 20, 20, 580, Color.black));
        blocks.add(new Block(780, 20, 20, 580, Color.black));
        blocks.add(new Block(0, 20, 800, 20, Color.black));
        for (Block b : blocks) {
            b.addToGame(this);
        }
        Block killerBlock = new Block(0, 599, 800, 10, Color.red);
        killerBlock.addHitListener(p2);
        killerBlock.addToGame(this);
        s.addToGame(this);
        indicator.addToGame(this);

        // the blocks in game
        for (Block b : info.blocks()) {
            b.addToGame(this);
            b.addHitListener(p1);
            b.addHitListener(p3);
        }

        // the balls in the game
        for (int i = 0; i < this.info.numberOfBalls(); i++) {
            Ball b = new Ball (400, 525, 6, Color.blue, this.environment);
            b.setVelocity(this.info.initialBallVelocities().get(i));
            b.addToGame(this);
        }

        // the paddle in the game
        Paddle paddle = new Paddle(this.gui, this.info.paddleSpeed(), this.info.paddleWidth());
        paddle.addToGame(this);
    }

    /**
     * Method runs the level.
     */
    public void run() {
        this.running = false;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        return this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blocksAmount.getValue() == 0 || this.ballsAmount.getValue() == 0) {
            this.running = true;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }
    }
}
