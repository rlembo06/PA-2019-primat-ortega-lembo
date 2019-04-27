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

    //private int MIN_USERS = 1;
    private int MAX_USERS = 3;

    @FXML
    private GridPane usersGridPane;

    public MenuController() throws IOException, ClassNotFoundException {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Movements.loadList();
        Movements.loadListName();
        Weapons.loadList();
        Weapons.loadListName();
    }

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
        int countUsers = Users.getList().size();

        if(countUsers >= 1) {
            setUserItems();
            launchArena();
            closeWindow(actionEvent);


            // CHANGES :
            System.out.println("ALL USERS :");
            for (User user : Users.getList()) {
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
        int countUsers = Users.getList().size();
        if(countUsers < MAX_USERS) {
            User newUser = new User(countUsers++);
            Users.getList().add(newUser);

            ComboBox<Weapon> weaponsComboBox = new ComboBox<>();
            weaponsComboBox.setId("weapons-" + newUser.getId());
            ComboBox<Movement> movementsComboBox = new ComboBox<>();
            movementsComboBox.setId("movements-" + newUser.getId());

            weaponsComboBox.getItems().addAll(Weapons.getList());
            movementsComboBox.getItems().addAll(Movements.getList());

            usersGridPane.add(new Label(newUser.toString()), 0, countUsers);
            usersGridPane.add(weaponsComboBox, 1, countUsers);
            usersGridPane.add(movementsComboBox, 2, countUsers);
        }
    }

    private void setUserItems() {
        for (User user : Users.getList()) {;
            for (Node child : usersGridPane.getChildren()) {
                setMovementSelected(child, user);
                setWeaponSelected(child, user);
            }
        }
    }

    private void setMovementSelected(Node child, User user) {
        if(child.getId() != null) {
            if(child.getId().equals("movements-" + user.getId())) {
                ComboBox<Movement> movementsComboBox = (ComboBox) child;
                user.setMovement(movementsComboBox.getValue());;
            }
        }
    }

    private void setWeaponSelected(Node child, User user) {
        if(child.getId() != null) {
            if(child.getId().equals("weapons-" + user.getId())) {
                ComboBox<Weapon> weaponsComboBox = (ComboBox) child;
                user.setWeapon(weaponsComboBox.getValue());
            }
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
