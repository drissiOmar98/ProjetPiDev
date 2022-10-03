package Nourhene.jeux;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;

public class Jeux {

    @FXML
    private TextField idjeux;

    @FXML
    private Label gagne;

    @FXML
    void btnjeux(ActionEvent event) {

        Random rand = new Random();
        String resulata ;
        int x = rand.nextInt(10)+1;
        if (Integer.parseInt(idjeux.getText())==x){
            resulata= "vous avez gagnez";
        }else {
            resulata="vous avez perdu";
        }
        gagne.setText(""+resulata+"le resultat est"+x);

    }


}
