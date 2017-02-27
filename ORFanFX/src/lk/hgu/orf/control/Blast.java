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

    public Blast(String inputFastaFile, String blastMethod, String organism,String taxLevel) {
        
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
                + " -remote"
                + " -entrez_query \'"+ taxLevel+"[Organism]\'";
        }else{
            command = settings.get("blast")
                + " -query " + inputFastaFile
                + " -db " + settings.get("defalt_database")
                + " -outfmt 6"
                + " -max_target_seqs " + settings.get("defalt_maxtargetseq")
                + " -evalue " + settings.get("defalt_maxevalue")
                + " -out " + settings.get("outputFile")
                + " -num_threads " + settings.get("defalt_threads")
                + " -entrez_query "+ organism +"[Organism]";
        }
    }

    public void doBlast() {

        Runtime rt = Runtime.getRuntime();
        try {
            // print the blast command to the terminal
            System.out.println("BLAST Command: " + command);
            //execute the blast command
            Process p = rt.exec(command);
            // wait until the command get executed
            if (p.waitFor() != 0) {
                // error occured
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