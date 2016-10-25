
package lk.hgu.orf.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Suresh Hewapathirana
 */
public class BlastResult {
    
    private final SimpleIntegerProperty detailTableId;
    private final SimpleStringProperty detailTableRankName;
    private final SimpleStringProperty detailTableTaxLevel;
    private final SimpleStringProperty detailTableParentTaxLevel;

    public BlastResult(Integer detailTableId, String detailTableRankName, String detailTableTaxLevel, String detailTableParentTaxLevel) {
        this.detailTableId = new SimpleIntegerProperty(detailTableId);
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
        return "BlastResult{" + "detailTableId=" + detailTableId + ", detailTableRankName=" + detailTableRankName + ", detailTableTaxLevel=" + detailTableTaxLevel + ", detailTableParentTaxLevel=" + detailTableParentTaxLevel + '}';
    }
}
