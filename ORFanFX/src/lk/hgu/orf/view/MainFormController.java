package lk.hgu.orf.view;

import com.jfoenix.controls.JFXDrawer;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 *
 * @author Suresh Hewapathirana
 */
public class MainFormController implements Initializable {

    @FXML
    private Pane paneHeader;

    @FXML
    private JFXHamburger hamburgerSideMenu;

    @FXML
    private JFXDrawer drawer;

    @FXML
    public static VBox dataPane;

    @FXML
    private TableView<?> tblOverview;

    @FXML
    private BarChart<String, Number> chartOverview;

    @FXML
    private CategoryAxis xAxisOverview;

    @FXML
    private NumberAxis yAxisOverview;

    @FXML
    private TableView<?> tblOrphanGenes;

    @FXML
    private TableColumn<?, ?> colGeneId;

    @FXML
    private TableColumn<?, ?> colGeneName;

    @FXML
    private TableColumn<?, ?> colORFGeneLevel;

    @FXML
    private TableColumn<?, ?> colTaxanomyLevel;

    @FXML
    private BarChart<String, Number> chartBlastHit;

    @FXML
    private CategoryAxis xAxisBlast;

    @FXML
    private NumberAxis yAxisBlast;

    @FXML
    private JFXTextField txtBlastHitTableSearch;

    @FXML
    private JFXTreeTableView<?> tblBlastHit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            VBox box = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburgerSideMenu);
            transition.setRate(-1);
            hamburgerSideMenu.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
            
            createOverViewChart();
            createBlastChart();

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Exception Found");
            alert.setHeaderText("ERROR");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    protected BarChart<String, Number> createOverViewChart() {

        // Define data
        String[] taxonomy = {"Native", "Superkingdom", "Phylum", "Class",
            "Order", "Family", "Genus", "Species", "Strict"};
        Number[] count = {0, 6, 5, 4, 0, 5, 4, 0, 2};

        // setup chart
        //xAxisOverview.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(taxonomy)));
        // add starting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName("Orphan gene count");
        // create sample data
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[0], count[0]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[1], count[1]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[2], count[2]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[3], count[3]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[4], count[4]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[5], count[5]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[6], count[6]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[7], count[7]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[8], count[8]));
        chartOverview.getData().add(series1);
        return chartOverview;
    }

    protected BarChart<String, Number> createBlastChart() {

//        // Define data
        String[] levels = {"Phylum", "Class", "Order", "Family", "Genus", "Species", "Subspecies"};
        Number[] hits = {3, 4, 16, 19, 41, 132, 430};
//
//        // setup chart
//        xAxisBlast.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(levels)));

//        // add starting data
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Matching hits");

        // create sample data
        series2.getData().add(new XYChart.Data<String, Number>(levels[0], hits[0]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[1], hits[1]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[2], hits[2]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[3], hits[3]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[4], hits[4]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[5], hits[5]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[6], hits[6]));
        chartBlastHit.getData().add(series2);
        return chartBlastHit;
    }
}
