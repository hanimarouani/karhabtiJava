/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidevhany.Models.mes_tests;
import pidevhany.Services.impl.Tests;
import pidevhany.Services.interfaces.Itests;

/**
 * FXML Controller class
 *
 * @author HANY
 */
public class ListeTestsController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXListView listView;
    
     Tests test = new Tests();
    // mes_tests mesTests = (mes_tests) listView.getSelectionModel().getSelectedItem();
     
     private IntegerProperty index = new SimpleIntegerProperty();
    public ObservableList<mes_tests> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
Itests testservice = new Tests();
        List<mes_tests> listtests = testservice.getAllTest();
        File file = null;
        File file2 = null;
        ImageView imagev;
        ImageView separateur,separateur2,separateur3,separateur4,separateur5;
        java.awt.Image image=null;
        VBox vb = new VBox();
        int i =0;
        listView.setExpanded(true);
        listView.depthProperty().set(1);
        for(mes_tests test : listtests){
        
        file = new File("D:\\Esprit\\Atelier Java\\piweb\\pidevHany\\src\\pidevHany\\images\\"+test.getImage());  
        imagev = new ImageView( new javafx.scene.image.Image(file.toURI().toString()) {});
        file2 = new File("D:\\Esprit\\Atelier Java\\piweb\\pidevHany\\src\\pidevHany\\images\\sep2.png"); 
        separateur = new ImageView( new javafx.scene.image.Image(file2.toURI().toString()) {});
        separateur2 = new ImageView( new javafx.scene.image.Image(file2.toURI().toString()) {});
        separateur3 = new ImageView( new javafx.scene.image.Image(file2.toURI().toString()) {});
        separateur4 = new ImageView( new javafx.scene.image.Image(file2.toURI().toString()) {});
        separateur5 = new ImageView( new javafx.scene.image.Image(file2.toURI().toString()) {});
        imagev.setFitHeight(80);
        imagev.setFitWidth(80);
        separateur.setFitHeight(40);
        separateur.setFitWidth(40);
        
        separateur2.setFitHeight(40);
        separateur2.setFitWidth(40);
        
        separateur3.setFitHeight(40);
        separateur3.setFitWidth(40);
        
        separateur4.setFitHeight(40);
        separateur4.setFitWidth(40);
        
        separateur5.setFitHeight(40);
        separateur5.setFitWidth(40);
        Label l1=new Label(test.getQuestion());
        Label l6=new Label("     ");
        Label l2=new Label(test.getChoix2());
        Label l3=new Label(test.getChoix3());
        String Choix4 = test.getChoix4();
        Label l4=new Label(Choix4);
        Label l5=new Label(test.getResponsej());
        HBox H = new HBox();       
            if (Choix4== null) {
                H.getChildren().addAll(imagev,separateur,l1,separateur2,l2,separateur3,l3,separateur4,l5);
            }
            else
            {
                H.getChildren().addAll(imagev,separateur,l1,separateur2,l2,separateur3,l3,separateur4,l4,separateur5,l5);
            }
        
        listView.getItems().add(H);
}
    }    
    
}
