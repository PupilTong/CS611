import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import java.lang.IllegalArgumentException;
/*
public class TestCard {
    @Test
    @DisplayName("Test illegal Card Input")
    public void TestillegalConstuctor(){
        assertThrows(IllegalArgumentException.class, ()->{new Card(1, "suit");});
        assertThrows(IllegalArgumentException.class, ()->{new Card(1, 5);});
        assertThrows(IllegalArgumentException.class, ()->{new Card(0, 4);});
        assertThrows(IllegalArgumentException.class, ()->{new Card(0, "♠");});
    }
    @Test
    @DisplayName("Test Constructor")
    public void TestCardConstructor(){
        assertEquals(new Card(1,"♠").toString(), new Card(1,0).toString());//♠A
        assertEquals(new Card(11,"♥").toString(), new Card(11,1).toString());//♥B
        assertEquals(new Card(12,"♣").toString(), new Card(12,2).toString());
        assertEquals(new Card(13,"♦").toString(), new Card(13,3).toString());

    }
    @Test
    @DisplayName("Test ♠A")
    public void TestCardA(){
        assertEquals(new Card(1,0).toString(),"♠\033[0;37mA(1)");//♠A
    }
    @Test
    @DisplayName("Test ♥J")
    public void TestCardJ(){
        assertEquals(new Card(11,1).toString(),"\033[0;31m♥\033[0;37mJ(10)");//J
    }
    @Test
    @DisplayName("Test ♣Q")
    public void TestCardQ(){
        Card c = new Card(12,2);
        assertEquals(c.toString(),"♣\033[0;37mQ(10)");
    }
    @Test
    @DisplayName("Test ♦K")
    public void TestCardK(){
        assertEquals(new Card(13,3).toString(),"\033[0;31m♦\033[0;37mK(10)");
    }
    @Test
    @DisplayName("Test ♠10")
    public void TestCardNormal(){
        assertEquals(new Card(10,0).toString(),"♠\033[0;37m10");//♠A
    }
}
*/