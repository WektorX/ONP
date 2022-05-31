package onp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ONPTest {
    private ONP onp = null;
    String rownanie = "(2+3)*(6-2)^2=";
    String rownanieOnp = "2 3 + 6 2 - 2 ^ * =";

    @BeforeEach
    public void prepare(){
        onp = new ONP();
    }

    @AfterEach
    public void clean(){
        onp = null;
    }

    @Test
    void czyPoprawneRownanie() {
        assertTrue(onp.czyPoprawneRownanie(rownanie));
        rownanie = "(2+3)*(6-2)^2";
        assertFalse(onp.czyPoprawneRownanie(rownanie));
    }

    @Test
    void eqToOnp() {
        assertEquals(rownanieOnp, onp.eqToOnp(rownanie));
        rownanie = "(2+3)*(6-2)^2";
        assertEquals(null, onp.eqToOnp(rownanie));
        rownanie = "(2+3)*(6-2)^2#&@=";
        assertEquals(null, onp.eqToOnp(rownanie));
    }

    @Test
    void calculate() {
        assertEquals("80.0", onp.calculate(rownanieOnp));
    }

    @Test
    void calculateDivideByZero() {
        rownanieOnp = "2 3 0 / + 6 2 - 2 ^ * =";
        Exception exception = assertThrows(ErrorHandler.class, ()->{
            onp.calculate(rownanieOnp);
        });
        assertEquals(exception.getMessage(), " Dzielenie przez zero");
        rownanieOnp = "2 3 0 % + 6 2 - 2 ^ * =";
        exception = assertThrows(ErrorHandler.class, ()->{
            onp.calculate(rownanieOnp);
        });
        assertEquals(exception.getMessage(), " Dzielenie przez zero");
    }

}