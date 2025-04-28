package Shapes;

import Calculations.DoubleComparing;
/**
 * Represents a two-dimensional space line.
 */
public class Line {
    private Point start;
    private Point end;

    static final int NONE = -1;

    /**
     * A builder of a line with the specified start and end coordinates.
     *
     * @param start the starting point of the line.
     * @param end the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * A builder of a line with the specified start x and y and end  x and y coordinates.
     *
     * @param x1 the x coordinate of the starting point.
     * @param y1 the y coordinate of the starting point.
     * @param x2 the x coordinate of the end point.
     * @param y2 the y coordinate of the end point.
     */
    public Line (double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates and returns the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * returns the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Compares the line to another line, if they are the same returns true.
     *
     * @param other the other line.
     * @return if the lines are the same returns true, otherwise return false.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end)) ||
                (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * Returns the slope of the line.
     *
     * @return the slope of the line, and null if slope is infinity.
     */
    public Double getSlope() {
        DoubleComparing d = new DoubleComparing();
        if (d.isAbsEqual(this.start.getX(), this.end.getX())) {
            return null;
        }
        else if (this.start.getX() < this.end.getX()) {
            return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        }
        else
            return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * Checks for an intersection between the line and the other line, and returns the intersection
     * point if there's only one intersection, otherwise returns null.
     *
     * @param other the other line.
     * @return the intersection point if there's only one intersection, otherwise returns null.
     */
    public Point intersectionWith (Line other) {
        DoubleComparing d = new DoubleComparing();
        Double m1 = this.getSlope(), m2 = other.getSlope(), x, y;

        // if the slopes are equal, set x and y to line's start point if it's the only intersection, else
        // set x and y to the line's end point if it's the only intersection, else return null.
        if (d.isNullOrAbsEqual(m1, m2)) {
            if ((this.start.equals(other.start) && !other.isBetween(this.end) && !this.isBetween(other.end)) ||
                    (this.start.equals(other.end) && !other.isBetween(this.end) && !this.isBetween(other.start))) {
                x = this.start.getX();
                y = this.start.getY();
            }
            else if ((this.end.equals(other.start) && !other.isBetween(this.start) && !this.isBetween(other.end)) ||
                    (this.end.equals(other.end) && !other.isBetween(this.start) && !this.isBetween(other.start))) {
                x = this.end.getX();
                y = this.end.getY();
            }
            else {
                return null;
            }
        }

        // if this line's slope is infinite and the other's slope isn't set x
        // to this line's x, and y to the functions meeting point.
        else if (m1 == null) {
            x = this.start.getX();
            y = m2 * (x - other.start.getX()) + other.start.getY();
        }

        // if the other's slope is infinite and this line's slope isn't set x
        // to the other's x, and y to the functions meeting point.
        else if (m2 == null) {
            x = other.start.getX();
            y = m1 * (x - this.start.getX()) + this.start.getY();
        }

        // else set x and y to the functions meeting points
        else {
            x = (m1 * this.start.getX() - m2 * other.start.getX() - this.start.getY() + other.start.getY()) / (m1 - m2);
            y = m1 * (x - this.start.getX()) + this.start.getY();
        }

        // return the found point if its between both lines, otherwise return null.
        Point p = new Point(x, y);
        if (this.isBetween(p) && other.isBetween(p)){
            return p;
        }
        else {
            return null;
        }
    }

    /**
     * Returns true if the point argument is between the line points coordinates, otherwise returns false.
     *
     * @param p the point arguments.
     * @return true if the point argument is between the line points coordinates, otherwise returns false.
     */
    public boolean isBetween (Point p) {
        DoubleComparing d = new DoubleComparing();
        if (d.isBigger(p.getX(), this.start.getX()) && d.isBigger(p.getX(), this.end.getX()) ||
                d.isBigger(this.start.getX(), p.getX()) && d.isBigger(this.end.getX(), p.getX()) ||
                d.isBigger(p.getY(), this.start.getY()) && d.isBigger(p.getY(), this.end.getY()) ||
                d.isBigger(this.start.getY(), p.getY()) && d.isBigger(this.end.getY(), p.getY())) {
            return false;
        }
        return true;
    }

    /**
     * Checks for intersections between the line and the other line, and returns true
     * if there's an intersection, otherwise returns false.
     *
     * @param other the other line
     * @return true if there's an intersection, otherwise returns false.
     */
    public boolean isIntersecting(Line other) {
        DoubleComparing d = new DoubleComparing();
        Double m1 = this.getSlope(), m2 = other.getSlope();

        // return true if there's one intersection point.
        if (this.intersectionWith(other) != null) {
            return true;
        }

        // if the slopes are infinite, and the x coordinates of the lines are equal, return true if
        // start or end point of the other line are between this line.
        else if (m1 == null && m2 == null && d.isAbsEqual(this.start.getX(), other.start.getX())) {
            return this.isBetween(other.start) || this.isBetween(other.end);
        }

        // if the slope are equal, then return true if slope is 0 and the start or end
        // point of the other line are between this line, else if the functions are equal return true if
        // start or end point of the other line are between this line.
        else if (m1 != null && m2 != null && d.isAbsEqual(m1, m2)) {
            if (d.isAbsEqual(m1, 0) && (this.isBetween(other.start) || this.isBetween(other.end))) {
                return true;
            }
            else if (d.isAbsEqual(m1 * this.start.getX() + other.start.getY(),
                    m2 * other.start.getX() + this.start.getY())) {
                return this.isBetween(other.start) || this.isBetween(other.end);
            }
        }

        // return false as there are no intersection points.
        return false;
    }


    /**
     * Method receives a rectangle and returns the closest point from the start of the line to an
     * intersection with the rectangle (null if there's no intersection).
     *
     * @param rect a rectangle to checks intersections with our line.
     * @return the closest intersection point to the start of the line (null if there's no intersection).
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        DoubleComparing d = new DoubleComparing();
        java.util.List<Shapes.Point> list = rect.intersectionPoints(this);
        Point closest = null;
        double closeDistance = NONE;
        for (Point point : list) {
            if (point != null) {
                if (d.isBigger(closeDistance, this.start.distance(point)) || closeDistance == NONE) {
                    closest = point;
                    closeDistance = this.start.distance(point);
                }
            }
        }
        return closest;
    }
}
