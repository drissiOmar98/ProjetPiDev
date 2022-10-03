package GestionOffre.GUI;

import GestionOffre.connection.DataBase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
//import sample.GestionReservation.Controller.ReservationOffres;
import static GestionOffre.connection.DataBase.conDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import GestionOffre.models.Offre;

import javafx.scene.chart.*;
import GestionOffre.models.Favoris;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormatSymbols;
import java.util.*;
import java.util.List;

public class interfaceCont implements Initializable {


    public interfaceCont() {
        connection = (Connection) DataBase.conDB();
    }

    /**
     * Initializes the controller class.
     */
    PreparedStatement preparedStatement;
    Connection connection;
    ObservableList<Offre> dataList;
        List<String> file;
        List<String> fileVid;
        String pathImg;
        String pathVideo;


    String remise = "";

    @FXML
    private AnchorPane anchorepane;


    @FXML
    private TableColumn<Offre, String> id_image;


    @FXML
    private TableColumn<Offre, Integer> colPrix;



    @FXML
    private TableColumn<Offre, String> id_video;


    @FXML
    private RadioButton radiobtn1;

    @FXML
    private RadioButton radiobtn2;

    @FXML
    private RadioButton radiobtn3;


    @FXML
    private TextField idreservation;

    @FXML
    private TextField Prix;

    @FXML
    private TableColumn<Favoris, Integer> vidoffre;

    @FXML
    private javafx.scene.control.TableView<Offre> TableView;

    @FXML
    private TableColumn<Offre, Integer> IdOffreCol;

    @FXML
    private TableColumn<Offre, Integer> IdReservationCol;

    @FXML
    private TableColumn<Offre, Date> DateVALCOL;

    @FXML
    private TableColumn<Offre,Integer > TAUXCOL;
    @FXML
    private TableColumn<Offre, String> DescColl;

    @FXML
    private DatePicker Datevalidite;

    @FXML
    private TextField descriptionOffre;

    @FXML
    private ImageView QRcode;

    @FXML
    private Label IdRemise;
    @FXML
    private TableColumn<Offre, String> TitreColl;
    @FXML
    private TextField IdTitre;
    @FXML
    private TextField RechercheID;




    @FXML
    private AreaChart<String, Number> AreaChart;
    @FXML
    private TableView<Favoris> tab2;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private TableColumn<Favoris, Integer> VLike;

    @FXML
    private TableColumn<Favoris, Integer> VComment;
    @FXML
    private TableColumn<Favoris, Date> vdatelike;

    @FXML
    private TableColumn<Favoris, Date> vdatecomment;

    int like;
    int com;

    String idR;

    ObservableList<Favoris> List ;

    public void setStatData(List<Favoris> Stats) {

        // Count the number of people having their birthday in a specific month.
        int[] monthCounter = new int[12];
        for ( Favoris s : Stats) {
            int month = s.getDatelike().getMonthValue()- 1;

            monthCounter[month]+=s.getVL();
           // System.out.println(month);
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);
    }

