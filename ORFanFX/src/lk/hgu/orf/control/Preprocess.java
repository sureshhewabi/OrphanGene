package lk.hgu.orf.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.hgu.orf.util.Util;
import lk.hgu.orf.view.MainFormController;

/**
 *
 * @author Suresh Hewapathirana
 */
public class Preprocess {

    /*
    * Get the text from the input text field and
    * write it into a file called input.fasta
    */
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
            System.out.println("IOError:" + e.getMessage());
        }
    }
    
    /*
    * Function to extract protein IDs from the input file by executing the
    * "extractIdsFromFasta" script
    */
    public void createIDFile(String inputFile) {
        // get settings from the settings file
        Map<String, String> settings = Util.getSettings();

        try {
            String inputFileName = inputFile;
            //Prepare parameters to execute "extractIdsFromFasta" script (create the ProcessBuilder object)
            ProcessBuilder pb = new ProcessBuilder(settings.get("extractIdsFromFasta"), inputFileName, settings.get("idFile"));
            // execute the "extractIdsFromFasta" script from the terminal
            Process p = pb.start();
            System.out.println("INFO: ID file created!!");
        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
