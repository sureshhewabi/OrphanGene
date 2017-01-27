
package lk.hgu.orf.control;

import java.io.IOException;
import java.util.Map;
import lk.hgu.orf.util.Util;

/**
 *
 * @author Suresh Hewapathirana
 */
public class ORFan {
    
     // ORFan command to be executed from command-line
    private String command = "";

    public ORFan(String organismTaxonomyID) {
        
         Map<String, String> settings = Util.getSettings();
        
         // building the command by combining settings
        command = settings.get("ORFanFinder") +
                " -query ./workingdir/blastResults.bl" +
                " -id ./workingdir/IDFile.id" +
                " -nodes ./workingdir/nodes.txt" + 
                " -names ./workingdir/names.txt" + 
                " -db " + settings.get("defalt_database") +
                " -tax " + organismTaxonomyID +  
                " -threads " + settings.get("defalt_threads") +
                " -out " + settings.get("ORFan_outputfile");       
    }
    
    public void findORFanGenes(){
        
         Runtime rt = Runtime.getRuntime();
        try {
            System.out.println("ORF Command: " + command);
            // execute the command
            Process p = rt.exec(command);

            // wait until command get executed
            if (p.waitFor() != 0) {
                throw new RuntimeException("ORF error occured");
            } else {
                System.out.println("ORF successfully Completed!!");
            }
        } catch (IOException ex) {
            System.err.println("IOError: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.err.println("InterruptedException: " + ex.getMessage());
        }
    }
}
