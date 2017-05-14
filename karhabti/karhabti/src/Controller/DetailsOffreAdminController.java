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
public class DetailsOffreAdminController implements Initializable {
    
    @FXML
    private Label dnomoffre;

    @FXML
    private Label dnompointdevente;

    @FXML
    private Label dprixinitial;

    @FXML
    private Label dprixapresremise;

    @FXML
    private Label ddescription;

    @FXML
    private ImageView imageoffre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        IOffreService offreService = new OffreService();
        AdminListOffresController z = new AdminListOffresController();
       
        Offre x = offreService.findById(Integer.parseInt(AdminListOffresController.getLabelid()));
        dnomoffre.setText(x.getNom_offre());
        ddescription.setText(x.getDescription());
        dprixinitial.setText(String.valueOf(x.getPrixinit()));
        dnompointdevente.setText(x.getPtvente().getNom());
        Float tr = x.getTaux_remise();
        Float pr = x.getPrixinit()-(x.getPrixinit()*tr)/100 ;
        dprixapresremise.setText(String.valueOf(pr));
        File file=null ;
        file = new File(x.getPhoto()); 
  
        Image image = new Image(file.toURI().toString());
      
        imageoffre.setImage(image);
        
        // TODO
    }    
    
        @FXML
    void admindetailstolist(ActionEvent event) throws IOException {
       Parent parent4 = FXMLLoader.load(getClass().getResource("/GUI/AdminListOffres.fxml"));
       Scene scene4 = new Scene(parent4);
        
       Stage stage4=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage4.hide();
       stage4.setScene(scene4);
       stage4.show();

    }
        @FXML
    void detailsoffreadmintoadminlistconfirmé(ActionEvent event) throws IOException {
        Parent parent1 = FXMLLoader.load(getClass().getResource("/GUI/AdminListeTouteOffres.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();


    }
    
}
