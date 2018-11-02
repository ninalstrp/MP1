public class Player {
    private int x;
    private int y;
    private final char symbol;
    private int previousX;
    private int previousY;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.previousX = x;
        this.previousY = y;
        this.symbol = 'X';
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

    public int getPreviousX() {
        return previousX;
    }

    public int getPreviousY() {
        return previousY;
    }

    public void moveUp(){
        previousX = x;
        previousY = y;
        y--;
    }

    public void moveDown(){
        previousX = x;
        previousY = y;
        y++;
    }

    public void moveLeft(){
        previousX = x;
        previousY = y;
        x--;
    }

    public void moveRight(){
        previousX = x;
        previousY = y;
        x++;
    }

    @Override
    public String toString() {
        return "Player{" +
                "x=" + x +
                ", y=" + y +
                ", symbol=" + symbol +
                ", previousX=" + previousX +
                ", previousY=" + previousY +
                '}';
    }
}
