package controllers.arena;

import entities.User;
import entities.Users;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ArenaController implements Initializable {

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY, orgCenterX, orgCenterY;

    @FXML
    private AnchorPane arena;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateUsers();
    }

    private void generateUsers() {
        for (User user : Users.getList()) {
            StackPane stack = new StackPane();
            Text playerName = new Text(user.toString());
            playerName.setBoundsType(TextBoundsType.VISUAL);
            Circle player = new Circle( 50.0f, Color.GREEN);

            player.setCursor(Cursor.MOVE);
            player.setCenterX(150);
            player.setCenterY(150);
            //player.setOnMousePressed(circleOnMousePressedEventHandler);
            //player.setOnMouseDragged(circleOnMouseDraggedEventHandler);


            arena.getChildren().add(player);
            createPathPlayer(player);
        }
    }

    private void createPathPlayer(Node shape) {
        PathTransition pathTransition = new PathTransition();

        pathTransition.setDuration(javafx.util.Duration.millis(1200));
        pathTransition.setPath(createRandomPath());
        pathTransition.setNode(shape);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(2);
        pathTransition.setAutoReverse(true);
        pathTransition.setOnFinished(e -> {
            pathTransition.setPath(createRandomPath());
            pathTransition.play();
        });
        pathTransition.play();
    }

    private Path createRandomPath() {
        Random ran = new Random();
        int loc = ran.nextInt(600 - 400 + 1) + 300;  // min=400 , max=600

        Path path = new Path();
        path.getElements().add(new MoveTo(ran.nextInt(100), ran.nextInt(100)));
        path.getElements().add(new LineTo(loc, ran.nextInt(350)));

        return path;
    }

}
