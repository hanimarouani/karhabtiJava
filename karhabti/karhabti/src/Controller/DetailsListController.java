/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Annonces;
import Karhabti.modeles.Signalisations;
import Karhabti.modeles.User;
import Karhabti.services.AnnoncesServices;
import Karhabti.services.SignalisationsServices;
import Karhabti.services.UserServices;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class DetailsListController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private Label TITRE;



    @FXML
    private Label CYLINDREE;

    @FXML
    private Label REGION;

    @FXML
    private Label PRIX;

    @FXML
    private Label NOMBRE_DE_CYLINDRES;

    @FXML
    private Label BOITE;

    @FXML
    private Label PUISSANCE_FISCALE;

    @FXML
    private Label ENERGIE;

    @FXML
    private Label Email;
        @FXML
    private Label Emailu;

    @FXML
    private Label NOM;

    @FXML
    private ImageView image;

    @FXML
    private Label nomu;

    @FXML
    private ImageView image1;
    @FXML
    private JFXTextArea DESCRIPITION;
        @FXML
    private Label NumTel;
            @FXML
    private Label Date;
    @FXML
    void Home(ActionEvent event) {

    }

    @FXML
    void deconnexion(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       if (ListannonceController.getIdlist()== null){
              AnnoncesServices annonceservice = new AnnoncesServices() ;
         Annonces annonce = annonceservice.findById(AnnonceListController.getId());
          System.out.println("hhhhh"+annonceservice.findById(AnnonceListController.getId()));
                TITRE.setText(annonce.getTITRE());
              Date.setText(annonce.getDate()+"");
          DESCRIPITION.setText(annonce.getDESCRIPITION());
         BOITE.setText(annonce.getBOITE());
          REGION.setText(annonce.getENERGIE());
          CYLINDREE.setText(String.valueOf(annonce.getCYLINDREE()));
          PRIX.setText(String.valueOf(annonce.getPRIX())+"Dt");
          PUISSANCE_FISCALE.setText(String.valueOf(annonce.getPUISSANCE_FISCALE()));
          NOMBRE_DE_CYLINDRES.setText(String.valueOf(annonce.getNOMBRE_DE_CYLINDRES()));
         NOM.setText(annonce.getUser().getUsername());
          Email.setText(annonce.getUser().getEmail());
          NumTel.setText(String.valueOf(annonce.getUser().getNumTel()));
          File file=null ;
        file = new File(annonce.getImage()); 
  
        Image image3 = new Image(file.toURI().toString());
      
        image1.setImage(image3);
//        //
//                       int id = User.getConnectedUser();
//           UserServices user = new UserServices();
//           User user1 = user.findById(id);
//           nomu.setText(user1.getUsername());
//           Emailu.setText(user1.getEmail());
//                  File file1=null ;
//        file1 = new File(user1.getImage()); 
//  
//        Image image2 = new Image(file1.toURI().toString());
//      
//        image1.setImage(image2);
       }
       else{
                 
              AnnoncesServices annonceservice = new AnnoncesServices() ;
         Annonces annonce = annonceservice.findById(Integer.parseInt(ListannonceController.getIdlist()));
          System.out.println("hhhhh"+annonceservice.findById(Integer.parseInt(ListannonceController.getIdlist())));
            TITRE.setText(annonce.getTITRE());
              Date.setText(annonce.getDate()+"");
          DESCRIPITION.setText(annonce.getDESCRIPITION());
         BOITE.setText(annonce.getBOITE());
          REGION.setText(annonce.getENERGIE());
          CYLINDREE.setText(String.valueOf(annonce.getCYLINDREE()));
          PRIX.setText(String.valueOf(annonce.getPRIX())+"Dt");
          PUISSANCE_FISCALE.setText(String.valueOf(annonce.getPUISSANCE_FISCALE()));
          NOMBRE_DE_CYLINDRES.setText(String.valueOf(annonce.getNOMBRE_DE_CYLINDRES()));
         NOM.setText(annonce.getUser().getUsername());
          Email.setText(annonce.getUser().getEmail());
          NumTel.setText(String.valueOf(annonce.getUser().getNumTel()));
          File file=null ;
        file = new File(annonce.getImage()); 
  
        Image image3 = new Image(file.toURI().toString());
      
        image1.setImage(image3);
        
       
       }
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
            @FXML
    void Signaler(ActionEvent event) {
          UserServices user = new UserServices();
          AnnoncesServices annonceservice = new AnnoncesServices();
         User id = user.findById(User.getConnectedUser());
         Annonces id_annonce = annonceservice.findById(AnnonceListController.getId());
         int id_user = id_annonce.getUser().getId();
         String nom_annonce = id_annonce.getTITRE();
            Signalisations signaler = new Signalisations (id_annonce,id_user,id,nom_annonce);
            SignalisationsServices sign = new SignalisationsServices();
            sign.ajouter(signaler);
            Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Signalisation effectué avec succès!! ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showInformation();
    }
        @FXML
    void rate(ActionEvent event) throws IOException {
            Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Rate.fxml"));
            Scene scene1 = new Scene(parent1);

            Stage stage1 = new Stage();
            stage1.setScene(scene1);
            stage1.showAndWait();           
            stage1.setScene(scene1);
            stage1.showAndWait();
    }
    }    
    

