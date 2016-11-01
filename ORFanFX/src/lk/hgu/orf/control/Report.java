package lk.hgu.orf.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import lk.hgu.orf.model.ORFGene;

/**
 *
 * @author Suresh Hewapathirana
 */
public class Report {

    public void readOutputFile() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("/Users/hgu/NetBeansProjects/OrphanGene/ORFanFX/workingdir/orfanResults.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                String[] record = line.split("\t");
                System.out.println(record[0]);
                System.out.println(record[1].split("-")[0]);
                if (!record[1].equals("strict ORFan")) {
                    System.out.println(record[2]);
                }
            }
        }
    }

}
