package Shapes;

import biuoop.DrawSurface;
import java.util.LinkedList;
import java.awt.*;
/**
 * Represents a two-dimensional space rectangle.
 */
public class Rectangle {
    private Shapes.Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * A builder of a rectangle with the specified upper left point, height and width.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle (Shapes.Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * A builder of a rectangle with the specified x and y coordinates of the upper left point, height and width.
     *
     * @param x the x coordinate of the start point.
     * @param y the y coordinate of the start point.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle (double x, double y, double width, double height) {
        this.upperLeft = new Shapes.Point(x,y);
        this.width = width;
        this.height = height;
    }

    /**
     * A builder of a rectangle with the specified x and y coordinates of the start and end points and the color.
     *
     * @param x the x coordinate of the start point.
     * @param y the y coordinate of the start point.
     * @param width the width coordinate of the rectangle.
     * @param height the height of the end rectangle.
     * @param color the color of the point.
     */
    public Rectangle (double x, double y, double width, double height, Color color) {
        this.upperLeft = new Shapes.Point(x,y);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * A builder of a rectangle with no arguments, sets the objects to default values.
     */
    public Rectangle() {
        this.upperLeft = new Shapes.Point(0,0);
        this.width = 200;
        this.height = 200;
    }

    /**
     * Returns the upper left point.
     *
     * @return the upper left point.
     */
    public Shapes.Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the rectangle's color to the inputted color.
     *
     * @param color the color to change the rectangle to.
     */
    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * Method receives a line and returns a list of intersection points between the line and our rectangle.
     *
     * @param line a line to checks for intersection points.
     * @return a list of intersection points between the line and our rectangle.
     */
    public java.util.List<Shapes.Point> intersectionPoints(Shapes.Line line) {
        java.util.List<Shapes.Point> list = new LinkedList<>();
        double upperLeftX = this.upperLeft.getX();
        double upperLeftY = this.upperLeft.getY();
        double lowerRightX = this.upperLeft.getX() + this.width;
        double lowerRightY = this.upperLeft.getY() + this.height;
        list.add(line.intersectionWith(new Line(upperLeftX, upperLeftY, upperLeftX, lowerRightY)));
        list.add(line.intersectionWith(new Line(upperLeftX, upperLeftY, lowerRightX, upperLeftY)));
        list.add(line.intersectionWith(new Line(lowerRightX, lowerRightY, upperLeftX, lowerRightY)));
        list.add(line.intersectionWith(new Line(lowerRightX, lowerRightY, lowerRightX, upperLeftY)));
        return list;
    }

    /**
     * draws the rectangle on the inputted surface.
     *
     * @param surface the surface to draw the rectangle on.
     */
    public void drawOn(DrawSurface surface) {
        int x = (int)Math.round(this.upperLeft.getX());
        int y = (int)Math.round(this.upperLeft.getY());
        int width = (int)Math.round(this.width);
        int height = (int)Math.round(this.height);
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.black);
        surface.drawRectangle(x, y, width, height);
    }
}
