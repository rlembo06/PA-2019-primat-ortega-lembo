package controllers.arena;

import entities.User;
import entities.Users;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import java.net.URL;
import java.util.ResourceBundle;

public class ArenaController implements Initializable {

    @FXML
    private AnchorPane arena;

    @FXML
    private GridPane playersLifeGridPane;

    private Color[] playerColor = {Color.GREEN, Color.RED, Color.BLUE};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateUsers();
    }

    private void generateUsers() {
        for (User user : Users.getList()) {
            Text playerName = new Text(user.toString());
            playerName.setBoundsType(TextBoundsType.VISUAL);
            Circle player = new Circle( 30.0f, playerColor[user.getId()]);

            player.setCursor(Cursor.MOVE);
            player.setCenterX(150);
            player.setCenterY(150);

            arena.getChildren().add(player);

            user.runMovementSelected(player);
            generePlayersLife(user);
        }
    }

    private void generePlayersLife(User user) {
        Label playerNameLabel = new Label(user.toString());
        Label playerLifeLabel = new Label(String.valueOf(user.getLife()));
        playerNameLabel.setTextFill(playerColor[user.getId()]);

        playersLifeGridPane.add(playerNameLabel, 0, user.getId() + 1);
        playersLifeGridPane.add(playerLifeLabel, 1, user.getId() + 1);
    }

}
