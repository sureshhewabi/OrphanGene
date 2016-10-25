package lk.hgu.orf.view;

import com.jfoenix.controls.JFXDrawer;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
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
import lk.hgu.orf.test.ChartData;

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
    private VBox dataPane;

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

    private VBox box;
    private HamburgerBackArrowBasicTransition transition;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Initialise side menu
            box = FXMLLoader.load(getClass().getResource("/lk/hgu/orf/view/Menu.fxml"));
            transition = new HamburgerBackArrowBasicTransition(hamburgerSideMenu);
            transition.setRate(-1);

            ChartData cd = new ChartData();

            // fill chart with data
            chartOverview.getData().add(cd.getOverviewChartData());
            chartBlastHit.getData().add(cd.getBlastChartData());

        } catch (IOException e) {
            System.err.println("Error at MainFormController Init: " + e.getMessage());
        }
    }

    @FXML
    void hamburgerSideMenu_clicked(MouseEvent event) {

        drawer.setSidePane(box);
        transition.setRate(transition.getRate() * -1);
        transition.play();

        if (drawer.isShown()) {
            drawer.close();
        } else {
            drawer.open();
        }
    }
}
