package mkkz7.utils;

import mkkz7.exceptions.PointValidationException;

public class PointValidator implements ValidatorInterface{
    @Override
    public boolean validateFromGraph(double x, double y, int r) throws PointValidationException {
        if(x < -4.0 || x > 4.0){throw new PointValidationException("Invalid X: " + x);}
        if(y < -5.0 || y > 3.0){throw new PointValidationException("Invalid Y: " + y);}
        if(r < -4 || r > 4){throw new PointValidationException("Invalid R: " + r);}

        return true;
    }
}
