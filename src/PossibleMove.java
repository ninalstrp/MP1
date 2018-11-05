public class PossibleMove {
    private int x;
    private int y;
    private int numberOfMoves;

    public PossibleMove(int x, int y, int numberOfMoves) {
        this.x = x;
        this.y = y;
        this.numberOfMoves = numberOfMoves;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }
}
