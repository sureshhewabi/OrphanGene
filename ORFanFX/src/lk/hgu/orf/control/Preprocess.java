package lk.hgu.orf.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

    public void saveInputSequence(String content) {

        try {
            File file = new File("./workingdir/input.fasta");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Input sequence saved in the working directory");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
