package lk.hgu.orf.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
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

    private void LoadSpecies() {

        List<String> speciesList = new ArrayList<String>();

        try {
            Scanner sc = new Scanner(new FileReader("/Users/hgu/NetBeansProjects/OrphanGene/ORFanFX/src/settings/names.txt"));
            while (sc.hasNextLine()) {
                speciesList.add(sc.next());
                System.out.println(sc.next());
            }
            System.out.println("speciesList length : " + speciesList.size());
//            ObservableList<String> items = FXCollections.observableArrayList(speciesList);
//            txtSettingSpeciesFrom.setItems(items);
        } catch (IOException e) {
            System.err.println("Setting loading  file error:" + e.getMessage());
        }
    }

    private void loadProperties() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("database"));
            txtDatabaseFile.setText(prop.getProperty("database"));
            System.out.println(prop.getProperty("species"));
            txtSpeciesFile.setText(prop.getProperty("species"));
            System.out.println(prop.getProperty("taxonomy"));
            txtTaxonomyFile.setText(prop.getProperty("taxonomy"));

        } catch (IOException ex) {
            System.out.println(" IOError: "+ ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println(" IOError: "+ e.getMessage());
                }
            }
        }

    }

}
