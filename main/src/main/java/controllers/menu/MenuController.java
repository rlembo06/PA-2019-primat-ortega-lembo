package controllers.menu;

import entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private int MIN_USERS = 1;
    private int MAX_USERS = 3;

    private Weapons weapons;
    private Movements movements;
    private Users users = new Users();

    @FXML
    private Label labelHello;

    @FXML
    private ComboBox<Weapon> weaponsComboBox;

    @FXML
    private ComboBox<Movement> movementsComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            weapons = new Weapons();
            movements = new Movements();
            weaponsComboBox.getItems().addAll(weapons.getList());
            movementsComboBox.getItems().addAll(movements.getList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void addUser(ActionEvent actionEvent) {
        int countUsers = users.getList().size();
        if(countUsers < MAX_USERS) {
            users.getList().add(new User(countUsers++));
            System.out.println("count users: " + countUsers);
        }
    }

    @FXML
    public void removeUser(ActionEvent actionEvent) {
        int countUsers = users.getList().size();
        if(countUsers >= MIN_USERS) {
            users.getList().remove(countUsers - 1);
            System.out.println("count users: " + countUsers);
        }
    }

}
