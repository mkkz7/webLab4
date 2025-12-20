package mkkz7.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class AuthValidationException extends BusinessException {
    public AuthValidationException(String message) {
        super(message, 400);
    }
}
