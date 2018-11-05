import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static void loop(Terminal terminal, Player player, Classroom classroom, Andreas andreas) throws InterruptedException, IOException {
        while (true) {
            KeyStroke keyStroke;

            do {
                Thread.sleep(5);
                keyStroke = terminal.pollInput();
            } while (keyStroke == null);
            System.out.println(player.toString());
            System.out.println(andreas.toString());
            player.movePlayer(keyStroke, classroom);

            printPlayer(terminal, player);
            classroom.printClassroom(terminal);
            printHighScore(terminal, player);
            if (classroom.isAllComputersLocked()) {
                classroom.randomComputerUnlocker();
            }

            if (andreas.hasReachedTarget(classroom.getUnlockedComputer())) {
                gameOver(terminal);
                break;

            }
            andreas.moveTowards(classroom.getUnlockedComputer());
            printAndreas(terminal, andreas);

            terminal.flush();
        }
    }

    public static void main(String[] args) {


        Player player = new Player(0, 1);
        Andreas andreas = new Andreas(45, 10);
        Classroom classroom = new Classroom();

        try {
            Terminal terminal = createTerminal();

            classroom.randomComputerUnlocker();
            classroom.printClassroom(terminal);
            printMission(terminal);
            // Print player
            terminal.setCursorPosition(player.getX(), player.getY());
            terminal.putCharacter(player.getSymbol());
            terminal.setCursorPosition(andreas.getX(), andreas.getY());
            terminal.putCharacter(andreas.getSymbol());
//
            loop(terminal, player, classroom, andreas);

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
        terminal.putCharacter(andreas.getSymbol());}

    private static Terminal createTerminal() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);
        return terminal;
    }

    private static void printHighScore(Terminal terminal, Player player) throws IOException {

        // Position för meddelandet
        TerminalPosition startPosition = terminal.getCursorPosition();
        TerminalPosition positionMessageA = startPosition.withColumn(0).withRelativeRow(2);
        terminal.setCursorPosition(positionMessageA);

        String highscore = "Highscore: " + player.getHighScore();
        for (char c : highscore.toCharArray()) {
            terminal.putCharacter(c);
        }
        terminal.flush();
    }
    private static void printMission(Terminal terminal) throws IOException {

        // Position för meddelandet
        TerminalPosition startPosition = terminal.getCursorPosition();
        TerminalPosition positionMessageA = startPosition.withColumn(25).withRow(0);
        terminal.setCursorPosition(positionMessageA);

        String mission = "HINDRA ANDREAS FRÅN ATT LAPPA OLÅSTA DATORN";
        for (char c : mission.toCharArray()) {
            terminal.putCharacter(c);
        }
        terminal.flush();
    }
    private static void gameOver(Terminal terminal) throws IOException {

        // Position för meddelandet
        TerminalPosition startPosition = terminal.getCursorPosition();
        TerminalPosition positionMessageA = startPosition.withColumn(0).withRelativeRow(50);
        terminal.setCursorPosition(positionMessageA);

        String gameOver = "GAME OVER";
        for (char c : gameOver.toCharArray()) {
            terminal.putCharacter(c);
        }
        terminal.flush();
//    public static void testColorsAndGraphics() throws IOException {
//        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
//        Terminal terminal = terminalFactory.createTerminal();
//
//        terminal.setBackgroundColor(TextColor.ANSI.BLUE);
//        terminal.setForegroundColor(TextColor.ANSI.YELLOW);
//        terminal.flush();
//        String messageA = "Yellow and blue";
//        for (char c : messageA.toCharArray()) {
//            terminal.putCharacter(c);
//        }
//        terminal.flush();
//
//        TerminalPosition positionMessageB = positionMessageA.withRelativeRow(1);
//        terminal.setCursorPosition(positionMessageB);
//
//        terminal.enableSGR(SGR.BOLD);
//        String messageB = "Bold message";
//        for (char c : messageB.toCharArray()) {
//            terminal.putCharacter(c);
//        }
//
//        terminal.resetColorAndSGR();
//        terminal.enableSGR(SGR.BLINK);
//        terminal.setCursorPosition(terminal.getCursorPosition().withColumn(0).withRelativeRow(1));
//        String messageDone = "Done\n";
//        for (char c : messageDone.toCharArray()) {
//            terminal.putCharacter(c);
//        }
//        terminal.flush();
//    }


}}


