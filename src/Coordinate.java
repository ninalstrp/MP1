import java.util.Comparator;
import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        Coordinate other = (Coordinate) otherObject;
        return (this.x == other.x) && (this.y == other.y);
    }

    public boolean equals(int x, int y) {
        return (this.x == x) && (this.y == y);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hashCode(());
}


