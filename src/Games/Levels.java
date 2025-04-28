package Games;

import Movement.Velocity;
import Sprites.Block;
import Sprites.Sprite;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Levels implements LevelInformation{
    private int numOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;

    /**
     * A builder for a level.
     *
     * @param numOfBalls the level's number of balls.
     * @param paddleSpeed the level's speed of paddle.
     * @param paddleWidth the level's width of paddle.
     * @param levelName the level's name.
     * @param background the level's background.
     * @param blocks the level's blocks.
     * @param initialBallVelocities the level's initial ball's velocities.
     */
    public Levels(int numOfBalls, int paddleSpeed, int paddleWidth, String levelName, Sprite background,
                 List<Block> blocks, List<Velocity> initialBallVelocities) {
        this.numOfBalls = numOfBalls;
        this.paddleWidth = paddleWidth;
        this.paddleSpeed = paddleSpeed;
        this.levelName = levelName;
        this.background = background;
        this.initialBallVelocities = initialBallVelocities;
        this.blocks = blocks;
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities(){
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    /**
     * Method create level one in our game.
     *
     * @return level one in our game.
     */
    public static LevelInformation setLevel1() {
        int numOfBalls = 1;
        int paddleWidth = 100;
        int paddleSpeed = 6;
        String levelName = "Level One";
        Sprite background = new Block(0, 0, 800, 600, Color.darkGray);
        List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(0, 8));
        List<Block> blocks = new ArrayList<Block>();
        Block b = new Block(370, 70, 60, 30, Color.red);
        blocks.add(b);
        return new Levels(numOfBalls, paddleSpeed, paddleWidth, levelName, background, blocks, initialBallVelocities);
    }

    /**
     * Method create level two in our game.
     *
     * @return level two in our game.
     */
    public static LevelInformation setLevel2() {
        int numOfBalls = 10;
        int paddleWidth = 450;
        int paddleSpeed = 6;
        String levelName = "Level Two";
        Sprite background = new Block(0, 0, 800, 600, Color.cyan);
        List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
        double dx = 4, dy = -2, changer = -0.5;
        for (int i = -50; i <= 50 ;i = i + 10) {
            if (i == 0) {
                i = i + 10;
                dx = -2;
                changer = 0.5;
            }
            initialBallVelocities.add(new Velocity(dx,dy));
            dx = dx - 0.5;
            dy = dy + changer;

        }
        List<Block> blocks = new ArrayList<Block>();
        Color color = Color.pink;
        boolean ispink = true;
        for(int i = 20; i < 720; i = i + 76) {
            Block b = new Block(i, 70, 76, 30, color);
            blocks.add(b);
            if (ispink) {
                color = Color.magenta;
                ispink = false;
            }
            else {
                color = Color.pink;
                ispink = true;
            }
        }
        return new Levels(numOfBalls, paddleSpeed, paddleWidth, levelName, background, blocks, initialBallVelocities);
    }

    /**
     * Method create level three in our game.
     *
     * @return level three in our game.
     */
    public static LevelInformation setLevel3() {
        int numOfBalls = 2;
        int paddleWidth = 100;
        int paddleSpeed = 6;
        String levelName = "Level Three";
        Sprite background = new Block(0, 0, 800, 600, Color.green.darker());
        List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(-30, 8));
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(30, 8));
        List<Block> blocks = new ArrayList<Block>();
        int row = 1, column = 10;
        Color color = Color.cyan.darker().darker().darker().darker();
        for (; row <= 5 ; row++){
            Point lastUpperLeft = new Point(720, 30 + 30 * row);
            for (int i = 0; i < column; i++) {
                Block b = new Block(lastUpperLeft.getX() - i * 60, lastUpperLeft.getY(),
                        60, 30, color);
                blocks.add(b);
            }
            column--;
            color = color.brighter();
        }
        return new Levels(numOfBalls, paddleSpeed, paddleWidth, levelName, background, blocks, initialBallVelocities);
    }
}
