import java.util.ArrayList;
import java.util.List;

public class Andreas {
    private int x;
    private int y;
    private final char symbol;
    private int previousX;
    private int previousY;
    private List<Coordinate> previousMoves;

    public Andreas(int x, int y) {
        this.x = x;
        this.y = y;
        this.symbol = '\u2689';
        this.previousX = x;
        this.previousY = y;
        this.previousMoves = new ArrayList<>();

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
        // Ovan target
        if (this.x == target.getX() && (this.y+1 == target.getY()))
            return true;

        // Under target
        else if (this.x == target.getX() && (this.y-1 == target.getY()))
            return true;

        // Till höger om target
        else if (this.x+1 == target.getX() && (this.y == target.getY()))
            return true;

        // Till vänster om target
        else if (this.x-1 == target.getX() && (this.y == target.getY()))
            return true;

        return false;
    }

    private void checkIfPossibleMove(List<PossibleMove> possibleMoves, int x, int y, Computer targetComputer) {
        Computer computer = Classroom.getComputerAt(x, y);
        if (computer == null && isNotPreviousMove(new Coordinate(x, y))) {
            int distance = Math.abs(targetComputer.getX() - x) + Math.abs(targetComputer.getY() - y);
            PossibleMove pm = new PossibleMove(x, y, distance);
            possibleMoves.add(pm);
        }

    }

    private boolean isNotPreviousMove(Coordinate coord) {
        for (Coordinate previousMove : previousMoves) {
            if (previousMove.equals(coord)) {
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
        // a monster wants to minimize the distance between itself and the player

        // Along which axis should the monster move in?
        // The monster will move in the direction in which the distance between monster and player is the largest.
        // Let's use the absolute value of the difference between the x-ccordinates vs the y-coordinates!
        // Example of Math.abs -> https://www.tutorialspoint.com/java/lang/math_abs_int.htm

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

        this.previousMoves.add(new Coordinate(this.x, this.y));

//        int diffX = this.x - computer.getX();
//        int absDiffX = Math.abs(diffX);
//        int diffY = this.y - computer.getY();
//        int absDiffY = Math.abs(diffY);
//
//
//        if (absDiffX + absDiffY == 1) {
//            return "Game over";
//        }
//
//        if (absDiffX > absDiffY) {
//            // Move horizontal! <--->}
//            if (diffX < 0 && Classroom.isPositionAvailable(this.x + 1, this.y)) {
//                this.x += 1;
//            } else if (diffX > 0 && Classroom.isPositionAvailable(this.x - 1, this.y))
//                this.x -= 1;
//        } else {
//            // Move vertical!
//            if (diffY < 0 && Classroom.isPositionAvailable(this.x, this.y + 1)) {
//                this.y += 1;
//            } else if (diffY >= 0 && Classroom.isPositionAvailable(this.x, this.y - 1)) {
//                this.y -= 1;
//            }
//        }
//        return void;
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
