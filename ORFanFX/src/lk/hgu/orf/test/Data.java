
package lk.hgu.orf.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.hgu.orf.util.Util;

/**
 *
 * @author Suresh Hewapathirana
 */
public class Data {
    
    Map<String,String> taxonomyNodeTable= new  HashMap<>();
    Map<String,String> speciesTable= new  HashMap<>();
    ObservableList<String> taxLevels = FXCollections.observableArrayList();
    
    /*
    * This function reads the entire NCBI species file (names.txt) and 
    * extract only the species name and finally return a list of organisms
    * available in the names.txt file
    */
    public ObservableList<String> getOranisms() throws IOException{
    
         // load settings from the settings config file
        Map<String, String> settings = Util.getSettings();
        ObservableList<String> organisms = FXCollections.observableArrayList();
        
        
         try (BufferedReader br = new BufferedReader(new FileReader(settings.get("defalt_species")))) {

            // variable hold to each line of the file
            String line;

            // read line by line
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\t");
                speciesTable.put(columns[0],columns[1]);
                // add each orgamism to the collection
                organisms.add(columns[1] + " (" + columns[0] + ")");
            }
         }
        return organisms;
    }
    
    public void getFullTaxonomy() throws FileNotFoundException, IOException{
        
          // load settings from the settings config file
        Map<String, String> settings = Util.getSettings();
        
         try (BufferedReader br = new BufferedReader(new FileReader(settings.get("defalt_taxonomy")))) {

            // variable hold to each line of the file
            String line;

            // read line by line
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\t");
                // add each level and the parent level to the collection
                taxonomyNodeTable.put(columns[0], columns[1]);
//                if(columns[0].equals(selectedOrganismTaxID)){
//                    break;
//                }
            }
         }
    }
    
    public ObservableList<String> getTaxLevels(String selectedOrganismTaxID) {
       
        try {
            getFullTaxonomy();
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        findParent(selectedOrganismTaxID);

        return taxLevels;
    }

    private String findParent(String organismTaxId) {
        taxLevels.add(speciesTable.get(organismTaxId));
        System.out.println("findParent ---> organismTaxId:"+organismTaxId+ speciesTable.get(organismTaxId));
        if (taxonomyNodeTable.get(organismTaxId).equals("1")) {
            return "";
        } else {
            return findParent(taxonomyNodeTable.get(organismTaxId));
        }
    }
}
