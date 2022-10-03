package AddManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class LoggedInController implements Initializable {

    @FXML
    private Button button_logout;

    @FXML
    private Label label_welcome;
    @FXML
    private Label label_fav_channel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

         button_logout.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "view/Dashboard.fxml", "Log in!", null, null);
             }
         });

    }

    public void setUserInformation(String username, String department) {
        label_fav_channel.setText(" Now You can Open your Account " );
    }
}
