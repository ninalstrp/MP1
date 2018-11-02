import com.googlecode.lanterna.terminal.Terminal;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

public class Classroom {

    private static List<Computer> computers = new ArrayList<>();

    public Classroom() {
        createComputers();
    }

    public void createComputers() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                computers.add(new Computer(3 * i, 3 * j));
            }
        }
    }

    public List<Computer> getComputers() {
        return computers;
    }

    public void printClassroom(Terminal terminal) {

        try {
            for (Computer computer : computers) {
                terminal.setCursorPosition(computer.getX(), computer.getY()); // go to position(column, row)
                terminal.putCharacter(computer.getSymbol());
            }
        } catch (Exception e) {//TODO}
        }
    }

    public static boolean isPositionAvailable(int x, int y) {
        for (Computer computer : computers) {
            if (x == computer.getX() && y == computer.getY())
                return false;
        }
        return true;
    }

}