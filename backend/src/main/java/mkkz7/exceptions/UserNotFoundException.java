package mkkz7.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String username) {
        super("User '" + username + "' not found", 401);
    }

    public UserNotFoundException(){
        super("Not authorized", 401);
    }
}
