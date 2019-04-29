package controllers.menu;

import entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    //private int MIN_PLAYERS = 1;
    private int MAX_PLAYERS = 3;

    @FXML
    private GridPane playersGridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    private void launchArena() throws IOException {
        FXMLLoader arenaSceneLoader = new FXMLLoader(getClass().getResource("/arena/arena.fxml"));
        Parent root = arenaSceneLoader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void closeWindow(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    @FXML
    public void startGame(ActionEvent actionEvent) throws IOException {
        int countPlayers = Players.getList().size();

        if(countPlayers >= 1) {
            setPlayerItems();
            launchArena();
            closeWindow(actionEvent);


            // CHANGES :
            System.out.println("ALL PLAYERS :");
            for (Player player : Players.getList()) {
                System.out.println(
                        "- Player: "+ player.getName()
                                + " [Movement] " + player.getMovement()
                                + " [Weapon] " + player.getWeapon()
                );
            }
        } else {
            System.out.println("Not enough players");
        }
    }

    @FXML
    public void addPlayer(ActionEvent actionEvent) {
        int countPlayers = Players.getList().size();
        if(countPlayers < MAX_PLAYERS) {
            Player newPlayer = new Player(countPlayers++);
            Players.getList().add(newPlayer);

            ComboBox<Weapon> weaponsComboBox = new ComboBox<>();
            weaponsComboBox.setId("weapons-" + newPlayer.getId());
            ComboBox<Movement> movementsComboBox = new ComboBox<>();
            movementsComboBox.setId("movements-" + newPlayer.getId());

            weaponsComboBox.getItems().addAll(Weapons.getList());
            movementsComboBox.getItems().addAll(Movements.getList());

            playersGridPane.add(new Label(newPlayer.toString()), 0, countPlayers);
            playersGridPane.add(weaponsComboBox, 1, countPlayers);
            playersGridPane.add(movementsComboBox, 2, countPlayers);
        }
    }

    private void setPlayerItems() {
        for (Player player : Players.getList()) {;
            for (Node child : playersGridPane.getChildren()) {
                setMovementSelected(child, player);
                setWeaponSelected(child, player);
            }
        }
    }

    private void setMovementSelected(Node child, Player player) {
        if(child.getId() != null) {
            if(child.getId().equals("movements-" + player.getId())) {
                ComboBox<Movement> movementsComboBox = (ComboBox) child;
                player.setMovement(movementsComboBox.getValue());;
            }
        }
    }

    private void setWeaponSelected(Node child, Player player) {
        if(child.getId() != null) {
            if(child.getId().equals("weapons-" + player.getId())) {
                ComboBox<Weapon> weaponsComboBox = (ComboBox) child;
                player.setWeapon(weaponsComboBox.getValue());
            }
        }
    }

    /*@FXML TODO
    public void removePlayer(ActionEvent actionEvent) {
        int countPlayers = players.getList().size();
        if(countPlayers >= MIN_PLAYERS) {
            players.getList().remove(countPlayers - 1);
            System.out.println("count players: " + countPlayers);

            playersGridPane.getChildren().set(countPlayers, null);
        }
    }*/

}
