import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Player player = new Player(0, 1);
        Classroom classroom = new Classroom();

        try {
            Terminal terminal = createTerminal();

            // Print computers
//            for (Computer computer : ) {
//                terminal.setCursorPosition(computer.getX(), computer.getY()); // go to position(column, row)
//                terminal.putCharacter(computer.getSymbol());
//            }
            classroom.printClassroom(terminal);

            // Print player
            terminal.setCursorPosition(player.getX(), player.getY());
            terminal.putCharacter(player.getSymbol());


        } catch (Exception e) {
            // TODO
        }


    }
    private static void movePlayer(Player player, KeyStroke keyStroke) {
        switch (keyStroke.getKeyType()) {
            case ArrowUp:
                if (Classroom.isPositionAvailable(player.getX(), player.getY()-1))
                player.moveUp();
                break;
            case ArrowDown:
                if (Classroom.isPositionAvailable(player.getX(), player.getY()+1))
                    player.moveDown();
                break;
            case ArrowLeft:
                if (Classroom.isPositionAvailable(player.getX()-1, player.getY()))
                    player.moveLeft();
                break;
            case ArrowRight:
                if (Classroom.isPositionAvailable(player.getX()+1, player.getY())
                    player.moveRight();
                break;
        }

    }

    private static Terminal createTerminal() throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);
        return terminal;
    }

}
