package Game;

import java.awt.Color;

/**
 * ConnectionSprite is a subclass of Sprite that represents connections between dots or horizontal connections between sprites.
 * The static method createConnection is used to conveniently create a ConnectionSprite at the specified coordinates and build its shape.
 * 
 * @author Diego Elizondo
 */
public class ConnectionSprite extends Sprite {


    /**
     * The constant HORIZ_CONN.
     */
    public static final int HORIZ_CONN = 1;
    /**
     * The constant VERT_CONN.
     */
    public static final int VERT_CONN = 2;

    /**
     * The Connection made.
     */
    boolean connectionMade;
    // Tracks whether the ConnectionSprite has been clicked on

    /**
     * Constructs a new ConnectionSprite object.
     * Initializes the fields and sets the color to white.
     */
    public ConnectionSprite() {
        // Initialize the fields
        super();
        connectionMade = false;
        color = Color.WHITE;
    }
    // Creates a new ConnectionSprite of the specified type

    /**
     * Creates a new ConnectionSprite of the specified type at the given coordinates.
     *
     * @param type The type of the connection: HORIZ_CONN for horizontal connection, VERT_CONN for vertical connection.
     * @param x    The x-coordinate of the ConnectionSprite.
     * @param y    The y-coordinate of the ConnectionSprite.
     * @return The created ConnectionSprite object, or null if the type is invalid.
     */
    public static ConnectionSprite createConnection(int type, int x, int y) {
        // Create a new ConnectionSprite
        ConnectionSprite conn = new ConnectionSprite();
        // Set the color

        if (type == ConnectionSprite.HORIZ_CONN) {
            // Set the width and height
            conn.width = GameBoard.DOT_GAP;
            conn.height = GameBoard.DOT_SIZE;
        } else if (type == ConnectionSprite.VERT_CONN) {
            // Set the width and height
            conn.width = GameBoard.DOT_SIZE;
            conn.height = GameBoard.DOT_GAP;
        } else {
            return null;
        }

        conn.x = x;
        conn.y = y;

        conn.shape.addPoint(-conn.width / 2, -conn.height / 2);
        conn.shape.addPoint(-conn.width / 2, conn.height / 2);
        conn.shape.addPoint(conn.width / 2, conn.height / 2);
        conn.shape.addPoint(conn.width / 2, -conn.height / 2);

        return conn;
        // Return the created ConnectionSprite
    }
}