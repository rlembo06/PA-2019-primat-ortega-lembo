package controllers.menu;

import entities.Movement;
import entities.Movements;
import entities.Weapon;
import entities.Weapons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private Weapons weapons;
    private Movements movements;

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
    public void sayHello(ActionEvent actionEvent) {
        labelHello.setText("Hello mother fucker !!!");
    }


}
