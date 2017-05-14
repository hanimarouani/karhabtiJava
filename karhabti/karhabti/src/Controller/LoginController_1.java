/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Pi_User;
import Karhabti.services.pi_userService;
import Karhabti.interfaces.Ipi_userService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.xml.ws.BindingProvider;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class LoginController_1 implements Initializable {
    
     @FXML
    private JFXTextField nomutilisateur;

    @FXML
    private JFXButton seconnecter;


    @FXML
    private JFXPasswordField motdepasse;

    @FXML
    private JFXButton sinscrire;

    @FXML
    void SeConnecter(ActionEvent event) throws IOException {

        pi_userService userservice = new pi_userService();
        if(nomutilisateur.getText().isEmpty()||motdepasse.getText().isEmpty()){
            Notifications NotificationBuilder = Notifications.create().title("Erreur").text("Veuillez remplir tout les champs s'il vous plait !!").graphic(null).hideAfter(Duration.seconds(8)).position(Pos.TOP_CENTER);
            NotificationBuilder.showError();
        }else {
            System.out.println(nomutilisateur.getText());
            if(userservice.authentication(nomutilisateur.getText(), motdepasse.getText())==true){
                System.out.println("connecte");
                Parent register_parent1 = FXMLLoader.load(getClass().getResource("/Gui/Home_1.fxml"));
                System.out.println(Pi_User.getConnectedUser());
                Scene register_scene1 = new Scene(register_parent1);
       
       
      Stage membresStage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
      membresStage1.hide();
      membresStage1.setScene(register_scene1);
      membresStage1.show();
    }else{        Notifications NotificationBuilder = Notifications.create()
                                .title("Erreur")
                                .text("login et mot de passe incorrectes ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.TOP_CENTER);
                        NotificationBuilder.showError();
    }
            }
        
        
    }

    @FXML
    void inscription(ActionEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/Gui/Register.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
       
      Stage membresStage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
      membresStage1.hide();
      membresStage1.setScene(register_scene1);
      membresStage1.show();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
