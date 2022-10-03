package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import entity.Manager;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane root;

    public int verifier_auth(String email ,String password) throws SQLException, IOException, ClassNotFoundException {

        Connection cnx;
        PreparedStatement ste ;
        cnx = DBConnection.getInstance().getConnection();

        String sql = "Select * from User where email  =? and password  =?";
        ResultSet rs;
        ste=cnx.prepareStatement(sql);
        User u = new User() ;

        int x=0;
        ste.setString(1, email);
        ste.setString(2, password);
        rs = ste.executeQuery();
        if (rs.next()){

            u.setId(rs.getInt(1));
            u.setNom(rs.getString(2));
            u.setPrenom(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setRoles(rs.getString(5));
            u.setCin(rs.getInt(6));
            u.setPassword(rs.getString(7));
            u.setIs_Verified(rs.getInt(8));
            u.setImage_name(rs.getString(9));


            if(rs.getString(5).equals("ADMIN")){ x = 1; }
            if(rs.getString(5).equals("USER1")){ x = 2; }
            if(rs.getString(5).equals("USER2")){ x = 3; }
            if(rs.getString(5).equals("USER6")){ x = 4; }
            if(rs.getString(5).equals("USER3")){ x = 5; }
            if(rs.getString(5).equals("USER4")){ x = 6; }
            if(rs.getString(5).equals("CLIENT")){ x = 7; }

        }

        return x;

    }

    public void LoginOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {


        String uNom = txtUserName.getText();
        String upass = txtPassword.getText();

        if (verifier_auth(uNom, upass) == 1) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/Dashboard.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();


        }
        if (verifier_auth(uNom, upass) == 2) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/AddCashierFormController.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();


        }

        if (verifier_auth(uNom, upass) == 3) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/AddItemForm.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();


        }

        if (verifier_auth(uNom, upass) == 4) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/AddSuply.fxml")));
            //Parent root = FXMLLoader.load(getClass().getResource("view/AddCashierForm.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }

        if (verifier_auth(uNom, upass) == 5) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/FXMLEvents.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();


        }

        if (verifier_auth(uNom, upass) == 6) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/interface.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }

        if (verifier_auth(uNom, upass) == 7) {
            txtUserName.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        }
    }
    public void btnCloaseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}




































/*
    public void LoginOnAction() throws IOException {

        String userName = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();
        if (Pattern.compile("^(Elyes)$").matcher(userName).matches()) {


        } else {
            txtUserName.setFocusColor(Paint.valueOf("red"));
            txtUserName.requestFocus();


        }
        if (Pattern.compile("^(2259)$").matcher(userName).matches()) {
        } else {
            txtPassword.setFocusColor(Paint.valueOf("red"));
            txtPassword.requestFocus();

        }
        if (userName.length() > 0 && password.length() > 0) {
            if (userName.equalsIgnoreCase("Elyes")
                    && password.equals("2259")) {

                Stage window = (Stage) this.root.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml"))));
                window.centerOnScreen();
                String tilte = "Sign In";
                String message = "WELCOME TO DEVAPP SYSTEM ";


            } else {
                LoginBO loginBO = new LoginBOImpl();
                try {
                    CashierDTO cashierDTO = loginBO.getValidated(txtUserName.getText());
                    System.out.println(cashierDTO.getCastID()+" id from login form");
                    System.out.println(cashierDTO.getCastlogin() + " userName");
                    System.out.println(cashierDTO.getCastPassword() + " password");

                    if (cashierDTO.getCastlogin().equals(txtUserName.getText()) &&
                            cashierDTO.getCastPassword().equals(txtPassword.getText())) {

                        Stage window = (Stage) this.root.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/CashierForm.fxml"));
                        Parent parent =  fxmlLoader.load();
                        CashierFormController controller = fxmlLoader.getController();
                        System.out.println("sending data");
                        controller.setCashierID(cashierDTO.getCastID());
                        window.setScene(new Scene(parent));
                        window.centerOnScreen();
                        window.show();

                    } else {
                        System.out.println("ERROR");
                    }
                } catch (NullPointerException e) {
                    System.out.println("user name error");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        } else {
            String tilte = "Sign In";

        }
    }
*/