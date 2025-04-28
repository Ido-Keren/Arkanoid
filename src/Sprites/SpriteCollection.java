package Sprites;

import java.util.*;
import biuoop.DrawSurface;

/**
 * Class represents a collection of multiple sprites.
 */
public class SpriteCollection {
    private java.util.List<Sprite> sprites;


    /**
     *  A constructor of uor sprite collection.
     */
    public SpriteCollection () {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Method adds inputted sprite to our collection.
     *
     * @param s the sprite to add to our collection.
     */
    public void addSprite (Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Method removes inputted sprite from our collection.
     *
     * @param s the sprite to remove from our collection.
     */
    public void removeSprite (Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Method calls all our sprites in the collection to the method timePassed.
     */
    public void notifyAllTimePassed() {
        int i = 1;
        List<Sprite>  list = new ArrayList<>(this.sprites);
        for (Sprite s : list) {
            s.timePassed();
        }
    }

    /**
     * Method calls all our sprites in the collection to the method drawOn with the inputted surface.
     *
     * @param d the surface to draw the collection on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}
