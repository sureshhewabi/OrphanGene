
package lk.hgu.orf.control;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.hgu.orf.view.MainFormController;

/**
 *
 * @author Suresh Hewapathirana
 */
public class Preprocess {

    private final String extractIdsFromFasta = "/Users/hgu/NetBeansProjects/OrphanGene/ORFanFX/src/settings/scripts/extractIdsFromFasta";
    private final String idFile = "IDFile.id";

    public void createIDFile(String inputFile) {

        try {
            String inputFileName = inputFile;

            ProcessBuilder pb = new ProcessBuilder(extractIdsFromFasta, inputFileName, idFile);
            Process p = pb.start();
            System.out.println("INFO: ID file created!!");
        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
