package Sprites;

import Calculations.DoubleComparing;
import Movement.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import Shapes.*;
import Games.Animations.GameLevel;

/**
 * Represents a two-dimensional space moving paddle.
 */
public class Paddle implements Sprite, Collidable {

    private Shapes.Rectangle size;
    private biuoop.KeyboardSensor keyboard;
    private int speed;


    /**
     * A constructor of a paddle.
     *
     * @param g the GUI where paddle will be and from which keyboard will be inputted.
     */
    public Paddle(GUI g, int paddleSpeed, int paddleWidth) {
        this.size = new Shapes.Rectangle(400 - paddleWidth / 2, 550, paddleWidth, 20, java.awt.Color.ORANGE);
        this.keyboard = g.getKeyboardSensor();
        this.speed = paddleSpeed;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.size.drawOn(d);
    }

    @Override
    public void timePassed() {
        DoubleComparing d = new DoubleComparing();
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (d.isBigger(this.size.getUpperLeft().getX(), 20)) {
                moveLeft();
            }
        }
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (d.isBigger(780, this.size.getUpperLeft().getX() + this.size.getWidth())) {
                moveRight();
            }
        }
    }

    @Override
    public Shapes.Rectangle getCollisionRectangle() {
        return this.size;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newV = currentVelocity;
        Point p = this.size.getUpperLeft();
        DoubleComparing d = new DoubleComparing();
        double fifth = this.size.getWidth() / 5;
        if (d.isAbsEqual(collisionPoint.getY(), this.size.getUpperLeft().getY())) {
            if (d.isBigger(collisionPoint.getX(), p.getX()) && d.isBigger(p.getX() + fifth, collisionPoint.getX())) {
                newV = Velocity.fromAngleAndSpeed(300, currentVelocity.speed());
            } else if (d.isBigger(collisionPoint.getX(), p.getX() + fifth) &&
                    d.isBigger(p.getX() + (fifth * 2), collisionPoint.getX())) {
                newV = Velocity.fromAngleAndSpeed(330, currentVelocity.speed());
            } else if (d.isBigger(collisionPoint.getX(), p.getX() + (fifth * 3)) &&
                    d.isBigger(p.getX() + (fifth * 4), collisionPoint.getX())) {
                newV = Velocity.fromAngleAndSpeed(30, currentVelocity.speed());
            } else if (d.isBigger(collisionPoint.getX(), p.getX() + (fifth * 4)) &&
                    d.isBigger(p.getX() + this.size.getWidth(), collisionPoint.getX())) {
                newV = Velocity.fromAngleAndSpeed(60, currentVelocity.speed());
            } else {
                newV.setDy(-newV.getDy());
            }
        }
        else if (d.isAbsEqual(collisionPoint.getY(), this.size.getUpperLeft().getY() + this.size.getHeight())){
            newV.setDy(-newV.getDy());

        }
        else if (d.isAbsEqual(collisionPoint.getX(), this.size.getUpperLeft().getX())) {
            newV.setDx(-Math.abs(newV.getDx()));
        }
        else newV.setDx(Math.abs(newV.getDx()));
        return newV;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Method moves our paddle left.
     */
    public void moveLeft() {
        this.size = new Shapes.Rectangle(this.size.getUpperLeft().getX() - speed, this.size.getUpperLeft().getY(),
                this.size.getWidth(), this.size.getHeight(), this.size.getColor());
    }

    /**
     * Method moves our paddle right.
     */
    public void moveRight() {
        this.size = new Shapes.Rectangle(this.size.getUpperLeft().getX() + speed, this.size.getUpperLeft().getY(),
                this.size.getWidth(), this.size.getHeight(), this.size.getColor());
    }


}
