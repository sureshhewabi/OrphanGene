package lk.hgu.orf.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.hgu.orf.model.BlastResult;
import lk.hgu.orf.model.ORFGene;
import lk.hgu.orf.model.ORFanGeneOverview;
import lk.hgu.orf.util.Util;

/**
 *
 * @author Suresh Hewapathirana
 */
public class Report {

    ObservableList<ORFGene> ORFGeneList = FXCollections.observableArrayList();
    ObservableList<ORFanGeneOverview> ORFanGeneOverviewList = FXCollections.observableArrayList();
    ObservableList<BlastResult> blastResultList = FXCollections.observableArrayList();

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

                System.out.println("Gene ID:" + columns[0]);
                // first column contains the Gene ID
                String geneID = columns[0];

                System.out.println("columns[1]:" + columns[1]);
                if (columns[1].contains("-")) {
                    // split second column to get ORF Gene Level and the Taxonomy Level
                    secondColumn = columns[1].split(" - ");
                } else {
                    secondColumn = new String[]{columns[1], "NA"};
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

                // calculate total number of ophan genes
                totalOphanGenes += row.getValue();
            }
            // add total line as the last record
            ORFanGeneOverviewList.add(new ORFanGeneOverview("Total", totalOphanGenes));
        }
    }
}
