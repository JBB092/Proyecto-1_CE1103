package DataStructures;

import java.util.ArrayList;
import java.util.List;

import Game.*;

public class Mesh {
    private List<Point> points;
    private List<Line> lines;
    private List<Box> boxes;
    private int activePlayer;

    public Mesh() {
        this.points = new ArrayList<>();
        this.lines = new ArrayList<>();
        this.boxes = new ArrayList<>();
        this.activePlayer = GameBoard.PLAYER_ONE;  // Initial active player
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Line> getLines() {
        return lines;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void addBox(Box box) {
        boxes.add(box);
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int activePlayer) {
        this.activePlayer = activePlayer;
    }
}