package DataStructures;

/**
 * Represents a 2D point with x and y coordinates.
 *
 * The Point class represents a point in a 2D coordinate system with x and y coordinates.
 *
 * @author Diego Elizondo
 */
public class Point {
    /**
     * The x-coordinate of the point.
     */
    private int x;

    /**
     * The y-coordinate of the point.
     */
    private int y;

    /**
     * Constructs a point with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return The x-coordinate of the point.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return The y-coordinate of the point.
     */
    public int getY() {
        return y;
    }
}
