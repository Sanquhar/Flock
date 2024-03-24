package tool;

public record Vector2D(double x, double y) {

    public static Vector2D add(Vector2D r1, Vector2D r2) {
        return new Vector2D(r1.x + r2.x, r1.y + r2.y);
    }

    public static Vector2D sub(Vector2D r1, Vector2D r2) {
        return new Vector2D(r1.x - r2.x, r1.y - r2.y);
    }

    public static Vector2D trans(Vector2D r, double dx, double dy) {
        return new Vector2D(r.x + dx, r.y + dy);
    }

    public static Vector2D mul(Vector2D r, double factx, double facty) {
        return new Vector2D(factx * r.x, facty * r.y);
    }

    public static Vector2D build(double x, double y) {
        return new Vector2D(x, y);
    }

    public static Vector2D modulo(Vector2D r,double min, double max) {
        double newX = ((r.x - min)%(max-min)) + min;
        double newY = ((r.y - min)%(max-min)) + min;
        newX = newX - min >=0 ? newX : newX + max - min;
        newY = newY - min >=0 ? newY : newY + max - min;
        return new Vector2D(newX,newY);
    }

    public static double distanceModulo(Vector2D r1, Vector2D r2, double min, double max,double baseNorm) {
        Vector2D nr1 = trans(r1,-min,-min);
        Vector2D nr2 = trans(r2,-min,-min);
        double abs1 = Math.abs(nr2.x - nr1.x);
        double dx = Math.min(abs1, max - abs1);
        double abs2 = Math.abs(nr2.y - nr1.y);
        double dy = Math.min(abs2, max - abs2);
        return Math.pow(Math.pow(dx,baseNorm)+Math.pow(dy,baseNorm),1/baseNorm);
    }

    public static double angle(Vector2D r1, Vector2D r2) {
        double n1 = norm(r1);
        double n2 = norm(r2);
        if (n1 * n2 != 0) {
            return Math.acos(r1.x * r2.x + r1.y * r2.y) / (n1 * n2);
        } else {
            return Math.acos(0);
        }
    }

    public static double norm(Vector2D r) {
        return Math.sqrt(r.x * r.x + r.y * r.y);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}