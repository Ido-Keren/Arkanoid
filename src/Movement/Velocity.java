package Movement;

import Calculations.DoubleComparing;
import Shapes.*;

/**
 * Represents the speed of two-dimensional space objects.
 */
public class Velocity {
    private double dx;
    private double dy;

    // constants used.
    final static int MAX_ANGLE = 360;
    final static int MIN_ANGLE = 0;
    final static int ONE_QUARTER_ANGLE = (MAX_ANGLE - MIN_ANGLE) / 4;
    final static int TWO_QUARTER_ANGLE = ONE_QUARTER_ANGLE * 2;
    final static int THREE_QUARTER_ANGLE = ONE_QUARTER_ANGLE * 3;
    final static int POSITIVE = 1;
    final static int NEGATIVE = -1;


    /**
     * A builder of a velocity with the specified dx and dy coordinates of the speed.
     *
     * @param dx the dx coordinate.
     * @param dy the dy coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * A builder of a velocity with the angle to move and the speed it will move at.
     *
     * @param angle the angle to move.
     * @param speed the speed it will move.
     * @return the velocity matching the arguments.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        DoubleComparing d = new DoubleComparing();
        double dx, dy;

        // make sure angle is between our limit of angle.
        while(angle >= MAX_ANGLE) {
            angle = angle - MAX_ANGLE;
        }
        while(angle < MIN_ANGLE) {
            angle = angle + MAX_ANGLE;
        }

        // set dx and dy to either positive or negative depending on the angle argument.
        if (angle <= TWO_QUARTER_ANGLE) {
            dx = POSITIVE;
        }
        else {
            dx = NEGATIVE;
        }
        if (angle > ONE_QUARTER_ANGLE && angle <= THREE_QUARTER_ANGLE) {
            dy = POSITIVE;
        }
        else {
            dy = NEGATIVE;
        }

        // change angle to its respected position in the first quadrant.
        while (angle > ONE_QUARTER_ANGLE) {
            angle = angle - ONE_QUARTER_ANGLE;
        }

        // set dx and dy via the correct calculations.
        if (d.isAbsEqual(dx, dy)) {
            dx = dx * (1 - angle / ONE_QUARTER_ANGLE) * speed;
            dy = dy * (angle / ONE_QUARTER_ANGLE) * speed;
        }
        else {
            dx = dx * (angle / ONE_QUARTER_ANGLE) * speed;
            dy = dy * (1 - angle / ONE_QUARTER_ANGLE) * speed;
        }

        // return the calculated velocity
        return new Velocity(dx, dy);
    }

    /**
     * Method calculates and returns the ball's speed.
     *
     * @return the ball's speed.
     */
    public double speed() {
        return Math.abs(dx) + Math.abs(dy);
    }
    /**
     * Applies the movement of a point using the velocity.
     *
     * @param p the point to move.
     * @return the point with the new position.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Returns the dx of the velocity.
     *
     * @return the dx of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the dy of the velocity.
     *
     * @return the dy of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the dx of the velocity to inputted value.
     *
     * @param dx the inputted value to change our dx to.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the dy of the velocity to inputted value.
     *
     * @param dy the inputted value to change our dy to.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }


    /**
     * Returns the maximum angle.
     *
     * @return the maximum angle.
     */
    public static int getMaxAngle() {
        return MAX_ANGLE;
    }
}
