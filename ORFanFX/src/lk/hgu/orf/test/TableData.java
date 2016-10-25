
package lk.hgu.orf.test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.hgu.orf.model.BlastResult;
import lk.hgu.orf.model.ORFGene;
import lk.hgu.orf.model.ORFanGeneOverview;

/**
 *
 * @author Suresh Hewapathirana
 */
public class TableData {
    
    public ObservableList<ORFGene> getORFGeneData(){
        
        ObservableList<ORFGene> data = FXCollections.observableArrayList(
            new ORFGene("Jacob", "Smith", "jacob.smith@example.com","dsfsdf"),
            new ORFGene("Isabella", "Johnson", "isabella.johnson@example.com","dsfsdf"),
            new ORFGene("Ethan", "Williams", "ethan.williams@example.com","dsfsdf"),
            new ORFGene("Emma", "Jones", "emma.jones@example.com","dsfsdf"),
            new ORFGene("Michael", "Brown", "michael.brown@example.com","dsfsdf")
        );
        return data;
    }
    
    public ObservableList<ORFanGeneOverview> getORFGeneOverviewData(){
        
        ObservableList<ORFanGeneOverview> data = FXCollections.observableArrayList(
            new ORFanGeneOverview("Jacob", 3),
            new ORFanGeneOverview("Isabella", 5),
            new ORFanGeneOverview("Ethan", 6)
        );
        return data;
    }
    
        public ObservableList<BlastResult> getBlastResultsData(){
        
        ObservableList<BlastResult> data = FXCollections.observableArrayList(
            new BlastResult(1, "Smith", "jacob.smith@example.com","dsfsdf"),
            new BlastResult(2, "Johnson", "isabella.johnson@example.com","dsfsdf"),
            new BlastResult(3, "Williams", "ethan.williams@example.com","dsfsdf"),
            new BlastResult(4, "Jones", "emma.jones@example.com","dsfsdf"),
            new BlastResult(5, "Brown", "michael.brown@example.com","dsfsdf")
        );
        return data;
    }
}
