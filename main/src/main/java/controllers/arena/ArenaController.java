package controllers.arena;

import entities.User;
import entities.Users;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ArenaController implements Initializable {

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    @FXML
    private AnchorPane arena;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateUsers();
    }

    private void generateUsers() {
        for (User user : Users.getList()) {
            Circle player = new Circle(50.0f, Color.GREEN);
            player.setCursor(Cursor.MOVE);
            player.setCenterX(150);
            player.setCenterY(150);
            player.setOnMousePressed(circleOnMousePressedEventHandler);
            player.setOnMouseDragged(circleOnMouseDraggedEventHandler);

            arena.getChildren().add(player);
        }
    }

    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
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
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Circle)(t.getSource())).setTranslateX(newTranslateX);
                    ((Circle)(t.getSource())).setTranslateY(newTranslateY);
                }
            };

}
