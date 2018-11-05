import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {

    private static void loop(Terminal terminal) throws InterruptedException, IOException {
        Player player = new Player(0, 1);
        Andreas andreas = new Andreas(45, 10);
        Classroom classroom = new Classroom();
        Computer target = null;

        while (true) {
            KeyStroke keyStroke;

            printClassroom(terminal);
            printPlayer(terminal, player);
            printHighScore(terminal, player);
            printAndreas(terminal, andreas);

            do {
                Thread.sleep(5);
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);

            if (classroom.isAllComputersLocked()) {
                target = classroom.unlockRandomComputer();
                andreas.resetVisitedCoordinates();
            }

            if (andreas.hasReachedTarget(target)) {
                gameOver(terminal);
                break;
            }

            player.movePlayer(keyStroke, classroom);
            andreas.moveTowards(target);

            terminal.flush();
        }
    }

    public static void main(String[] args) {

        try {
            Terminal terminal = createTerminal();
            printMission(terminal);
            loop(terminal);
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }


    private static void printPlayer(Terminal terminal, Player player) throws IOException {
        terminal.setCursorPosition(player.getPreviousX(), player.getPreviousY());
        terminal.putCharacter(' ');

        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());
    }

    private static void printAndreas(Terminal terminal, Andreas andreas) throws IOException {
        terminal.setCursorPosition(andreas.getPreviousX(), andreas.getPreviousY());
        terminal.putCharacter(' ');

        terminal.setCursorPosition(andreas.getX(), andreas.getY());
        terminal.putCharacter(andreas.getSymbol());
    }

    private static void printClassroom(Terminal terminal) throws IOException {
        try {
            for (Computer computer : Classroom.getComputers()) {
                if (computer.isLocked()) {
                    terminal.setBackgroundColor(TextColor.ANSI.RED);
                } else {
                    terminal.setBackgroundColor(TextColor.ANSI.GREEN);
                }
                terminal.setCursorPosition(computer.getX(), computer.getY()); // go to position(column, row)
                terminal.putCharacter(computer.getSymbol());
            }
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
        terminal.resetColorAndSGR();
    }


    private static Terminal createTerminal() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);
        return terminal;
    }

    private static void printHighScore(Terminal terminal, Player player) throws IOException {
        // Position för meddelandet
        String highscore = "Highscore: " + player.getHighScore();
        terminal.setCursorPosition(0, 20); // col, row

        for (char c : highscore.toCharArray()) {
            terminal.putCharacter(c);
        }
        terminal.flush();
    }

    private static void printMission(Terminal terminal) throws IOException {
        // Position för meddelandet
        terminal.setCursorPosition(25, 0);

        String mission = "HINDRA ANDREAS FRÅN ATT LAPPA DE OLÅSTA DATORERNA";
        for (char c : mission.toCharArray()) {
            terminal.putCharacter(c);
        }
        terminal.flush();
    }

    private static void gameOver(Terminal terminal) throws IOException {
        // Position för meddelandet
        terminal.setCursorPosition(25, 3);

        String gameOver = "GAME OVER";
        for (char c : gameOver.toCharArray()) {
            terminal.putCharacter(c);
        }
        terminal.flush();
    }
}


