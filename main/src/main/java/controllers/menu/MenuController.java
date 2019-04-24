package controllers.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Label labelHello;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    @FXML
    public void sayHello(ActionEvent actionEvent) {
        labelHello.setText("Hello mother fucker !!!");
    }
}
