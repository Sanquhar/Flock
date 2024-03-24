package gui;

import javafx.scene.paint.Color;
import tool.Vector2D;

import java.util.Set;

public interface Positionable {

    public Vector2D nextPosition();

    public Vector2D getPosition();

    public void setNeighbors(Set<Positionable> neighbors);

    public double getAlpha();

    public Color getColor();
}
