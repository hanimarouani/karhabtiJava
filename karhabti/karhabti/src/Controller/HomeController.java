/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Annonces;
import Karhabti.modeles.User;
import Karhabti.services.AnnoncesServices;
import Karhabti.services.UserServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
 *
 * @author Mohamed
 */
public class HomeController implements Initializable {
    @FXML
    private Label nom;
@FXML
 private ImageView image;
    @FXML
    private Label Email;

    @FXML
    void Home(ActionEvent event) throws IOException {
 Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
    }

    @FXML
    void Listannonce(ActionEvent event) throws IOException {
 Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Listannonce.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
    }

    @FXML
    void Mesannonce(ActionEvent event) throws IOException {
 Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Mesannonce.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
    }

    @FXML
    void Rechercher(ActionEvent event) throws IOException {
 Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Rechercher.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
    }

    @FXML
    void ajouter(ActionEvent event) throws IOException {
 Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Ajout.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
    }

    @FXML
    void deconnexion(ActionEvent event) throws IOException {
 Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           int id = User.getConnectedUser();
           UserServices user = new UserServices();
           User user1 = user.findById(id);
           nom.setText(user1.getUsername());
           Email.setText(user1.getEmail());
                  File file=null ;
        file = new File(user1.getImage()); 
  
        Image image1 = new Image(file.toURI().toString());
      
        image.setImage(image1);
    }
    
    
    @FXML
    void ListSignalisation(ActionEvent event) throws IOException {
        
         Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/MesSignaler.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();

    }
    
}
