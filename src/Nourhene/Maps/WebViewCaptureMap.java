
package Nourhene.Maps;

import javafx.animation.PauseTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static Nourhene.Maps.MapController.map_value;

public class WebViewCaptureMap {


    private static final String HOME_LOC = "map.html";
    static String path;
    static String db_path;

    public static String getDb_path() {
        return db_path;
    }
    private WebView webView;

    private File captureFile;

    //public static void main(String[] args) { Application.launch(WebViewCaptureMap.class); }
    public void start(Stage stage) throws Exception {
        webView = new WebView();
        webView.setPrefSize(1000, 600);
        URL url = this.getClass().getResource("map.html");
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url.toString());
        MapController mk = new MapController();
        webEngine.setOnAlert(event -> mk.showAlert(event.getData()));

        ScrollPane webViewScroll = new ScrollPane();
        webViewScroll.setContent(webView);
        webViewScroll.setPrefSize(600, 400);

        final Button capture = new Button("Capture");

        final ProgressIndicator progress = new ProgressIndicator();
        progress.setVisible(false);

        final TextField prefWidth = new TextField("600");
        final TextField prefHeight = new TextField("400");

        HBox controls = new HBox(10);
        controls.getChildren().addAll(capture, progress, prefWidth, prefHeight);

        // final ImageView imageView = new ImageView();
        //ScrollPane imageViewScroll = makeScrollable(imageView);
        //imageViewScroll.setPrefSize(600, 400);
        final PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.millis(500));
        pt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                WritableImage image = webView.snapshot(null, null);
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                try {
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    int response = JOptionPane.showConfirmDialog(null, "Do you want to Save?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.NO_OPTION) {
                        System.out.println("No button clicked");
                        //return;
                    } else if (response == JOptionPane.YES_OPTION) {


                        System.out.println("Yes button clicked");
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Specify a file to save");
                        JFrame parentFrame = new JFrame();
                        int userSelection = fileChooser.showSaveDialog(parentFrame);

                        if (userSelection == JFileChooser.APPROVE_OPTION) {
                            File fileToSave = fileChooser.getSelectedFile();

                            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                            captureFile = new File(fileToSave.getAbsolutePath());
                            path = fileToSave.getAbsolutePath();

                            ImageIO.write(bufferedImage, "png", captureFile);
                           // FileUploader fu = new FileUploader("http://localhost:8080/blog/");
                         //   String fileNameInServer = fu.upload(path);
                          //  System.out.println(fileNameInServer.substring(fileNameInServer.indexOf("f")));
                          //  db_path = "localhost/blog//uploads/" + fileNameInServer.substring(fileNameInServer.indexOf("f"));
                            stage.close();
                            stage.fireEvent(
                                    new WindowEvent(
                                            stage,
                                            WindowEvent.WINDOW_CLOSE_REQUEST
                                    )
                            );
                            return;
                        }
                        capture.setDisable(true);
                        progress.setVisible(true);

                    } else if (response == JOptionPane.CLOSED_OPTION) {
                        System.out.println("JOptionPane closed");
                        //return;

                    }
                    if (captureFile == null) {
                        File currentDirectory = new File(new File("map.png").getAbsolutePath());
                        ImageIO.write(bufferedImage, "png", currentDirectory);
                        //FileUploader fu = new FileUploader("http://localhost/blog/");
                      //  String fileNameInServer = fu.upload(currentDirectory.toString());
                     //   System.out.println(fileNameInServer.substring(fileNameInServer.indexOf("f")));
                      //  db_path = "localhost/blog/uploads/" + fileNameInServer.substring(fileNameInServer.indexOf("f"));
                        //System.out.println(currentDirectory);
                        stage.close();
                        stage.fireEvent(
                                new WindowEvent(
                                        stage,
                                        WindowEvent.WINDOW_CLOSE_REQUEST
                                )
                        );
                        return;
                    }
                    //imageView.setImage(new Image(captureFile.toURI().toURL().toExternalForm()));
                    //System.out.println("Captured WebView to: " + captureFile.getAbsoluteFile());
//                    MapController mp = new MapController();
//                    Version_3Controller vr = new Version_3Controller();
//                    vr.setLocation(mp.getMap_value());
                    progress.setVisible(false);
                    capture.setDisable(false);
                    stage.close();
                    stage.fireEvent(
                            new WindowEvent(
                                    stage,
                                    WindowEvent.WINDOW_CLOSE_REQUEST
                            )
                    );

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        capture.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                NumberStringConverter converter = new NumberStringConverter();
                double W = converter.fromString(prefWidth.getText()).doubleValue();
                double H = converter.fromString(prefHeight.getText()).doubleValue();

                // ensure that the capture size has a reasonable min size and is within the limits of what JavaFX is capable of processing.
                if (W < 100) {
                    W = 100;
                    prefWidth.setText("100");
                }

                if (W > 2000) {
                    W = 2000;
                    prefWidth.setText("2000");
                }

                if (H < 100) {
                    H = 100;
                    prefHeight.setText("100");
                }

                if (H > 16000) {
                    H = 16000;
                    prefHeight.setText("16000");
                }

                webView.setPrefWidth(W);
                webView.setPrefHeight(H);

                pt.play();

            }
        });

        //webView.getEngine().load(HOME_LOC);
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 10; -fx-background-color: cornsilk;");
        layout.getChildren().setAll(
                webViewScroll,
                controls
        //imageViewScroll,
        //new Label("Capture File: " + captureFile)
        );
        //VBox.setVgrow(imageViewScroll, Priority.ALWAYS);

        stage.setScene(new Scene(layout));
        stage.show();
    }

    public static String getMap_value() {
        return map_value;
    }

    public static void setPath(String path) {
        WebViewCaptureMap.path = path;
    }

    public static String getPath() {
        return path;
    }

}
