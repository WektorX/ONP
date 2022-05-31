//klasa ErrorHandler
package onp;

public class ErrorHandler extends RuntimeException {

    public ErrorHandler(String message)
    {
        super(message);
    }

}