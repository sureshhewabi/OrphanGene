package lk.hgu.orf.control;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.hgu.orf.view.MainFormController;

/**
 *
 * @author Suresh Hewapathirana
 */
public class Blast {

    private final String blast = "/Users/hgu/NetBeansProjects/OrphanGene/ORFanFX/src/settings/scripts/blast";
    private final String outputFile = "blastoutput.bl";
    private final String outfmt = "6";

    public void doBlast(String query, String db, String max_target_seqs, String evalue, String num_threads, String blastMethod) {

        try {
            ProcessBuilder pb = new ProcessBuilder(blast, query, db, max_target_seqs, evalue, outfmt, num_threads, blastMethod, outputFile);
            Process p = pb.start();
            System.out.println("Blast Completed!!");
        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
