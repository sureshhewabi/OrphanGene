package lk.hgu.orf.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lk.hgu.orf.util.Util;

/**
 * This is the controller class which executes the Blast script as a subprocess
 *
 * @author Suresh Hewapathirana
 */
public class Blast {

    // Blast command
    List<String> command;

    public Blast(String inputFastaFile, String blastMethod, String organism,String taxLevel) {
        
        Map<String, String> settings = Util.getSettings();
         command=Arrays.asList(settings.get("blast"),
                "-query",
               inputFastaFile,
                "-db",
                "nr",
                "-outfmt",
                "6",
                "-max_target_seqs",
                settings.get("defalt_maxtargetseq"),
                "-evalue",
                settings.get("defalt_maxevalue"),
                "-out",
                settings.get("workingdir") + "blastResults.bl",
                "-remote");
//                "-entrez_query",
//                organism+"[Organism]");
    }

    public void doBlast() {
        
        try {
            // print the blast command to the terminal
            System.out.println("BLAST Command: " + command.toString());
            System.out.println("Be patient...This will take 2-15 min...");
            
            //execute the blast command
             ProcessBuilder pb = new ProcessBuilder(command);
             Process p = pb.start();
    
            // wait until the command get executed
            if (p.waitFor() != 0) {
                // error occured
                throw new RuntimeException("BLAST error occured");
            } else {
                System.out.println("BLAST successfully Completed!!");
            }
             System.out.println((p.exitValue() == 0) ? "Blast ran Successfully":"Blast Failed with " + p.exitValue() + " value");
        } catch (IOException ex) {
            System.err.println("IOError: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.err.println("InterruptedException: " + ex.getMessage());
        }
    }
}