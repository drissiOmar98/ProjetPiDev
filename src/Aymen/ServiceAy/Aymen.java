package Aymen.ServiceAy;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import Aymen.entityAy.Books;
import Aymen.entityAy.FactureE;
import Aymen.entityAy.FactureEmp;
import Aymen.entityAy.Stat;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aymen implements Initializable {

    @FXML
    private BarChart<String, Number> barcharts;

    @FXML
    private CategoryAxis device;

    @FXML
    private NumberAxis visits;

    @FXML
    private TextField tfid;

    @FXML
    private TextField tftitle;

    @FXML
    private TextField tfauthor;

    @FXML
    private TextField tfyear;

    @FXML
    private TextField tfpages;

    @FXML
    private TextField filtrage;

    @FXML
    private TableView<Books> tvBooks;

    @FXML
    private TableColumn<Books, Integer> colID;

    @FXML
    private TableColumn<Books, String> colTitle;

    @FXML
    private TableColumn<Books, String> colAuthor;

    @FXML
    private TableColumn<Books, Integer> colYear;

    @FXML
    private TableColumn<Books, Integer> colPages;

    @FXML
    private Button btninsert;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;

    @FXML
    private TableView<Stat> tab2;

    @FXML
    private TableColumn<Stat, Integer> valeurRes;

    @FXML
    private TableColumn<Stat, Integer> valeurOff;
    //******************facture client**********************
    @FXML
    private DatePicker date_fac;

    @FXML
    private TextField TVA_fac;

    @FXML
    private TextField remise_fac;

    @FXML
    private TextArea NB_fac;

    @FXML
    private TextField Totale;

    @FXML
    private TextField nameClient;

    @FXML
    private Label id_fac;

    @FXML
    private ComboBox comboMode1;

    @FXML
    private ComboBox comboEtat1;

    @FXML
    private TableView<FactureE> table_facC;

    @FXML
    private TableColumn<FactureE,String> fac_id;

    @FXML
    private TableColumn<FactureE,String> fac_client;

    @FXML
    private TableColumn<FactureE, Date> fac_date;

    @FXML
    private TableColumn<FactureE, String> fac_reglement;

    @FXML
    private TableColumn<FactureE, String> fac_etat;

    @FXML
    private TableColumn<FactureE,Integer> fac_tva;

    @FXML
    private TableColumn<FactureE,Integer> fac_remise;

    @FXML
    private TableColumn<FactureE,String> fac_nb;

    @FXML
    private TableColumn<FactureE, Integer> fac_totale;

    @FXML
    private Button btninsertClient;

    @FXML
    private Button btnupdateClient;

    @FXML
    private Button btndeleteClient;

    //***************Employes****************
    @FXML
    private TableView<FactureEmp> table_Emp;

    @FXML
    private TableColumn<FactureEmp,String> emp_id;

    @FXML
    private TableColumn<FactureEmp,String> emp_client;

    @FXML
    private TableColumn<FactureEmp,Date> emp_date;

    @FXML
    private TableColumn<FactureEmp,String> emp_reglement;

    @FXML
    private TableColumn<FactureEmp, String> emp_etat;

    @FXML
    private TableColumn<FactureEmp,Integer> emp_tva;

    @FXML
    private TableColumn<FactureEmp,Integer> emp_remise;

    @FXML
    private TableColumn<FactureEmp,String> emp_nb;

    @FXML
    private TableColumn<FactureEmp,Integer> emp_totale;


    @FXML
    private TextField nameEmploye;

    @FXML
    private DatePicker date_emp;

    @FXML
    private ComboBox comboMode_Emp;

    @FXML
    private ComboBox comboEtat1_Emp;

    @FXML
    private TextField TVA_Emp;

    @FXML
    private TextField bonus_Emp;

    @FXML
    private TextArea NB_Emp;

    @FXML
    private TextField Totale_emp;

    @FXML
    private Label id_emp;

    @FXML
    private Button btninsertEmp;

    @FXML
    private Button btnupdateEmp;

    @FXML
    private Button btndeleteEmp;

    //************
    @FXML
    private Label nbr_Emp1;

    @FXML
    private Label nbr_Emp2;

    @FXML
    private Label nbr_Emp3;

    @FXML
    void ComboboxEtat_Empoyes(ActionEvent event) {
        EtatE = comboEtat1_Emp.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void ComboboxMode_Employes(ActionEvent event) {
        ModeReglementE = comboMode_Emp.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void mousebtn4(MouseEvent event) {
        FactureEmp facture= table_Emp.getSelectionModel().getSelectedItem();
        id_emp.setText(facture.getId());
        nameEmploye.setText(facture.getNomEmp());
        //comboMode.setText(facture.getRegliment());
        //tfyear.setText(""+facture.getEtatFacture());
        TVA_Emp.setText(""+facture.getTVA());
        bonus_Emp.setText(""+facture.getBonus());
        NB_Emp.setText(""+facture.getNBFacture());
        Totale_emp.setText(""+facture.getTotale());
    }
    //***************************************
    @FXML
    void ComboboxMode(ActionEvent event) {
        ModeReglement = comboMode1.getSelectionModel().getSelectedItem().toString();
    }



    @FXML
    void ComboboxEtat(ActionEvent event) {
        Etat = comboEtat1.getSelectionModel().getSelectedItem().toString();
    }

    String ModeReglement, Etat ,ModeReglementE , EtatE ;
    int res;
    int off;
    int Clien;


    @FXML
    void handleButtonAction( ActionEvent event) {
        if(event.getSource()== btninsert){
            insertRecord();
        } else if (event.getSource()==btnupdate){
            update();
        }
        else if (event.getSource()==btndelete){
            delete();
        }

    }

    @FXML
    void handleButtonAction1(ActionEvent event) {
        if(event.getSource()== btninsertClient){
            insertRecord1();
        } else if (event.getSource()==btnupdateClient){
            update1();
        }
        else if (event.getSource()==btndeleteClient){
            delete1();
        }
    }

    //********************************employes******************
    @FXML
    void handleButtonAction2(ActionEvent event) {
        if(event.getSource()== btninsertEmp){
            insertRecordE();
    /*        Notifications notificationBuilder = Notifications.create()
                    .title("insertion validée")
                    .text("Enregistre dans le list employées ")
                    .graphic(null)
                    .hideAfter(Duration.seconds(2))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            System.out.println("Clicked on Notification");
                        }
                    });
            notificationBuilder.showConfirm();*/
        } else if (event.getSource()==btnupdateEmp){
            updateE();
        }
        else if (event.getSource()==btndeleteEmp){
            deleteE();
        }
    }

    public Connection getConnection(){
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aero_space","root","");
            return conn;
        }catch (Exception ex ){
            System.out.println("Error :"+ex.getMessage());
            return null;
        }
    }
    public ObservableList<Books> getBooksList(){
        ObservableList<Books> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM books";
        Statement st;
        ResultSet rs;
        try {
            st= conn.createStatement();
            rs =st.executeQuery(query);
            Books books;
            while (rs.next()){
                books =new Books(rs.getInt("id"), rs.getString("title"),rs.getString("author"), rs.getInt("year"), rs.getInt("pages") );
                bookList.add(books);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bookList;
    }

    //********************************Facture Client
    public ObservableList<FactureE> getFactureEList(){
        ObservableList<FactureE> FactureList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM factures_clients";
        Statement st;
        ResultSet rs;
        try {
            st= conn.createStatement();
            rs =st.executeQuery(query);
            FactureE facture1;
            while (rs.next()){
                facture1 =new FactureE(rs.getInt("id_Fac"), rs.getString("nom_client"),rs.getDate("date_fac"), rs.getString("reglement_fac"), rs.getString("etat_fac"), rs.getInt("TVA_fac"), rs.getInt("remise_fac"), rs.getString("NB_fac"), rs.getInt("Totale_fac") );
                FactureList.add(facture1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return FactureList;
    }
    //********************Employes**********************
    public ObservableList<FactureEmp> getFactureEmpList(){
        ObservableList<FactureEmp> FactureList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM factures_employes";
        Statement st;
        ResultSet rs;
        try {
            st= conn.createStatement();
            rs =st.executeQuery(query);
            FactureEmp facture2;
            while (rs.next()){
                facture2 =new FactureEmp(rs.getString("id_Emp"), rs.getString("nom_Emp"),rs.getDate("date_fac"), rs.getString("reglement_fac"), rs.getString("etat_fac"), rs.getInt("TVA_fac"), rs.getInt("bonus_fac"), rs.getString("NB_fac"), rs.getInt("Totale_fac") );
                FactureList.add(facture2);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return FactureList;
    }
    //********************stat************************
    public ObservableList<Stat> getStatList(){
        ObservableList<Stat> statList = FXCollections.observableArrayList();
        Connection conn = getConnection();

        String query = "SELECT * FROM offvsres";
        Statement st;
        ResultSet rs;
        try {
            st= conn.createStatement();
            rs =st.executeQuery(query);
            Stat stats;
            while (rs.next()){
                stats =new Stat(rs.getInt("Vres"),rs.getInt("Voff") );
                statList.add(stats);
                res= stats.getVrs();
                off = stats.getVoff();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return statList;
    }
//**********************************************




    public void showBooks(){
        ObservableList<Books> list =getBooksList();
        colID.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
        colYear.setCellValueFactory(new PropertyValueFactory<Books,Integer>("year"));
        colPages.setCellValueFactory(new PropertyValueFactory<Books,Integer>("pages"));
        tvBooks.setItems(list);
        //******************Stat
        ObservableList<Stat> list1 =getStatList();
        valeurRes.setCellValueFactory(new PropertyValueFactory<Stat,Integer>("vrs"));
        valeurOff.setCellValueFactory(new PropertyValueFactory<Stat,Integer>("voff"));
        tab2.setItems(list1);
        //******************Client
        ObservableList<FactureE> list2 =getFactureEList();
        fac_id.setCellValueFactory(new PropertyValueFactory<FactureE,String>("id"));
        fac_client.setCellValueFactory(new PropertyValueFactory<FactureE,String>("nomClient"));
        fac_date.setCellValueFactory(new PropertyValueFactory<FactureE,Date>("date"));
        fac_reglement.setCellValueFactory(new PropertyValueFactory<FactureE,String>("Regliment"));
        fac_etat.setCellValueFactory(new PropertyValueFactory<FactureE,String>("EtatFacture"));
        fac_tva.setCellValueFactory(new PropertyValueFactory<FactureE,Integer>("TVA"));
        fac_remise.setCellValueFactory(new PropertyValueFactory<FactureE,Integer>("Remise"));
        fac_nb.setCellValueFactory(new PropertyValueFactory<FactureE,String>("NBFacture"));
        fac_totale.setCellValueFactory(new PropertyValueFactory<FactureE,Integer>("Totale"));

        table_facC.setItems(list2);
        //*******************Employes
        ObservableList<FactureEmp> list3 =getFactureEmpList();
        emp_id.setCellValueFactory(new PropertyValueFactory<FactureEmp,String>("id"));
        emp_client.setCellValueFactory(new PropertyValueFactory<FactureEmp,String>("nomEmp"));
        emp_date.setCellValueFactory(new PropertyValueFactory<FactureEmp,Date>("date"));
        emp_reglement.setCellValueFactory(new PropertyValueFactory<FactureEmp,String>("Regliment"));
        emp_etat.setCellValueFactory(new PropertyValueFactory<FactureEmp,String>("EtatFacture"));
        emp_tva.setCellValueFactory(new PropertyValueFactory<FactureEmp,Integer>("TVA"));
        emp_remise.setCellValueFactory(new PropertyValueFactory<FactureEmp,Integer>("Bonus"));
        emp_nb.setCellValueFactory(new PropertyValueFactory<FactureEmp,String>("NBFacture"));
        emp_totale.setCellValueFactory(new PropertyValueFactory<FactureEmp,Integer>("Totale"));

        table_Emp.setItems(list3);
        //**************************
        try {
            orders ();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            orders1 ();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        nbr_Emp2.setText(String.valueOf(res));
        nbr_Emp1.setText(String.valueOf(off));
        nbr_Emp3.setText(String.valueOf(Clien));
    }

    private void orders () throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT COUNT(custID) FROM Customer")
                .executeQuery();
        if (set.next()) {
            Clien = set.getInt(1);
        }
    }

    private void orders1 () throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT COUNT(idRes) FROM resevation")
                .executeQuery();
        if (set.next()) {
             res = set.getInt(1);
        }
    }

    private void orders2 () throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT COUNT(idoffre) FROM offre")
                .executeQuery();
        if (set.next()) {
            res = set.getInt(1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> listP = FXCollections.observableArrayList("Espece","Carte");

        ObservableList<String> listL = FXCollections.observableArrayList("Le paiement a ete effectue","factures archivee");
        comboEtat1.getSelectionModel().select("Etat");
        comboEtat1.setItems(listL);

        comboMode1.getSelectionModel().select("Mode");
        comboMode1.setItems(listP);
        //************************comboboxEmployes***********
        ObservableList<String> listEmp1 = FXCollections.observableArrayList("Espece","Carte");

        ObservableList<String> listEmp2 = FXCollections.observableArrayList("Le salaire a ete envoyé","pas en core");
        comboEtat1_Emp.getSelectionModel().select("Etat");
        comboEtat1_Emp.setItems(listEmp1);

        comboMode_Emp.getSelectionModel().select("Mode");
        comboMode_Emp.setItems(listEmp2);
        //****************************************************

        showBooks();
        search_facture();

    }
    //********************fonction Random*************
    public String random_id() {

        Random random = new Random();

        String generatedString = random.ints(6,65,90)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return generatedString;
    }
    //********************************************
    private void insertRecord(){
        String query ="INSERT INTO books VALUES("+ tfid.getText()+",'"+ tftitle.getText() +"','"+tfauthor.getText()+"',"+tfyear.getText()+","+tfpages.getText()+")";
        executeQuery(query);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Insert Success";
        String message =" Is Inserted";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        showBooks();
        search_facture();
    }
    private void update(){
        String query = "UPDATE books SET title='"+tftitle.getText()+"',author='"+tfauthor.getText()+"',year="+
                tfyear.getText()+",pages="+tfpages.getText()+" WHERE id = "+tfid.getText()+"";
        executeQuery(query);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Update Success";
        String message ="Facture Is Updated";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        showBooks();
        search_facture();
    }

    private void delete(){
        String query ="DELETE FROM books WHERE id ="+tfid.getText()+"";
        executeQuery(query);
        tray.notification.TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Delete Success";
        String message =" Is Deleted";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        showBooks();
        search_facture();
    }

    //*********************facture client ********************
    private void insertRecord1(){
        String query ="INSERT INTO factures_clients VALUES('"+ nameClient.getText() +"','"+ date_fac.getValue()+"','"+ModeReglement+"','"+Etat+"',"+TVA_fac.getText()+","+remise_fac.getText()+",'"+NB_fac.getText()+"',"+Totale.getText()+")";
        executeQuery(query);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Insert Success";
        String message ="Facture Is Inserted";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));

        showBooks();
        search_facture();
    }
    private void update1(){
        FactureE facture=table_facC.getSelectionModel().getSelectedItem();
        String query = "UPDATE factures_clients SET nom_client='"+nameClient.getText()+"',date_fac='"+date_fac.getValue()+"',reglement_fac='"+
                ModeReglement+"',etat_fac='"+Etat+"',TVA_fac="+TVA_fac.getText()+",remise_fac="+remise_fac.getText()+",NB_fac='"+NB_fac.getText()+"' ,Totale_fac="+Totale.getText()+" WHERE id_Fac = '"+facture.getId()+"'";
        executeQuery(query);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Update Success";
        String message ="Facture Is Updated";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        showBooks();
        search_facture();
    }

    private void delete1(){

        FactureE facture=table_facC.getSelectionModel().getSelectedItem();

        String query ="DELETE FROM factures_clients WHERE id_Fac ='"+facture.getId()+"'";
        executeQuery(query);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Delete Success";
        String message ="Facture Is Deleted";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        showBooks();
        search_facture();
    }

    //********************************************************

    //*********************facture Employes ********************
    private void insertRecordE(){
        String query ="INSERT INTO factures_employes VALUES('"+random_id()+"','"+ nameEmploye.getText() +"','"+ date_emp.getValue()+"'," +
                "'"+ModeReglementE+"','"+EtatE+"',"+TVA_Emp.getText()+","+bonus_Emp.getText()+",'"+NB_Emp.getText()+"',"+Totale_emp.getText()+")";
        executeQuery(query);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Insert Success";
        String message ="Facture Is Inserted";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        showBooks();
        search_facture();
    }
    private void updateE(){
        FactureEmp facture=table_Emp.getSelectionModel().getSelectedItem();
        String query = "UPDATE factures_employes SET nom_emp='"+nameEmploye.getText()+"',date_fac='"+date_emp.getValue()+"',reglement_fac='"+
                ModeReglementE+"',etat_fac='"+EtatE+"',TVA_fac="+TVA_Emp.getText()+",bonus_fac="+bonus_Emp.getText()+",NB_fac='"+NB_Emp.getText()+"' ,Totale_fac="+Totale_emp.getText()+" WHERE id_Emp = '"+facture.getId()+"'";
        executeQuery(query);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Update Success";
        String message ="Facture Is Updated";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        showBooks();
        search_facture();
    }

    private void deleteE(){

        FactureEmp facture=table_Emp.getSelectionModel().getSelectedItem();

        String query ="DELETE FROM factures_employes WHERE id_Emp ='"+facture.getId()+"'";
        executeQuery(query);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Delete Success";
        String message ="Facture Is Deleted";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        showBooks();
        search_facture();
    }

    //********************************************************

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st =conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @FXML
    private void mousebtn (MouseEvent event) {
        Books book = tvBooks.getSelectionModel().getSelectedItem();
        tfid.setText(""+book.getId());
        tftitle.setText(book.getTitle());
        tfauthor.setText(book.getAuthor());
        tfyear.setText(""+book.getYear());
        tfpages.setText(""+book.getPages());
    }

    @FXML
    void mousebtn2(MouseEvent event) {

        Stat stat = tab2.getSelectionModel().getSelectedItem();
        XYChart.Series<String,Number> series= new XYChart.Series<>();
        series.setName("Device comparison");
        series.getData().add(new XYChart.Data<>("reservation", stat.getVrs()));
        series.getData().add(new XYChart.Data<>("offer", stat.getVoff()));
        barcharts.getData().add(series);
    }
    @FXML
    void mousebtn3(MouseEvent event) {
        FactureE facture= table_facC.getSelectionModel().getSelectedItem();
        id_fac.setText(""+facture.getId());
        nameClient.setText(facture.getNomClient());
        //comboMode.setText(facture.getRegliment());
        //tfyear.setText(""+facture.getEtatFacture());
        TVA_fac.setText(""+facture.getTVA());
        remise_fac.setText(""+facture.getRemise());
        NB_fac.setText(""+facture.getNBFacture());
        Totale.setText(""+facture.getTotale());

    }

    ObservableList<Books> dataList;

    void search_facture()
    {
        colID.setCellValueFactory(new PropertyValueFactory<Books,Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<Books,String>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Books,String>("author"));
        colYear.setCellValueFactory(new PropertyValueFactory<Books,Integer>("year"));
        colPages.setCellValueFactory(new PropertyValueFactory<Books,Integer>("pages"));

        dataList = getBooksList();
        tvBooks.setItems(dataList);

        FilteredList<Books> filteredList = new FilteredList<>(dataList , b -> true );
        filtrage.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filteredList.setPredicate(facture ->
            {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();

                if(String.valueOf(facture.getId()).indexOf(lowerCaseFilter) !=-1) {
                    return true;

                }else if (facture.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }else if(facture.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }else if (String.valueOf(facture.getYear()).indexOf(lowerCaseFilter) !=-1){
                    return true;

                }else if(String.valueOf(facture.getPages()).indexOf(lowerCaseFilter) !=-1)
                    return true;

                else
                    return false;


            });
        }));

        SortedList<Books> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tvBooks.comparatorProperty());
        tvBooks.setItems(sortedList);

    }



    //////////pdf

    @FXML
    void PDF(ActionEvent event) throws DocumentException {
        Document doc = new Document() ;
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Nom\\Desktop\\test.pdf"));
            doc.open();

            Paragraph p = new Paragraph("*Facture* ");
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);


            Paragraph contenu = new Paragraph(preparerMsg());
            doc.add(contenu) ;
            doc.close();


            Desktop.getDesktop().open(new File("C:\\Users\\Nom\\Desktop\\test.pdf"));
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Aymen.class.getName()).log(Level.SEVERE, null, ex);
        }catch (BadElementException ex) {
            Logger.getLogger(Aymen.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(Aymen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    private String preparerMsg() {
        FactureE f = table_facC.getSelectionModel().getSelectedItem();
        String ch = " \n \n"
                + "Id Facture: " + f.getId()  +  "\n \n"

                + "Nom Client: " + f.getNomClient() + "\n \n"
                + "Date Facture: " + f.getDate()  +  "\n \n"

                + "Reglement: " + f.getRegliment()  +  "\n \n"
                + "Etat : " + f.getEtatFacture()+ "\n \n "
                + "TVA: " + f.getTVA()  +  "\n \n"
                + "Remise: " + f.getRemise()  +  "\n \n"
                + "Detail Facture: " + f.getNBFacture()  +  "\n \n"
                + "Totale: " + f.getTotale()  +  "\n \n" ;


        return ch;
    }

    /*******************************************/





    /********************************************/

}

