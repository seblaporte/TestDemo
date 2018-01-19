package fr.apside.demo.exception;

public class UserAlreadyExistsException extends ClientErrorException {

    private static final long serialVersionUID = 5981646704063511235L;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
