/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import pidevhany.Services.impl.Reponse;

/**
 * FXML Controller class
 *
 * @author HANY
 */
public class StatisticNiv2Controller implements Initializable {
@FXML
private BorderPane niv1Pane;
private PieChart pieChart;
public final ObservableList<PieChart.Data> detatils = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Reponse reponse = new Reponse();
         int repCorrectes= reponse.nombreTestsSucces(2).getReponseSucces();
         int repFausses =reponse.nombreTestsSucces(2).getReponseEchec();
         detatils.addAll( new PieChart.Data("Correctes = "+repCorrectes, repCorrectes),
                        new PieChart.Data("Fausses = "+repFausses, repFausses));
         pieChart = new PieChart();
        pieChart.setData(detatils);
        pieChart.setLabelsVisible(true);
        pieChart.setLegendSide(Side.BOTTOM);
        niv1Pane.setCenter(pieChart);
    }    
    
}
