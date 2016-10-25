
package lk.hgu.orf.view;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
}
