package controllers.menu;

import entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private int MIN_USERS = 1;
    private int MAX_USERS = 3;

    private Weapons weapons = new Weapons();
    private Movements movements = new Movements();
    private Users users = new Users();

    @FXML
    private GridPane usersGridPane;

    public MenuController() throws IOException, ClassNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    public void addUser(ActionEvent actionEvent) {
        int countUsers = users.getList().size();
        if(countUsers < MAX_USERS) {
            users.getList().add(new User(countUsers++));
            System.out.println("count users: " + countUsers);

            ComboBox<Weapon> weaponsComboBox = new ComboBox<>();
            ComboBox<Movement> movementsComboBox = new ComboBox<>();

            weaponsComboBox.getItems().addAll(weapons.getList());
            movementsComboBox.getItems().addAll(movements.getList());

            usersGridPane.add(new Label("Joueur " + countUsers), 0, countUsers);
            usersGridPane.add(weaponsComboBox, 1, countUsers);
            usersGridPane.add(movementsComboBox, 2, countUsers);
        }
    }

    /*@FXML TODO
    public void removeUser(ActionEvent actionEvent) {
        int countUsers = users.getList().size();
        if(countUsers >= MIN_USERS) {
            users.getList().remove(countUsers - 1);
            System.out.println("count users: " + countUsers);

            usersGridPane.getChildren().set(countUsers, null);
        }
    }*/

}
