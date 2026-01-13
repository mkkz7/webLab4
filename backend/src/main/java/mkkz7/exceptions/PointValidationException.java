package mkkz7.exceptions;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class PointValidationException extends BusinessException{
    public PointValidationException(String message){
        super(message, 400);
    }
}
