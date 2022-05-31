package onp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabStackTest {

    private TabStack stack = null;
    static String negativeIndexExceptionMsg = " ---> ujemny indeks stosu";
    static String[] values = {"17", "+" ,"5","/", "5", "=", "16"};
    @BeforeEach
    public void prepare(){
        stack = new TabStack();
    }


    @Test
    public void testEmptyStack(){
        assertTrue(stack.isEmpty(), "New stack should be empty");
//        handle exception first than check for text

        Exception exceptionPop = assertThrows(IndexOutOfBoundsException.class, () ->{
            stack.pop();
        });
        assertEquals( negativeIndexExceptionMsg, exceptionPop.getMessage());

        Exception exceptionTop = assertThrows(IndexOutOfBoundsException.class, () ->{
            stack.top();
        });
        assertEquals(negativeIndexExceptionMsg, exceptionTop.getMessage());
    }

    @Test
    public void testStackPush(){
        assertTrue(stack.isEmpty(), "Stos powinien być pusty");
        for(String val : values){
            stack.push(val);
            assertEquals(val, stack.top(), "Otrzymano " +stack.top() +" oczekiwano " + val);
        }
        assertFalse(stack.isEmpty(), "Stos nie powinien być pusty");
    }

    @Test
    public void testStackPop(){
        for(String val : values){
            stack.push(val);
            assertSame(val, stack.pop(), "Stos zwrócił niepoprawny obiekt");
        }
        assertTrue(stack.isEmpty(), "Stos powinien być pusty");

        Exception exception = assertThrows(IndexOutOfBoundsException.class, ()->{
            stack.pop();
        });
        assertEquals(negativeIndexExceptionMsg, exception.getMessage());
    }

    @Test
    public void testCorrectOrderPop(){

        for(String val : values){
            stack.push(val);
        }
        for(int i = (values.length - 1); i>= 0;i--){
            assertSame(values[i], stack.pop(), "Stos zwrócił inny obiekt!");
        }
        assertTrue(stack.isEmpty(), "Stos powinien być pusty");

        Exception exception = assertThrows(IndexOutOfBoundsException.class, ()->{
            stack.pop();
        });
        assertEquals(negativeIndexExceptionMsg, exception.getMessage());
    }

    @Test
    public void testForNull(){
        stack.push(null);
        assertNull(stack.pop(), "Stos zwrócił coś innego niż null");
    }

    @Test
    public void testStackAfterException(){
        Exception e = assertThrows(IndexOutOfBoundsException.class, ()->{
            stack.pop();
        });
        String element = "62";
        stack.push(element);
        assertSame(element, stack.pop(), "Stos nie działa poprawnie po zwróconym wyjątku");

    }

    @AfterEach
    public void clean(){
        stack = null;
    }

}