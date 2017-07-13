
package lk.hgu.orf.control;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lk.hgu.orf.util.Util;

/**
 *
 * @author Suresh Hewapathirana
 */
public class ORFan {
    
     // ORFan command to be executed from command-line
    List<String> command;

    public ORFan(String organismTaxonomyID) {
        
         Map<String, String> settings = Util.getSettings();
        
         // building the command by combining settings
     command = Arrays.asList(settings.get("ORFanFinder"),
                 "-query",
                 settings.get("workingdir")+"blastResults.bl",
                 "-id",
                 settings.get("workingdir")+"IDFile.id",
                 "-nodes",
                 settings.get("workingdir")+"nodes.txt",
                 "-names",
                 settings.get("workingdir")+"names.txt",
                 "-db",
                 settings.get("defalt_database"),
                 "-tax",
                 organismTaxonomyID,
                 "-threads",
                 settings.get("defalt_threads"),
                 "-out",
                 settings.get("workingdir")+"orfanResults.csv"
                 );
                 
    }
    
    public void findORFanGenes(){
        
        try {
            System.out.println("ORF Command: " + command.toString());
            System.out.println("Be patient...This will take 2-15 min...");
           
            // execute the command
             ProcessBuilder pb = new ProcessBuilder(command);
             Process p = pb.start();

            // wait until command get executed
            if (p.waitFor() != 0) {
                throw new RuntimeException("ORF error occured");
            } else {
                System.out.println("ORF successfully Completed!!");
            }
            System.out.println((p.exitValue() == 0) ? "ORFanFinder ran Successfully":"ORFanFinder Failed with " + p.exitValue() + " value");
        } catch (IOException ex) {
            System.err.println("IOError: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.err.println("InterruptedException: " + ex.getMessage());
        }
    }
}
