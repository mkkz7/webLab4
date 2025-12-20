package mkkz7.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class UserAlreadyExistsException extends BusinessException {
    public UserAlreadyExistsException(String username) {
        super("User '" + username + "' already exists", 409);
    }
}
