package Shapes;

import Calculations.DoubleComparing;
/**
 * Represents a two-dimensional space point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * A builder of a point with the specified x and y coordinates.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the point.
     *
     * @return the x coordinate of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of the point.
     *
     * @return the y coordinate of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Calculates the distance between this point and the other point.
     *
     * @param other the other point.
     * @return the distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.getX()) * (this.x - other.getX()) +
                (this.y - other.getY()) * (this.y - other.getY()));
    }

    /**
     * Compares the point to another point, if they are the same returns true.
     *
     * @param other the other point.
     * @return if the points are the same returns true, otherwise return false.
     */
    public boolean equals(Point other) {
        DoubleComparing d = new DoubleComparing();
        return d.isAbsEqual(this.x, other.getX()) && d.isAbsEqual(this.y, other.getY());
    }
}
