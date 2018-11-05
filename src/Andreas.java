import java.util.ArrayList;
import java.util.List;

public class Andreas {
    private int x;
    private int y;
    private final char symbol;
    private int previousX;
    private int previousY;
    private List<Coordinate> visitedCoordinates;

    public Andreas(int x, int y) {
        this.x = x;
        this.y = y;
        this.symbol = '\u2689';
        this.previousX = x;
        this.previousY = y;
        this.visitedCoordinates = new ArrayList<>();
    }

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean hasReachedTarget(Computer target) {
        return target.getCoordinate().equals(x, y + 1) ||     // Position above target
                target.getCoordinate().equals(x, y - 1) ||    // Position below target
                target.getCoordinate().equals(x + 1, y) ||    // Position right of target
                target.getCoordinate().equals(x - 1, y);      // Position left of target
    }

    private void checkIfPossibleMove(List<PossibleMove> possibleMoves, int x, int y, Computer targetComputer) {
        Computer computer = Classroom.getComputerAt(x, y);

        if (computer == null && isNotPreviousMove(x, y)) {
            int distance = Math.abs(targetComputer.getX() - x) + Math.abs(targetComputer.getY() - y);
            PossibleMove pm = new PossibleMove(x, y, distance);
            possibleMoves.add(pm);
        }
    }

    private boolean isNotPreviousMove(int x, int y) {
        for (Coordinate visited : visitedCoordinates) {
            if (visited.equals(x, y)) {
                return false;
            }
        }
        return true;
    }

    private PossibleMove findShortestPath(List<PossibleMove> possibleMoves) {
        PossibleMove shortest = possibleMoves.get(0);

        for (PossibleMove possibleMove : possibleMoves) {
            if (possibleMove.getNumberOfMoves() < shortest.getNumberOfMoves()) {
                shortest = possibleMove;
            }
        }
        return shortest;
    }

    public void moveTowards(Computer computer) {

        previousX = x;
        previousY = y;

        List<PossibleMove> possibleMoveList = new ArrayList<>();
        checkIfPossibleMove(possibleMoveList, x + 1, y, computer); // Höger
        checkIfPossibleMove(possibleMoveList, x - 1, y, computer); // Vänster
        checkIfPossibleMove(possibleMoveList, x, y + 1, computer); // Upp
        checkIfPossibleMove(possibleMoveList, x, y - 1, computer); // Ner

        PossibleMove bestRoute = findShortestPath(possibleMoveList);
        this.x = bestRoute.getX();
        this.y = bestRoute.getY();

        this.visitedCoordinates.add(new Coordinate(this.x, this.y));

    }

    public void resetVisitedCoordinates() {
        this.visitedCoordinates = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Andreas{" +
                "x=" + x +
                ", y=" + y +
                ", symbol=" + symbol +
                ", previousX=" + previousX +
                ", previousY=" + previousY +
                '}';
    }
}
