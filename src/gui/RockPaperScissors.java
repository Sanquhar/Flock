package gui;

import javafx.scene.paint.Color;
import tool.Vector2D;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RockPaperScissors implements Positionable{

    private Vector2D r;
    private Vector2D v;
    private double alpha;
    private Color color;
    private final static double DEV = 0.1;
    private final static double VS = 0.005;
    public final static double MIN_SIZE = -1;
    public final static double MAX_SIZE = 1;
    private Set<Positionable> neighbors;
    private final Random rand = new Random();

    public RockPaperScissors() {
        this.r = Vector2D.trans(Vector2D.mul(Vector2D.build(Math.random(),Math.random()),2, 2),
                -1,-1);
        this.alpha = Math.random() * 2 * Math.PI;
        this.v = computeVelocityFromAlpha(alpha);
        //List<Color> colors = List.of(Color.RED,Color.GREEN,Color.BLUE);
        //this.color = colors.get(rand.nextInt(3));
        this.color = new Color(Math.random(),Math.random(),Math.random(),1.0);
        this.color = Color.WHITE;
        this.neighbors = new HashSet<>();
    }

    @Override
    public Vector2D nextPosition() {
        return null;
    }

    @Override
    public Vector2D getPosition() {
        return null;
    }

    @Override
    public void setNeighbors(Set<Positionable> neighbors) {

    }

    @Override
    public double getAlpha() {
        return 0;
    }

    @Override
    public Color getColor() {
        return null;
    }

    private Vector2D computeVelocityFromAlpha(double alpha) {
        return Vector2D.build(VS * Math.cos(alpha),VS * Math.sin(alpha));
    }
}
