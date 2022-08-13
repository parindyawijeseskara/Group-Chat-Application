package lk.ijse.gdse.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {

    public TextField txtUserName;
    public Button btnLogIn;

    public String name;
    public AnchorPane loginFormContext;

    public void userLoginOnAction(ActionEvent actionEvent) throws IOException {
        name = txtUserName.getText();

        URL resource = getClass().getResource("../Client/ChatRoom.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) loginFormContext.getScene().getWindow();
        window.setTitle(name);
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }
}
