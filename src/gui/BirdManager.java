package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import tool.Vector2D;

import java.util.HashSet;

public final class BirdManager implements PositionableManager {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private final ObservableList<Bird> birds;
    private double canvasWidth;
    private double canvasHeight;
    private final double RADIUS = 5;
    private final double BASE_NORM = 2;
    private final double DISTANCE;
    //private final Image image;

    public BirdManager(int nbElements, int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.canvas = new Canvas(canvasWidth,canvasHeight);
        this.gc = canvas.getGraphicsContext2D();
        this.birds = FXCollections.observableArrayList();
        for (int i = 0; i < nbElements; i++) {
            birds.add(new Bird());
        }
        DISTANCE = 0.1*(Bird.MAX_SIZE-Bird.MIN_SIZE);
        gc.setFill(Color.RED);
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void drawElements() {
        gc.clearRect(0,0,canvasWidth,canvasHeight);
        computeNeighbors();
        for (Bird p: birds) {
            gc.setFill(p.getColor());

            Vector2D r =  p.nextPosition();
            Vector2D rc = computeNewPosition(r);
            //gc.drawImage(image,rc.x(),rc.y());
            //double angle = p.getAlpha();
            //double[] p1 = new double[]{rc.x()-RADIUS,rc.y()+RADIUS};
            //double[] p2 = new double[]{rc.x()-RADIUS,rc.y()-RADIUS};
            //double[] p3 = new double[]{rc.x()+2*RADIUS,rc.y()};
            //p1 = rotatePoint(p1,angle,rc.x(),rc.y());
            //p2 = rotatePoint(p2,angle,rc.x(),rc.y());
            //p3 = rotatePoint(p3,angle,rc.x(),rc.y());
            ////gc.fillPolygon(new double[]{p1[0],p2[0],p3[0]},
            //                new double[]{p1[1],p2[1],p3[1]},3);


            //gc.fillPolygon(new double[]{rc.x()-RADIUS,,rc.x()+2*RADIUS},
            //               new double[]{rc.y()+RADIUS,rc.y()-RADIUS,rc.y()},
            //        3);
            gc.fillOval(rc.x()-RADIUS, rc.y()-RADIUS,2*RADIUS,2*RADIUS);
        }
    }

    @Override
    public void setCanvasWidth(double canvasWidth) {
        this.canvas.setWidth(canvasWidth);
        this.canvasWidth = canvasWidth;
    }

    @Override
    public void setCanvasHeight(double canvasHeight) {
        this.canvas.setHeight(canvasHeight);
        this.canvasHeight = canvasHeight;
    }

    private void computeNeighbors() {
        for (Bird p1: birds) {
            HashSet<Positionable> neighbors = new HashSet<>();
            for (Bird p2: birds) {
                double distance = Vector2D.distanceModulo(p1.getPosition(),p2.getPosition(),Bird.MIN_SIZE,Bird.MAX_SIZE,BASE_NORM);
                if (distance < DISTANCE) {
                    neighbors.add(p2);
                }
            }
            p1.setNeighbors(neighbors);
        }
    }

    private Vector2D computeNewPosition(Vector2D r) {
        double midWidth = (canvasWidth) / 2;
        double midHeight = (canvasHeight) / 2;
        Vector2D rc = Vector2D.trans(Vector2D.mul(r, midWidth,midHeight),
                midWidth,midHeight);
        return rc;
    }

    private static double[] rotatePoint(double[] p, double angle, double x, double y) {
        double[] newP = new double[]{p[0] - x, p[1] - y};
        double[] finalP = new double[2];
        finalP[0] = newP[0] * Math.cos(angle) - newP[1] * Math.sin(angle) + x;
        finalP[1] = newP[0] * Math.sin(angle) + newP[1] * Math.cos(angle) + y;
        return finalP;
    }

}
