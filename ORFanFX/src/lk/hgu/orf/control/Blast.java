package lk.hgu.orf.control;

import java.io.IOException;

/**
 * This is the controller class which executes the Blast script as a subprocess
 * 
 * @author Suresh Hewapathirana
 */
public class Blast {

    // // BLAST Script
    private final String blast = "/Users/hgu/NetBeansProjects/OrphanGene/ORFanFX/src/settings/scripts/blast";

    // filename of the output file
    private final String outputFile = "blastoutput.bl";

    // // output format - tab delimmeterd file(6)
    private final String outfmt = "6";

    /**
     * This method collect all the parameters required to do the blast and
     * executes the bash script written for BLAST
     *
     * @param query protein FASTA sequence or file
     * @param db local database to blast [not used if it is a online(remote)
     * BLAST]
     * @param max_target_seqs maximum target sequences
     * @param evalue e-value to evaluate the confidancy of the hits
     * @param num_threads number of threads used for local BLAST
     * @param blastMethod Online (remote) or offline (local) mode
     */
    public void doBlast(String query, String db, String max_target_seqs, String evalue, String num_threads, String blastMethod) {

        try {
            
            // build the process
            ProcessBuilder pb = new ProcessBuilder(blast,
                    query, db, outfmt, max_target_seqs,
                    evalue, outputFile, num_threads, blastMethod);
            
            // start the process
            Process p = pb.start();

            // cause this process to stop until process p is terminated
            //p.waitFor();
            if(p.waitFor()!=0) throw new RuntimeException("error occured");
            
            // done
            System.out.println("Blast Completed!!");
        } catch (IOException ex) {
            System.err.println("IOError: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.err.println("InterruptedException: " + ex.getMessage());
        }
    }

}
