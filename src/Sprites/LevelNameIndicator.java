package Sprites;

import Calculations.Counter;
import Games.Animations.GameLevel;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * Represents a two-dimensional space level name indicator.
 */
public class LevelNameIndicator implements Sprite{
    private String levelName;

    /**
     * A constructor of a level name indicator.
     *
     * @param levelName the level's name.
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(450,15,"Level Name: " + this.levelName, 10);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
