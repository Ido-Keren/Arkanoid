package Sprites;

import Calculations.Counter;
import Games.Animations.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Represents a two-dimensional space score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * A constructor of a score indicator.
     *
     * @param score the counter that counts our score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * A constructor of a score indicator.
     *
     * @param score the number that will be our current score in the counter.
     */
    public ScoreIndicator(int score) {
        this.score = new Counter(score);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(350,15,"Score: " + this.score, 10);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Method returns the score counter.
     *
     * @return the score counter.
     */
    public Counter getScore() {
        return this.score;
    }

}
