public class PossibleMove {
    private Coordinate coordinate;
    private int numberOfMoves;

    public PossibleMove(int x, int y, int numberOfMoves) {
        this.coordinate = new Coordinate(x, y);
        this.numberOfMoves = numberOfMoves;
    }

    public int getX() {
        return coordinate.getX();
    }

    public int getY() {
        return coordinate.getY();
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }
}
