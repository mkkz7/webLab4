package mkkz7.beans;

import jakarta.ejb.Stateless;
import mkkz7.db.entities.PointResult;
import mkkz7.utils.AreaChecker;
import mkkz7.utils.CheckerInterface;

@Stateless
public class AreaCheckBean {
    private final CheckerInterface checker = new AreaChecker();

    public PointResult createResponse(double x, double y, int r, long startTime){
        boolean hit = checkFromForm(x, y, r);
        double executionTime = ((System.nanoTime() - startTime) / 1_000_000.0);
        return PointResult.builder()
                .id(0L)
                .x(x)
                .y(y)
                .r(r)
                .hit(hit)
                .execTime(executionTime).build();
    }

    private boolean checkFromForm(double x, double y, int r){
        return checker.calculate(x, y, r);
    }
}
