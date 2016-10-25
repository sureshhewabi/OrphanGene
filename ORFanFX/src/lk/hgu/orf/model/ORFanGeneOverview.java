
package lk.hgu.orf.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Suresh Hewapathirana
 */
public class ORFanGeneOverview {
    
    private final SimpleStringProperty overviewTaxonomyLevel;
    private final SimpleIntegerProperty overviewCount;

    public ORFanGeneOverview(String overviewTaxonomyLevel, Integer overviewCount) {
        this.overviewTaxonomyLevel = new SimpleStringProperty(overviewTaxonomyLevel);
        this.overviewCount = new SimpleIntegerProperty(overviewCount);
    }

    /**
     * @return the overviewTaxonomyLevel
     */
    public String getOverviewTaxonomyLevel() {
        return overviewTaxonomyLevel.get();
    }

    /**
     * @return the overviewCount
     */
    public Integer getOverviewCount() {
        return overviewCount.get();
    }

    @Override
    public String toString() {
        return "ORFanGeneOverview{" + "overviewTaxonomyLevel=" + overviewTaxonomyLevel + ", overviewCount=" + overviewCount + '}';
    }
}
