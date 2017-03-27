
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
    private String command = "";
    List<String> commandnew;

    public ORFan(String organismTaxonomyID) {
        
         Map<String, String> settings = Util.getSettings();
        
         // building the command by combining settings
//        command = settings.get("ORFanFinder") +
//                " -query "+ settings.get("workingdir")+"blastResults.bl" +
//                " -id "+ settings.get("workingdir")+"IDFile.id" +
//                " -nodes "+ settings.get("workingdir")+"nodes.txt" + 
//                " -names "+ settings.get("workingdir")+"names.txt" + 
//                " -db " + settings.get("defalt_database") +
//                " -tax " + organismTaxonomyID +  
//                " -threads " + settings.get("defalt_threads") +
//                " -out " + settings.get("workingdir")+"orfanResults.csv";    
        
         commandnew=Arrays.asList(settings.get("ORFanFinder"),
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
        
//         Runtime rt = Runtime.getRuntime();
        try {
            System.out.println("ORF Command: " + commandnew.toString());
            // execute the command
//            Process p = rt.exec(command);
             ProcessBuilder pb = new ProcessBuilder(commandnew);
             Process p = pb.start();

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
