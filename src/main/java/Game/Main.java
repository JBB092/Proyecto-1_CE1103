package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Color;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The type Sprite.
 */
class Sprite {

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

/**
 * ConnectionSprite is a subclass of Sprite that represents connections between dots or horizontal connections between sprites.
 * The static method createConnection is used to conveniently create a ConnectionSprite at the specified coordinates and build its shape.
 */
class ConnectionSprite extends Sprite {


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
            conn.width = Dots.DOT_GAP;
            conn.height = Dots.DOT_SIZE;
        } else if (type == ConnectionSprite.VERT_CONN) {
            // Set the width and height
            conn.width = Dots.DOT_SIZE;
            conn.height = Dots.DOT_GAP;
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

/**
 * BoxSprite is a subclass of Sprite that represents the actual boxes made up by the Dot Sprites and ConnectionSprites.
 * <p>
 * The BoxSprite contains references to the four ConnectionSprites which make up its borders.
 * The `isBoxed` method returns `true` when all four of the border ConnectionSprites have `true` `connectionMade` fields.
 * BoxSprites should be created using the static `createBox` method.
 */
class BoxSprite extends Sprite{

    /**
     * The Horizontal connections.
     */
    ConnectionSprite[] horizontalConnections; // The ConnectionSprites that are the top and bottom borders of the box
    /**
     * The Vertical connections.
     */
    ConnectionSprite[] verticalConnections; // The ConnectionSprites that are the left and right borders of the box

    /**
     * The Player.
     */
    int player; // Tracks the player that closed the box

    /**
     * Constructs a new BoxSprite object.
     * Initializes the fields and sets the color to black.
     */
    public BoxSprite() {
        super();

        color = Color.BLACK; // Initially the box should be the same color as the background

        horizontalConnections = new ConnectionSprite[2];
        verticalConnections = new ConnectionSprite[2];

        width = Dots.DOT_GAP;
        height = Dots.DOT_GAP;

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

/**
 * This class represents a JFrame that implements MouseMotionListener and MouseListener.
 * It is used for handling mouse events and drawing dots.
 */
class Dots extends JFrame implements MouseMotionListener, MouseListener {

    /**
     * The constant DOT_NUMBER.
     */
    public static final int DOT_NUMBER=12;	//	The number of dots on each side of the square game board
    /**
     * The constant DOT_GAP.
     */
    public static final int DOT_GAP=24;		//	The space between each dot
    /**
     * The constant DOT_SIZE.
     */
    public static final int DOT_SIZE=5;		//	The length of the sides of the square dot

    /**
     * The constant PLAYER_ONE.
     */
    public static final int PLAYER_ONE=1;
    /**
     * The constant PLAYER_TWO.
     */
    public static final int PLAYER_TWO=2;

    /**
     * The constant PLAYER_ONE_COLOR.
     */
    public static final Color PLAYER_ONE_COLOR= new Color(158, 4, 4, 255);    //	The color of player1's boxes
    /**
     * The constant PLAYER_TWO_COLOR.
     */
    public static final Color PLAYER_TWO_COLOR=new Color(4, 15, 140, 255); // 	The color of player2's boxes
    private LinkedList<ConnectionSprite> horizontalConnections;
    // 	The ConnectionSprites that are the top and bottom borders of the game-board
    private LinkedList<ConnectionSprite> verticalConnections;
    // 	The ConnectionSprites that are the left and right borders of the game-board
    private LinkedList<BoxSprite> boxes;
    // 	The BoxSprites that make up the game-board
    private LinkedList<Sprite> dots;
    // 	The Dots that make up the game-board

    private Dimension dim;		//	Window dimensions

    private int clickX;		//	Holds the x coordinate of mouse click
    private int clickY;		// 	Holds the y coordinate of mouse click

    private int mouseX;		// 	Holds the x coordinate of the mouse location
    private int mouseY; 	// 	Holds the y coordinate of the mouse location

    private int centerX;	//	x coordinate of the center of the game-board
    private int centerY; 	// 	y coordinate of the center of the gamepad

    private int side;	//	Length of the sides of the square game-board
    private int space;	// Length of 1 dot + 1 connection

    private int activePlayer;	// 	Holds the current player

    /**
     * Initializes the JFrame for the "Connect the Dots" game.
     * Sets the size, title, and behavior of the frame.
     * Adds mouse listeners and loads properties and dots.
     * Starts a new game and makes the frame visible.
     */
    public Dots() {
        super("Connect the Dots");
        setSize(500, 550);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(this);
        addMouseMotionListener(this);

        loadProperties();
        loadDots();
        startNewGame();

        setVisible(true);
        // Draw the dots
    }

    private void loadProperties() {
        //	Initialize fields

        clickX=0;
        clickY=0;
        mouseX=0;
        mouseY=0;

        dim=getSize();
        centerX=dim.width/2;
        centerY=(dim.height - 100) /2;

        side=DOT_NUMBER * DOT_SIZE + (DOT_NUMBER - 1) * DOT_GAP;
        //	There is one less connection than dot per side
        space=DOT_SIZE + DOT_GAP;
        //	There is one less dot than connection
    }

   /**
     * Initializes the horizontal and vertical connections.
     *
     * There are two ways to cycle through the Connections, Boxes, and Dots grids. This way uses only 1 for loop and keeps
     * track of the current row and column number in colsX, rowsX, colsY, rowsY. colsX and rowsX track the columns and rows
     * for the horizontalConnections while colsY and rowsY track the columns and rows for the vertical connections.
     *
     * The reason to have different fields for vertical and horizontal connections is so that both grids will be filled
     * in left to right and then top to bottom (rows first then columns). This makes it easier to match the connection up
     * to box or boxes it borders. Simple setting colsY=rowsX and rowsY=colsX will put the vertical connections on the
     * correct place on the screen, but they won't match up to the boxes correctly.
     */
    private void loadConnections() {
        horizontalConnections = new LinkedList<>();
        // 	The ConnectionSprites that are the top and bottom borders of the game-board
        verticalConnections = new LinkedList<>();
        // 	The ConnectionSprites that are the left and right borders of the game-board

        for (int i = 0; i < (DOT_NUMBER - 1) * DOT_NUMBER; i++) {
            // 	Cycle through the connections
            int colsX = i % (DOT_NUMBER - 1);
            int rowsX = i / (DOT_NUMBER - 1);
            int horX = centerX - side / 2 + DOT_SIZE + colsX * space;
            int horY = centerY - side / 2 + rowsX * space;
            horizontalConnections.add(ConnectionSprite.createConnection(ConnectionSprite.HORIZ_CONN, horX, horY));
            // 	Cycle through the boxes

            int colsY = i % DOT_NUMBER;
            int rowsY = i / DOT_NUMBER;
            int vertX = centerX - side / 2 + colsY * space;
            int vertY = centerY - side / 2 + DOT_SIZE + rowsY * space;
            verticalConnections.add(ConnectionSprite.createConnection(ConnectionSprite.VERT_CONN, vertX, vertY));
            // 	Cycle through the dots
        }
    }

    /**
     * Populates the list of boxes.
     *
     * loadBoxes cycles through the box grid in a similar way to loadConnections. There is one less box per side than dots
     * per side.
     */
    private void loadBoxes() {
        // 	The BoxSprites that make up the game-board
        boxes = new LinkedList<>();
        // 	The Boxes that make up the game-board

        for (int i = 0; i < (DOT_NUMBER - 1) * (DOT_NUMBER - 1); i++) {
            // 	Cycle through the boxes
            int cols = i % (DOT_NUMBER - 1);
            int rows = i / (DOT_NUMBER - 1);

            int boxX = centerX - side / 2 + DOT_SIZE + cols * space;
            int boxY = centerY - side / 2 + DOT_SIZE + rows * space;

            ConnectionSprite[] horConn = new ConnectionSprite[2];
            horConn[0] = horizontalConnections.get(i);
            horConn[1] = horizontalConnections.get(i + (DOT_NUMBER - 1));
            // 	Cycle through the connections

            ConnectionSprite[] verConn = new ConnectionSprite[2];
            verConn[0] = verticalConnections.get(i + rows);
            verConn[1] = verticalConnections.get(i + rows + 1);
            // 	Cycle through the connections

            boxes.add(BoxSprite.createBox(boxX, boxY, horConn, verConn));
            // 	Cycle through the dots
        }
    }
    /**
     * Populates the list of dots.
     *
     * loadDots cycles through the dot grid differently than the loadConnections and loadBoxes methods.
     * It uses two nested for loops to iterate over the rows and columns of the dot grid. The order in which
     * the dots are loaded into the dots array does not matter, as they are for visual purposes only.
     *
     * The body of the loop contains the code to build the shape of each dot, setting its position, size,
     * and shape points.
     */
    private void loadDots() {

        dots = new LinkedList<>();
        // 	The Dots that make up the game-board
        for (int rows = 0; rows < DOT_NUMBER; rows++) {
            for (int cols = 0; cols < DOT_NUMBER; cols++) {
                Sprite dot = new Sprite();
                dot.width=DOT_SIZE;
                dot.height=DOT_SIZE;
                dot.x=centerX - side/2 + cols * space;
                dot.y=centerY - side/2 + rows * space;
                dot.shape.addPoint(-DOT_SIZE/2, -DOT_SIZE/2);
                dot.shape.addPoint(-DOT_SIZE/2, DOT_SIZE/2);
                dot.shape.addPoint(DOT_SIZE/2, DOT_SIZE/2);
                dot.shape.addPoint(DOT_SIZE/2, -DOT_SIZE/2);
                dots.add(dot);

            }
        }
    }

    /**
     * Starts a new game by initializing the active player, loading connections, and loading boxes.
     *
     * The method sets the active player to PLAYER_ONE. It then calls the loadConnections() method to initialize
     * the connections and the loadBoxes() method to populate the boxes.
     */
    private void startNewGame() {
        // 	Start a new game
        activePlayer = PLAYER_ONE;
        // 	Set the active player
        loadConnections();
        loadBoxes();
    }

    /**
     * Retrieves the connection at the specified point (x, y).
     *
     * The method iterates over the list of horizontal connections and checks if each connection contains the given point (x, y).
     * If a match is found, the matching horizontal connection is returned.
     *
     * If no match is found in the horizontal connections, the method then iterates over the list of vertical connections and
     * checks if each connection contains the given point (x, y). If a match is found, the matching vertical connection is returned.
     *
     * If no match is found in either list, the method returns null.
     */
    private ConnectionSprite getConnection(int x, int y) {
        for (ConnectionSprite horizontalConnection : horizontalConnections) {
            if (horizontalConnection.containsPoint(x, y)) {
                // 	Found a match
                return horizontalConnection;
                // 	Return the matching horizontal connection
            }
        }

        for (ConnectionSprite verticalConnection : verticalConnections) {
            if (verticalConnection.containsPoint(x, y)) {
                // 	Found a match
                return verticalConnection;
                // 	Return the matching vertical connection
            }
        }

        return null;
    }

    /**
     * Retrieves the status of each box.
     *
     * The method creates a new LinkedList<Boolean> called 'status' with the same length as the 'boxes' list. It then iterates over
     * the 'status' list and assigns the value of 'isBoxed()' method for each box in the 'boxes' list to the corresponding
     * element in the 'status' list.
     *
     * The resulting 'status' list contains the status of each box, where 'true' indicates that the box is boxed and 'false'
     * indicates that the box is not boxed.
     *
     * @return The LinkedList<Boolean> containing the status of each box.
     */
    private LinkedList<Boolean> getBoxStatus() {
        // 	Create a new LinkedList<Boolean>
        LinkedList<Boolean> status = new LinkedList<>();
        // 	Cycle through the boxes

        for (int i = 0; i < boxes.size(); i++) {
            // 	Set the status
            status.add(boxes.get(i).isBoxed());
        }

        return status;
    }

    /**
     * Calculates the scores for each player.
     *
     * The method initializes an integer array called 'scores' with two elements, both set to 0. It then iterates over the
     * 'boxes' collection and checks each 'BoxSprite' object. If a 'BoxSprite' object is boxed and has a non-zero player value,
     * the corresponding element in the 'scores' array is incremented.
     *
     * The resulting 'scores' array contains the scores for each player, where the first element corresponds to Player 1 and
     * the second element corresponds to Player 2.
     *
     * @return The integer array containing the scores for each player.
     */
    private int[] calculateScores() {
        int[] scores = {0, 0};


        for (BoxSprite box : boxes) {
            // 	Check if the box is boxed
            if (box.isBoxed() && box.player != 0) {
                scores[box.player - 1]++;
                // 	Increment the score
            }
        }

        return scores;
    }

    /**
     * Makes a connection between two sprites.
     *
     * The method takes a 'ConnectionSprite' object as a parameter and performs the following steps:
     *
     * 1. Initializes a boolean variable 'newBox' to false.
     * 2. Retrieves the status of the boxes before the connection using the 'getBoxStatus' method and stores it in a LinkedList<Boolean> called 'boxStatusBeforeConnection'.
     * 3. Sets the 'connectionMade' flag of the 'connection' object to true.
     * 4. Retrieves the updated status of the boxes after the connection using the 'getBoxStatus' method and stores it in a LinkedList<Boolean> called 'boxStatusAfterConnection'.
     * 5. Iterates over each 'BoxSprite' object in the 'boxes' list. For each box, it checks if the status of the box after the connection is different from the status before the connection. If it is different, it sets the 'newBox' variable to true and assigns the 'activePlayer' to the 'player' variable of the box.
     * 6. Checks if 'newBox' is false. If it is false, it switches the 'activePlayer' to the other player.
     * 7. Calls the 'checkForGameOver' method.
     */
    private void makeConnection(ConnectionSprite connection){
        boolean newBox = false;

        LinkedList<Boolean> boxStatusBeforeConnection = getBoxStatus();
        connection.connectionMade = true;
        LinkedList<Boolean> boxStatusAfterConnection = getBoxStatus();

        int i = 0;
        for (BoxSprite box : boxes) {
            // 	Check if the box is boxed
            if (boxStatusAfterConnection.get(i) != boxStatusBeforeConnection.get(i)) {
                // 	Set the newBox
                newBox = true;
                box.player = activePlayer;
                // 	Set the active player
            }
            i++;
        }

        if (!newBox) {
            // 	Set the active player
            if (activePlayer == PLAYER_ONE)
                activePlayer = PLAYER_TWO;
            else
                activePlayer = PLAYER_ONE;
        }

        checkForGameOver();
        // 	Call the checkForGameOver method
    }

    /**
     * Checks if the game is over.
     *
     * The method performs the following steps:
     *
     * 1. Calculates the scores of the two players using the 'calculateScores' method and stores them in an integer array called 'scores'.
     * 2. Checks if the sum of the scores of both players is equal to the square of 'DOT_NUMBER - 1'.
     * 3. If the above condition is true, it displays a message dialog using 'JOptionPane.showMessageDialog' with the scores of both players and a "Game Over" message.
     * 4. It then starts a new game by calling the 'startNewGame' method.
     * 5. Finally, it repaints the game to update the display.
     */
    private void checkForGameOver() {
        int[] scores = calculateScores();

        if ((scores[0] + scores[1]) == ((DOT_NUMBER - 1) * (DOT_NUMBER - 1))) {
            // 	Display a message
            JOptionPane.showMessageDialog(this, "Player1: " +
                    scores[0] + "\nPlayer2: " + scores[1], "Game Over",
                    JOptionPane.PLAIN_MESSAGE);

            startNewGame();
            repaint();
        }
    }

    /**
     * Handles a click event.
     *
     * The method performs the following steps:
     *
     * 1. Retrieves a 'ConnectionSprite' object based on the 'clickX' and 'clickY' coordinates.
     * 2. Checks if the retrieved 'connection' object is null. If it is, the method returns.
     * 3. Checks if the 'connectionMade' property of the 'connection' object is false.
     *     - If it is false, the method calls the 'makeConnection' method passing in the 'connection' object.
     * 4. Calls the 'repaint' method to update the display.
     */
    private void handleClick() {
        ConnectionSprite connection = getConnection(clickX, clickY);

        if (connection == null)
            return;
        // 	Check if the connection is null

        if (!connection.connectionMade) {
            makeConnection(connection);
        }
        // 	Call the makeConnection method

        repaint();
    }

    /**
     * Handles the mouse movement event.
     *
     * The method performs the following steps:
     *
     * 1. Updates the 'mouseX' variable with the x-coordinate of the mouse event.
     * 2. Updates the 'mouseY' variable with the y-coordinate of the mouse event.
     * 3. Calls the 'repaint' method to trigger a screen update.
     */
    public void mouseMoved(MouseEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
        repaint();
    }

    /**
     * Handles the mouse dragged event.
     *
     * The method performs the following steps:
     *
     * 1. Calls the 'mouseMoved' method and passes the 'event' object as a parameter.
     */
    public void mouseDragged(MouseEvent event) {
        mouseMoved(event);
    }

    /**
     * Handles the mouse click event.
     *
     * The method performs the following steps:
     *
     * 1. Updates the 'clickX' variable with the x-coordinate of the mouse event.
     * 2. Updates the 'clickY' variable with the y-coordinate of the mouse event.
     * 3. Calls the 'handleClick' method to handle the click event.
     */
    public void mouseClicked(MouseEvent event) {
        clickX = event.getX();
        clickY = event.getY();

        handleClick();
    }

    /**
     * Handles the mouse entered event.
     */
    public void mouseEntered(MouseEvent event) {
    }

    /**
     * Handles the mouse exited event.
     */
    public void mouseExited(MouseEvent event) {
    }

    /**
     * Handles the mouse pressed event.
     */
    public void mousePressed(MouseEvent event) {
    }

    /**
     * Handles the mouse released event.
     */
    public void mouseReleased(MouseEvent event) {
    }

    /**
     * Paints the background of the component.
     *
     * The method performs the following steps:
     *
     * 1. Sets the color of the 'Graphics' object to black using 'Color.BLACK'.
     * 2. Fills a rectangle with black color using the 'fillRect' method of the 'Graphics' object.
     *    The rectangle covers the entire area of the component being painted, which is determined by the 'dim' width and height.
     */
    private void paintBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, dim.width, dim.height);
    }

    /**
     * Paints the dots on the graphical display.
     *
     * The method performs the following steps:
     *
     * 1. Iterates over the collection of 'dots' using a for-each loop.
     * 2. Calls the 'render' method on each 'dot' passing in the 'Graphics' object.
     *    This method is responsible for rendering each dot on the graphical display.
     */
    private void paintDots(Graphics g) {
        for (Sprite dot : dots) {
            dot.render(g);
        }
    }

    /**
     * Paints the connections on the graphical display.
     *
     * The method performs the following steps:
     *
     * 1. Iterates over the collection of 'horizontalConnections' using a for-each loop.
     *    a. Checks if the 'connectionMade' flag of the 'horizontalConnection' is false.
     *       - If true:
     *           - Checks if the 'horizontalConnection' contains the mouse coordinates (mouseX, mouseY).
     *             - If true, sets the 'color' of the 'horizontalConnection' to a specific shade of green (new Color(10, 160, 8, 230)).
     *             - If false, sets the 'color' of the 'horizontalConnection' to black.
     *       - If false, sets the 'color' of the 'horizontalConnection' to a specific shade of gray (new Color(225, 225, 228, 230)).
     *    b. Calls the 'render' method on the 'horizontalConnection' to draw it on the graphical display.
     *
     * 2. Iterates over the collection of 'verticalConnections' using a for-each loop.
     *    a. Checks if the 'connectionMade' flag of the 'verticalConnection' is false.
     *       - If true:
     *           - Checks if the 'verticalConnection' contains the mouse coordinates (mouseX, mouseY).
     *             - If true, sets the 'color' of the 'verticalConnection' to a specific shade of green (new Color(10, 160, 8, 230)).
     *             - If false, sets the 'color' of the 'verticalConnection' to black.
     *       - If false, sets the 'color' of the 'verticalConnection' to a specific shade of gray (new Color(225, 225, 228, 229)).
     *    b. Calls the 'render' method on the 'verticalConnection' to draw it on the graphical display.
     */
    private void paintConnections(Graphics g) {
        for (ConnectionSprite horizontalConnection : horizontalConnections) {

            if (!horizontalConnection.connectionMade) {
                if (horizontalConnection.containsPoint(mouseX, mouseY)) {
                    horizontalConnection.color = new Color(10, 160, 8, 230);
                } else {
                    horizontalConnection.color = Color.BLACK;
                }
            } else {
                horizontalConnection.color = new Color(225, 225, 228, 230);
            }

            horizontalConnection.render(g);
        }

        for (ConnectionSprite verticalConnection : verticalConnections) {

            if (!verticalConnection.connectionMade) {
                if (verticalConnection.containsPoint(mouseX, mouseY)) {
                    verticalConnection.color = new Color(10, 160, 8, 230);
                } else {
                    verticalConnection.color = Color.BLACK;
                }
            } else {
                verticalConnection.color = new Color(225, 225, 228,229);
            }

            verticalConnection.render(g);
        }
    }

    /**
     * Paints the boxes on the graphical display.
     * <p>
     * The method performs the following steps:
     * <p>
     * 1. Iterates over the collection of 'boxes' using a for-each loop.
     * 2. Checks if each 'box' is boxed.
     * - If true:
     * - Checks the player associated with the 'box':
     * - If the player is 'PLAYER_ONE', sets the 'color' of the 'box' to 'PLAYER_ONE_COLOR'.
     * - If the player is 'PLAYER_TWO', sets the 'color' of the 'box' to 'PLAYER_TWO_COLOR'.
     * - If false, sets the 'color' of the 'box' to black.
     * 3. Calls the 'render' method on each 'box' to draw it on the graphical display, passing in the 'Graphics' object.
     *
     * @param g the g
     */
    public void paintBoxes(Graphics g) {
        for (BoxSprite box : boxes) {
            if (box.isBoxed()) {
                if (box.player == PLAYER_ONE) {
                    box.color = PLAYER_ONE_COLOR;
                } else if (box.player == PLAYER_TWO) {
                    box.color = PLAYER_TWO_COLOR;
                }
            } else {
                box.color = Color.BLACK;
            }

            box.render(g);
        }
    }

    /**
     * Paints the status on the graphical display.
     * <p>
     * The method performs the following steps:
     * <p>
     * 1. Calculates the scores using the 'calculateScores' method and stores them in an integer array 'scores'.
     * 2. Creates three status strings:
     * - 'status': Represents the current player's turn, using the 'activePlayer' variable.
     * - 'status2': Represents the score of Player 1, using the first element of the 'scores' array.
     * - 'status3': Represents the score of Player 2, using the second element of the 'scores' array.
     * 3. Sets the color of the graphics object to white.
     * 4. Draws the 'status' string on the graphical display at the coordinates (10, dim.height-50).
     * 5. Sets the color of the graphics object to the color associated with 'PLAYER_ONE_COLOR'.
     * 6. Draws the 'status2' string on the graphical display at the coordinates (10, dim.height-35).
     * 7. Sets the color of the graphics object to the color associated with 'PLAYER_TWO_COLOR'.
     * 8. Draws the 'status3' string on the graphical display at the coordinates (10, dim.height-20).
     *
     * @param g the g
     */
    public void paintStatus(Graphics g) {
        int[] scores = calculateScores();
        String status = "It is Player" + activePlayer + "'s turn";
        String status2 = "Player 1: " + scores[0];
        String status3 = "Player 2: " + scores[1];

        g.setColor(Color.WHITE);
        g.drawString(status, 10, dim.height - 50);

        g.setColor(PLAYER_ONE_COLOR);
        g.drawString(status2, 10, dim.height - 35);

        g.setColor(PLAYER_TWO_COLOR);
        g.drawString(status3, 10, dim.height - 20);
    }

    /**
     * Updates the graphical display.
     *
     * The method performs the following steps:
     *
     * 1. Calls the 'paint' method, passing in the 'Graphics' object as a parameter.
     */
    public void update(Graphics g) {
        paint(g);
    }

    /**
     * Paints the graphical display.
     *
     * The method performs the following steps:
     *
     * 1. Creates a buffer image using the dimensions 'dim.width' and 'dim.height'.
     * 2. Initializes a buffer graphics object using the buffer image.
     * 3. Calls the following methods to paint different elements on the buffer graphics object:
     *    - 'paintBackground': Paints the background.
     *    - 'paintDots': Paints dots.
     *    - 'paintConnections': Paints connections.
     *    - 'paintBoxes': Paints boxes.
     *    - 'paintStatus': Paints status.
     * 4. Draws the buffer image on the graphical display at the coordinates (0, 0) using the 'drawImage' method of the 'Graphics' object passed as a parameter.
     */
    public void paint(Graphics g) {
        /** The double buffer technique is not really
         * necessary because there is no animation
         */

        Image bufferImage = createImage(dim.width, dim.height);
        Graphics bufferGraphics = bufferImage.getGraphics();

        paintBackground(bufferGraphics);
        paintDots(bufferGraphics);
        paintConnections(bufferGraphics);
        paintBoxes(bufferGraphics);
        paintStatus(bufferGraphics);

        g.drawImage(bufferImage, 0, 0, null);
    }

    /**
     * The entry point for the Java program.
     * <p>
     * The method performs the following steps:
     * <p>
     * 1. Creates a new instance of the 'Dots' class.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Dots();
    }
}