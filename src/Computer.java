public class Computer {
    private boolean isLocked;
    private final Coordinate coordinate;
    private final char symbol;


    public Computer(int x, int y) {
        this.coordinate = new Coordinate(x, y);
        this.isLocked = true;
        this.symbol = '\uDCBB';
    }


    public void unlock() {
        this.isLocked = false;
    }

    public void lock() {
        this.isLocked = true;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isUnlocked() {
        return !isLocked;
    }

    public int getX() {
        return coordinate.getX();
    }

    public int getY() {
        return coordinate.getY();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
