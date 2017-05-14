/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidevhany.Models.pi_user;
import pidevhany.Services.impl.UserService;
import pidevhany.Services.interfaces.IUserService;

/**
 * FXML Controller class
 *
 * @author HANY
 */
public class LoginController implements Initializable {
public static final String ACCOUNT_SID = "ACe848124277c5272fa7731689c59b32b6";
public static final String AUTH_TOKEN = "c12ec03c9857ec634c0d59606f4cb05c";


    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private JFXTextField username;

  
    @FXML

    void Bienvenue(ActionEvent event) throws IOException, URISyntaxException {

        IUserService userservice = new UserService();
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Notifications NotificationBuilder = Notifications.create()
                    .title("error")
                    .text("veuiller remplir tt les champs svp !! ")
                    .graphic(null)
                    .hideAfter(Duration.seconds(8))
                    .position(Pos.TOP_LEFT);
            NotificationBuilder.showError();

        } else {
            System.out.println(username.getText());
            System.out.println(password.getText());
            if (userservice.authentication(username.getText(), password.getText()) == true) {
                System.out.println("connected ");
                if (userservice.findById(pi_user.getConnectedUser()).getRoles().equals("a:2:{i:0;s:9:\"ROLE_USER\";i:1;s:10:\"ROLE_ADMIN\";}")) {
                    Parent register_parent1 = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/homeAdmin.fxml"));

                    Scene register_scene1 = new Scene(register_parent1);

                    Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    membresStage1.hide();
                    membresStage1.setScene(register_scene1);
                    membresStage1.show();

                } else if (userservice.authentication(username.getText(), password.getText()) == true) {
                    if (userservice.findById(pi_user.getConnectedUser()).getRoles().equals("a:0:{}")) {
                    Parent register_parent1 = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/CodeDeLaRoute.fxml"));

                    Scene register_scene1 = new Scene(register_parent1);

                    Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    membresStage1.hide();
                    membresStage1.setScene(register_scene1);
                    membresStage1.show();
                    }   

                }
            } else {
                Notifications NotificationBuilder = Notifications.create()
                        .title("error")
                        .text("login et mot de passe incorrects ")
                        .graphic(null)
                        .hideAfter(Duration.seconds(8))
                        .position(Pos.TOP_LEFT);
                NotificationBuilder.showError();
            }

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
