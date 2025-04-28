package Sprites;

import Calculations.DoubleComparing;
import Games.Animations.GameLevel;
import biuoop.DrawSurface;
import Shapes.*;
import Movement.*;

/**
 * Represents a two-dimensional space ball.
 */
public class Ball implements Sprite {
    private Point location;
    private int size;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * A builder of a line with the specified location, radius and color.
     *
     * @param center the ball's center location.
     * @param r the ball's radius.
     * @param color the ball's color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.location = center;
        this.size = Math.abs(r);
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * A builder of a line with the specified x and y coordinates for its location, radius and color.
     *
     * @param x the x coordinate for the ball's location.
     * @param y the y coordinate for the ball's location.
     * @param r the ball's radius.
     * @param color the ball's color.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.location = new Point(x, y);
        this.size = Math.abs(r);
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * A builder of a line with the specified x and y coordinates for its location, radius, color and movement limit.
     *
     * @param x the x coordinate for the ball's location.
     * @param y the y coordinate for the ball's location.
     * @param r the ball's radius.
     * @param color the ball's color.
     * @param environment the ball's limits of movement.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment environment) {
        this.location = new Point(x, y);
        this.size = Math.abs(r);
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.environment = environment;
    }

    /**
     * Returns the x coordinate of the ball's location.
     *
     * @return Returns the x coordinate of the ball's location.
     */
    public int getX() {
        return (int)Math.round(this.location.getX());
    }

    /**
     * Returns the y coordinate of the ball's location.
     *
     * @return Returns the y coordinate of the ball's location.
     */
    public int getY() {
        return (int)Math.round(this.location.getY());
    }

    /**
     * Returns the ball's size.
     *
     * @return Returns the ball's size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns the ball's color.
     *
     * @return Returns the ball's color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int)Math.round(this.location.getX());
        int y = (int)Math.round(this.location.getY());
        surface.setColor(this.color);
        surface.fillCircle(x, y, this.size);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Returns the ball's velocity.
     *
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the ball's velocity to the inputted velocity.
     *
     * @param v the velocity value to set the ball velocity to.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the ball's velocity to the inputted dx and dy.
     *
     * @param dx the dx to set the velocity's dx to.
     * @param dy the dy to set the velocity's dy to.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * moves the ball one step inside its limit.
     */
    public void moveOneStep() {
        DoubleComparing d = new DoubleComparing();
        int signX, signY;
        Line trajectory = new Line(this.location.getX(), this.location.getY(),
                this.location.getX() + this.velocity.getDx(), this.location.getY() + this.velocity.getDy());
        CollisionInfo c = environment.getClosestCollision(trajectory);
        if (c == null) {
            this.location = new Point(this.location.getX() + this.velocity.getDx(),
                    this.location.getY() + this.velocity.getDy());
        }
        else {
            if (this.velocity.getDx() < 0) {
                signX = 1;
            } else if (this.velocity.getDx() > 0){
                signX = -1;
            } else
                signX = 0;
            if (this.velocity.getDy() < 0) {
                signY = 1;
            } else if (this.velocity.getDy() > 0){
                signY = -1;
            } else {
                signY = 0;
            }
            this.location = new Point(c.collisionPoint().getX() + signX, c.collisionPoint().getY() + signY);
            Velocity v = c.collisionObject().hit(this, c.collisionPoint(), this.velocity);
            this.velocity = v;
        }
    }
}
