package lk.hgu.orf.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lk.hgu.orf.control.Blast;
import lk.hgu.orf.control.ORFan;
import lk.hgu.orf.control.Preprocess;
import lk.hgu.orf.control.Report;
import lk.hgu.orf.model.BlastResult;
import lk.hgu.orf.model.ORFGene;
import lk.hgu.orf.model.ORFanGeneOverview;
import lk.hgu.orf.test.ChartData;
import lk.hgu.orf.test.Data;
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

    @FXML
    private HBox hboxtxtOganism;

    private VBox box;
    private HamburgerBackArrowBasicTransition transition;

    // To load data into data tables
    TableData td = new TableData();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            progressBar.setVisible(false);

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

            // load species
            Data data = new Data();

            //CustomControl
            AutoCompleteTextField autotxtOrganism = new AutoCompleteTextField();
            autotxtOrganism.setFocusColor(txtProteinSequence.getFocusColor());
            autotxtOrganism.setPromptText("Organism");
            autotxtOrganism.prefWidthProperty().bind(hboxtxtOganism.widthProperty().multiply(0.9));
            autotxtOrganism.setLabelFloat(true);
            autotxtOrganism.getEntries().addAll(data.getOranisms());

            // final AutoFillTextBox box = new AutoFillTextBox(data.getOranisms());/
            hboxtxtOganism.getChildren().addAll(autotxtOrganism);

        } catch (IOException e) {
            System.err.println("Error at MainFormController Init: " + e.getMessage());
        }

        txtProteinSequence.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("")) {
                btnFindOrphanGenes.setDisable(true);
                txtStatusLabel.setText("Enter a protein sequence!");
            } else {
                btnFindOrphanGenes.setDisable(false);
            }
        });
        btnFindOrphanGenes.setDisable(true);
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
    void txtProteinSequence_OnDragDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    }

    @FXML
    void txtProteinSequence_OnDragDropped(DragEvent event) {
        final Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles()) {
            Path file = dragboard.getFiles().get(0).toPath();
            try {
                txtProteinSequence.setText(new String(Files.readAllBytes(file)));
            } catch (IOException e) {
                System.err.println("Error : " + e.getMessage());
            }
        }
    }

    @FXML
    void btnFindOrphanGenes_clicked(ActionEvent event) {

        Preprocess prep = new Preprocess();
        progressBar.setVisible(true);

        try {
            // Step 1 - Save input sequence as a fasta file
            txtStatusLabel.setText("Saving input sequence...");
            prep.saveInputSequence(txtProteinSequence.getText());

            // Step 2 - create a ID file for indexing
            txtStatusLabel.setText("Creating ID file...");
            File inputFastaFile = new File("./workingdir/input.fasta");
            System.out.println("Input Sequence file : " + inputFastaFile.getAbsolutePath());
            prep.createIDFile(inputFastaFile.getAbsolutePath());

            // Step 3 - BLAST
            txtStatusLabel.setText("Blasting ...");
            Blast blast = new Blast(inputFastaFile.getAbsolutePath(), "online");
            blast.doBlast();
            progressBar.setVisible(false);

            // Step 4 - find Orphan Genes
            txtStatusLabel.setText("Finding orphan genes ...");
            ORFan orf = new ORFan();
            orf.findORFanGenes();

            // Step 5 - Report results
            Report report = new Report();
            report.readOutputFile();
            
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
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
}
