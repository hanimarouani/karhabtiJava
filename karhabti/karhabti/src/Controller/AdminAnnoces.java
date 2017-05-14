/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author HANY
 */
public class AdminAnnoces {
    @FXML
    private ScrollPane mainPane;
    @FXML
    void AnnoncesNonConfirmé(ActionEvent event) throws IOException {
AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/Listannonce.fxml"));
        mainPane.setContent(pane);
    }

    @FXML
    void AnnoncesSignalé(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/Gui/SignalerList.fxml"));
        mainPane.setContent(pane);
    }
     @FXML
    void retour(ActionEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/homeAdmin.fxml"));

                    Scene register_scene1 = new Scene(register_parent1);

                    Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    membresStage1.hide();
                    membresStage1.setScene(register_scene1);
                    membresStage1.show();
    }
}
