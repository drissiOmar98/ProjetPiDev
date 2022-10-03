package controller;


import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    public Pane context;


    private void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/view/" + location + ".fxml")));
    }
    private void setUi1(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/Aymen/GUIay/" + location + ".fxml")));
    }
    private void setUi2(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/GestionOffre/GUI/" + location + ".fxml")));
    }

    private void setUi3(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/Nourhene/GUIno/" + location + ".fxml")));
    }

    private void setUi5(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/Omar/GUIom/" + location + ".fxml")));
    }

    private void setUi4(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/Badis/GUIba/" + location + ".fxml")));
    }



    public void btnEnetOnAction() throws IOException {
        setUi5("FXMLEvents");
        new FadeIn(context).play();

    }

    public void DashBoardOnAction() throws IOException {
        setUi("DashboardForm");
        new FadeIn(context).play();

    }


    public void btnOffreOnAction() throws IOException {

        setUi2("interface");
        new FadeIn(context).play();
    }

    public void btnAddCashier() throws IOException {
        setUi1("AddCashierFormController");
        new FadeIn(context).play();
    }


    public void btnLogOut() {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.close();
    }

    public void btnAddCustomer() throws IOException {
        setUi("AddEmployeeForm");
        new FadeIn(context).play();
    }

    public void btnReportOnAction() throws IOException {
        setUi("Mail");
        new FadeIn(context).play();
    }

    public void btnAboutOnAction() throws IOException {
        setUi("AboutForm");
        new FadeIn(context).play();
    }

    public void btnSuplayerOnAction() throws IOException {
        setUi3("AddSuply");
        new FadeIn(context).play();
    }

    public void btnCloaseOnAction() {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            DashBoardOnAction();
            new FadeIn(context).play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddItemOnAction(ActionEvent actionEvent) throws IOException {

        setUi4("AddItemForm");
        new FadeIn(context).play();
    }
}



