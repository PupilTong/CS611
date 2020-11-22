import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
/*
public class TestPlayer {
    private static final Class IllegalAccessException = null;
    private PrintStream generateOutputString(){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        return new PrintStream(os);


    }
    @Test
    public void TestConstructor(){
        Scanner i = new Scanner("source");
        assertDoesNotThrow(()->{
            BlackJackPlayer player = new BlackJackPlayer(i, this.generateOutputString(), 1, "player");
        });
        assertThrows(IllegalArgumentException.class, ()->{
            new BlackJackPlayer(i, this.generateOutputString(), 0, "player");
        });
    }
    @Test
    public void TestGetStatics(){
        Scanner i = new Scanner("source");
        PrintStream o = this.generateOutputString();
        BlackJackPlayer player = new BlackJackPlayer(i, o, 1, "player");
        
        assertEquals(player.getStatics(), "player player current Currency:1");
    }
    @Test
    public void TestaskBet(){
        Scanner i = new Scanner("error\n10\n1\n");
        PrintStream o = this.generateOutputString();
        BlackJackPlayer player = new BlackJackPlayer(i, o, 5, "player");
        
        assertEquals(player.askBet(), 1);

    }
    @Test
    public void TestaskSplit(){
        Scanner i = new Scanner("yyy\nn\nnn\ny");
        PrintStream o = this.generateOutputString();
        BlackJackPlayer player = new BlackJackPlayer(i, o, 5, "player");
        assertEquals(player.askSplit(1), false);
        assertEquals(player.askSplit(1), true);
        assertEquals(player.getCurrency(), 4);
        assertEquals(player.askSplit(10), false);

    }
    @Test
    public void TestaskHit(){
        Scanner i = new Scanner("yyy\nn\nnn\ny");
        PrintStream o = this.generateOutputString();
        BlackJackPlayer player = new BlackJackPlayer(i, o, 5, "player");
        assertEquals(player.askHit(), false);
        assertEquals(player.askHit(), true);
    }
    @Test
    public void TestaskDouble(){
        Scanner i = new Scanner("yyy\nn\nnn\ny");
        PrintStream o = this.generateOutputString();
        BlackJackPlayer player = new BlackJackPlayer(i, o, 5, "player");
        assertEquals(player.askSplit(1), false);
        assertEquals(player.askSplit(1), true);
        assertEquals(player.getCurrency(), 4);
        assertEquals(player.askSplit(10), false);
    }
    @Test
    public void TestaskQuit(){
        Scanner i = new Scanner("yyy\nn\nnn\ny");
        PrintStream o = this.generateOutputString();
        BlackJackPlayer player = new BlackJackPlayer(i, o, 5, "player");
        assertEquals(player.askQuit(), false);
        assertEquals(player.askQuit(), true);
        

        i = new Scanner("4\nn\n1\ny");
        player = new BlackJackPlayer(i, o, 5, "player");
        player.askBet();
        assertEquals(player.askQuit(), false);
        player.askBet();
        assertEquals(player.askQuit(), true);
    }
    @Test
    public void TestgiveCurrency(){
        Scanner i = new Scanner("yyy\nn\nnn\ny");
        PrintStream o = this.generateOutputString();
        BlackJackPlayer player = new BlackJackPlayer(i, o, 5, "player");
        assertEquals(player.getCurrency(), 5);
        player.giveCurrency(5);
        assertEquals(player.getCurrency(), 10);
        
    }
}
*/