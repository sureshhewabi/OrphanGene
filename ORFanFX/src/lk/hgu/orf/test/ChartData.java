/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.hgu.orf.test;

import javafx.scene.chart.XYChart;

/**
 *
 * @author Suresh Hewapathirana
 */
public class ChartData {
    
        public XYChart.Series<String, Number> getOverviewChartData() {

        // Define data
        String[] taxonomy = {"Native", "Superkingdom", "Phylum", "Class",
            "Order", "Family", "Genus", "Species", "Strict"};
        Number[] count = {0, 6, 5, 4, 0, 5, 4, 0, 2};

        // add starting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.setName("Orphan gene count");
        // create sample data
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[0], count[0]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[1], count[1]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[2], count[2]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[3], count[3]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[4], count[4]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[5], count[5]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[6], count[6]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[7], count[7]));
        series1.getData().add(new XYChart.Data<String, Number>(taxonomy[8], count[8]));
       
        return series1;
    }
    
    public XYChart.Series<String, Number> getBlastChartData() {

//        // Define data
        String[] levels = {"Phylum", "Class", "Order", "Family", "Genus", "Species", "Subspecies"};
        Number[] hits = {3, 4, 16, 19, 41, 132, 430};

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Matching hits");

        // create sample data
        series2.getData().add(new XYChart.Data<String, Number>(levels[0], hits[0]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[1], hits[1]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[2], hits[2]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[3], hits[3]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[4], hits[4]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[5], hits[5]));
        series2.getData().add(new XYChart.Data<String, Number>(levels[6], hits[6]));
        
        return series2;
    }
    
}
