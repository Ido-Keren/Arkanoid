package Sprites;

import Calculations.DoubleComparing;
import Games.Animations.GameLevel;
import Movement.*;
import Movement.HitAffects.*;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.LinkedList;

/**
 * Represents a two-dimensional space block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Shapes.Rectangle size;
    private java.util.List<HitListener> hitListeners;

    @Override
    public Shapes.Rectangle getCollisionRectangle() {
        return this.size;
    }

    @Override
    public Velocity hit(Ball hitter, Shapes.Point collisionPoint, Velocity currentVelocity) {
        Velocity newV = currentVelocity;
        DoubleComparing d = new DoubleComparing();
        if (d.isAbsEqual(collisionPoint.getX(), this.size.getUpperLeft().getX()) ||
                d.isAbsEqual(collisionPoint.getX(), this.size.getUpperLeft().getX() + this.size.getWidth())){
            newV.setDx(-newV.getDx());
        }
        if (d.isAbsEqual(collisionPoint.getY(), this.size.getUpperLeft().getY()) ||
                d.isAbsEqual(collisionPoint.getY(), this.size.getUpperLeft().getY() + this.size.getHeight())){
            newV.setDy(-newV.getDy());
        }
        this.notifyHit(hitter);
        return newV;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.size.drawOn(d);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Method notifies all listeners that a ball hit our block.
     *
     * @param hitter the ball that hit our block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        java.util.List<HitListener> listeners = new LinkedList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Method removes our block from inputted game.
     *
     * @param game the game to remove our block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * A constructor of a block.
     *
     * @param r the size of the block created.
     */
    public Block(Shapes.Rectangle r){
        this.size = r;
        this.hitListeners = new java.util.ArrayList<>();
    }

    /**
     * A constructor of a block with rectangle inputs.
     *
     * @param upperLeftX the x coordinate of the start point.
     * @param upperLeftY the y coordinate of the start point.
     * @param width the width coordinate of the block.
     * @param height the height of the end block.
     * @param color the color of the point.
     */
    public Block(double upperLeftX, double upperLeftY, double width, double height, Color color) {
        this.size = new Shapes.Rectangle(upperLeftX, upperLeftY, width, height, color);
        this.hitListeners = new java.util.ArrayList<>();
    }
}
