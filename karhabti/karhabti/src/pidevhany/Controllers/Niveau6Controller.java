/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pidevhany.Models.mes_tests;
import pidevhany.Services.impl.Reponse;
import pidevhany.Services.impl.Tests;
import pidevhany.Services.interfaces.Itests;
import pidevhany.Techniques.DataSource;

/**
 * FXML Controller class
 *
 * @author HANY
 */
public class Niveau6Controller implements Initializable {
    public static int repSuccniv6;
    public static int repEchniv6;
    List<String> response = new ArrayList<String>();
@FXML
    private ScrollPane container;
       
@FXML
public void Retour(ActionEvent event) throws Exception{
         Parent Retour_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/CodeDeLaRoute.fxml"));
         Scene Retour_screen = new Scene(Retour_parent);
         Stage RetourStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         RetourStage.hide();
         RetourStage.setScene(Retour_screen);
         RetourStage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   Connection connection = DataSource.getInstane().getConnection();
        Itests testservice = new Tests();
        List<mes_tests> listtests = testservice.getAllTestNiveau(3);
        File file = null;
        ImageView imagev;
        Image image=null;
        VBox vb = new VBox();
        int i =0;
      
      
for(mes_tests test : listtests){
    GridPane grid = new GridPane();
    VBox Vtest= new VBox();
        
        file = new File("D:\\Esprit\\Atelier Java\\piweb\\pidevHany\\src\\pidevHany\\images\\"+test.getImage());  
        imagev = new ImageView( new javafx.scene.image.Image(file.toURI().toString()) {});
        imagev.setFitHeight(300);
        imagev.setFitWidth(500);
        grid.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        Label l1=new Label(test.getQuestion());
        VBox V = new VBox();
        String choix2 = test.getChoix2();
        String choix3 = test.getChoix3();
        String choix4 = test.getChoix4();
        RadioButton r1;
        RadioButton r2;
        RadioButton r3 = null;
        ToggleGroup g1;
        
        if (choix4==null) {
            g1 = new ToggleGroup();
             r1 = new RadioButton(choix2);
             r1.setToggleGroup(g1);
            r2 = new RadioButton(choix3);
            r2.setToggleGroup(g1);
            V.getChildren().addAll(r1,r2);
        }
        else
        {
            g1 = new ToggleGroup();
            r1 = new RadioButton(choix2);
            r2 = new RadioButton(choix3);
            r3 = new RadioButton(choix4);
            r1.setToggleGroup(g1);
            r2.setToggleGroup(g1);
            r3.setToggleGroup(g1);
            V.getChildren().addAll(r1,r2,r3);
        }
          Vtest.getChildren().addAll(imagev,l1,V);    
        vb.getChildren().addAll(Vtest);
            
        g1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

        @Override
        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
            if(r1.isSelected())
            {
                response.add(r1.getText());
            }
            else if(r2.isSelected())
            {
                response.add(r2.getText());
            }

        }
    });
                
}
        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(vb);
        
  container.setContent(pane);
    }
    
    @FXML
    void valider(ActionEvent event) throws IOException {
        Reponse reponse = new Reponse();
  Itests testservice = new Tests();
  List<String> responsebase = testservice.getAllResponses(3);
  Iterator<String> iter1 = responsebase.iterator();
  Iterator<String> iter2 = response.iterator();
        while (iter1.hasNext()&& iter2.hasNext()) {
            if(iter1.next().toString().equals(iter2.next().toString())){
                repSuccniv6++;
            }
            else
            {   
                repEchniv6++;
            }
        }
        System.out.println("reponses correctes = "+repSuccniv6);
        System.out.println("reponses fausses = "+repEchniv6);
        if (repSuccniv6>repEchniv6) {
            reponse.updateSucce(reponse.nombreTestsSucces(3).getReponseSucces()+1, 1);
        }
        else if (repSuccniv6<repEchniv6) {
            reponse.updateEchec(reponse.nombreTestsSucces(3).getReponseEchec()+1, 1);
        }
        Parent Retour_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/ResultatDuTest6.fxml"));
         Scene Retour_screen = new Scene(Retour_parent);
         Stage RetourStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         RetourStage.hide();
         RetourStage.setScene(Retour_screen);
         RetourStage.show();
    }  
    
}
