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

            //stack.getChildren().addAll(player, playerName);
            //stack.setOnMousePressed(circleOnMousePressedEventHandler);
            //stack.setOnMouseDragged(circleOnMouseDraggedEventHandler);

            arena.getChildren().add(player);
            createPathPlayer(player);
        }
    }

    private void createPathPlayer(Node shape) {
        PathTransition pathTransition = new PathTransition();

        pathTransition.setDuration(javafx.util.Duration.millis(800));
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
        int loc = ran.nextInt(600 - 300 + 1) + 300; // min=300 , max=600

        Path path = new Path();
        path.getElements().add(new MoveTo(20, 20));
        path.getElements().add(new LineTo(loc, 600));

        return path;
    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    //orgCenterX = ((Circle)(t.getSource())).getCenterX();
                    //orgCenterY = ((Circle)(t.getSource())).getCenterY();
                    orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
                }
            };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    //double newCenterX = orgCenterX + offsetX;
                    //double newCenterY = orgCenterY + offsetY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    //((Circle)(t.getSource())).setCenterX(newCenterX);
                    //((Circle)(t.getSource())).setCenterY(newCenterY);
                    ((Circle)(t.getSource())).setTranslateX(newTranslateX);
                    ((Circle)(t.getSource())).setTranslateY(newTranslateY);
                }
            };

}
