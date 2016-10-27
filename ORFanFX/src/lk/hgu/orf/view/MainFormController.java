package lk.hgu.orf.view;

import com.jfoenix.controls.JFXDrawer;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.hgu.orf.model.BlastResult;
import lk.hgu.orf.model.ORFGene;
import lk.hgu.orf.model.ORFanGeneOverview;
import lk.hgu.orf.test.ChartData;
import lk.hgu.orf.test.TableData;

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
    private TableView<ORFanGeneOverview> tblOverview;

    @FXML
    private TableColumn<ORFanGeneOverview, String> overviewTaxonomyLevel;

    @FXML
    private TableColumn<ORFanGeneOverview, Integer> overviewCount;

    @FXML
    private BarChart<String, Number> chartOverview;

    @FXML
    private CategoryAxis xAxisOverview;

    @FXML
    private NumberAxis yAxisOverview;

    @FXML
    private TableView<ORFGene> tblOrphanGenes;

    @FXML
    private TableColumn<ORFGene, String> GeneId;

    @FXML
    private TableColumn<ORFGene, String> GeneName;

    @FXML
    private TableColumn<ORFGene, String> ORFanGeneLevel;

    @FXML
    private TableColumn<ORFGene, String> TaxonomyLevel;

    @FXML
    private BarChart<String, Number> chartBlastHit;

    @FXML
    private CategoryAxis xAxisBlast;

    @FXML
    private NumberAxis yAxisBlast;

    @FXML
    private JFXTextField txtBlastHitTableSearch;

    @FXML
    private TableView<BlastResult> tblBlastHit;

    @FXML
    private TableColumn<BlastResult, Integer> detailTableId;

    @FXML
    private TableColumn<BlastResult, String> detailTableRankName;

    @FXML
    private TableColumn<BlastResult, String> detailTableTaxLevel;

    @FXML
    private TableColumn<BlastResult, String> detailTableParentTaxLevel;

    private VBox box;
    private HamburgerBackArrowBasicTransition transition;
    TableData td = new TableData();

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

            // file table with data
            initORFanGeneTable();
            initORFanGeneOverviewTable();
            initBlastResultsTable();

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

    void initORFanGeneTable() {

        TableData td = new TableData();
        GeneId.setCellValueFactory(new PropertyValueFactory<>("GeneId"));
        GeneName.setCellValueFactory(new PropertyValueFactory<>("GeneName"));
        ORFanGeneLevel.setCellValueFactory(new PropertyValueFactory<>("ORFanGeneLevel"));
        TaxonomyLevel.setCellValueFactory(new PropertyValueFactory<>("TaxonomyLevel"));

        ObservableList<ORFGene> data = td.getORFGeneData();
        tblOrphanGenes.setItems(data);
    }

    void initORFanGeneOverviewTable() {

        overviewTaxonomyLevel.setCellValueFactory(new PropertyValueFactory<>("overviewTaxonomyLevel"));
        overviewCount.setCellValueFactory(new PropertyValueFactory<>("overviewCount"));

        ObservableList<ORFanGeneOverview> data = td.getORFGeneOverviewData();
        tblOverview.setItems(data);
    }

    void initBlastResultsTable() {

        detailTableId.setCellValueFactory(new PropertyValueFactory<>("detailTableId"));
        detailTableRankName.setCellValueFactory(new PropertyValueFactory<>("detailTableRankName"));
        detailTableTaxLevel.setCellValueFactory(new PropertyValueFactory<>("detailTableTaxLevel"));
        detailTableParentTaxLevel.setCellValueFactory(new PropertyValueFactory<>("detailTableParentTaxLevel"));

        ObservableList<BlastResult> data = td.getBlastResultsData();
        tblBlastHit.setItems(data);
    }
}
