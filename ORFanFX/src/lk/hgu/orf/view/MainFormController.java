package lk.hgu.orf.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
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
import lk.hgu.orf.test.Data;
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
    private Hyperlink lblShowExample;

    @FXML
    private JFXButton btnFindOrphanGenes;

    @FXML
    private TableView<BlastResult> tblBlastHit;

    @FXML
    private TableColumn<BlastResult, Integer> detailTableId;

    @FXML
    private TableColumn<BlastResult, String> detailTableGene;

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
    
    @FXML
    private JFXComboBox<String> txtTaxLevels;

    private VBox box;
    private HamburgerBackArrowBasicTransition transition;
    AutoCompleteTextField autotxtOrganism;
    Report report = new Report();

    // To load data into data tables
    TableData td = new TableData();
    Data data;

    @FXML
    void autotxtOrganism_clicked(ActionEvent event) {
        System.out.println("changed");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // generate json files
//            Util util = new Util();
//            util.getOranisms();
//            
            progressBar.setVisible(false);

            // Initialise side menu
            box = FXMLLoader.load(getClass().getResource("/lk/hgu/orf/view/Menu.fxml"));
            transition = new HamburgerBackArrowBasicTransition(hamburgerSideMenu);
            transition.setRate(-1);

            // set width for tables
            initTableWidths();

            // load species to the organism textbox(customized textbox)
            data = new Data();
            autotxtOrganism = new AutoCompleteTextField();
            autotxtOrganism.setFocusColor(txtProteinSequence.getFocusColor());
            autotxtOrganism.setPromptText("Organism");
            autotxtOrganism.prefWidthProperty().bind(hboxtxtOganism.widthProperty().multiply(0.9));
            autotxtOrganism.setLabelFloat(true);
            autotxtOrganism.getEntries().addAll(data.getOranisms());
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

        autotxtOrganism.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(")")) {
                String organismTaxonomyID = autotxtOrganism.getText().split("[\\(\\)]")[1];
                System.out.println("taxonomy ID: " + organismTaxonomyID);
                txtTaxLevels.setItems(data.getTaxLevels(organismTaxonomyID));
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
        // enable file drag onto textbox
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
        String organismTaxonomyID = "";
        String organismName = "";

        if (!autotxtOrganism.getText().equals("")) {
            organismTaxonomyID = autotxtOrganism.getText().split("[\\(\\)]")[1];
            organismName = autotxtOrganism.getText().split("[\\(\\)]")[0].trim();
            System.out.println("Selected Organism:" + organismName);
        } else {
            System.err.println("Error: No organism selected!");
        }
        try {
            // Step 1 - Save input sequence as a fasta file
            txtStatusLabel.setText("Saving input sequence...");
            prep.saveInputSequence(txtProteinSequence.getText());

            // Step 2 - create a ID file for indexing
            txtStatusLabel.setText("Creating ID file...");
            File inputFastaFile = new File("workingdir/input.fasta");
            System.out.println("Input Sequence file : " + inputFastaFile.getAbsolutePath());
            prep.createIDFile(inputFastaFile.getAbsolutePath());

            // Step 3 - BLAST
            txtStatusLabel.setText("Blasting ...");
            String taxlevel=txtTaxLevels.getValue();
            Blast blast = new Blast(inputFastaFile.getAbsolutePath(), "online", organismName,taxlevel);
            blast.doBlast();

            // Step 4 - find Orphan Genes
            txtStatusLabel.setText("Finding orphan genes ...");
            ORFan orf = new ORFan(organismTaxonomyID);
            orf.findORFanGenes();

            // Step 5 - Report results
            report.readOutputFile();

            // update tables with results
            initORFanGeneTable(report.getORFGeneList());
            initORFanGeneOverviewTable(report.getORFanGeneOverviewList());
            initBlastResultsTable(report.getBlastResultList());

            // Table filter 
            tblOverview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    //Check whether item is selected and set value of selected item to Label
                    if (tblOverview.getSelectionModel().getSelectedItem() != null) {
                        ORFanGeneOverview record = tblOverview.getSelectionModel().getSelectedItem();
                        System.out.println(record.getOverviewTaxonomyLevel());
                        ObservableList<ORFGene> sortedData = filterORFanGeneTable(report.getORFGeneList(), record.getOverviewTaxonomyLevel());
                        // 5. Add sorted (and filtered) data to the table.
                        tblOrphanGenes.setItems(sortedData);
                    }
                }
            });

            tblOrphanGenes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    //Check whether item is selected and set value of selected item to Label
                    if (tblOrphanGenes.getSelectionModel().getSelectedItem() != null) {
                        ORFGene record = tblOrphanGenes.getSelectionModel().getSelectedItem();
                        System.out.println(record.getGeneId());
                        updateBlastHitsBySelectedGene(report.getBlastResultList(), record.getGeneId());
                    }
                }
            });

            filterBlastHitsTable(report.getBlastResultList());

            // fill chart with data
            chartOverview.getData().add(report.getSeriesOverview());

        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    void initORFanGeneTable(ObservableList<ORFGene> data) {

        GeneId.setCellValueFactory(new PropertyValueFactory<>("GeneId"));
        GeneName.setCellValueFactory(new PropertyValueFactory<>("GeneName"));
        ORFanGeneLevel.setCellValueFactory(new PropertyValueFactory<>("ORFanGeneLevel"));
        TaxonomyLevel.setCellValueFactory(new PropertyValueFactory<>("TaxonomyLevel"));

        tblOrphanGenes.setItems(data);
    }

    void initORFanGeneOverviewTable(ObservableList<ORFanGeneOverview> data) {

        overviewTaxonomyLevel.setCellValueFactory(new PropertyValueFactory<>("overviewTaxonomyLevel"));
        overviewCount.setCellValueFactory(new PropertyValueFactory<>("overviewCount"));

        tblOverview.setItems(data);
    }

    void initBlastResultsTable(ObservableList<BlastResult> data) {

        detailTableId.setCellValueFactory(new PropertyValueFactory<>("detailTableId"));
        detailTableGene.setCellValueFactory(new PropertyValueFactory<>("detailTableGene"));
        detailTableRankName.setCellValueFactory(new PropertyValueFactory<>("detailTableRankName"));
        detailTableTaxLevel.setCellValueFactory(new PropertyValueFactory<>("detailTableTaxLevel"));
        detailTableParentTaxLevel.setCellValueFactory(new PropertyValueFactory<>("detailTableParentTaxLevel"));

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
        detailTableGene.prefWidthProperty().bind(tblBlastHit.widthProperty().multiply(0.1));
        detailTableRankName.prefWidthProperty().bind(tblBlastHit.widthProperty().multiply(0.1));
        detailTableTaxLevel.prefWidthProperty().bind(tblBlastHit.widthProperty().multiply(0.35));
        detailTableParentTaxLevel.prefWidthProperty().bind(tblBlastHit.widthProperty().multiply(0.35));
    }

    private void filterBlastHitsTable(ObservableList<BlastResult> masterData) {

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<BlastResult> filteredData = new FilteredList<>(masterData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        txtBlastHitTableSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(BlastResult -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare in lower case
                String lowerCaseFilter = newValue.toLowerCase();

                if (BlastResult.getDetailTableRankName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches Rank Name.
                } else if (BlastResult.getDetailTableGene().contains(lowerCaseFilter)) {
                    return true; // Filter matches Taxonomy Level.
                } else if (BlastResult.getDetailTableTaxLevel().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches Taxonomy Level.
                } else if (BlastResult.getDetailTableParentTaxLevel().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches Parent Taxonomy Level.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<BlastResult> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblBlastHit.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblBlastHit.setItems(sortedData);
    }

    private ObservableList<ORFGene> filterORFanGeneTable(ObservableList<ORFGene> masterData, String searchTerm) {

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<ORFGene> filteredData = new FilteredList<>(masterData, p -> true);

        // 2. Set the filter Predicate
        filteredData.setPredicate(ORFGene -> {
            // If filter text is empty, display all records.
            if (searchTerm == null || searchTerm.isEmpty() || "Total".equals(searchTerm)) {
                return true;
            }

            // facilitate search for upper and lower cases.
            String lowerCaseFilter = searchTerm.toLowerCase();

            if (ORFGene.getORFanGeneLevel().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches Gene Level.
            }
            return false; // Does not match.
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ORFGene> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblOrphanGenes.comparatorProperty());

        return sortedData;
    }

    private ObservableList<BlastResult> updateBlastHitsBySelectedGene(ObservableList<BlastResult> masterData, String gene) {

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<BlastResult> filteredData = new FilteredList<>(masterData, p -> true);

        // 2. Set the filter Predicate
        filteredData.setPredicate(BlastResult -> {
            // If filter text is empty, display all records.
            if (gene == null || gene.isEmpty()) {
                return true;
            }

            if (BlastResult.getDetailTableGene().contains(gene)) {
                return true; // Filter matches Gene Level.
            }
            return false; // Does not match.
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<BlastResult> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblBlastHit.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblBlastHit.setItems(sortedData);

        Map<String, Integer> summary = new HashMap<>();
        XYChart.Series<String, Number> seriesBlastHit = new XYChart.Series<>();

        for (BlastResult blastResult : sortedData) {
            if (summary.containsKey(blastResult.getDetailTableRankName())) {
                // increment cout by one
                summary.replace(blastResult.getDetailTableRankName(), summary.get(blastResult.getDetailTableRankName()) + 1);
            } else { // if the key is not available, add it as a new record
                summary.put(blastResult.getDetailTableRankName(), 1);
            }
        }

        for (Map.Entry<String, Integer> row : summary.entrySet()) {
            seriesBlastHit.getData().add(new XYChart.Data<String, Number>(row.getKey(), row.getValue()));
        }
        chartBlastHit.getData().clear();
        chartBlastHit.getData().add(seriesBlastHit);

        filterBlastHitsTable(sortedData);

        return sortedData;
    }

    @FXML
    void lblShowExample_clicked(ActionEvent event) {

        try {
            txtProteinSequence.setText(Util.getExampleProtSeq());
            autotxtOrganism.setText("Homo sapiens (9606)");
        } catch (IOException ex) {
            System.out.println("Error:" + ex.getMessage());
        }

    }
}
