package Omar.ApiSmS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import javax.swing.JOptionPane;

public class FXMLSMSapi {
    @FXML
    private Button quitbtn;

    @FXML
    private TextField txtapi;

    @FXML
    private TextArea txtmessage;

    @FXML
    private TextField txtnumber;

    @FXML
    private TextField txtsender;

    @FXML
    void btnquit(ActionEvent event) throws Exception {
        Stage stage = (Stage) txtsender.getScene().getWindow();
        stage.close();


        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLEvents.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    @FXML
    void btnsend(ActionEvent event) {
        try {
            // Construct data
            String apiKey = "apikey=" + txtapi.getText();
            String message = "&message=" + txtmessage.getText();
            String sender = "&sender=" + txtsender.getText();
            String numbers = "&numbers=" + txtnumber.getText();

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                //stringBuffer.append(line);
                JOptionPane.showMessageDialog(null, "message"+line);
            }
            rd.close();

            //return stringBuffer.toString();
        } catch (Exception e) {
            //System.out.println("Error SMS "+e);
            JOptionPane.showMessageDialog(null,e);
            //return "Error "+e;
        }

    }







}
