package mkkz7.utils;

import mkkz7.exceptions.PointValidationException;

public class PointValidator implements ValidatorInterface{
    @Override
    public boolean validate(int x, double y, int r) throws PointValidationException {
        if(x <= -4 || x >= 4){throw new PointValidationException("Invalid X: " + x);}
        if(y <= -5 || y >= 3){throw new PointValidationException("Invalid Y: " + y);}
        if(r < 0 || r > 4){throw new PointValidationException("Invalid R: " + r);}

        return true;
    }
}
