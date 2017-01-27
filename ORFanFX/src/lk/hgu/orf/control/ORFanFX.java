
package lk.hgu.orf.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Suresh Hewapathirana
 */
public class ORFanFX extends Application {
    
    @Override
    public void start(Stage window) throws Exception {
        // locate the main form to start the application
        Parent root = FXMLLoader.load(getClass().getResource("/lk/hgu/orf/view/MainForm.fxml"));
    
        Scene scene = new Scene(root);
       
        window.setScene(scene);
        window.setTitle("ORFan Finder");
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
