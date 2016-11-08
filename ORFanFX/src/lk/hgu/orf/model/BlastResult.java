
package lk.hgu.orf.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Suresh Hewapathirana
 */
public class BlastResult {
    
    private final SimpleIntegerProperty detailTableId;
    private final SimpleStringProperty detailTableGene;
    private final SimpleStringProperty detailTableRankName;
    private final SimpleStringProperty detailTableTaxLevel;
    private final SimpleStringProperty detailTableParentTaxLevel;

    public BlastResult(Integer detailTableId, String detailTableGene, String detailTableRankName, String detailTableTaxLevel, String detailTableParentTaxLevel) {
        this.detailTableId = new SimpleIntegerProperty(detailTableId);
        this.detailTableGene = new SimpleStringProperty(detailTableGene);
        this.detailTableRankName = new SimpleStringProperty(detailTableRankName);
        this.detailTableTaxLevel = new SimpleStringProperty(detailTableTaxLevel);
        this.detailTableParentTaxLevel = new SimpleStringProperty(detailTableParentTaxLevel);
    }

    /**
     * @return the detailTableId
     */
    public Integer getDetailTableId() {
        return detailTableId.get();
    }

       /**
     * @return the detailTableGene
     */
    public String getDetailTableGene() {
        return detailTableGene.get();
    }
    
    /**
     * @return the detailTableRankName
     */
    public String getDetailTableRankName() {
        return detailTableRankName.get();
    }

    /**
     * @return the detailTableTaxLevel
     */
    public String getDetailTableTaxLevel() {
        return detailTableTaxLevel.get();
    }

    /**
     * @return the detailTableParentTaxLevel
     */
    public String getDetailTableParentTaxLevel() {
        return detailTableParentTaxLevel.get();
    }

    @Override
    public String toString() {
        return "BlastResult{" + "detailTableId=" + detailTableId + ", detailTableGene=" + detailTableGene + ", detailTableRankName=" + detailTableRankName + ", detailTableTaxLevel=" + detailTableTaxLevel + ", detailTableParentTaxLevel=" + detailTableParentTaxLevel + '}';
    }

    

 
}
