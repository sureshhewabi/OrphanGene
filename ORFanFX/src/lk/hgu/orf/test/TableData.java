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

    public ObservableList<ORFGene> getORFGeneData() {

        ObservableList<ORFGene> data = FXCollections.observableArrayList(
                new ORFGene("gi|16128551|ref|NP_415100.1", "bacteriophage N4 receptor, outer membrane subunit ", "superkingdom ORFan", "Bacteria"),
                new ORFGene("gi|16128552|ref|NP_415101.1", "bacteriophage N4 receptor, inner membrane subunit", "superkingdom ORFan", "Bacteria"),
                new ORFGene("gi|16128556|ref|NP_415105.1", "periplasmic copper- and silver-binding protein", "superkingdom ORFan", "Bacteria"),
                new ORFGene("gi|16128557|ref|NP_415106.1", "copper/silver efflux system, membrane fusion protein", "superkingdom ORFan", "Bacteria"),
                new ORFGene("gi|90111296|ref|NP_416077.4", "Qin prophage; putative antitermination protein Q", "superkingdom ORFan", "Bacteria"),
                new ORFGene("gi|16128555|ref|NP_415104.1", "copper/silver efflux system, outer membrane component", "superkingdom ORFan", "Bacteria"),
                new ORFGene("gi|16128942|ref|NP_415495.1", "putative HyaA chaperone", "phylum ORFan", "Proteobacteria"),
                new ORFGene("gi|16128985|ref|NP_415540.1", "biofilm PGA synthase PgaCD, regulatory subunit; c-di-GMP-stimulated activity and dimerization", "phylum ORFan", "Proteobacteria"),
                new ORFGene("gi|16129084|ref|NP_415639.1", "inner membrane protein", "phylum ORFan", "Proteobacteria"),
                new ORFGene("gi|16129100|ref|NP_415655.1", "e14 prophage; putative SAM-dependent methyltransferase", "phylum ORFan", "Proteobacteria"),
                new ORFGene("gi|16129101|ref|NP_415656.1", "e14 prophage; putative inner membrane protein", "phylum ORFan", "Proteobacteria")
        );
        return data;
    }

    public ObservableList<ORFanGeneOverview> getORFGeneOverviewData() {

        ObservableList<ORFanGeneOverview> data = FXCollections.observableArrayList(
                new ORFanGeneOverview("Native", 0),
                new ORFanGeneOverview("Superkingdom", 6),
                new ORFanGeneOverview("Phylum", 5),
                new ORFanGeneOverview("Class", 4),
                new ORFanGeneOverview("Order", 0),
                new ORFanGeneOverview("Family", 5),
                new ORFanGeneOverview("Genus", 4),
                new ORFanGeneOverview("Species", 0),
                new ORFanGeneOverview("Strict", 2),
                new ORFanGeneOverview("TOTAL", 34)
        );
        return data;
    }

    public ObservableList<BlastResult> getBlastResultsData() {

        ObservableList<BlastResult> data = FXCollections.observableArrayList(
                new BlastResult(1, "phylum", "Verrucomicrobia", "Chlamydiae/Verrucomicrobia group"),
                new BlastResult(2, "phylum", "Cyanobacteria", "Bacteria"),
                new BlastResult(3, "phylum", "Proteobacteria", "Bacteria"),
                new BlastResult(4, "class", "Spartobacteria", "Verrucomicrobia"),
                new BlastResult(5, "class", "Alphaproteobacteria", "Proteobacteria"),
                new BlastResult(6, "class", "Betaproteobacteria", "Proteobacteria"),
                new BlastResult(7, "class", "Gammaproteobacteria", "Proteobacteria	"),
                new BlastResult(8, "order", "Salinisphaerales", "Gammaproteobacteria"),
                new BlastResult(9, "order", "Pleurocapsales", "Cyanobacteria"),
                new BlastResult(10, "order", "Rhodospirillales", "Alphaproteobacteria"),
                new BlastResult(11, "order", "Enterobacteriales", "Gammaproteobacteria"),
                new BlastResult(12, "order", "Alteromonadales", "Gammaproteobacteria"),
                new BlastResult(13, "order", "Chroococcales", "Oscillatoriophycideae"),
                new BlastResult(14, "order", "Neisseriales", "Betaproteobacteria"),
                new BlastResult(15, "order", "Burkholderiales", "Betaproteobacteria"),
                new BlastResult(16, "order", "Chromatiales", "Gammaproteobacteria"),
                new BlastResult(17, "order", "Sphingomonadales", "Alphaproteobacteria"),
                new BlastResult(18, "order", "Nostocales", "Cyanobacteria"),
                new BlastResult(19, "order", "Rhizobiales", "Alphaproteobacteria"),
                new BlastResult(20, "order", "Oceanospirillales", "Gammaproteobacteria"),
                new BlastResult(21, "order", "Vibrionales", "Gammaproteobacteria"),
                new BlastResult(22, "order", "Pseudomonadales", "Gammaproteobacteria"),
                new BlastResult(23, "order", "Xanthomonadales", "Gammaproteobacteria")
        );
        return data;
    }
}
