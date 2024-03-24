package tool;

public final class Math2 {

    public static double clamp(double x, double min, double max) {
        if (x>max) {
            x = max;
        } else if (x<min) {
            x = min;
        }
        return x;
    }

}
