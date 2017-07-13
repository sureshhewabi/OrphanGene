package lk.hgu.orf.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 

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
            // read data from settings file
            input = new FileInputStream("config.properties");

            // load the property file
            prop.load(input);

            // save settings in a hash table
            settings.put("defalt_database", prop.getProperty("defalt_database"));
            settings.put("defalt_species", prop.getProperty("defalt_species"));
            settings.put("defalt_taxonomy", prop.getProperty("defalt_taxonomy"));
            settings.put("example_protseq", prop.getProperty("example_protseq"));
            settings.put("workingdir", prop.getProperty("workingdir"));
            
            // settings for preprocessing step
            settings.put("extractIdsFromFasta", prop.getProperty("extractIdsFromFasta"));
            settings.put("idFile", prop.getProperty("idFile"));
            
            // settings for BLAST step
            settings.put("defalt_maxevalue", prop.getProperty("defalt_maxevalue"));
            settings.put("defalt_maxtargetseq", prop.getProperty("defalt_maxtargetseq"));
            settings.put("defalt_threads", prop.getProperty("defalt_threads"));
            settings.put("defalt_blastmethod", prop.getProperty("defalt_blastmethod"));
            settings.put("blast", prop.getProperty("blast"));
            settings.put("outputFile", prop.getProperty("outputFile"));
            settings.put("outfmt", prop.getProperty("outfmt"));

            // settings for ORFanFinder step
            settings.put("ORFanFinder", prop.getProperty("ORFanFinder"));
            settings.put("ORFan_outputfile", prop.getProperty("ORFan_outputfile"));
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

    public static String getExampleProtSeq() throws FileNotFoundException, IOException {

        String sequence = "";
        Map<String, String> settings = Util.getSettings();

        // read the example FASTA file line by line
        BufferedReader in = new BufferedReader(new FileReader(settings.get("example_protseq")));
        String line = in.readLine();
        while (line != null) {
            sequence += line + "\n";
            line = in.readLine();
        }
        return sequence;
    }

    Map<String,String> taxonomyNodeTable= new  HashMap<>();
    Map<String,String> speciesTable= new  HashMap<>();
     JSONArray taxLevels;
    
    /*
    * This function reads the entire NCBI species file (names.txt) and 
    * extract only the species name and finally return a list of organisms
    * available in the names.txt file
    */
    public List<JSONObject> getOranisms() throws IOException{
    
        getFullTaxonomy();
        
         // load settings from the settings config file
        Map<String, String> settings = Util.getSettings();
        List<JSONObject> organisms = new ArrayList<>();
        
         try (BufferedReader br = new BufferedReader(new FileReader(settings.get("workingdir")+"names.txt"))) {

            // variable hold to each line of the file
            String line;

            // read line by line
            while ((line = br.readLine()) != null) {
                
                String[] columns = line.split("\t");
                speciesTable.put(columns[0],columns[1]);
                if("511145".equals(columns[0])){
                    // add each orgamism to the collection
                   JSONObject obj = new JSONObject();
                    obj.put("SpeciesName",columns[1]);
                    obj.put("NCBITaxID",columns[0]);
                    JSONArray taxonomy = new JSONArray();
                    taxonomy = getTaxLevels(columns[0]);
                    if(taxonomy !=null){
                        obj.put("Taxonomy", taxonomy);
                    }
                    organisms.add(obj);
                }
            }
         }
         
         try (FileWriter file = new FileWriter("/Users/Suresh/Documents/TaxData.json")) {
            for (JSONObject organism : organisms) {
                file.write(organism.toJSONString());
            }
		System.out.println("Successfully Copied JSON Object to File...");
	}
        return organisms;
    }
    
    public void getFullTaxonomy() throws FileNotFoundException, IOException{
        
          // load settings from the settings config file
        Map<String, String> settings = Util.getSettings();
        
         try (BufferedReader br = new BufferedReader(new FileReader(settings.get("workingdir")+"nodes.txt"))) {

            // variable hold to each line of the file
            String line;

            // read line by line
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\t");
                // add each level and the parent level to the collection
                taxonomyNodeTable.put(columns[0], columns[1]);
            }
         }
    }
    
    public JSONArray getTaxLevels(String selectedOrganismTaxID) throws IOException {
       taxLevels = new JSONArray();
     
        getFullTaxonomy();
        
        findParent(selectedOrganismTaxID);

        return taxLevels;
    }

    private String findParent(String organismTaxId) {
        if((speciesTable.get(organismTaxId) != null)&&(!organismTaxId.equals("1"))){
            taxLevels.add("Taxonomy:"+ speciesTable.get(organismTaxId));
        }
       // System.out.println("findParent ---> organismTaxId:"+organismTaxId+ speciesTable.get(organismTaxId));
       // if (taxonomyNodeTable.get(organismTaxId).equals("1")) {
       if(organismTaxId.equals("1")){
         // System.out.println(taxLevels.toString());
            return "";
        } else {
            return findParent(taxonomyNodeTable.get(organismTaxId));
        }
    }
}
