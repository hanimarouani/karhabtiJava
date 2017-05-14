/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import pidevhany.Services.impl.Reponse;

/**
 * FXML Controller class
 *
 * @author HANY
 */
public class SatatisticsController implements Initializable {
    
    @FXML
    private BorderPane mainPaneStat;
    @FXML
    private ScrollPane contenu;
     
     @FXML 
    public void niveauUn(ActionEvent event) throws IOException
    {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/statisticNiv1.fxml"));
        contenu.setContent(pane);
        
    }
     @FXML 
    public void niveauDeux(ActionEvent event) throws IOException
    {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/statisticNiv2.fxml"));
        contenu.setContent(pane);
        
    }
     @FXML 
    public void niveauTrois(ActionEvent event) throws IOException
    {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/statisticNiv3.fxml"));
        contenu.setContent(pane);
        
    }
     @FXML 
    public void niveauQuatre(ActionEvent event) throws IOException
    {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/statisticNiv4.fxml"));
        contenu.setContent(pane);
        
    }
     @FXML 
    public void niveauCinq(ActionEvent event) throws IOException
    {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/statisticNiv5.fxml"));
        contenu.setContent(pane);
        
    }
     @FXML 
    public void niveauSix(ActionEvent event) throws IOException
    {
        BorderPane pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/statisticNiv6.fxml"));
        contenu.setContent(pane);
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      

    }    
    
}
