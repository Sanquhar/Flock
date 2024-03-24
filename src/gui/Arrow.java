package gui;

import tool.Vector2D;

import java.util.List;

abstract public class Arrow implements Positionable {

    private Vector2D r;
    private Vector2D v;
    private double alpha;
    private List<Arrow> neighbors;


    @Override
    abstract public Vector2D nextPosition();

    public void setNeighbors(List<Arrow> neighbors) {
        this.neighbors = neighbors;
    }
}
