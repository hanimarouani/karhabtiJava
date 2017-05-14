/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Tests;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pidevhany.Models.mes_tests;
import pidevhany.Models.resultat;
import pidevhany.Services.impl.Reponse;
import pidevhany.Services.impl.Tests;
import pidevhany.Techniques.DataSource;

/**
 *
 * @author HANY
 */
public class testajout extends Application{
    private BorderPane root;
    private PieChart pieChart;
   public final ObservableList<PieChart.Data> detatils = FXCollections.observableArrayList();
    public void start(Stage stage) {
        Reponse reponse = new Reponse();
//        int rep = reponse.nombreTestsSucces(1);
//        System.out.println(reponse.nombreTestsSucces(1));
        
        
        detatils.addAll( new PieChart.Data("Correctes", reponse.nombreTestsSucces(1).getReponseSucces()),
                        new PieChart.Data("Fausses", reponse.nombreTestsSucces(1).getReponseEchec()));
        root = new BorderPane();
        Scene scene = new Scene(root,600,600);
        pieChart = new PieChart();
        pieChart.setData(detatils);
        pieChart.setLabelsVisible(true);
        pieChart.setLegendSide(Side.BOTTOM);
        root.setCenter(pieChart);
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[]args){
        launch(args);
//              mes_tests test = new mes_tests(1,2,"kj","jg","sf","hg","ghj","sd","sdf");
//              Tests testdeux = new Tests();
//              testdeux.ajouterTest(test);
//              testdeux.supprimerTest(60);
//              mes_tests test2 = new mes_tests(1,2,"yeschanged!!","jg","sf","hg","ghj","sd","sdf");
//              testdeux.modifierTest(test2,61);
//              testdeux.getField("mes_tests","responseforja",1);
//              testdeux.getAllTest();
//              testdeux.getAllTestNiveau(2);
               


    }
    
}
