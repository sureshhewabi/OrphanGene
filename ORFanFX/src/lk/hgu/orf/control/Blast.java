package lk.hgu.orf.control;

import java.io.IOException;
import java.util.Map;
import lk.hgu.orf.util.Util;

/**
 * This is the controller class which executes the Blast script as a subprocess
 *
 * @author Suresh Hewapathirana
 */
public class Blast {

    // Blast command
    private String command = "";

    public Blast(String inputFastaFile, String blastMethod) {
        
        Map<String, String> settings = Util.getSettings();
        
        // construct command based on blast method - online or offline
        if("online".equals(blastMethod)){
            command = settings.get("blast")
                + " -query " + inputFastaFile
                + " -db nr"
                + " -outfmt 6"
                + " -max_target_seqs " + settings.get("defalt_maxtargetseq")
                + " -evalue " + settings.get("defalt_maxevalue")
                + " -out " + settings.get("outputFile")
                + " -remote";
        }else{
            command = settings.get("blast")
                + " -query " + inputFastaFile
                + " -db " + settings.get("defalt_database")
                + " -outfmt 6"
                + " -max_target_seqs " + settings.get("defalt_maxtargetseq")
                + " -evalue " + settings.get("defalt_maxevalue")
                + " -out " + settings.get("outputFile")
                + " -num_threads " + settings.get("defalt_threads");
        }
    }

    public void doBlast() {

        Runtime rt = Runtime.getRuntime();
        try {
            System.out.println("BLAST Command: " + command);
            Process p = rt.exec(command);

            if (p.waitFor() != 0) {
                throw new RuntimeException("BLAST error occured");
            } else {
                System.out.println("BLAST successfully Completed!!");
            }
        } catch (IOException ex) {
            System.err.println("IOError: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.err.println("InterruptedException: " + ex.getMessage());
        }
    }
}