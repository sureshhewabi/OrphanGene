package lk.hgu.orf.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.hgu.orf.control.Blast;
import lk.hgu.orf.control.Preprocess;
import lk.hgu.orf.model.BlastResult;
import lk.hgu.orf.model.ORFGene;
import lk.hgu.orf.model.ORFanGeneOverview;
import lk.hgu.orf.test.ChartData;
import lk.hgu.orf.test.TableData;
import lk.hgu.orf.util.Util;

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
    private JFXTextArea txtProteinSequence;

    @FXML
    private JFXButton btnFindOrphanGenes;

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

    @FXML
    private Label txtStatusLabel;

    @FXML
    private JFXProgressBar progressBar;

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

            // set width for tables
            initTableWidths();

            // Drag and Drop
        } catch (IOException e) {
            System.err.println("Error at MainFormController Init: " + e.getMessage());
        }
    }

    @FXML
    void hamburgerSideMenu_clicked(MouseEvent event) {

        // set the menu to the side drawer
        drawer.setSidePane(box);

        // if transition is open, then set it to close. and vice versa
        transition.setRate(transition.getRate() * -1);
        transition.play();

        if (drawer.isShown()) {
            drawer.close();
        } else {
            drawer.open();
        }
    }

    @FXML
    void btnFindOrphanGenes_clicked(ActionEvent event) {

        Preprocess prep = new Preprocess();
        Blast blast = new Blast();
        Map<String, String> settings = new HashMap<>();

        // create a ID file for indexing
        String inputFastaFile = txtProteinSequence.getText();
        txtStatusLabel.setText("Creating ID file...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        prep.createIDFile(inputFastaFile);

        // BLAST
        settings = Util.getSettings();
        System.out.println("############### BLAST PARAMETERS ################");
        System.out.println("-query " + inputFastaFile);
        System.out.println("-db " + settings.get("defalt_database"));
        System.out.println("-outfmt 6");
        System.out.println("-max_target_seqs " + settings.get("defalt_maxtargetseq"));
        System.out.println("-evalue " + settings.get("defalt_maxevalue"));
        System.out.println("-out blastoutput.bl");
        System.out.println("-num_threads " + settings.get("defalt_threads"));
        System.out.println("-blastMethod " + settings.get("defalt_blastmethod"));

         txtStatusLabel.setText("Blasting ...");
        blast.doBlast(inputFastaFile,
                settings.get("defalt_database"),
                settings.get("defalt_maxtargetseq"),
                settings.get("defalt_maxevalue"),
                settings.get("defalt_threads"),
                settings.get("defalt_blastmethod")
        );
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

    /**
     * This function sets column widths of each table dynamically based on the
     * with of the parent table.
     */
    void initTableWidths() {

        // Overview Table
        overviewTaxonomyLevel.prefWidthProperty().bind(tblOverview.widthProperty().multiply(0.6));
        overviewCount.prefWidthProperty().bind(tblOverview.widthProperty().multiply(0.4));

        // Orphan Gene Table
        GeneId.prefWidthProperty().bind(tblOrphanGenes.widthProperty().multiply(0.2));
        GeneName.prefWidthProperty().bind(tblOrphanGenes.widthProperty().multiply(0.4));
        ORFanGeneLevel.prefWidthProperty().bind(tblOrphanGenes.widthProperty().multiply(0.2));
        TaxonomyLevel.prefWidthProperty().bind(tblOrphanGenes.widthProperty().multiply(0.2));

        // BLAST Hits detailed table
        detailTableId.prefWidthProperty().bind(tblBlastHit.widthProperty().multiply(0.1));
        detailTableRankName.prefWidthProperty().bind(tblBlastHit.widthProperty().multiply(0.2));
        detailTableTaxLevel.prefWidthProperty().bind(tblBlastHit.widthProperty().multiply(0.35));
        detailTableParentTaxLevel.prefWidthProperty().bind(tblBlastHit.widthProperty().multiply(0.35));
    }

//    private Map<String, String> loadSettings(){
////        Map<String, String> settings = new HashMap<>();
////        
////           settings =  Util.getSettings();
////    
////                    // Set property values to relevant fields
////            txtDatabaseFile.setText(settings.get("defalt_database"));
////            txtSpeciesFile.setText(settings.get("defalt_species"));
////            txtTaxonomyFile.setText(settings.get("defalt_taxonomy"));
////            txtMaxEValue.setText(settings.get("defalt_maxevalue"));
////            txtMaxTargetSeq.setText(settings.get("defalt_maxtargetseq"));
////            sliderThreads.setValue(Double.parseDouble(settings.get("defalt_threads")));
//
//        return settings;
//    }
}
