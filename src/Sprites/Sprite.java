package Sprites;

import Games.Animations.GameLevel;
import biuoop.DrawSurface;

/**
 * Interface for all in game objects.
 */
public interface Sprite {

    /**
     * Draws the sprite on the inputted surface.
     *
     * @param d the surface to draw the rectangle on.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify sprite that time has passed and do what he needs to everytime.
     */
    void timePassed();

    /**
     * Method adds the sprite to inputted game.
     *
     * @param g the game to add out sprite to.
     */
    void addToGame (GameLevel g);
}
