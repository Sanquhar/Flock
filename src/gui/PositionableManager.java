package gui;

import javafx.scene.canvas.Canvas;

public interface PositionableManager {

    public Canvas getCanvas();

    public void drawElements();

    public void setCanvasWidth(double canvasWidth);

    public void setCanvasHeight(double canvasHeight);
}
