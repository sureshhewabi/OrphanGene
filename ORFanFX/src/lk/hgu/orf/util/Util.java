package lk.hgu.orf.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Suresh Hewapathirana
 */
public class Util {

    public static Map<String, String> getSettings() {

        Map<String, String> settings = new HashMap<>();
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load the property file
            prop.load(input);

            // save settings in a hash table
            settings.put("defalt_database", prop.getProperty("defalt_database"));
            settings.put("defalt_species", prop.getProperty("defalt_species"));
            settings.put("defalt_taxonomy", prop.getProperty("defalt_taxonomy"));
            settings.put("defalt_maxevalue", prop.getProperty("defalt_maxevalue"));
            settings.put("defalt_maxtargetseq", prop.getProperty("defalt_maxtargetseq"));
            settings.put("defalt_threads", prop.getProperty("defalt_threads"));
            settings.put("defalt_blastmethod", prop.getProperty("defalt_blastmethod"));
            
            settings.put("blast", prop.getProperty("blast"));
            settings.put("outputFile", prop.getProperty("outputFile"));
            settings.put("outfmt", prop.getProperty("outfmt"));
            
            settings.put("ORFanFinder", prop.getProperty("ORFanFinder"));
            

        } catch (IOException ex) {
            System.out.println(" IOError: " + ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    // in the case of unsucceessful attempt, close the input
                    input.close();
                } catch (IOException e) {
                    System.out.println(" IOError: " + e.getMessage());
                }
            }
        }
        return settings;

    }

}
