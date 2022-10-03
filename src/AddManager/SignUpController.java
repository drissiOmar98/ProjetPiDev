package AddManager;

import entity.User;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document ;
import com.itextpdf.text.DocumentException ;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.FileChooser;
import Badis.entityBa.Hotel;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
public class SignUpController implements Initializable {
    public PreparedStatement ste;
    public Connection cnx;
    public SignUpController() throws SQLException {
        cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/aero_space", "root", "");
    }


    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfPrenom;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfRoles;

    @FXML
    private TextField tfCin;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfIsVerified;

    @FXML
    private TextField tfImageName;

    @FXML
    private TableView<User> tvUser;

    @FXML
    private TableColumn<User, Integer> colid;

    @FXML
    private TableColumn<User, String> colnom;

    @FXML
    private TableColumn<User, String> colprenom;

    @FXML
    private TableColumn<User, String> colemail;

    @FXML
    private TableColumn<User, String> colroles;

    @FXML
    private TableColumn<User, Integer> colcin;

    @FXML
    private TableColumn<User, String> colpaswword;

    @FXML
    private TableColumn<User, Integer> colisverified;

    @FXML
    private TableColumn<User, String> colimage_name;

    @FXML
    private Button btnInsert;


    @FXML
    void handleButtonAction(ActionEvent event) throws  IOException {

        if(event.getSource() ==btnInsert){

            int jj=31 ; int yy=1998 ; int mm=8;
            Date dd = new Date(yy-1900,mm-1,yy);
            String  uid = tfId.getText();
            String uNom = tfNom.getText();
            String uPrenom = tfPrenom.getText();
            String uEmail = tfEmail.getText();
            String uRoles = tfRoles.getText();
            String uCin = tfCin.getText();
            String uPassword = tfPassword.getText();
            String uIsVerified = tfIsVerified.getText();
            String uImageName = tfImageName.getText();
            int i=Integer.parseInt(uCin);
            int j=Integer.parseInt(uid);
            int k=Integer.parseInt(uIsVerified);

            User u = new User (j,uNom,uPrenom,uEmail,uRoles,i,uPassword,k,uImageName);

            ajouterUser(u);
            showUsers();
        }



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showUsers();
    }
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aero_space", "root", "");
            System.out.println("cnx etablie");
            return conn;

        } catch (Exception ex) {
            System.out.println("Error:  " + ex.getMessage());
            return null;
        }

    }

    public ObservableList<User> getUserList() {
        ObservableList<User> userlist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM user";
        Statement st;
        ResultSet rs;
        try {
            st=conn.createStatement();
            rs=st.executeQuery(query);
            User user ;
            while(rs.next()){
                user = new User(rs.getInt("id"),rs.getString("Nom"),rs.getString("Prenom"),rs.getString("email"),rs.getString("roles"),rs.getInt("Cin"),rs.getString("password"),rs.getInt("is_verified"),rs.getString("image_name"));
                userlist.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userlist ;
    }
    public void showUsers(){
        ObservableList<User> list  =getUserList();
        colid.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<User,String>("Nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<User,String>("Prenom"));
        colemail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        colroles.setCellValueFactory(new PropertyValueFactory<User,String>("Cin"));
        colcin.setCellValueFactory(new PropertyValueFactory<User,Integer>("roles"));
        colpaswword.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        colisverified.setCellValueFactory(new PropertyValueFactory<User,Integer>("is_Verified"));
        colimage_name.setCellValueFactory(new PropertyValueFactory<User,String>("image_name"));
        tvUser.setItems(list);
    }

    

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st ;
        try{
            st=conn.createStatement();
            st.executeQuery(query);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }




    public void ajouterUser(User u)  {

        try {
            String sql = "insert into user (id,Nom,Prenom,email,roles,Cin,password,is_verified,image_name)" + "values(?,?,?,?,?,?,?,?,?)";
            ste=cnx.prepareStatement(sql);

            ste.setInt(1, u.getId());
            ste.setString(2, u.getNom());
            ste.setString(3,u.getPrenom());
            ste.setString(4,u.getEmail());
            ste.setString(5,u.getRoles());
            ste.setInt(6,u.getCin());
            ste.setString(7,u.getPassword());
            ste.setInt(8, u.getIs_Verified());
            ste.setString(9, u.getImage_name());

            ste.executeUpdate();

            System.out.println("******* User a Ajouter aves succeé *******");
        }catch (SQLException ex) { System.out.println(ex);
        }}
}
