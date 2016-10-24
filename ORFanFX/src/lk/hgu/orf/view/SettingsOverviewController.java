/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.hgu.orf.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import java.awt.Image;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Suresh Hewapathirana
 */
public class SettingsOverviewController implements Initializable {

    
    
      @FXML
    private VBox dataPane;

    @FXML
    private JFXButton btnDatabaseUpload;

    @FXML
    private JFXButton btnTaxonomyUpload;

    @FXML
    private JFXButton btnSpeciesUpload;

    @FXML
    private JFXSlider sliderThreads;

    @FXML
    private JFXButton btnMainForm;

    @FXML
    private JFXHamburger hamburgerSideMenu;
    
    

 
 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Settings Init");
            alert.setHeaderText("TEST");
    }
   
    
}
