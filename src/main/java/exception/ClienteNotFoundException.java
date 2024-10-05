package exception;

public class ClienteNotFoundException extends ResourceNotFoundException {

    public ClienteNotFoundException() {
    }

    public ClienteNotFoundException(String message) {
        super(message);
    }

    public ClienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    //No debemos hacerlo aqui
    /*
    public ClienteNotFoundException(Throwable cause) {
        super(cause);
    }
    */
}