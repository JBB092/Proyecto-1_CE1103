package DataStructures;

/**
 * Represents a line defined by two points.
 *
 * This class provides functionality to work with a line by defining its start and end points.
 *
 * @author Diego Elizondo
 */
public class Line {
    /**
     * The starting point of the line.
     */
    private Point startPoint;

    /**
     * The ending point of the line.
     */
    private Point endPoint;

    /**
     * Constructs a line with the given start and end points.
     *
     * @param startPoint The starting point of the line.
     * @param endPoint The ending point of the line.
     */
    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    /**
     * Gets the starting point of the line.
     *
     * @return The starting point of the line.
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * Gets the ending point of the line.
     *
     * @return The ending point of the line.
     */
    public Point getEndPoint() {
        return endPoint;
    }
}
