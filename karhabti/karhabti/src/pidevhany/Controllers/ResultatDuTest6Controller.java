/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HANY
 */
public class ResultatDuTest6Controller implements Initializable {
@FXML
    private Label repFausses;

    @FXML
    private Label repCorrecte;

    @FXML
    void imprimerRes(ActionEvent event) throws IOException {
        GeneratePDF pdf;
        String ReponsesCorretes = "Nombre de réponses Correctes = "+Niveau6Controller.repSuccniv6;
        String ReponsesFausses = "Nombre de réponses fausses = "+Niveau6Controller.repEchniv6;
        
        pdf = new GeneratePDF(ReponsesCorretes, ReponsesFausses);
    }

    @FXML
    void Retour(ActionEvent event) throws IOException {
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
        repCorrecte.setText("Nombre de réponses Correctes = "+Niveau6Controller.repSuccniv6);
        repFausses.setText("Nombre de réponses fausses = "+Niveau6Controller.repEchniv6);
    }   
    
}
