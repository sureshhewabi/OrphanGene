/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.hgu.orf.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.hgu.orf.util.Util;

/**
 *
 * @author Suresh Hewapathirana
 */
public class Data {
    
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
                organisms.add(columns[1] + " (" + columns[0] + ")");
            }
         }
        return organisms;
    }
    
}
