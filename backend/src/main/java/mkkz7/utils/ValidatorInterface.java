package mkkz7.utils;

import mkkz7.exceptions.PointValidationException;

public interface ValidatorInterface {
    boolean validate(int x, double y, int r) throws PointValidationException;
}
