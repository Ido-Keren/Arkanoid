package Games.Animations;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * Class runs inputted animations on its GUI.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * A builder for an animationRunner.
     *
     * @param g the animation runners GUI.
     * @param fps the animations runner frames per second.
     */
    public AnimationRunner(GUI g, int fps) {
        this.gui = g;
        this.framesPerSecond = fps;
    }

    /**
     * Method run the animation inputted.
     *
     * @param animation the animation to run.
     */
    public void run(Animation animation) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = this.framesPerSecond;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
