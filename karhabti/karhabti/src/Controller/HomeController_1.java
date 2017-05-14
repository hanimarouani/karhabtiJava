/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Pi_User;
import Karhabti.services.pi_userService;
import Karhabti.interfaces.Ipi_userService;
import Karhabti.modeles.Signalisations;
import Karhabti.modeles.User;
import Karhabti.services.AnnoncesServices;
import Karhabti.services.SignalisationsServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class HomeController_1 implements Initializable {
    
    
    @FXML
    private Label bienvenue;
    @FXML
    private Label husername;
    @FXML
    private Label hemail;
    @FXML
    private ImageView himage;
      
    @FXML
    void toaccueil(ActionEvent event) throws IOException {
        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
        

    }
          
    @FXML
    void Annonces(ActionEvent event) throws IOException {

        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/homeTest.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
                  
    }
    
    
    @FXML
    void tomesoffres(ActionEvent event) throws IOException {
        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/FXMLAfficheroffres.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();

    }
    
    @FXML
    void toajouteruneoffre(ActionEvent event) throws IOException {
        Parent parent2 = FXMLLoader.load(getClass().getResource("/Gui/Ajout_1.fxml"));
       Scene scene2 = new Scene(parent2);
        
       Stage stage2=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage2.hide();
       stage2.setScene(scene2);
       stage2.show();

    }
    @FXML
    void totoutelesoffre(ActionEvent event) throws IOException {
        Parent parent3 = FXMLLoader.load(getClass().getResource("/Gui/AfficherToutesOffres.fxml"));
       Scene scene3 = new Scene(parent3);
        
       Stage stage3=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage3.hide();
       stage3.setScene(scene3);
       stage3.show();

    }
    
    @FXML
    void sedeconnecter(ActionEvent event) throws IOException {
        
         Parent parent4 = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
       Scene scene4 = new Scene(parent4);
        
       Stage stage4=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage4.hide();
       stage4.setScene(scene4);
       stage4.show();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Ipi_userService userservice = new pi_userService();
//        Pi_User x = userservice.findById(Pi_User.getConnectedUser());
////        bienvenue.setText(x.getUsername());
//      //  husername.setText(x.getUsername());
//     //   hemail.setText(x.getEmail());
//        File file=null ;
//        file = new File(x.getImage()); 
//  
//        Image image = new Image(file.toURI().toString());
//      
//        himage.setImage(image);
        
        
    }
    
}
