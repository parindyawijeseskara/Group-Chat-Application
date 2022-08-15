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
    public AnchorPane loginFormContext;
    public TextField txtUserName;
    public Button btnLogIn;

    public static String name;

    public void userLoginOnAction(ActionEvent actionEvent) throws IOException {
        name = txtUserName.getText();

        Stage stage = (Stage) btnLogIn.getScene().getWindow();
        stage.close();
        URL resource = getClass().getResource("ClientForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage window = new Stage();
        window.setTitle(name);
        window.setMaximized(false);
        window.setScene(scene);
        window.centerOnScreen();
        window.show();


    }
}
