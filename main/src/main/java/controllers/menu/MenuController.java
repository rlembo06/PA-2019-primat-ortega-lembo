package controllers.menu;

import entities.*;
import javafx.application.Application;
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

    //private int MIN_USERS = 1;
    private int MAX_USERS = 3;

    private Weapons weapons = new Weapons();
    private Movements movements = new Movements();
    private Users users = new Users();

    @FXML
    private GridPane usersGridPane;

    public MenuController() throws IOException, ClassNotFoundException {}

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
        int countUsers = users.getList().size();

        if(countUsers >= 1) {
            setUserItems();
            launchArena();
            closeWindow(actionEvent);


            // CHANGES :
            System.out.println("ALL USERS :");
            for (User user : users.getList()) {
                System.out.println(
                        "- user: "+ user.getName()
                                + " [Movement] " + user.getMovement()
                                + " [Weapon] " + user.getWeapon()
                );
            }
        } else {
            System.out.println("Not enough players");
        }
    }

    @FXML
    public void addUser(ActionEvent actionEvent) {
        int countUsers = users.getList().size();
        if(countUsers < MAX_USERS) {
            User newUser = new User(countUsers++);
            users.getList().add(newUser);

            ComboBox<Weapon> weaponsComboBox = new ComboBox<>();
            weaponsComboBox.setId("weapons-" + newUser.getId());
            ComboBox<Movement> movementsComboBox = new ComboBox<>();
            movementsComboBox.setId("movements-" + newUser.getId());

            weaponsComboBox.getItems().addAll(weapons.getList());
            movementsComboBox.getItems().addAll(movements.getList());

            usersGridPane.add(new Label(newUser.toString()), 0, countUsers);
            usersGridPane.add(weaponsComboBox, 1, countUsers);
            usersGridPane.add(movementsComboBox, 2, countUsers);
        }
    }

    private void setUserItems() {
        for (User user : users.getList()) {;
            for (Node child : usersGridPane.getChildren()) {
                getMovementSelected(child, user);
                getWeaponSelected(child, user);
            }
        }
    }

    private void getMovementSelected(Node child, User user) {
        if(child.getId() != null) {
            if(child.getId().equals("movements-" + user.getId())) {
                ComboBox<Movement> movementsComboBox = (ComboBox) child;
                user.setMovement(movementsComboBox.getValue());;
            }
        }
    }

    private void getWeaponSelected(Node child, User user) {
        if(child.getId() != null) {
            if(child.getId().equals("weapons-" + user.getId())) {
                ComboBox<Weapon> weaponsComboBox = (ComboBox) child;
                user.setWeapon(weaponsComboBox.getValue());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

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
