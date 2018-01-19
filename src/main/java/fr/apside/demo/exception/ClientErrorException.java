package fr.apside.demo.exception;

public class ClientErrorException extends Exception {

    private static final long serialVersionUID = -8133101134313352386L;

    public ClientErrorException(String message) {
        super(message);
    }
}
