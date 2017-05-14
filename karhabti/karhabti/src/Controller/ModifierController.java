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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class ModifierController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField TITRE;

    @FXML
    private TextField CYLINDREE;

    @FXML
    private TextField NOMBRE_DE_CYLINDRES;

    @FXML
    private TextField PRIX;

    @FXML
    private TextArea DESCRIPITION;

    @FXML
    private TextField ENERGIE;

    @FXML
    private Button modifier;

    @FXML
    private TextField PUISSANCE_FISCALE;

    @FXML
    private TextField BOITE;

    @FXML
    private TextField REGION;
    private TextField txtimage;
    private static Path destination;
    private static File selectedfile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            // TODO
//            modifier();
//        } catch (IOException ex) {
//            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    void uplod(ActionEvent event) {
        FileChooser fc = new FileChooser();
        selectedfile = fc.showOpenDialog(null);

        destination = Paths.get("/root/NetBeansProjects/karhabti.v1/src/Image", selectedfile.getName());

        txtimage.setText(selectedfile.getAbsolutePath());
    }

//    public void modifier() throws IOException {

//        AnnoncesServices annonceservice = new AnnoncesServices();
//
//        Annonces annonce = new Annonces(1, TITRE.getText(), BOITE.getText(), REGION.getText(), Integer.parseInt(CYLINDREE.getText()), ENERGIE.getText(), DESCRIPITION.getText(), Float.parseFloat(PRIX.getText()), Float.parseFloat(PUISSANCE_FISCALE.getText()), Float.parseFloat(CYLINDREE.getText()));
//
//        if (selectedfile == null) {
//            annonce.setImage(null);
//        } else {
//            Files.copy(selectedfile.toPath(), destination);
//            annonce.setImage(destination.toString());
//        }
//        annonceservice.modifier(annonce);
//        System.out.println("bien");
//    }

}
