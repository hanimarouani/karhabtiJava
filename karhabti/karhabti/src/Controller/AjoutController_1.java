/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Offre;
import Karhabti.technique.Mailer;
import Karhabti.modeles.Pi_User;
import Karhabti.modeles.PointDeVente;
import Karhabti.services.OffreService;
import Karhabti.services.PointDeVenteService;
import Karhabti.services.Sms;
import Karhabti.services.pi_userService;
import Karhabti.interfaces.IOffreService;
import Karhabti.interfaces.IPointVenteService;
import Karhabti.interfaces.Ipi_userService;
import Karhabti.modeles.User;
import Karhabti.services.AnnoncesServices;
import Karhabti.services.UserServices;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class AjoutController_1 implements Initializable {
        
    @FXML
    private JFXComboBox<String> ptventeid;
   @FXML
    private JFXTextField prinxinitial;

    @FXML
    private JFXTextField taux;

    @FXML
    private JFXTextField nomoffre;
    
        @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private JFXTextArea description;
     @FXML
    private JFXTextField imagepath;
     private static Path destination;
    private static File selectedfile;
   @FXML
    void moveToListeOffre(ActionEvent event) throws IOException {
       
    Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/FXMLAfficheroffres.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
    }
    
    
    @FXML
    void erreurmessagesyntaxe(KeyEvent event) {
        
//         Pattern VALID_REGEX = Pattern.compile("[+]?[0-9]*\\.?[0-9]*", Pattern.CASE_INSENSITIVE);
//         Matcher matcher = VALID_REGEX .matcher(prinxinitial.getText().subSequence(0, prinxinitial.getText().length()));
//
//         if (!matcher.find()){
//             System.out.println("lol");
//             text1.setText("Valeur invalide");
//         }
//         else text1.setText(null);
    }

    @FXML
    void erreurmessagesyntaxetaux(KeyEvent event) {
//        Pattern VALID_REGEX = Pattern.compile("[+]?[0-9]*\\.?[0-9]*", Pattern.CASE_INSENSITIVE);
//         Matcher matcher = VALID_REGEX .matcher(taux.getText().subSequence(0, taux.getText().length()));
//         if (matcher.find())
//             text2.setText("");
//         else{ 
//             
//                 text2.setText("Valeur invalide");
//                  
//
//         }
    }
    
    
    @FXML
    void ajouterimage(ActionEvent event) {
  FileChooser fc = new FileChooser();
      selectedfile = fc.showOpenDialog(null);
        
      destination = Paths.get("D:\\Esprit\\Atelier Java\\piweb\\karhabti\\src\\Image",selectedfile.getName());
       
        imagepath.setText(selectedfile.getAbsolutePath());
    }
    @FXML
    void addAction(ActionEvent event) throws IOException {
     
//        System.out.println("succsessfully inserted ");
        
        if(nomoffre.getText().isEmpty()||description.getText().isEmpty() || prinxinitial.getText().isEmpty() || taux.getText().isEmpty()){
            Notifications NotificationBuilder = Notifications.create().title("Erreur").text("Veuillez remplir tout les champs s'il vous plait !!").graphic(null).hideAfter(Duration.seconds(8)).position(Pos.CENTER);
            NotificationBuilder.showError();
        }else{
        
          UserServices id = new UserServices();

               User user = id.findById(User.getConnectedUser());
           AnnoncesServices annonceservice = new AnnoncesServices();
        pi_userService userservice = new pi_userService();
     //  Pi_User user = userservice.findById(Pi_User.getConnectedUser());
        OffreService offreservice = new OffreService();
        PointDeVenteService ptventeservice = new PointDeVenteService();
        PointDeVente pt = ptventeservice.getptVenteByName(ptventeid.getValue());
        Offre offre = new Offre(nomoffre.getText(),description.getText(),pt,0,user,Float.parseFloat(prinxinitial.getText()), Float.parseFloat(taux.getText()));
//        System.out.println("succsessfully inserted ");
         if(selectedfile==null){
         offre.setPhoto(null);
        }else{
            Files.copy(selectedfile.toPath(),destination,StandardCopyOption.REPLACE_EXISTING); 
         offre.setPhoto(destination.toString());
        }


       
        offreservice.add(offre);
        
        Mailer.send("headevelopers@gmail.com","123456*/",user.getEmail(),"Offre en attente","Bonsoir, "+user.getUsername()+"\n"+"Votre offre ajout de l'offre a été effectué avec succès. "+"\n"+"Après sa confirmation, cette offre sera publiée et publique."+"\n"+"Cordiallement, "+"\n"+"He&A developers team.");  
        Parent register_parent11 = FXMLLoader.load(getClass().getResource("/Gui/FXMLAfficheroffres.fxml"));
        Scene register_scene11 = new Scene(register_parent11);
        Stage membresStage11 = (Stage)((Node)event.getSource()).getScene().getWindow();
        membresStage11.hide();
        membresStage11.setScene(register_scene11);
        membresStage11.show();
        
        Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Ajout effectué avec succès!! Vous devez attendre la confirmation de votre offre. ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showInformation();
        }
        
//        System.out.println("succsessfully inserted ");
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PointDeVenteService  ptventeservices = new PointDeVenteService();
        List<PointDeVente> list2 = ptventeservices.getAllpointVente();
   
     int i=0;
     for(PointDeVente pt :list2) 
     ptventeid.getItems().add(i++,pt.getNom());
     
   }
        @FXML
    void ajouttoaccueil(ActionEvent event) throws IOException {
        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Home_1.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();

    }
        @FXML
    void ajouttoalloffres(ActionEvent event) throws IOException {
          Parent parent2 = FXMLLoader.load(getClass().getResource("/Gui/AfficherToutesOffres.fxml"));
       Scene scene2 = new Scene(parent2);
        
       Stage stage2=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage2.hide();
       stage2.setScene(scene2);
       stage2.show();

    }
        @FXML
    void sedeconnecterajout(ActionEvent event) throws IOException {
       Parent parent2 = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
       Scene scene2 = new Scene(parent2);
        
       Stage stage2=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage2.hide();
       stage2.setScene(scene2);
       stage2.show();

    }
        @FXML
    void Annonces(ActionEvent event) throws IOException {

        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/homeTest.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
                  
    }
    
}