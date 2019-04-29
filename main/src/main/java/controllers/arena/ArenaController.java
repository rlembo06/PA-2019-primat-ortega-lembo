package controllers.arena;

import entities.Player;
import entities.Players;
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
        generatePlayers();
    }

    private void generatePlayers() {
        for (Player player : Players.getList()) {
            Text playerName = new Text(player.toString());
            playerName.setBoundsType(TextBoundsType.VISUAL);

            Circle playerShape = new Circle( 30.0f, playerColor[player.getId()]);

            playerShape.setCursor(Cursor.MOVE);
            playerShape.setCenterX(150);
            playerShape.setCenterY(150);

            arena.getChildren().add(playerShape);

            player.runMovementSelected(playerShape);
            generePlayersLife(player);
        }
    }

    private void generePlayersLife(Player player) {
        Label playerNameLabel = new Label(player.toString());
        Label playerLifeLabel = new Label(String.valueOf(player.getLife()));
        playerNameLabel.setTextFill(playerColor[player.getId()]);

        playersLifeGridPane.add(playerNameLabel, 0, player.getId() + 1);
        playersLifeGridPane.add(playerLifeLabel, 1, player.getId() + 1);
    }
}
