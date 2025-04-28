package Games.Animations;

import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.*;

/**
 * Class creates a countdown animation.
 */
public class CountdownAnimation implements Animation {
    double numOfSeconds;
    int countFrom;
    int secondsLeft;
    SpriteCollection gameScreen;

    /**
     * A builder for a countdown animation.
     *
     * @param numOfSeconds amount of seconds for screen to appear.
     * @param countFrom the number to count from to zero
     * @param gameScreen the screen to have in the background.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, Sprites.SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.secondsLeft = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.secondsLeft < this.countFrom) {
            Sleeper sleeper = new Sleeper();
            long wait = 1000 * (long) numOfSeconds / (long) countFrom;
            sleeper.sleepFor(wait);
        }
        gameScreen.drawAllOn(d);
        if (this.secondsLeft > 0) {
            Integer countDisplay = this.secondsLeft;
            d.setColor(Color.white);
            d.drawText(400, d.getHeight() / 2, countDisplay.toString(), 32);
        }
        this.secondsLeft--;
    }

    @Override
    public boolean shouldStop(){
        return (this.secondsLeft < 0);
    }


}
