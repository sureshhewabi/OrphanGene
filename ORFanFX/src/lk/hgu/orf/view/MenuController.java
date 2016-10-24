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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author hgu
 */
public class MenuController implements Initializable {

    
    @FXML
    private ImageView imageSideMenu;

    @FXML
    private JFXButton menuSettings;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void menuSettings_action(ActionEvent event) throws IOException {
        
//       Parent settingsPage =  FXMLLoader.load(getClass().getResource("Settings.fxml"));
//       Scene settingScene =  new Scene(settingsPage);
//       Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//       stage.setScene(settingScene);
//       stage.show();
    }
}
