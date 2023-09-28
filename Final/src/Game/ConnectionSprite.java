package Game;

import java.awt.Color;

public class ConnectionSprite extends Sprite {
    /*
     *
     *	ConnectionSprite is a subclass of Sprite. There are two types of connections: vertical
     *	connections between dots and horizontal connections between sprites. The static method
     *	createConnection is a convenience method to create the ConnectionSprite at the proper
     *	coordinates and build its shape.
     *
     */

     public static final int HORIZ_CONN=1;
     public static final int VERT_CONN=2;
 
     boolean connectionMade;	// Tracks weather the ConnectionSprite has been clicked on
 
     public ConnectionSprite() {
         // Initialize all the fields
         super();
 
         connectionMade=false;
         color=Color.WHITE;
     }
 
     public static ConnectionSprite createConnection(int type, int x, int y) {
         ConnectionSprite conn=new ConnectionSprite();
 
         if(type==ConnectionSprite.HORIZ_CONN) {
             conn.width=GameBoard.DOT_GAP;
             conn.height=GameBoard.DOT_SIZE;
         } else if(type==ConnectionSprite.VERT_CONN) {
             conn.width=GameBoard.DOT_SIZE;
             conn.height=GameBoard.DOT_GAP;
         } else {
             return null;
         }
 
         conn.x=x;
         conn.y=y;
 
         conn.shape.addPoint(-conn.width/2, -conn.height/2);
         conn.shape.addPoint(-conn.width/2, conn.height/2);
         conn.shape.addPoint(conn.width/2, conn.height/2);
         conn.shape.addPoint(conn.width/2, -conn.height/2);
 
         return conn;
     }
}