/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Karhabti.technique.Mail;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author raed
 */
public class EmailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField recepteur;

    @FXML
    private JFXTextField objet;

    @FXML
    private TextField description;

    @FXML
    private JFXButton inscription;

    @FXML
    void envoiMail(ActionEvent event) {
        Mail.send(recepteur.getText(), objet.getText(), description.getText());
        
        Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Votre mail à été envoyé avec succès ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showInformation();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
            @FXML
    void Retour(ActionEvent event) throws IOException {
            Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/homeTest.fxml"));
            Scene scene1 = new Scene(parent1);

            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage1.hide();
            stage1.setScene(scene1);
            stage1.show();
    }
}
