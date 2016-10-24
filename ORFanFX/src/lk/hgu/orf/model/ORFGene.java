package lk.hgu.orf.model;

/**
 *
 * @author Suresh Hewapathirana
 */
public class ORFGene {

    private String GeneID;
    private String GeneName;
    private String ORFanGeneLevel;
    private String TaxonomyLevel;

    /**
     * @return the GeneID
     */
    public String getGeneID() {
        return GeneID;
    }

    /**
     * @param GeneID the GeneID to set
     */
    public void setGeneID(String GeneID) {
        this.GeneID = GeneID;
    }

    /**
     * @return the GeneName
     */
    public String getGeneName() {
        return GeneName;
    }

    /**
     * @param GeneName the GeneName to set
     */
    public void setGeneName(String GeneName) {
        this.GeneName = GeneName;
    }

    /**
     * @return the ORFanGeneLevel
     */
    public String getORFanGeneLevel() {
        return ORFanGeneLevel;
    }

    /**
     * @param ORFanGeneLevel the ORFanGeneLevel to set
     */
    public void setORFanGeneLevel(String ORFanGeneLevel) {
        this.ORFanGeneLevel = ORFanGeneLevel;
    }

    /**
     * @return the TaxonomyLevel
     */
    public String getTaxonomyLevel() {
        return TaxonomyLevel;
    }

    /**
     * @param TaxonomyLevel the TaxonomyLevel to set
     */
    public void setTaxonomyLevel(String TaxonomyLevel) {
        this.TaxonomyLevel = TaxonomyLevel;
    }
}
