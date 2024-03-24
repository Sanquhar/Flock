import gui.BirdManager;
import gui.PositionableManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import tool.Vector2D;

public class mainTest extends Application {

    private static int INITIAL_WIDTH = 500;
    private static int INITIAL_HEIGHT = 500;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT, Color.BLACK);

        PositionableManager positionableManager = new BirdManager(50,INITIAL_WIDTH, INITIAL_HEIGHT);

        scene.widthProperty().addListener((observableValue, previous, next) -> {
            //stage.setWidth((Double) next);
            positionableManager.setCanvasWidth((Double) next);
        });
        scene.heightProperty().addListener((observableValue, previous, next) -> {
            //stage.setHeight((Double) next);
            positionableManager.setCanvasHeight((Double) next);
        });



        Timeline timeline = new Timeline();
        timeline.setCycleCount(Integer.MAX_VALUE);
        //timeline.setCycleCount(10);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(10),actionEvent -> {
            positionableManager.drawElements();
        });
        timeline.getKeyFrames().add(keyFrame);

        root.getChildren().add(positionableManager.getCanvas());

        stage.setTitle("Flock Project");
        stage.setScene(scene);

        stage.show();
        timeline.play();
    };



}
