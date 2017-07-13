package lk.hgu.orf.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import lk.hgu.orf.util.Util;

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
    private JFXTextField txtMaxEValue;

    @FXML
    private JFXToggleButton tbtnBlastDatabase;

    @FXML
    private JFXTextField txtMaxTargetSeq;

    @FXML
    private JFXButton btnResetDefault;

    @FXML
    private JFXButton btnSaveOverview;

    @FXML
    private JFXButton btnSaveTaxonomy;

    @FXML
    private JFXButton btnSaveSpecies;

    @FXML
    private JFXListView<String> txtSettingTaxFrom;

    @FXML
    private JFXListView<String> txtSettingTaxTo;

    @FXML
    private JFXListView<String> txtSettingSpeciesFrom;

    @FXML
    private JFXListView<String> txtSettingSpeciesTo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadProperties();

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

    private void loadProperties() {

        Map<String, String> settings = new HashMap<>();

        settings = Util.getSettings();

        // Set property values to relevant fields
        txtDatabaseFile.setText(settings.get("defalt_database"));
        txtSpeciesFile.setText(settings.get("workingdir")+"names.txt");
        txtTaxonomyFile.setText(settings.get("workingdir")+"nodes.txt");
        txtMaxEValue.setText(settings.get("defalt_maxevalue"));
        txtMaxTargetSeq.setText(settings.get("defalt_maxtargetseq"));
        sliderThreads.setValue(Double.parseDouble(settings.get("defalt_threads")));
    }

}
