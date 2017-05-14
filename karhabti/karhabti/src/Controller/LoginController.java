/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.services.UserServices;
import Karhabti.modeles.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URISyntaxException;
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
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField nomutilisateur;

    @FXML
    private JFXButton seconnecter;

    @FXML
    private JFXTextField motdepasse;

    @FXML
    private JFXButton sinscrire;

    @FXML
    void SeConnecter(ActionEvent event) throws IOException,URISyntaxException {

        UserServices userservice = new UserServices();
        if (nomutilisateur.getText().isEmpty() || motdepasse.getText().isEmpty()) {
            Notifications NotificationBuilder = Notifications.create().title("Erreur").text("Veuillez remplir tout les champs s'il vous plait !!").graphic(null).hideAfter(Duration.seconds(8)).position(Pos.TOP_CENTER);
            NotificationBuilder.showError();
        } else {

            if (userservice.authentication(nomutilisateur.getText(), motdepasse.getText()) == true) {
                System.out.println("connected ");
                if (userservice.findById(User.getConnectedUser()).getRoles().equals("a:1:{i:0;s:9:\"ROLE_USER\";}")) {
             
                    Parent register_parent1 = FXMLLoader.load(getClass().getResource("/Gui/homeTest.fxml"));

                    Scene register_scene1 = new Scene(register_parent1);

                    Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    membresStage1.hide();
                    membresStage1.setScene(register_scene1);
                    membresStage1.show();

                } 
            else if (userservice.authentication(nomutilisateur.getText(), motdepasse.getText()) == true) {
                    if (userservice.findById(User.getConnectedUser()).getRoles().equals("a:2")) {
                    Parent register_parent1 = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/homeAdmin.fxml"));

                    Scene register_scene1 = new Scene(register_parent1);

                    Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    membresStage1.hide();
                    membresStage1.setScene(register_scene1);
                    membresStage1.show();
                    }   

                }
            }
            else {
                Notifications NotificationBuilder = Notifications.create()
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

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
