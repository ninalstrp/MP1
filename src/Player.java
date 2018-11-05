import com.googlecode.lanterna.input.KeyStroke;

public class Player {
    private int x;
    private int y;
    private final char symbol;
    private int previousX;
    private int previousY;
    private int highScore;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.previousX = x;
        this.previousY = y;
        this.symbol = '\u263A';
        this.highScore = 0;
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

    public void moveUp() {
        previousX = x;
        previousY = y;
        y--;
    }

    public void moveDown() {
        previousX = x;
        previousY = y;
        y++;
    }

    public void moveLeft() {
        previousX = x;
        previousY = y;
        x--;
    }

    public void moveRight() {
        previousX = x;
        previousY = y;
        x++;
    }

    public void increaseHighScore() {
        this.highScore++;
    }

    public int getHighScore() {
        return highScore;
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

    public void movePlayer(KeyStroke keyStroke) {

        int x = this.getX();
        int y = this.getY();
        Computer computer;

        switch (keyStroke.getKeyType()) {
            case ArrowUp:
                computer = Classroom.getComputerAt(x, y-1);
                if (computer == null)
                    this.moveUp();
                else if (computer.isUnlocked()) {
                    computer.lock();
                    this.increaseHighScore();
                }
                break;
            case ArrowDown:
                computer = Classroom.getComputerAt(x, y+1);
                if (computer == null)
                    this.moveDown();
                else if (computer.isUnlocked()) {
                    computer.lock();
                    this.increaseHighScore();
                }
                break;
            case ArrowLeft:
                computer = Classroom.getComputerAt(x - 1, y);
                if (computer == null)
                    this.moveLeft();
                else if (computer.isUnlocked()) {
                    computer.lock();
                    this.increaseHighScore();
                }
                break;
            case ArrowRight:
                computer = Classroom.getComputerAt(x + 1, y);
                if (computer == null)
                    this.moveRight();
                else if (computer.isUnlocked()) {
                    computer.lock();
                    this.increaseHighScore();
                }
                break;
        }
    }
}
