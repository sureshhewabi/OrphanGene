/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.hgu.orf.view;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Suresh Hewapathirana
 */
public class MenuController implements Initializable {

    @FXML
    private ImageView imageSideMenu;

    @FXML
    private Button btnSettingsOverview;

    @FXML
    private Button openFrm3;

    @FXML
    private JFXButton btnSettings;

    @FXML
    void btnSettings_clicked(ActionEvent event) throws IOException {

        Parent settingsPage = FXMLLoader.load(getClass().getResource("/lk/hgu/orf/view/SettingsOverview.fxml"));
        Scene settingScene = new Scene(settingsPage);
        Stage window = new Stage(); //(Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("ORFan Finder - Settings");
        window.setScene(settingScene);
        window.show();
    }

    @FXML
    void btnSettingsOverview_clicked(ActionEvent event) {

    }

    @FXML
    void loadPane3(ActionEvent event) {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

//    @FXML
//    void menuSettings_action(ActionEvent event) throws IOException {
//        
////       Parent settingsPage =  FXMLLoader.load(getClass().getResource("Settings.fxml"));
////       Scene settingScene =  new Scene(settingsPage);
////       Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
////       stage.setScene(settingScene);
////       stage.show();
//    }
//    public VBox fadeAnimate(String url) {
//        VBox v = new VBox();
//        try {
//
//            v = (VBox) FXMLLoader.load(getClass().getResource(url));
//            FadeTransition ft = new FadeTransition(Duration.millis(1500));
//            ft.setNode(v);
//            ft.setFromValue(0.1);
//            ft.setToValue(1);
//            ft.setCycleCount(1);
//            ft.setAutoReverse(false);
//            ft.play();
//
//        } catch (IOException e) {
//            System.out.println("IOError : " + e.getMessage());
//        }
//        return v;
//    }
//    @FXML
//    void loadPane2(ActionEvent event) {
//        try {
//            String url = "/lk/hgu/orf/view/SettingsOverview.fxml";
//            Node node = fadeAnimate(url);
//            MainFormController.dataPane = (VBox) node;
//                    
//            
////            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//////            
////            Scene currentScene = primaryStage.getScene();
////            primaryStage.setScene(currentScene);
////            primaryStage.show();
//
//        } catch (Exception e) {
//            System.out.println("Error : " + e.getMessage());
//        }
//    }
}
