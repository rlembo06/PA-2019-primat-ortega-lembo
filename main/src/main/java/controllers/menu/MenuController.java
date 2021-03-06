package controllers.menu;

import entities.players.Player;
import entities.players.Players;
import entities.players.ShapePlayer;
import entities.players.Shapes;
import entities.weapons.Weapon;
import entities.weapons.Weapons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public final class MenuController implements Initializable {

    //private int MIN_PLAYERS = 1;
    private int MAX_PLAYERS = 3;
    private static boolean withCollision = true;

    @FXML
    private Button btnWithCollision;

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

        if(countPlayers >= 2) {
            setPlayerItems();
            launchArena();
            closeWindow(actionEvent);


            // CHANGES :
            System.out.println("ALL PLAYERS :");
            for (Player player : Players.getList()) {
                System.out.println(
                        "- Player: "+ player.getName()
                                + " [Shape] " + player.getShape()
                                + " [Weapon] " + player.getWeapon()
                );
            }
        } else {
            System.out.println("Not enough players");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Ajouter au moins 2 joueur!");
            alert.showAndWait();
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
            ComboBox<ShapePlayer> shapesComboBox = new ComboBox<>();
            shapesComboBox.setId("shapes-" + newPlayer.getId());
            //ComboBox<Movement> movementsComboBox = new ComboBox<>();
            //movementsComboBox.setId("movements-" + newPlayer.getId());

            weaponsComboBox.getItems().addAll(Weapons.getList());
            shapesComboBox.getItems().addAll(Shapes.getList());
            //movementsComboBox.getItems().addAll(Movements.getList());

            playersGridPane.add(new Label(newPlayer.toString()), 0, countPlayers);
            playersGridPane.add(weaponsComboBox, 1, countPlayers);
            playersGridPane.add(shapesComboBox, 2, countPlayers);
            //playersGridPane.add(movementsComboBox, 2, countPlayers);
        }
    }

    private void setPlayerItems() {
        for (Player player : Players.getList()) {;
            for (Node child : playersGridPane.getChildren()) {
                //setMovementSelected(child, player);
                setShapeSelected(child, player);
                setWeaponSelected(child, player);
            }
        }
    }

    private void setShapeSelected(Node child, Player player) {
        if(child.getId() != null) {
            if(child.getId().equals("shapes-" + player.getId())) {
                ComboBox<ShapePlayer> shapesComboBox = (ComboBox) child;
                player.setShape(shapesComboBox.getValue());
                player.getShape().setLabel(shapesComboBox.getValue().getLabel());
            }
        }
    }

    /*private void setMovementSelected(Node child, Player player) {
        if(child.getId() != null) {
            if(child.getId().equals("movements-" + player.getId())) {
                ComboBox<Movement> movementsComboBox = (ComboBox) child;
                player.setMovement(movementsComboBox.getValue());
            }
        }
    }*/

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

    @FXML
    public void handleWithCollision() {
        if(withCollision) btnWithCollision.setText("Sans collision");
        else btnWithCollision.setText("Avec collision");
        withCollision = !withCollision;
    }

    public static boolean isWithCollision() {
        return withCollision;
    }
}
