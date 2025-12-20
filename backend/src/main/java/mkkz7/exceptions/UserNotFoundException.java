package mkkz7.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String username) {
        super("User '" + username + "' not found", 404);
    }
}