    public ObservableList<Favoris> getStatList(){
       ObservableList<Favoris> statList = FXCollections.observableArrayList();
        Connection conn = DataBase.conDB();
        String query = "SELECT * FROM fav";
        Statement st;
        ResultSet rs;
        try {
            st= conn.createStatement();
            rs =st.executeQuery(query);
            Favoris stats;
            while (rs.next()){
                stats =new Favoris(rs.getString("idFav"),rs.getString("idoffre"),rs.getInt("VL"),rs.getDate("datelike").toLocalDate());
                statList.add(stats);
                like= rs.getInt("VL");
               // System.out.println(like);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return statList;


    }



    public String random_id() {

        Random random = new Random();

        String generatedString = random.ints(6,65,90)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return generatedString;
    }

List<File> f = new ArrayList<>();
        @FXML
        void btnfile(ActionEvent event) {
/*
            FileChooser fc= new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",file));
            Stage stage=(Stage)anchorepane.getScene().getWindow();

           List<File> list= fc.showOpenMultipleDialog(stage);

            if (list != null)
            {
                for (File file : list)
                {
                    f.add(file);

                }
                pathImg= f.toString();
                System.out.println(pathImg);

            }
*/
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", file));
            File f = fc.showOpenDialog(null);

            if(f != null) {

                pathImg = f.getAbsolutePath();

            }

            }
    @FXML
    void btnvideo(ActionEvent event) {
        FileChooser fc= new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files",fileVid));
        File f = fc.showOpenDialog(null);

        if (f != null)
        {
            pathVideo=f.toString();
        }

    }


    @FXML
    String btnAdd(ActionEvent event) {
        //String query = "insert into offre values("+idfieled.getText()+","+idreservation.getText()+",'"+java.sql.Date.valueOf(Datevalidite.getValue())+"','"+remise+"','"+descriptionOffre.getText() +"','"+pathImg +"','"+pathVideo+"')";
        //executeQuery(query);
        //showOffres();
        try{
            String st="insert into offre (id_res,nom,date_validite,taux_de_remise,description,path,Path_video,prix) values(?,?,?,?,?,?,?,?)";
            preparedStatement=(PreparedStatement)connection.prepareStatement(st);
            preparedStatement.setString(2,idreservation.getText());
            preparedStatement.setString(3,IdTitre.getText());
            preparedStatement.setDate(4, Date.valueOf(Datevalidite.getValue()));
            preparedStatement.setString(5,remise);
            preparedStatement.setString(6,descriptionOffre.getText());
            preparedStatement.setString(7,pathImg);
            preparedStatement.setString(8,pathVideo);
            preparedStatement.setInt(9, Integer.parseInt((Prix.getText())));




            preparedStatement.executeUpdate();
            preparedStatement.close();
           // DictionaryBasedBreakIterator connlabel = null;
            //connlabel.setText("Added Successfuly");
            showOffres();
            search_offres();
            Notification("Success","Insertion Avec Succée Merci","SUCCESS");
            return"Success";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Notification("Failed ! ","Sorry :( ","ERROR");
            return "Exception";
        }


    }

    @FXML
    void btnEdit(ActionEvent event) {
        Offre offre = TableView.getSelectionModel().getSelectedItem();

        //String query = "UPDATE offre SET idoffre="+idfieled.getText()+",id_reservation="+idreservation.getText()+",date_validite='"+Datevalidite.getValue()+"',taux_de_remise='"++"' WHERE id="+idfieled.getText()+"";
        //executeQuery(query);
        //showOffres();
        try{
            String st="UPDATE offre set nom=?,date_validite=?,taux_de_remise=?,description=?,path=?,Path_video=?,prix=? WHERE id=?";
            preparedStatement=(PreparedStatement)connection.prepareStatement(st);
            preparedStatement.setString(1,IdTitre.getText());
            preparedStatement.setDate(2, Date.valueOf(Datevalidite.getValue()));
            preparedStatement.setString(3,remise);
            preparedStatement.setString(4,descriptionOffre.getText());
            preparedStatement.setString(5,pathImg);
            preparedStatement.setString(6,pathVideo);
            preparedStatement.setInt(7, Integer.parseInt(String.valueOf(Prix.getText())));

            preparedStatement.setInt(8,offre.getIdoffre());




            preparedStatement.executeUpdate();
            preparedStatement.close();
            showOffres();
            search_offres();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


    }



    @FXML
    void btnDell(ActionEvent event) {
        Offre offre = TableView.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM offre WHERE id='"+offre.getIdoffre()+"'";
        executeQuery(query);
        showOffres();
        search_offres();
    }





    private void showOffres() {

        ObservableList<Offre> list = getVolsList();

        IdOffreCol.setCellValueFactory ( new PropertyValueFactory<Offre,Integer>("id") );
        IdReservationCol.setCellValueFactory(new PropertyValueFactory<Offre,Integer>("id_res"));
        TitreColl.setCellValueFactory(new PropertyValueFactory<Offre,String>("nom"));
        DateVALCOL.setCellValueFactory(new PropertyValueFactory<Offre,Date>("date_validite"));
        TAUXCOL.setCellValueFactory(new PropertyValueFactory<Offre,Integer>("taux_de_remise"));
        DescColl.setCellValueFactory(new PropertyValueFactory<Offre,String>("description"));
        id_image.setCellValueFactory(new PropertyValueFactory<Offre,String>("path"));
        id_video.setCellValueFactory(new PropertyValueFactory<Offre,String>("Path_video"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("prix"));


        //**********
        ObservableList<Favoris> list1 =getStatList();
        VLike.setCellValueFactory(new PropertyValueFactory<Favoris,Integer>("VL"));

        vdatelike.setCellValueFactory(new PropertyValueFactory<Favoris,Date>("datelike"));
        vidoffre.setCellValueFactory(new PropertyValueFactory<Favoris,Integer>("id"));


        tab2.setItems(list1);


        //***************



        TableView.setItems(list);


    }

    @FXML
    void handelMouseClick(MouseEvent event) {
            Offre offre =TableView.getSelectionModel().getSelectedItem();
        //idfieled.setText(""+offre.getIdoffre());
        idreservation.setText(""+offre.getId_reservation());
        descriptionOffre.setText(offre.getDescription());
        IdRemise.isCache();
        IdRemise.setText("taux de remise de cette offre est: "+ String.valueOf(offre.getTaux_de_remise()));
        IdTitre.setText(String.valueOf(offre.getTitre()));


        qrcode();
    }



    private ObservableList<Offre> getVolsList() {

        ObservableList<Offre> offreList = FXCollections.observableArrayList();

        String query = "SELECT * FROM offre";

        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Offre offre;
            while(rs.next()) {
                offre = new Offre(rs.getInt("id"),rs.getInt("id_res"),rs.getDate("date_validite"),rs.getString("taux_de_remise"),rs.getString("description"),rs.getString("path"),rs.getString("Path_video"),rs.getString("nom"),rs.getInt("prix"));
                offreList.add(offre);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return offreList;
    }

    private void executeQuery(String query) {

        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    //ReservationOffres.wew();
    //String idt = ReservationOffres.iddddd();


      //  System.out.println("id reservation est !!!!!!!!!! "+idt +" ");
       // idreservation.setText(idt);


    //*****statistique

        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));

        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);

        List=getStatList();
        setStatData(List);

        //************
        showOffres();
        search_offres();
        file = new ArrayList<>();
        file.add("*.doc");
        file.add("*.png");
        file.add("*.jpeg");
        file.add("*.jpg");

        fileVid = new ArrayList<>();
        fileVid.add("*.mp4");
        fileVid.add("*.mp3");
        fileVid.add("*.wmv");
        fileVid.add("*.avi");
        fileVid.add("*.mkv");
        fileVid.add("*.flv");


    }






    public void rdselect(ActionEvent actionEvent) {
          final ToggleGroup group = new ToggleGroup();


        if (radiobtn1.isSelected()) {
            remise = radiobtn1.getText() + "\n";

        }
       else if (radiobtn2.isSelected()) {
            remise = radiobtn2.getText() + "\n";
        }
       else if(radiobtn3.isSelected()){

            remise = radiobtn3.getText() + "\n";
        }
       radiobtn1.setToggleGroup(group);
       radiobtn1.setSelected(true);
        radiobtn2.setToggleGroup(group);
        radiobtn3.setToggleGroup(group);

    }
    protected void qrcode()
    {
        Offre offre = TableView.getSelectionModel().getSelectedItem();




        try {
            final BufferedImage bi = generateData(offre.getIdoffre() + " " + offre.getDate_validite() + " " +offre.getId_reservation());
            WritableImage img = SwingFXUtils.toFXImage(bi, null);
            QRcode.setFitWidth(img.getWidth());
            QRcode.setFitHeight(img.getHeight());
            QRcode.setImage(img);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private BufferedImage generateData(final String url) throws IOException, WriterException {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 153, 124);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            BufferedImage bi = MatrixToImageWriter.toBufferedImage(bitMatrix);
            return bi;
        } catch (Exception ex)
        {
            throw ex;
        }
    }

    void search_offres()
    {


        IdOffreCol.setCellValueFactory(new PropertyValueFactory<Offre,Integer>("id"));
        IdReservationCol.setCellValueFactory(new PropertyValueFactory<Offre,Integer>("id_res"));
        DateVALCOL.setCellValueFactory(new PropertyValueFactory<Offre,Date>("date_validite"));
        TAUXCOL.setCellValueFactory(new PropertyValueFactory<Offre,Integer>("taux_de_remise"));
        DescColl.setCellValueFactory(new PropertyValueFactory<Offre,String>("description"));
        id_image.setCellValueFactory(new PropertyValueFactory<Offre, String>("path"));
        id_video.setCellValueFactory(new PropertyValueFactory<Offre, String>("Path_video"));
        TitreColl.setCellValueFactory(new PropertyValueFactory<Offre, String>("nom"));


        dataList = getVolsList();
        TableView.setItems(dataList);

        FilteredList<Offre> filteredList = new FilteredList<>(dataList , b -> true );
        RechercheID.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filteredList.setPredicate(offre->
            {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;

                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(String.valueOf(offre.getIdoffre()).indexOf(lowerCaseFilter) !=-1) {
                    return true;

                }else if (offre.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;

                }else if(String.valueOf(offre.getId_reservation()).indexOf(lowerCaseFilter) !=-1 ) {
                    return true;

                }else if (String.valueOf(offre.getTaux_de_remise()).indexOf(lowerCaseFilter) !=-1){
                    return true;

                }else if(String.valueOf(offre.getDate_validite()).indexOf(lowerCaseFilter) !=-1) {
                    return true;

                }
                else
                    return false;


            });
        }));

        SortedList<Offre> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(TableView.comparatorProperty());
        TableView.setItems(sortedList);


    }



    public void OnMouseClicked(MouseEvent mouseEvent) {

    }




    @FXML
    void btnRes(ActionEvent event) throws IOException {
        Stage stage = (Stage) TableView.getScene().getWindow();
        stage.close();



        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/GestionReservation/views/OffresReservation.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void Notification(String Title,String Message,String icon)
    {


        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;



        tray.setNotificationType(NotificationType.valueOf(icon));

        tray.setAnimationType(type);
        tray.setTitle(Title);
        tray.setMessage(Message);
        tray.showAndDismiss(Duration.millis(3000));

    }



}
