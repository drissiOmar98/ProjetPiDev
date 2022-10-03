package Omar.ServiceOm;

import Omar.entityOm.Events;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.net.URL;
import java.sql.*;

import java.util.Date;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class FXMLEventsController implements Initializable {






    @FXML
    private Text Location;
    @FXML
    void btnweather(ActionEvent event) throws Exception {
        Stage stage = (Stage) Location.getScene().getWindow();
        stage.close();


        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Omar/WeatherAPI/primary.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();



    }

    @FXML
    void btnrun(ActionEvent event) throws Exception {
        Stage stage = (Stage) Location.getScene().getWindow();
        stage.close();


        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Omar/ApiSmS/smsapi.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }



    @FXML
    private TableView<Events> TableView;

    @FXML
    private TableColumn<Events, String> availableColumn;

    @FXML
    private Label connlabel;

    @FXML
    private TableColumn<Events, Date> dateColumn;
    @FXML
    private TableColumn<Events, String> priceColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private DatePicker fielddate;

    @FXML
    private TextField filterfield;
    @FXML
    private TextField textprice;

    @FXML
    private TableColumn<Events, String> idColumn;

    @FXML
    private Button insertButton;

    @FXML
    private TableColumn<Events, String> nameColumn;
    @FXML
    private TableColumn<Events, String> locationColumn;

    @FXML
    private TableColumn<Events, String> periodColumn;

    @FXML
    private Button quitbtn;

    @FXML
    private TextField textavailable;

    @FXML
    private TextField textid;

    @FXML
    private TextField textlocation;

    @FXML
    private TextField textname;

    @FXML
    private TextField textperiod;

    @FXML
    private Button updateButton;


    @FXML
    void btnsend(ActionEvent event) {

    }

    @FXML
    void btnquit(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) quitbtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    ObservableList<Events> dataList;

    void search_event()
    {


        idColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("idevent"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("name"));
        periodColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("period"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Events, Date>("date"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("location"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("available"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("price"));



        dataList = getEventsList();
        TableView.setItems(dataList);

        FilteredList<Events> filteredList = new FilteredList<>(dataList , b -> true );
        filterfield.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filteredList.setPredicate(evenment->
            {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;

                }


                String lowerCaseFilter = newValue.toLowerCase();

                if(evenment.getIdevent().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                }else if (evenment.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }else if(evenment.getAvailable().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }else if (String.valueOf(evenment.getDate()).indexOf(lowerCaseFilter) !=-1){
                    return true;

                }else if (evenment.getLocation().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
                    return true;

                }else if(evenment.getPeriod().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
                    return true;
                }else if(String.valueOf(evenment.getPrice()).indexOf(lowerCaseFilter) !=-1) {
                    return true;
                } else
                    return false;


            });
        }));

        SortedList<Events> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(TableView.comparatorProperty());
        System.out.println(sortedList.toString());
        TableView.setItems(sortedList);


    }






    /*@FXML
    void deleteButton(ActionEvent event) {
        String query="DELETE FROM events WHERE idevent ='"+textid.getText()+ "'";
        executeQuery(query);
        showEvents();

    }*/
    @FXML
    void deleteButton(ActionEvent event) {

        Events events = TableView.getSelectionModel().getSelectedItem();


        String query = "DELETE FROM events WHERE idevent='"+events.getIdevent()+"'";
        executeQuery(query);
        showEvents();
        search_event();
        Notification("Success","suppression avec succès");

    }

    @FXML
    void handleMouseClick(MouseEvent event) {

    }
    @FXML
    void insertButton(ActionEvent event) {
        String query ="INSERT INTO events VALUES ( '"+textid.getText()+"','"+textname.getText()+"','"+textperiod.getText()+"','"+textlocation.getText()+"','"+ java.sql.Date.valueOf(fielddate.getValue())+"','"+textavailable.getText()+"','"+textprice.getText()+"')";
        executeQuery(query);
        showEvents();
        Notification("Success","insertion avec succés");


        /*Notifications notificationBuilder= Notifications.create()
                .title("Download complete")
                .text("Saved to home ")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_LEFT)
                .onAction( new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        System.out.println("clicked on notif");
                    }
                });
        notificationBuilder.showConfirm();
       // notificationBuilder.showConfirm();*/

    }
    /*@FXML
    void updateButton(ActionEvent event) {
       String  query="UPDATE events SET name= "+textname.getText()+",period='"+textperiod.getText()+"',location="+textlocation.getText()+"',date="+java.sql.Date.valueOf(fielddate.getValue())+"',available="+textavailable.getText()+"' WHERE idevent = "+textid.getText()+"";
        executeQuery(query);
        showEvents();

    }*/
    PreparedStatement preparedStatement;
    Connection conn;
    @FXML
    void updateButton(ActionEvent event) {

        Events events = TableView.getSelectionModel().getSelectedItem();

         String query = "UPDATE events SET name ='"+textname.getText()+"',period='"+textperiod.getText()+"',location='"+textlocation.getText()+"',date='"+java.sql.Date.valueOf(fielddate.getValue())+"',available='"+textavailable.getText()+"' WHERE idevent='"+events.getIdevent()+"'";
          executeQuery(query);
          showEvents();
          search_event();

/*
        try {
            String query = "UPDATE events set period=? ,  name=? , location=? , date=? , available=? WHERE idevent=?";

            preparedStatement = (PreparedStatement) conn.prepareStatement(query);

            preparedStatement.setString(1, "jhgjh");
            preparedStatement.setString(2, "ytryt");
            preparedStatement.setString(3, "hgfhgf");
            preparedStatement.setDate(4, java.sql.Date.valueOf(fielddate.getValue()));
            preparedStatement.setString(5,  "vbnb");
        //    preparedStatement.setInt(6,  Integer.valueOf(textprice.getText()) );
            preparedStatement.setString(6, events.getIdevent());

            System.out.println(query);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connlabel.setText("Added Successfully");
           // showEvents();
           // search_event();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        //showEvents();
        //search_event();

*/



    }









    public void initialize(URL url , ResourceBundle rb){
        search_event();

    }
    public Connection getConnection(){
        Connection conn;
        try{
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/aero_space","root","");
            return conn;
        }catch (Exception ex){
            System.out.println("Error :"+ex.getMessage());
            return null;
        }
    }
    public ObservableList<Events> getEventsList(){
        ObservableList<Events> eventList= FXCollections.observableArrayList();
        Connection conn =getConnection();
        String query ="SELECT * FROM events";
        Statement st;
        ResultSet rs;
        try{
            st=conn.createStatement();
            rs=st.executeQuery(query);
            Events events;
            while (rs.next()){
                events=new Events(rs.getString("idevent"),rs.getString("name"),rs.getString("period"),
                rs.getString("location"),rs.getDate("date"),rs.getString("available"),rs.getInt("price"));
                eventList.add(events);

            }
        }catch (Exception ex ){
            ex.printStackTrace();
        }return eventList;
    }
    public void showEvents(){
        ObservableList<Events> list = getEventsList();
        idColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("idevent"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("name"));
        periodColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("period"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Events, Date>("date"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("location"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("available"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("price"));
        TableView.setItems(list);

    }

    public void showEvents2( ObservableList<Events> list) {

        idColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("idevent"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("name"));
        periodColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("period"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Events, Date>("date"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Events,String>("location"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("available"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Events, String>("price"));
        TableView.setItems(list);

    }



    private void executeQuery(String query){
        Connection conn = getConnection();
        Statement st ;
        try{
            st= conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    /*
    @FXML
    private void handleMouseClick(MouseEvent event){
        Omar.entityOm.Events events = TableView.getSelectionModel().getSelectedItem();
        textid.setText(""+events.getId());
        textname.setText(events.getName());
        textperiod.setText(""+events.getPeriod());
        textlocation.setText(""+events.getLocation());
        fielddate.set(""+events.getDate());
        textavailable.setText(""+events.getAvailable());

    }*/

    public void Notification(String Title,String Message)
    {


        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;


        tray.setAnimationType(type);
        tray.setTitle(Title);
        tray.setMessage(Message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));

    }

    ObservableList<Events> trieListeEvent;

    void trieparEvents()
    {
        trieListeEvent = getEventsList();
        List<Events> r = trieListeEvent.stream().sorted(Comparator.comparingInt(Events::getPrice).reversed()).collect(Collectors.toList());
        System.out.println(r);


    }

    public void btntrier(ActionEvent event) {
        trieparEvents();
    }
}
