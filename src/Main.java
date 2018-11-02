import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        try {
            testPuttingCharactersOnTerminal(); // LESSON 1
//            testReadingFromTerminal(); // LESSON 2
//            movingInsideTheTerminal(); // LESSON 3
//            testColorsAndGraphics(); // LESSON 4
//            testRandomColors(); // LESSON 5
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            System.out.println("DONE!");
        }
    }

        // LESSON 1
        public static void testPuttingCharactersOnTerminal() throws IOException {
            // Create a "factory object" (it's a design-pattern) that can create a terminal for us
            DefaultTerminalFactory terminalFactory =
                    new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8"));
            Terminal terminal = terminalFactory.createTerminal(); // most terminal methods can throw IOException

            // Write out a couple of 'X'
            for (int column = 0; column < 5; column++) {
                terminal.setCursorPosition(column, 0); // go to position(column, row)
                terminal.putCharacter('X');
            }

            // Write out a couple of 'O'
            for (int row = 2; row < 6; row++) {
                terminal.setCursorPosition(2, row); // go to position(column, row)
                terminal.putCharacter('O');
            }


        }
}
