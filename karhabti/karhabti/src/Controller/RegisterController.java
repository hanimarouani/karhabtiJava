/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.services.UserServices;
import Karhabti.modeles.Annonces;
import Karhabti.modeles.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class RegisterController implements Initializable {
    
    @FXML
    private JFXTextField remail;

    @FXML
    private JFXTextField radresse;

    @FXML
    private JFXTextField rnomutilisateur;

    @FXML
    private JFXTextField rmotdepasse;

    @FXML
    private JFXTextField rcmotdepasse;

    @FXML
    private JFXTextField rimagepath;

    @FXML
    private JFXTextField rnumtel;

    @FXML
    private JFXButton inscription;
        @FXML
    private JFXTextField Adress;

    @FXML
    private JFXTextField NumTel;

    @FXML
    private JFXTextField CodePostal;

    @FXML
     private static Path destination;
    private static File selectedfile;
    
    
    @FXML
    void rajouterimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        selectedfile = fc.showOpenDialog(null);
        
        destination = Paths.get("D:\\Esprit\\Atelier Java\\piweb\\karhabti\\src\\Image",selectedfile.getName());
       
        rimagepath.setText(selectedfile.getAbsolutePath());

    }

    @FXML
    void rinscription(ActionEvent event) throws IOException {
        UserServices service = new UserServices();
        User user = new User();
        if (service.RechLogin(rnomutilisateur.getText())== true) {
            System.out.println("login existant");
        }else if (!remail.getText().contains("@")) {
            System.out.println("Email non valide");

   }else if ( remail.getText().isEmpty()) { 
       System.out.println("Saisir Email");
        
        }else{
       if(selectedfile==null){
        user.setImage(null);
        }else{
            Files.copy(selectedfile.toPath(),destination,StandardCopyOption.REPLACE_EXISTING); 
             user.setImage(destination.toString());
        }
        user.setUsername(rnomutilisateur.getText());
        user.setEmail(remail.getText());
        user.setPassword(rmotdepasse.getText());
        user.setPassword(rcmotdepasse.getText());
       user.setAdresse(Adress.getText());
       user.setCodePostal(CodePostal.getText());
       user.setNumTel(NumTel.getText());
        service.ajouter(user);
        System.out.println("Inscription valide");
 Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
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
