/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Pi_User;
import Karhabti.modeles.User;
import Karhabti.services.UserServices;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class HomeTestController implements Initializable {
    @FXML
    private Label nom;

    @FXML
    private Label Email;

    @FXML
    private ImageView image;
    @FXML
    private ScrollPane mainPane;
    @FXML
    void deconnexion(ActionEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));

                    Scene register_scene1 = new Scene(register_parent1);

                    Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    membresStage1.hide();
                    membresStage1.setScene(register_scene1);
                    membresStage1.show();
    }
    @FXML 
    public void ajouter(ActionEvent event) throws IOException
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Ajout.fxml"));
        mainPane.setContent(pane);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                int id = User.getConnectedUser();
        UserServices user = new UserServices();
        User user1 = user.findById(id);
        nom.setText(user1.getUsername());
        Email.setText(user1.getEmail());
        File file = null;
        file = new File(user1.getImage());

        Image image1 = new Image(file.toURI().toString());

        image.setImage(image1);
    }    
    
    
        @FXML
    void AnnonceList(ActionEvent event) throws IOException {

             AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/AnnonceList.fxml"));
        mainPane.setContent(pane);   
        
    }

    @FXML
    void ListSignalisation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/MesSignaler.fxml"));
        mainPane.setContent(pane);
    }

    @FXML
    void MesAnnonces(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Mesannonce.fxml"));
        mainPane.setContent(pane);
    }

    @FXML
    void Recherche(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Rechercher.fxml"));
        mainPane.setContent(pane);
    }
        @FXML
    void ContactezNous(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Email.fxml"));
        mainPane.setContent(pane);
    }

    @FXML
    void Home(ActionEvent event) throws IOException {
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
    void CodeDeLaRoute(ActionEvent event) throws IOException 
    {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/CodeDeLaRoute.fxml"));
            System.out.println(Pi_User.getConnectedUser());
            Scene register_scene1 = new Scene(register_parent1);

            Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            membresStage1.hide();
            membresStage1.setScene(register_scene1);
            membresStage1.show();
    }
}