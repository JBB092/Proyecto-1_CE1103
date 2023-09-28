package DataStructures;

/**
 * The Box class represents a box in the game grid.
 * Each box has four sides represented by Line objects (top, bottom, left, right) and a player identifier.
 * @author Diego Elizondo
 */
public class Box {
    private Line top;       // The top side of the box
    private Line bottom;    // The bottom side of the box
    private Line left;      // The left side of the box
    private Line right;     // The right side of the box
    private int player;     // The player identifier for the box (0 represents no player initially)

    /**
     * Constructs a Box object with the specified sides (lines) and initializes the player as 0 (no player initially).
     *
     * @param top    The top side of the box.
     * @param bottom The bottom side of the box.
     * @param left   The left side of the box.
     * @param right  The right side of the box.
     */
    public Box(Line top, Line bottom, Line left, Line right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.player = 0;
    }

    /**
     * Gets the top side of the box.
     *
     * @return The Line representing the top side of the box.
     */
    public Line getTop() {
        return top;
    }

    /**
     * Gets the bottom side of the box.
     *
     * @return The Line representing the bottom side of the box.
     */
    public Line getBottom() {
        return bottom;
    }

    /**
     * Gets the left side of the box.
     *
     * @return The Line representing the left side of the box.
     */
    public Line getLeft() {
        return left;
    }

    /**
     * Gets the right side of the box.
     *
     * @return The Line representing the right side of the box.
     */
    public Line getRight() {
        return right;
    }

    /**
     * Gets the player identifier for the box.
     *
     * @return The player identifier for the box.
     */
    public int getPlayer() {
        return player;
    }

    /**
     * Sets the player identifier for the box.
     *
     * @param player The player identifier to set for the box.
     */
    public void setPlayer(int player) {
        this.player = player;
    }
}
