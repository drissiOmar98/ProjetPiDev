package Nourhene.ServiceNo;

import Aymen.ServiceAy.Aymen;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import Nourhene.entityNo.Stat1;
import Nourhene.entityNo.Vol;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Nourhene.Maps.MapController;
import Nourhene.Maps.WebViewCaptureMap;
import Nourhene.entityNo.Moyen;
import Nourhene.entityNo.Stat1;
import Nourhene.entityNo.Vol;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;


import Nourhene.entityNo.Moyen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Nourhene <file> implements Initializable {

    @FXML
    private TextField ref;

    @FXML
    private TextField Prix;

    @FXML
    private TextField Prix1;

    PreparedStatement preparedStatement;
    Connection connection;

    @FXML
    private TextField dispo;

    @FXML
    private TextField driver;

    @FXML
    private Button insert;

    @FXML
    private TableView<Moyen> tbmoyen;

    @FXML
    private TableColumn<Moyen, Integer> colref;

    @FXML
    private TableColumn<Moyen, String> coltype;

    @FXML
    private TableColumn<Moyen, String> coldispo;

    @FXML
    private TableColumn<Moyen, String> colchauff;

    @FXML
    private TableColumn<Moyen, Integer> colPrix1;


    @FXML
    private TextField departvol;

    @FXML
    private TextField arriver;

    @FXML
    private Button delete;

    @FXML
    private Button update;

    @FXML
    private Button image;

    @FXML
    private TextField rech;

    @FXML
    private TextField numv;

    @FXML
    private TextField nomv;

    @FXML
    private DatePicker dated;

    @FXML
    private DatePicker datea;

    @FXML
    private Button localiser;

    @FXML
    private TextField chauffeur;

    @FXML
    private TableView<Vol> tbvol;

    @FXML
    private TableColumn<Vol, String> colnumv;

    @FXML
    private TableColumn<Vol, String> colnomv;

    @FXML
    private TableColumn<Vol, Date> coldated;

    @FXML
    private TableColumn<Vol, String> coldatea;

    @FXML
    private TableColumn<Vol, String> colpilote;

    @FXML
    private TableColumn<Vol, String> coldepart;

    @FXML
    private TableColumn<Vol, String> colarriver;

    @FXML
    private TableColumn<Vol, Integer> colPrix;

    @FXML
    private TextField rechv;

    @FXML
    private Button insertv;

    @FXML
    private Button deletev;

    @FXML
    private Button updatev;

    @FXML
    private ComboBox typet;


    @FXML
    private Text location;

    @FXML
    private BarChart<String, Number> barcharts;

    @FXML
    private TableView<Stat1> tab2;

    @FXML
    private TableColumn<Stat1, Integer> referencestat;

    @FXML
    private TableColumn<Stat1, Integer> nbrpanne;

    @FXML
    void combot(ActionEvent event) {
        combobtype = typet.getSelectionModel().getSelectedItem().toString();

    }

    String combobtype;
    String map_path;
    private static String img_path;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showMoyen();
        search_transport();
        showVol();
        search_vol();

        ObservableList<String> listP = FXCollections.observableArrayList("Bus", "Car", "Taxi");

        typet.getSelectionModel().select("Type");
        typet.setItems(listP);
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

    public ObservableList<Moyen> getMoyenList() {
        ObservableList<Moyen> moyenList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM transport";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Moyen moyen;
            while (rs.next()) {
                moyen = new Moyen(rs.getInt("reference"), rs.getString("typee"), rs.getString("availability"), rs.getString("driver"), rs.getString("prix"));
                moyenList.add(moyen);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return moyenList;
    }


    public void showMoyen() {
        ObservableList<Moyen> List = getMoyenList();
        colref.setCellValueFactory(new PropertyValueFactory<Moyen, Integer>("id"));

        coltype.setCellValueFactory(new PropertyValueFactory<Moyen, String>("type"));

        coldispo.setCellValueFactory(new PropertyValueFactory<Moyen, String>("disponibilite"));

        colchauff.setCellValueFactory(new PropertyValueFactory<Moyen, String>("chauffeur"));

        colPrix1.setCellValueFactory(new PropertyValueFactory<Moyen, Integer>("prix1"));

        tbmoyen.setItems(List);

        //stat
        ObservableList<Stat1> List1 =getStatList();
        referencestat.setCellValueFactory(new PropertyValueFactory<Stat1,Integer>("referencestat"));
        nbrpanne.setCellValueFactory(new PropertyValueFactory<Stat1,Integer>("nbrpanne"));
        tab2.setItems(List1);

    }


    public void btnsupp(ActionEvent actionEvent) throws InterruptedException {
        //  String query = "DELETE FROM transport WHERE reference =" + ref.getText() ;
        // executeQuery(query);
        // showMoyen();

        Moyen moyen = tbmoyen.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM transport WHERE reference='" + moyen.getId() + "'";
        executeQuery(query);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Delete Success";
        String message ="Transport Is Deleted";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        showMoyen();
        search_transport();
        search_vol();

        //appel animation

/*
        Stage stageclose = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stageclose.close();
        FXMLLoader loader = new FXMLLoader ();

        try {
            loader.setLocation(getClass().getResource("/sample/app/app.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));

            stage.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event  -> {
                stage.close();
                FXMLLoader loader1 = new FXMLLoader ();

                loader1.setLocation(getClass().getResource("transport.fxml"));
                try {
                    loader1.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent parent1 = loader1.getRoot();
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(parent1));

                stage1.show();
            });
            delay.play();

        //TimeUnit.SECONDS.sleep(5);



        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
*/

    }


    public String btnmodi(javafx.event.ActionEvent actionEvent) {

        try {
            String st = "UPDATE  transport SET typee =?,availability =?, driver=? , prix=? WHERE reference = ? ";
            Connection conn = getConnection();
            preparedStatement = (PreparedStatement) conn.prepareStatement(st);

            // preparedStatement.setString(1, random_id());

            preparedStatement.setString(1, combobtype);
            preparedStatement.setString(2, dispo.getText());
            preparedStatement.setString(3, driver.getText());
            preparedStatement.setString(4, ref.getText());
            preparedStatement.setInt(5, Integer.parseInt(ref.getText()));

            preparedStatement.executeUpdate();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Update Success";
            String message ="Transport Is Updated";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
            preparedStatement.close();
            // connlabel.setTextFill(Color.GREEN);
            // connlabel.setText("Added Successfully");
            showMoyen();
            search_transport();

            return "Success";


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // connlabel.setTextFill(Color.TOMATO);
            //  connlabel.setText(ex.getMessage());
            return "Exception";

        }


    }

    @FXML
    private void insertRecord() {
        StringBuilder errors = new StringBuilder();
        if(ref.getText().trim().isEmpty()){
            errors.append("- Please enter the Reference.\n");
        }
        if(dispo.getText().trim().isEmpty()){
            errors.append("- Please enter the Disponibility.\n");
        }
        if(driver.getText().trim().isEmpty()){
            errors.append("- Please enter the full name.\n");
        }
        if(typet.getValue().toString().equals("Type")){
            errors.append("- Please enter the type of Transport.\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty");
            alert.setContentText(errors.toString());

            alert.showAndWait();
        } else {

            try {

                String st = "INSERT INTO transport (reference,typee,availability,driver,prix)VALUES(?,?,?,?,?)";
                Connection conn = getConnection();
                preparedStatement = (PreparedStatement) conn.prepareStatement(st);

                preparedStatement.setString(1, ref.getText());
                preparedStatement.setString(2, combobtype);
                preparedStatement.setString(3, dispo.getText());
                preparedStatement.setString(4, driver.getText());
                preparedStatement.setInt(5, Integer.parseInt(Prix1.getText()));

                preparedStatement.executeUpdate();
                preparedStatement.close();

                showMoyen();
                search_transport();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    private void random_id() {
    }


    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
   /* file = new ArrayList<>();
        file.("*.doc");
        file.add("*.png");
        file.add("*.jpeg");
        file.add("*.jpg");
    public void btnimage(javafx.event.ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", file));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            String pathImg = f.toString();
        }
    }*/

    @FXML
    void handelMouseClick(MouseEvent event) {
        Moyen moyen = tbmoyen.getSelectionModel().getSelectedItem();
        ref.setText("" + moyen.getId());
        // typet.com(moyen.getType());
        dispo.setText(moyen.getDisponibilite());
        driver.setText(moyen.getChauffeur());
        Prix1.setText((moyen.getPrix1()));

    }

    void search_transport() {
        colref.setCellValueFactory(new PropertyValueFactory<Moyen, Integer>("id"));

        coltype.setCellValueFactory(new PropertyValueFactory<Moyen, String>("type"));

        coldispo.setCellValueFactory(new PropertyValueFactory<Moyen, String>("disponibilite"));

        colchauff.setCellValueFactory(new PropertyValueFactory<Moyen, String>("chauffeur"));

        dataList = getMoyenList();
        tbmoyen.setItems(dataList);

        FilteredList<Moyen> filteredList = new FilteredList<>(dataList, b -> true);
        rech.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filteredList.setPredicate(mtransport ->
            {
                if (newValue == null || newValue.isEmpty()) {
                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(mtransport.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (mtransport.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (mtransport.getDisponibilite().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (mtransport.getChauffeur().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;


            });
        }));

        SortedList<Moyen> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tbmoyen.comparatorProperty());
        tbmoyen.setItems(sortedList);


    }

    ObservableList<Moyen> dataList;


    // ******************* GESTION DE VOL **********************


    public ObservableList<Vol> getVolList() {
        ObservableList<Vol> volList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM vol";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Vol vol;
            while (rs.next()) {
                vol = new Vol(rs.getInt("id"),rs.getString("nom"), rs.getString("apartir"), rs.getString("vers"), rs.getDate("dated"), rs.getString("path"), rs.getString("chauffeur"), rs.getString("prix"));
                volList.add(vol);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return volList;
    }

    public void showVol() {
        ObservableList<Vol> List = getVolList();
        colnumv.setCellValueFactory(new PropertyValueFactory<Vol, String>("numv"));

        colnomv.setCellValueFactory(new PropertyValueFactory<Vol, String>("nomv"));

        coldated.setCellValueFactory(new PropertyValueFactory<Vol, Date>("dated"));

        coldatea.setCellValueFactory(new PropertyValueFactory<Vol, String>("datea"));




        colpilote.setCellValueFactory(new PropertyValueFactory<Vol, String>("chauffeure"));

        coldepart.setCellValueFactory(new PropertyValueFactory<Vol, String>("depart"));

        colarriver.setCellValueFactory(new PropertyValueFactory<Vol, String>("arriver"));

        colPrix.setCellValueFactory(new PropertyValueFactory<Vol, Integer>("prix"));




        tbvol.setItems(List);
    }

    @FXML
    void btn_ajoutv(ActionEvent event) {

        StringBuilder errors = new StringBuilder();
        if(nomv.getText().trim().isEmpty()){
            errors.append("- Please enter the Name.\n");
        }
        if(numv.getText().trim().isEmpty()){
            errors.append("- Please enter the Number.\n");
        }
        if(dated.getValue()==null){
            errors.append("- Please enter the Date.\n");
        }
        if(datea.getValue()== null){
            errors.append("- Please enter the Date.\n");
        }
        if(chauffeur.getText().trim().isEmpty()){
            errors.append("- Please enter the full name.\n");
        }
        if(departvol.getText().trim().isEmpty()){
            errors.append("- Please enter the destination.\n");
        }
        if(arriver.getText().trim().isEmpty()){
            errors.append("- Please enter the destination.\n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Required Fields Empty");
            alert.setContentText(errors.toString());

            alert.showAndWait();
        }
        else {
            try {
                String st = "INSERT INTO vol (nom,id,dated,path,chauffeur,apartir,vers,prix)VALUES(?,?,?,?,?,?,?,?)";
                Connection conn = getConnection();
                preparedStatement = (PreparedStatement) conn.prepareStatement(st);
                // preparedStatement.setString(1, random_id());
                preparedStatement.setString(1, nomv.getText());
                preparedStatement.setString(2, numv.getText());
                preparedStatement.setDate(3, java.sql.Date.valueOf(String.valueOf(dated.getValue())));
                preparedStatement.setDate(4, java.sql.Date.valueOf(String.valueOf(datea.getValue())));
                preparedStatement.setString(5, chauffeur.getText());
                preparedStatement.setString(6, departvol.getText());
                preparedStatement.setString(7, arriver.getText());
                preparedStatement.setInt(8, Integer.parseInt(Prix.getText()));



                preparedStatement.executeUpdate();
                preparedStatement.close();
                // connlabel.setTextFill(Color.GREEN);
                // connlabel.setText("Added Successfully");
                showVol();
                search_vol();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());


            }
        }

    }

    @FXML
    void btn_suppv(ActionEvent event) {
        Vol vol = tbvol.getSelectionModel().getSelectedItem();
        String query = "DELETE FROM vol WHERE id='" + vol.getNumv() + "'";
        executeQuery(query);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Delete Success";
        String message ="Vol Is Deleted";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(2000));
        showVol();
        search_vol();

    }

    @FXML
    public String btn_updatev(javafx.event.ActionEvent actionEvent) {
        try {
            String st = "UPDATE  vol SET nom =?,dated =?, path=? , chauffeur=?, apartir=?, vers=? ,prix=? WHERE id = ? ";
            Connection conn = getConnection();
            preparedStatement = (PreparedStatement) conn.prepareStatement(st);
            preparedStatement.setString(1, nomv.getText());
            preparedStatement.setDate(2, java.sql.Date.valueOf(String.valueOf(dated.getValue())));
            preparedStatement.setDate(3, java.sql.Date.valueOf(String.valueOf(datea.getValue())));
            preparedStatement.setString(4, chauffeur.getText());
            preparedStatement.setString(5, departvol.getText());
            preparedStatement.setString(6, arriver.getText());
            preparedStatement.setInt(7, Integer.parseInt(Prix.getText()));
            preparedStatement.setString(8, numv.getText());


            preparedStatement.executeUpdate();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Update Success";
            String message ="Vol Is Updated";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(2000));
            preparedStatement.close();
            // connlabel.setTextFill(Color.GREEN);
            // connlabel.setText("Added Successfully");
            showVol();
            search_vol();

            return "Success";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // connlabel.setTextFill(Color.TOMATO);
            //  connlabel.setText(ex.getMessage());
            return "Exception";

        }
    }

    @FXML
    void mouseClick(MouseEvent event) {
        Vol vol = tbvol.getSelectionModel().getSelectedItem();
        numv.setText("" + vol.getNumv());
        nomv.setText(vol.getNomv());
        //dated.setDate(vol.getDated());
        chauffeur.setText(vol.getChauffeure());
        departvol.setText(vol.getDepart());
        arriver.setText(vol.getArriver());
        Prix.setText(vol.getPrix());


    }

    ObservableList<Vol> volList;

    void search_vol() {

        colnumv.setCellValueFactory(new PropertyValueFactory<Vol, String>("id"));

        colnomv.setCellValueFactory(new PropertyValueFactory<Vol, String>("nom"));

        coldated.setCellValueFactory(new PropertyValueFactory<Vol, Date>("dated"));

        coldatea.setCellValueFactory(new PropertyValueFactory<Vol, String>("path"));

        colpilote.setCellValueFactory(new PropertyValueFactory<Vol, String>("chauffeur"));

        coldepart.setCellValueFactory(new PropertyValueFactory<Vol, String>("apartir"));

        colarriver.setCellValueFactory(new PropertyValueFactory<Vol, String>("vers"));



        volList = getVolList();
        tbvol.setItems(volList);

        FilteredList<Vol> filteredList = new FilteredList<>(volList, b -> true);
        rechv.textProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            filteredList.setPredicate(vtransport ->
            {
                if (newValue == null || newValue.isEmpty()) {
                    return true;

                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(vtransport.getNumv()).indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (vtransport.getNomv().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (String.valueOf(vtransport.getDated()).indexOf(lowerCaseFilter) != -1) {

                    return true;

                } else if (vtransport.getChauffeure().indexOf(lowerCaseFilter) != -1) {
                    return true;


                } else if (String.valueOf(vtransport.getDated()).indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (vtransport.getDepart().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else if (vtransport.getArriver().indexOf(lowerCaseFilter) != -1) {
                    return true;


                } else
                    return false;


            });
        }));

        SortedList<Vol> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tbvol.comparatorProperty());
        tbvol.setItems(sortedList);
    }

    @FXML
    void btnlocaliser(ActionEvent event) throws Exception {


        WebViewCaptureMap w = new WebViewCaptureMap();
        Stage stage = new Stage();
        w.start(stage);


        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            public void handle(WindowEvent we) {

                System.out.println("Stage is closing");
                MapController mp = new MapController();
                location.setText(mp.getMap_value());
                System.out.println(mp.getMap_value());
                String map_path = mp.getMap_value();
                if (!"".equals(location.getText())) {

                    localiser.setDisable(true);
                    WebViewCaptureMap w = new WebViewCaptureMap();
                    String m = "http://" + w.getDb_path();
                    // img_path = m;
                    Image img = new Image("file:///"+w.getPath()+"");
                    System.out.println(w.getPath());

                    System.out.println("done");
                }

            }
        });

    }
    int res,off;
    public ObservableList<Stat1> getStatList(){
        ObservableList<Stat1> statList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM statistique";
        Statement st;
        ResultSet rs;
        try {
            st= conn.createStatement();
            rs =st.executeQuery(query);
            Stat1 stats;
            while (rs.next()){
                stats =new Stat1(rs.getInt("referencestat"),rs.getInt("nbrpanne") );
                statList.add(stats);
                res= stats.getReferencestat();
                off = stats.getNbrpanne();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return statList;
    }

    @FXML
    void mousebtnstat(MouseEvent event) {
        Stat1 stat = tab2.getSelectionModel().getSelectedItem();
        XYChart.Series<String,Number> series= new XYChart.Series<>();
        series.setName("Device comparison");
        series.getData().add(new XYChart.Data<>("reference", stat.getReferencestat()));
        series.getData().add(new XYChart.Data<>("nombre de panne", stat.getNbrpanne()));
        barcharts.getData().add(series);

    }

    @FXML
    void PDF(ActionEvent event) throws DocumentException {
        Document doc = new Document() ;
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Nom\\Desktop\\test.pdf"));
            doc.open();

            // Image img = Image.getInstance("C:\\Users\\Badis Khalsi\\Desktop\\badis.PNG");
            //       float documentWidth = doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin();
            //      float documentHeight = doc.getPageSize().getHeight() - doc.topMargin() - doc.bottomMargin();


            Paragraph p = new Paragraph("Detail Vol ");
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            //  doc.add(img2);
            // doc.newPage();
            // doc.close();

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
        Vol f = tbvol.getSelectionModel().getSelectedItem();
        String ch = " \n \n"
                + "Num Vol: " + f.getNumv()  +  "\n \n"

                + "Nom Vol: " + f.getNomv() + "\n \n"
                + "Date Vol (date de depart) : " + f.getDated()  +  "\n \n"

                + "Date Vol (date de d'arriver): " + f.getDatea()  +  "\n \n"
                + "Pilote d'avion : " + f.getChauffeure()+ "\n \n "
                + "Depart: " + f.getDepart()  +  "\n \n"
                + "Arriver: " + f.getArriver()  +  "\n \n";


        return ch;
    }



    @FXML
    private JFXButton GAME1;

    @FXML
    void JEUX(ActionEvent event) throws IOException{
            if (event.getSource() == GAME1) {
               // GAME1.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Nourhene/jeux/jeux.fxml")));
                //Parent root = FXMLLoader.load(getClass().getResource("view/AddCashierForm.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
        }

}