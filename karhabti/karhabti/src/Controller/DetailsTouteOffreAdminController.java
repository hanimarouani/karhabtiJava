/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Offre;
import Karhabti.services.OffreService;
import Karhabti.interfaces.IOffreService;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class DetailsTouteOffreAdminController implements Initializable {
        @FXML
    private Label ddnomoffre;

    @FXML
    private Label ddnompointdevente;

    @FXML
    private Label ddprixinitial;

    @FXML
    private Label ddprixapresremise;

    @FXML
    private Label dddescription;

    @FXML
    private ImageView dimageoffre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
               IOffreService offreService = new OffreService();
        AdminListeTouteOffresController z = new AdminListeTouteOffresController();
       
        Offre x = offreService.findById(Integer.parseInt(AdminListeTouteOffresController.getLabelid()));
        ddnomoffre.setText(x.getNom_offre());
        dddescription.setText(x.getDescription());
        ddprixinitial.setText(String.valueOf(x.getPrixinit()));
        ddnompointdevente.setText(x.getPtvente().getNom());
        Float tr = x.getTaux_remise();
        Float pr = x.getPrixinit()-(x.getPrixinit()*tr)/100 ;
        ddprixapresremise.setText(String.valueOf(pr));
        File file=null ;
        file = new File(x.getPhoto()); 
  
        Image image = new Image(file.toURI().toString());
      
        dimageoffre.setImage(image);
        // TODO
    }    
        @FXML
    void detailstouteoffretooffreconfirmé(ActionEvent event) throws IOException {
         Parent parent1 = FXMLLoader.load(getClass().getResource("/GUI/AdminListeTouteOffres.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();

    }

    @FXML
    void detailstouteoffretooffrenonconfirme(ActionEvent event) throws IOException {
           Parent parent2 = FXMLLoader.load(getClass().getResource("/GUI/AdminListOffres.fxml"));
       Scene scene2 = new Scene(parent2);
        
       Stage stage2=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage2.hide();
       stage2.setScene(scene2);
       stage2.show();

    }
    
}
