package DataStructures;

import java.util.ArrayList;
import java.util.List;
import Game.GameBoard;

/**
 * Represents a mesh composed of points, lines, and boxes.
 *
 * The Mesh class holds information about the points, lines, and boxes that form a mesh, and tracks the active player.
 *
 * @author Diego Elizondo
 */
public class Mesh {
    /**
     * List of points in the mesh.
     */
    private List<Point> points;

    /**
     * List of lines in the mesh.
     */
    private List<Line> lines;

    /**
     * List of boxes in the mesh.
     */
    private List<Box> boxes;

    /**
     * The currently active player in the mesh.
     */
    private int activePlayer;

    /**
     * Constructs an empty mesh with no points, lines, or boxes and sets the active player.
     */
    public Mesh() {
        this.points = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.boxes = new ArrayList<>();
        this.activePlayer = GameBoard.PLAYER_ONE;  // Initial active player
    }

    /**
     * Gets the list of points in the mesh.
     *
     * @return The list of points in the mesh.
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * Adds a point to the mesh.
     *
     * @param point The point to be added to the mesh.
     */
    public void addPoint(Point point) {
        points.add(point);
    }

    /**
     * Gets the list of lines in the mesh.
     *
     * @return The list of lines in the mesh.
     */
    public List<Line> getLines() {
        return lines;
    }

    /**
     * Adds a line to the mesh.
     *
     * @param line The line to be added to the mesh.
     */
    public void addLine(Line line) {
        lines.add(line);
    }

    /**
     * Gets the list of boxes in the mesh.
     *
     * @return The list of boxes in the mesh.
     */
    public List<Box> getBoxes() {
        return boxes;
    }

    /**
     * Adds a box to the mesh.
     *
     * @param box The box to be added to the mesh.
     */
    public void addBox(Box box) {
        boxes.add(box);
    }

    /**
     * Gets the currently active player in the mesh.
     *
     * @return The currently active player in the mesh.
     */
    public int getActivePlayer() {
        return activePlayer;
    }

    /**
     * Sets the currently active player in the mesh.
     *
     * @param activePlayer The player to be set as the active player.
     */
    public void setActivePlayer(int activePlayer) {
        this.activePlayer = activePlayer;
    }
}
