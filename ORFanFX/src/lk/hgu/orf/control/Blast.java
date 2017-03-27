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
    private String command = "";
    List<String> commandnew;

    public Blast(String inputFastaFile, String blastMethod, String organism,String taxLevel) {
        
        Map<String, String> settings = Util.getSettings();
         commandnew=Arrays.asList(settings.get("blast"),
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
               // "-entrez_query",
               // "organism[Organism]");
         
//         commandnew=Arrays.asList("/usr/local/ncbi/blast/bin/blastp",
//                "-query",
//                "/Users/suresh/NetBeansProjects/OrphanGene/ORFanFX/workingdir/input.fasta",
//                "-db",
//                "nr",
//                "-outfmt",
//                "6",
//                "-max_target_seqs",
//                "1000",
//                "-evalue",
//                "1e-3",
//                "-out",
//                "/Users/suresh/NetBeansProjects/OrphanGene/ORFanFX/workingdir/blastResults.bl",
//                "-remote",
//                "-entrez_query",
//                "Mammalia[Organism]");
        
//        // construct command 
//        if("online".equals(blastMethod)){
//            command = settings.get("blast")
//                + " -query " + inputFastaFile
//                + " -db nr"
//                + " -outfmt 6"
//                + " -max_target_seqs " + settings.get("defalt_maxtargetseq")
//                + " -evalue " + settings.get("defalt_maxevalue")
//                + " -out " + settings.get("workingdir") + "blastResults.bl"
//                + " -remote"
//                + " -entrez_query '"+ taxLevel+"[Organism]'";
//        }else{
//            command = settings.get("blast")
//                + " -query " + inputFastaFile
//                + " -db " + settings.get("defalt_database")
//                + " -outfmt 6"
//                + " -max_target_seqs " + settings.get("defalt_maxtargetseq")
//                + " -evalue " + settings.get("defalt_maxevalue")
//                + " -out " + settings.get("outputFile")
//                + " -num_threads " + settings.get("defalt_threads")
//                + " -entrez_query "+ organism +"[Organism]";
//        }
    }

    public void doBlast() {
        
        //Runtime rt = Runtime.getRuntime();
        try {
            // print the blast command to the terminal
            System.out.println("BLAST Command: " + commandnew.toString());
            
            //execute the blast command
             ProcessBuilder pb = new ProcessBuilder(commandnew);
            //Process p = rt.exec(command);
             Process p = pb.start();
    
            // wait until the command get executed
            if (p.waitFor() != 0) {
                // error occured
                throw new RuntimeException("BLAST error occured");
            } else {
                System.out.println("BLAST successfully Completed!!");
            }
            System.out.println("process exit value:"+ p.exitValue());
        } catch (IOException ex) {
            System.err.println("IOError: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.err.println("InterruptedException: " + ex.getMessage());
        }
    }
}