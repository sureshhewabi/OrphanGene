package lk.hgu.orf.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Suresh Hewapathirana
 */
public class ORFGene {

    private final SimpleStringProperty GeneId;
    private final SimpleStringProperty GeneName;
    private final SimpleStringProperty ORFanGeneLevel;
    private final SimpleStringProperty TaxonomyLevel;

    public ORFGene(String GeneId, String GeneName, String ORFanGeneLevel, String TaxonomyLevel) {
        this.GeneId = new SimpleStringProperty(GeneId);
        this.GeneName = new SimpleStringProperty(GeneName);
        this.ORFanGeneLevel = new SimpleStringProperty(ORFanGeneLevel);
        this.TaxonomyLevel = new SimpleStringProperty(TaxonomyLevel);
    }

    /**
     * @return the GeneID
     */
    public String getGeneId() {
        return GeneId.get();
    }

    /**
     * @return the GeneName
     */
    public String getGeneName() {
        return GeneName.get();
    }

    /**
     * @return the ORFanGeneLevel
     */
    public String getORFanGeneLevel() {
        return ORFanGeneLevel.get();
    }

    /**
     * @return the TaxonomyLevel
     */
    public String getTaxonomyLevel() {
        return TaxonomyLevel.get();
    }

    @Override
    public String toString() {
        return "ORFGene{" + "GeneID=" + GeneId + ", GeneName=" + GeneName + ", ORFanGeneLevel=" + ORFanGeneLevel + ", TaxonomyLevel=" + TaxonomyLevel + '}';
    }

    
}
