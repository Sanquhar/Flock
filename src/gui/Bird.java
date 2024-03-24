package gui;

import javafx.scene.paint.Color;
import tool.Math2;
import tool.Vector2D;

import java.util.*;

public class Bird implements Positionable {

    private Vector2D r;
    private Vector2D v;
    private double alpha;
    private final Color color;
    private final static double DEV = 0.1;
    private final static double VS = 0.005;
    public final static double MIN_SIZE = -1;
    public final static double MAX_SIZE = 1;
    private Set<Positionable> neighbors;
    private final Random rand = new Random();

    public Bird() {
        this.r = Vector2D.trans(Vector2D.mul(Vector2D.build(Math.random(),Math.random()),2, 2),
                -1,-1);
        this.alpha = Math.random() * 2 * Math.PI;
        this.v = computeVelocityFromAlpha(alpha);
        //List<Color> colors = List.of(Color.RED,Color.GREEN,Color.BLUE);
        //this.color = colors.get(rand.nextInt(3));
        this.color = new Color(Math.random(),Math.random(),Math.random(),1.0);
        //this.color = Color.WHITE;
        this.neighbors = new HashSet<>();
    }

    @Override
    public Vector2D nextPosition() {

        Vector2D avgPos = Vector2D.build(0,0);
        double avgAlpha = 0;
        for (Positionable b: neighbors) {
            avgPos = Vector2D.add(avgPos,b.getPosition());
            avgAlpha += b.getAlpha();
        }
        double nbNeighbors = neighbors.size();
        if (nbNeighbors > 0) {
            avgPos = Vector2D.modulo(Vector2D.mul(avgPos,1/nbNeighbors,1/nbNeighbors),MIN_SIZE,MAX_SIZE);
            avgAlpha /= nbNeighbors;
        }

        //Vector2D avgV = computeVelocityFromAlpha(avgAlpha);
        //double angleBetween = Vector2D.angle(Vector2D.sub(avgPos,r),avgV) % (2*Math.PI);
        //this.alpha += 0.9*((alpha + 0.1*angleBetween)%(2*Math.PI)) + 0.1*rand.nextGaussian(0,DEV);
        //this.alpha += 0.01*rand.nextGaussian(angleBetween,DEV) ;

        /**
        int[] n = new int[]{0,0,0};
        for (Bird b: neighbors) {
            if (b.getColor()==Color.RED) {
                n[0] += 1;
            } else if (b.getColor() == Color.GREEN) {
                n[1] += 1;
            } else {
                n[2] += 1;
            }
        }
 **/

        //int nbN = neighbors.size();
        //double newShape = (double)nbN/30;
        //if (newShape > 1) {
        //    newShape = 1;
        //}
        //double red = this.color.getRed() + rand.nextGaussian(0,0.01);
        //double green = this.color.getGreen() + rand.nextGaussian(0,0.01);
        //double blue = this.color.getBlue() + rand.nextGaussian(0,0.01);
        //this.color = new Color(Math2.clamp(red,0,1),
        //                       Math2.clamp(green,0,1),
        //                       Math2.clamp(blue,0,1),1);



        this.alpha += rand.nextGaussian(0,DEV);
        this.v = computeVelocityFromAlpha(alpha);
        this.r = Vector2D.modulo(Vector2D.add(r,v),MIN_SIZE,MAX_SIZE);
        return this.r;
    }

    @Override
    public void setNeighbors(Set<Positionable> neighbors) {
        this.neighbors = neighbors;
    }

    public Vector2D getV() {
        return v;
    }

    public Vector2D getPosition() {
        return r;
    }

    @Override
    public double getAlpha() {
        return alpha;
    }

    @Override
    public Color getColor() {
        return color;
    }

    private Vector2D computeVelocityFromAlpha(double alpha) {
        return Vector2D.build(VS * Math.cos(alpha),VS * Math.sin(alpha));
    }
}
