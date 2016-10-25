package lk.hgu.orf.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

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
    private JFXTextField txtDatabaseFile;

    @FXML
    private JFXButton btnTaxonomyUpload;

    @FXML
    private JFXTextField txtSpeciesFile;

    @FXML
    private JFXButton btnSpeciesUpload;

    @FXML
    private JFXTextField txtTaxonomyFile;

    @FXML
    private JFXSlider sliderThreads;

    @FXML
    private JFXButton btnMainForm;

    @FXML
    private JFXButton btnSaveOverview;

    @FXML
    private JFXButton btnSaveTaxonomy;

    @FXML
    private JFXButton btnSaveSpecies;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void btnDatabaseUpload_clicked(ActionEvent event) {
        txtDatabaseFile.setText(getFilePath());
    }

    @FXML
    void btnSpeciesUpload_clicked(ActionEvent event) {
        txtSpeciesFile.setText(getFilePath());
    }

    @FXML
    void btnTaxonomyUpload_clicked(ActionEvent event) {
        txtTaxonomyFile.setText(getFilePath());
    }

    @FXML
    void btnMainForm_clicked(ActionEvent event) {

    }

    @FXML
    void btnSaveSpecies_clicked(ActionEvent event) {

    }

    @FXML
    void btnSaveTaxonomy_clicked(ActionEvent event) {

    }

    private String getFilePath() {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        return selectedFile.getPath();
    }

}
