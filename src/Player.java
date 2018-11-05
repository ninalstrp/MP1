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

    public void movePlayer(KeyStroke keyStroke, Classroom classroom) {

        int x = this.getX();
        int y = this.getY();

        switch (keyStroke.getKeyType()) {
            case ArrowUp:
                if (Classroom.isPositionAvailable(x, y - 1))
                    this.moveUp();
                else if (!classroom.getComputerAt(x, y - 1).isLocked()) {
                    classroom.getComputerAt(x, y - 1).lock();
                    this.increaseHighScore();
                }
                break;
            case ArrowDown:
                if (Classroom.isPositionAvailable(this.getX(), this.getY() + 1))
                    this.moveDown();
                else if (!classroom.getComputerAt(this.getX(), this.getY() + 1).isLocked()) {
                    classroom.getComputerAt(this.getX(), this.getY() + 1).lock();
                    this.increaseHighScore();
                }
                break;
            case ArrowLeft:
                if (Classroom.isPositionAvailable(this.getX() - 1, this.getY()))
                    this.moveLeft();
                else if (!classroom.getComputerAt(this.getX() - 1, this.getY()).isLocked()) {
                    classroom.getComputerAt(this.getX() - 1, this.getY()).lock();
                    this.increaseHighScore();
                }
                break;
            case ArrowRight:
                if (Classroom.isPositionAvailable(this.getX() + 1, this.getY()))
                    this.moveRight();
                else if (!classroom.getComputerAt(this.getX() + 1, this.getY()).isLocked()) {
                    classroom.getComputerAt(this.getX() + 1, this.getY()).lock();
                    this.increaseHighScore();
                }
                break;
        }
    }
}
