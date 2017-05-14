/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import Karhabti.modeles.Pi_User;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HANY
 */

public class CodeDeLaRouteController implements Initializable {

@FXML
public void nivUn(ActionEvent event) throws Exception{
         Parent niv1_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/niveau1.fxml"));
         Scene niv1_screen = new Scene(niv1_parent);
         Stage nivUnStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivUnStage.hide();
         nivUnStage.setScene(niv1_screen);
         nivUnStage.show();
        
    }
@FXML
public void nivDeux(ActionEvent event) throws Exception{
         Parent niv2_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/niveau2.fxml"));
         Scene niv2_screen = new Scene(niv2_parent);
         Stage nivDeuxStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivDeuxStage.hide();
         nivDeuxStage.setScene(niv2_screen);
         nivDeuxStage.show();
        
    }
@FXML
public void nivTrois(ActionEvent event) throws Exception{
         Parent niv3_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/niveau3.fxml"));
         Scene niv3_screen = new Scene(niv3_parent);
         Stage nivTroisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivTroisStage.hide();
         nivTroisStage.setScene(niv3_screen);
         nivTroisStage.show();
        
    }
@FXML
public void nivQuatre(ActionEvent event) throws Exception{
         Parent niv4_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/niveau4.fxml"));
         Scene niv4_screen = new Scene(niv4_parent);
         Stage nivQuatreStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivQuatreStage.hide();
         nivQuatreStage.setScene(niv4_screen);
         nivQuatreStage.show();
        
    }
@FXML
public void nivCinq(ActionEvent event) throws Exception{
         Parent niv5_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/niveau5.fxml"));
         Scene niv5_screen = new Scene(niv5_parent);
         Stage nivCinqStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivCinqStage.hide();
         nivCinqStage.setScene(niv5_screen);
         nivCinqStage.show();
        
    }
@FXML
public void nivSix(ActionEvent event) throws Exception{
         Parent niv6_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/niveau6.fxml"));
         Scene niv6_screen = new Scene(niv6_parent);
         Stage nivSixStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivSixStage.hide();
         nivSixStage.setScene(niv6_screen);
         nivSixStage.show();
        
    }
 @FXML
    void annonce(ActionEvent event) throws IOException {
          Parent register_parent1 = FXMLLoader.load(getClass().getResource("/Gui/homeTest.fxml"));
                Scene register_scene1 = new Scene(register_parent1);
       
       
      Stage membresStage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
      membresStage1.hide();
      membresStage1.setScene(register_scene1);
      membresStage1.show();
    }

    @FXML
    void offre(ActionEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/Gui/Home_1.fxml"));
            System.out.println(Pi_User.getConnectedUser());
            Scene register_scene1 = new Scene(register_parent1);

            Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            membresStage1.hide();
            membresStage1.setScene(register_scene1);
            membresStage1.show();
    }

    @FXML
    void ptVente(ActionEvent event) throws IOException {
      
    }

    @FXML
    void evennement(ActionEvent event) throws IOException {
     
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
