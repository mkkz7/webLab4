package mkkz7.utils;

public class AreaChecker implements CheckerInterface{
    @Override
    public boolean calculate(double x, double y, int r){
        if(r>0){
            //1 четверть
            if (x >= 0 && y >= 0) {
                if ((x * x + y * y) >= (r/2.0)*(r/2.0)) {
                    return false;
                }
            }
            //2 четверть
            if(x <= 0 && y >= 0){
                return false;
            }

            //3 четверть
            if (x <= 0 && y <= 0) {
                if (y < -x - r) {
                    return false;
                }
            }

            //4 четверть
            if (x >= 0 && y <= 0) {
                if (x > r/2.0 || y < -r) {
                    return false;
                }
            }
        }else{
            //1 четверть
            if (x >= 0 && y >= 0) {
                if ((x * x + y * y) > (r/2.0)*(r/2.0)) {
                    return false;
                }
            }
            //2 четверть
            if(x <= 0 && y >= 0){
                return false;
            }

            //3 четверть
            if (x <= 0 && y <= 0) {
                if (y < -x + r) {
                    return false;
                }
            }

            //4 четверть
            if (x >= 0 && y <= 0) {
                if (x > -r/2.0 || y < r) {
                    return false;
                }
            }
        }

        return true;
    }
}
