package Game;

import java.awt.Polygon;
import java.awt.Color;
import java.awt.Graphics;

/**
 * The type Sprite.
 * 
 * @author Diego Elizondo
 */
public class Sprite {

    /**
     * Sprite is the basic object that is drawn onto the screen. The dots
     * are all Sprite objects. ConnectionSprite and BoxSprite are subclasses
     * of Sprite. Sprite has a method check to see if a point is within
     * the drawn object. Sprite also has method to draw the Sprite to the screen.
     */
    Polygon shape;	//	The shape that is to be drawn
    /**
     * The Color.
     */
    Color color;	//	The color of the shape
    /**
     * The Width.
     */
    int width;		//	Width of the Sprite
    /**
     * The Height.
     */
    int height;		//	Height of the Sprite
    /**
     * The X.
     */
    int x;			//	Horizontal coordinate of the center of the sprite
    /**
     * The Y.
     */
    int y;			//	Vertical coordinate of the center of the sprite

    /**
     * Instantiates a new Sprite.
     */
    public Sprite() {
        //	Initialize all the fields
        shape=new Polygon();
        width=0;
        height=0;
        x=0;
        y=0;
        color=Color.WHITE;
    }

    /**
     * Renders the sprite by positioning it at the proper location and filling the shape with the specified color.
     *
     * @param g The Graphics object to render the sprite.
     */
    public void render(Graphics g) {
        // Position the sprite at the proper location
        g.setColor(color);

        Polygon renderedShape = new Polygon();
        // Fill the shape
        for (int i = 0; i < shape.npoints; i++) {
            //int renderedX = shape.points[i] + x + width / 2;
            int renderedX = shape.xpoints[i] + x + width / 2;
            int renderedY = shape.ypoints[i] + y + height / 2;
            renderedShape.addPoint(renderedX, renderedY);
            //
        }
        g.fillPolygon(renderedShape);
        // Draw the shape
    }
    // Checks if the specified point is contained within the visible shape of the sprite

    /**
     * Checks if the specified point is contained within the visible shape of the sprite.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return {@code true} if the point is contained within the shape, {@code false} otherwise.
     */
    public boolean containsPoint(int x, int y) {
        // Check if the point is contained within the shape
        return shape.contains(x - this.x - width / 2, y - this.y - height / 2);
    }
    // Draws the sprite to the screen
}