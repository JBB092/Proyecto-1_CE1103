package Game;

import java.awt.Color;

/**
 * Represents a box sprite in the game.
 * Extends the Sprite class and includes information about horizontal and vertical connections, as well as the player who closed the box.
 * 
 * The BoxSprite class represents a box sprite in the game. It extends the Sprite class and includes
 * information about horizontal and vertical connections, as well as the player who closed the box.
 * 
 * @author Diego Elizondo
 */
public class BoxSprite extends Sprite {

    /**
     * The horizontal connections of the box.
     */
    ConnectionSprite[] horizontalConnections;

    /**
     * The vertical connections of the box.
     */
    ConnectionSprite[] verticalConnections;

    /**
     * The player who closed the box.
     */
    int player;

    /**
     * Constructs a new BoxSprite object.
     * Initializes the fields and sets the color to black.
     */
    public BoxSprite() {
        super();

        color = Color.BLACK; // Initially the box should be the same color as the background

        horizontalConnections = new ConnectionSprite[2];
        verticalConnections = new ConnectionSprite[2];

        width = GameBoard.DOT_GAP;
        height = GameBoard.DOT_GAP;

        shape.addPoint(-width / 2, -height / 2);
        shape.addPoint(-width / 2, height / 2);
        shape.addPoint(width / 2, height / 2);
        shape.addPoint(width / 2, -height / 2);
    }

    /**
     * Checks if the BoxSprite is fully boxed.
     * Returns true if all four border ConnectionSprites have true connectionMade fields, false otherwise.
     *
     * @return True if the BoxSprite is fully boxed, false otherwise.
     */
    public boolean isBoxed() {
        boolean boxed = true;

        for (int i = 0; i < 2; i++) {
            if (!horizontalConnections[i].connectionMade || !verticalConnections[i].connectionMade) {
                boxed = false;
                break;
            }
        }

        return boxed;
    }

    /**
     * Creates a new BoxSprite with the specified coordinates and border ConnectionSprites.
     *
     * @param x                     The x-coordinate of the BoxSprite.
     * @param y                     The y-coordinate of the BoxSprite.
     * @param horizontalConnections The ConnectionSprites that are the top and bottom borders of the box.
     * @param verticalConnections   The ConnectionSprites that are the left and right borders of the box.
     * @return The created BoxSprite object.
     */
    public static BoxSprite createBox(int x, int y, ConnectionSprite[] horizontalConnections, ConnectionSprite[] verticalConnections) {
        BoxSprite box = new BoxSprite();
        box.player = 0;
        box.x = x;
        box.y = y;
        box.horizontalConnections = horizontalConnections;
        box.verticalConnections = verticalConnections;
        return box;
    }
}