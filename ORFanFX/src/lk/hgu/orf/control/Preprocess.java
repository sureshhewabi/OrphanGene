/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.hgu.orf.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.hgu.orf.view.MainFormController;

/**
 *
 * @author Suresh Hewapathirana
 */
public class Preprocess {

    private final String extractIdsFromFasta = "/Users/hgu/NetBeansProjects/OrphanGene/ORFanFX/src/settings/scripts/extractIdsFromFasta";
    private final String idFile = "IDFile.id";

    public void createIDFile(String inputFile) {

        try {
            String inputFileName = inputFile;

            ProcessBuilder pb = new ProcessBuilder(extractIdsFromFasta, inputFileName, idFile);
            Process p = pb.start();
            System.out.println("INFO: ID file created!!");
        } catch (IOException ex) {
            Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
