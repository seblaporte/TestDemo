package fr.apside.demo.exception;

public class UserNotFoundException extends ClientErrorException {

    private static final long serialVersionUID = 5981646704063511235L;

    public UserNotFoundException(Integer userId) {
        super(userId == null ? "L'utilisateur n'a pas été trouvé"
                : String.format("L'utilisateur avec l'id %d n'a pas été trouvé", userId));
    }
}
