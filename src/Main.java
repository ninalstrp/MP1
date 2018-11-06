import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {

    private static void loop(Terminal terminal) throws InterruptedException, IOException {

        while (true) {
            Player player = new Player(0, 1);
            Andreas andreas = new Andreas(45, 10);
            Classroom classroom = new Classroom();
            Computer target = null;
            boolean gameOver = false;


            while (!gameOver) {
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
                    gameOver = true;
                }

                player.movePlayer(keyStroke);
                andreas.moveTowards(target);

                terminal.flush();
            }
            KeyStroke restart;
            do {
                restart = terminal.pollInput();
            } while (restart == null || restart.getCharacter()== null);
            if (restart.getCharacter() != 's')
                break;
            else {
                terminal.clearScreen();
                Classroom.resetComputers();
            }
        }
    }

    public static void main(String[] args) {

        try {
            Terminal terminal = createTerminal();
            printMission(terminal);
            printTutorial(terminal);
            printAndreasStart(terminal);
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
        terminal.setCursorPosition(25, 2);

        String mission = "HINDRA ANDREAS FRÅN ATT LAPPA DE OLÅSTA DATORERNA";
        for (char c : mission.toCharArray()) {
            terminal.putCharacter(c);
        }
        terminal.flush();
    }
    private static void printTutorial(Terminal terminal) throws IOException {

        String[] tutorialLines = new String[3];

        tutorialLines[0] = "Använd piltangenterna för att styra din spelare";
        tutorialLines[1] = "  Lås en dator genom att trycka pil in i den";
        tutorialLines[2] = "     Samla poäng genom att låsa datorer!";

        int rad = 4;
        for (String tutorial : tutorialLines) {
            terminal.setCursorPosition(26, rad);
            for (char c : tutorial.toCharArray()) {
                terminal.putCharacter(c);
            }
            rad++;
        }
        terminal.flush();
    }

    private static void gameOver(Terminal terminal) throws IOException {
        // Position för meddelandet
        terminal.setCursorPosition(0, 8);
        terminal.enableSGR(SGR.BLINK);
        terminal.enableSGR(SGR.BORDERED);
        terminal.enableSGR(SGR.BOLD);
        terminal.setBackgroundColor(TextColor.ANSI.RED);

        String gameOver = " d888b   .d8b.  .88b  d88. d88888b       .d88b.  db    db d88888b d8888b. \n" +
                "88' Y8b d8' `8b 88'YbdP`88 88'          .8P  Y8. 88    88 88'     88  `8D \n" +
                "88      88ooo88 88  88  88 88ooooo      88    88 Y8    8P 88ooooo 88oobY' \n" +
                "88  ooo 88~~~88 88  88  88 88~~~~~      88    88 `8b  d8' 88~~~~~ 88`8b   \n" +
                "88. ~8~ 88   88 88  88  88 88.          `8b  d8'  `8bd8'  88.     88 `88. \n" +
                " Y888P  YP   YP YP  YP  YP Y88888P       `Y88P'     YP    Y88888P 88   YD \n\n" +
                "Tryck 's' för att starta om.";
        for (char c : gameOver.toCharArray()) {
            terminal.putCharacter(c);
        }
        terminal.resetColorAndSGR();
        terminal.flush();
    }
    private static void printAndreasStart(Terminal terminal) throws IOException {
        // Position för meddelandet
        terminal.setCursorPosition(50, 10);

        String andreasStart = "<---- ANDREAS";
        for (char c : andreasStart.toCharArray()) {
            terminal.putCharacter(c);
        }
        terminal.flush();
    }
}


