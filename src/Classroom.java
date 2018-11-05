import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Classroom {

    private static List<Computer> computers = new ArrayList<>();


    public Classroom() {
        createComputers();
    }

    public void createComputers() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                computers.add(new Computer(3 * i, 3 * j));
            }
        }
    }


    public void randomComputerUnlocker() throws InterruptedException {
        int randomInt = ThreadLocalRandom.current().nextInt(0, computers.size());
        computers.get(randomInt).unlock();
    }
    public static List<Computer> getComputers() {
        return computers;
    }

    public void printClassroom(Terminal terminal) throws IOException {

        try {
            for (Computer computer : computers) {
                if(computer.isLocked()) {
                    terminal.setBackgroundColor(TextColor.ANSI.RED);

                }
                else {
                    terminal.setBackgroundColor(TextColor.ANSI.GREEN);
                }
                terminal.setCursorPosition(computer.getX(), computer.getY()); // go to position(column, row)
                terminal.putCharacter(computer.getSymbol());
            }
        } catch (Exception e) {//TODO}
        }
        terminal.resetColorAndSGR();
    }

    public static boolean isPositionAvailable(int x, int y) {
        for (Computer computer : computers) {
            if (x == computer.getX() && y == computer.getY())
                return false;
        }
        return true;
    }

    public static Computer getComputerAt(int x, int y) {
        for (Computer computer : computers) {
            if (x == computer.getX() && y == computer.getY())
                return computer;
        }
        return null;
    }

    public boolean isAllComputersLocked(){
        for (Computer computer : computers) {
            if (!computer.isLocked())
                return false;
        }
        return true;
    }
    public Computer getUnlockedComputer() {
        for (Computer computer : computers) {
            if (!computer.isLocked())
                return computer;
        }
        return null;
    }

}