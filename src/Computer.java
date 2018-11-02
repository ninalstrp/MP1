public class Computer {
    private boolean isLocked;
    private final int x;
    private final int y;
    private final char symbol;


    public Computer(int x, int y) {
        this.x = x;
        this.y = y;
        this.isLocked = true;
        this.symbol = 'O';
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
