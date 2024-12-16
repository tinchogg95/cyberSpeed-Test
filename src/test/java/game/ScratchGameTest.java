package game;

import game.ScratchGame;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class ScratchGameTest {

    @Test
    public void testPlayGame() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ScratchGame game = new ScratchGame();
        game.playGame("config.json",100.0);
        
        String output = outContent.toString();
        assertFalse("The output should not be empty.", output.isEmpty());
        assertTrue("The output should contain a JSON.", output.contains("{"));
        assertTrue("The JSON should contain the 'reward' field.", output.contains("reward"));
        assertTrue("The JSON should contain the 'matrix' field.", output.contains("matrix"));
        assertTrue("The JSON should contain the 'combinations' field.", output.contains("combinations"));

        System.setOut(System.out);
    }
}
