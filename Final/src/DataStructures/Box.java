package DataStructures;

public class Box {
    private Line top;
    private Line bottom;
    private Line left;
    private Line right;
    private int player;

    public Box(Line top, Line bottom, Line left, Line right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.player = 0;  // 0 represents no player initially
    }

    public Line getTop() {
        return top;
    }

    public Line getBottom() {
        return bottom;
    }

    public Line getLeft() {
        return left;
    }

    public Line getRight() {
        return right;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}