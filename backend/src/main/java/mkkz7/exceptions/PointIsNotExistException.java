package mkkz7.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class PointIsNotExistException extends BusinessException {
    public PointIsNotExistException(String message) {
        super(message, 400);
    }
}
