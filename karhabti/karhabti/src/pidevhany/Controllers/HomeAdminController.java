/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import Karhabti.modeles.Pi_User;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HANY
 */
public class HomeAdminController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    public ScrollPane mainPane;

    @FXML
    private JFXHamburger hamburger;
    @FXML
    public void exit(ActionEvent event) throws IOException
    {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));

                    Scene register_scene1 = new Scene(register_parent1);

                    Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    membresStage1.hide();
                    membresStage1.setScene(register_scene1);
                    membresStage1.show();
    }
    @FXML
    public void lister(ActionEvent event) throws IOException
    {
        Parent pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/ListeTests.fxml"));
          mainPane.setContent(pane);
    }
    @FXML
    public void ajouter(ActionEvent event) throws IOException
    {
        Parent pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/AddTest.fxml"));
          mainPane.setContent(pane);
    }
    @FXML
    public void modifier(ActionEvent event) throws IOException
    {
        Parent pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/ModifyTest.fxml"));
          mainPane.setContent(pane);
    }
    @FXML
    public void supprimer(ActionEvent event) throws IOException
    {
        Parent pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/DeleteTests.fxml"));
          mainPane.setContent(pane);
    }
    @FXML
    public void statistics(ActionEvent event) throws IOException
    {
        Parent pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/Satatistics.fxml"));
          mainPane.setContent(pane);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
//                        if(!pidev.Pidev.isSplashedLoad){
//                loadSplashScreen();}
            
           
    }
    private void loadSplashScreen(){
        try {
            karhabti.Karhabti.isSplashedLoad=true;
            StackPane pane = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/Splash.fxml"));
            borderPane.getChildren().setAll(pane);
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            
            fadeIn.play();
            
            fadeIn.setOnFinished((e)->{
                fadeOut.play();
            });
            fadeOut.setOnFinished((e)->{
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/homeAdmin.fxml"));
                    borderPane.getChildren().setAll(root);
                } catch (IOException ex) {
                    Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
        } catch (IOException ex) {
            Logger.getLogger(HomeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    @FXML
    void offres(ActionEvent event) throws IOException {
          Parent register_parent1 = FXMLLoader.load(getClass().getResource("/Gui/AdminListeTouteOffres.fxml"));
            System.out.println(Pi_User.getConnectedUser());
            Scene register_scene1 = new Scene(register_parent1);

            Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            membresStage1.hide();
            membresStage1.setScene(register_scene1);
            membresStage1.show();
    }

    @FXML
    void ptvente(ActionEvent event) {

    }
       @FXML
    void annonce(ActionEvent event) throws IOException {
            Parent register_parent1 = FXMLLoader.load(getClass().getResource("/Gui/AdminAnnonces.fxml"));
            System.out.println(Pi_User.getConnectedUser());
            Scene register_scene1 = new Scene(register_parent1);

            Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            membresStage1.hide();
            membresStage1.setScene(register_scene1);
            membresStage1.show();
    }

    @FXML
    void codeRoute(ActionEvent event) {

    }

    @FXML
    void evennement(ActionEvent event) {

    }
    
}
