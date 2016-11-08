package lk.hgu.orf.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lk.hgu.orf.model.BlastResult;
import lk.hgu.orf.model.ORFGene;
import lk.hgu.orf.model.ORFanGeneOverview;
import lk.hgu.orf.util.Util;

/**
 *
 * @author Suresh Hewapathirana
 */
public class Report {

    private ObservableList<ORFGene> ORFGeneList = FXCollections.observableArrayList();
    private ObservableList<ORFanGeneOverview> ORFanGeneOverviewList = FXCollections.observableArrayList();
    private ObservableList<BlastResult> blastResultList = FXCollections.observableArrayList();
    private XYChart.Series<String, Number> seriesBlastHit;
    private XYChart.Series<String, Number> seriesOverview;

    public XYChart.Series<String, Number> getSeriesOverview() {
        return seriesOverview;
    }

    public XYChart.Series<String, Number> getSeriesBlastHit() {
        return seriesBlastHit;
    }

    public ObservableList<ORFGene> getORFGeneList() {
        return ORFGeneList;
    }

    public ObservableList<ORFanGeneOverview> getORFanGeneOverviewList() {
        return ORFanGeneOverviewList;
    }

    public ObservableList<BlastResult> getBlastResultList() {
        return blastResultList;
    }

    public void readOutputFile() throws IOException {

        // load settings from the settings config file
        Map<String, String> settings = Util.getSettings();
        Map<String, Integer> summary = new HashMap<>();
        seriesOverview = new XYChart.Series<>();
        ORFGene record;
        int totalOphanGenes = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(settings.get("ORFan_outputfile")))) {

            // variable hold to each line of the file
            String line;
            String[] secondColumn;

            // read line by line
            while ((line = br.readLine()) != null) {

                // split line by tab to get each column of the raw
                String[] columns = line.split("\t");

                // System.out.println("Gene ID:" + columns[0]);
                // first column contains the Gene ID
                String geneID = columns[0];

                //System.out.println("columns[1]:" + columns[1]);
                if (columns[1].contains("-")) {
                    // split second column to get ORF Gene Level and the Taxonomy Level
                    secondColumn = columns[1].split(" - ");
                    extractBlastHits(columns);
                } else { // strict ORFan does not have taxonomy or any other details
                    secondColumn = new String[]{columns[1], ""};
                }

                // copy all the data to the ORFGene object
                record = new ORFGene(geneID, "Not Available", secondColumn[0], secondColumn[1]);

                ORFGeneList.add(record);

                // Calculate group sum for the summary table
                // if the key is already added
                if (summary.containsKey(record.getORFanGeneLevel())) {
                    // increment cout by one
                    summary.replace(record.getORFanGeneLevel(), summary.get(record.getORFanGeneLevel()) + 1);
                } else { // if the key is not available, add it as a new record
                    summary.put(record.getORFanGeneLevel(), 1);
                }
            }

            // copy summary data to the summary object
            for (Map.Entry<String, Integer> row : summary.entrySet()) {

                System.out.println(row.getKey() + " | " + row.getValue());

                // add each record(ophan gene level and the number of orphan genes) to the list
                ORFanGeneOverviewList.add(new ORFanGeneOverview(row.getKey(), row.getValue()));
                seriesOverview.getData().add(new XYChart.Data<String, Number>(row.getKey(), row.getValue()));

                // calculate total number of ophan genes
                totalOphanGenes += row.getValue();
            }
            // add total line as the last record
            ORFanGeneOverviewList.add(new ORFanGeneOverview("Total", totalOphanGenes));
        }
    }

    private void extractBlastHits(String[] columns) {

        String rankName = "NA";
        int rankCount = 0;

        seriesBlastHit = new XYChart.Series<>();
        seriesBlastHit.setName("Matching hits");

        //System.out.println("-----------------" + columns[1] + "------------------");
        for (int i = 2; i < columns.length; i++) {
            String column = columns[i];
            // System.out.println(column);
            String rankCountRecord = column.split("[\\[\\]]")[1];

            if (rankCountRecord.split(",").length >= 2) {
                rankName = rankCountRecord.split(",")[0];
                rankCount = Integer.parseInt(rankCountRecord.split(",")[1]);
                seriesBlastHit.getData().add(new XYChart.Data<String, Number>(rankName, rankCount));
            }

            String taxonomyDetails = column.split("[\\[\\]]")[2];
            String[] taxList = taxonomyDetails.split(",");
            for (int j = 0; j < taxList.length; j++) {
                String tax = taxList[j];

                if (tax.split("[\\(\\)]").length >= 2) {
                    String taxomomy = tax.split("[\\(\\)]")[0];
                    String parentTaxomomy = tax.split("[\\(\\)]")[1];
                    this.blastResultList.add(new BlastResult(i - 1, columns[0],rankName, taxomomy, parentTaxomomy));
                }
            }
        }
    }
}
