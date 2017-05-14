/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Offre;
import Karhabti.modeles.Pi_User;
import Controller.AfficherToutesOffresController;
import Karhabti.services.OffreService;
import Karhabti.services.pi_userService;
import Karhabti.interfaces.IOffreService;
import Karhabti.interfaces.Ipi_userService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class DetailsOffreController implements Initializable {
    
    
    @FXML
    private Label ddescription;

    @FXML
    private Label dnomoffre;

    @FXML
    private Label dprixinitial;

    @FXML
    private Label dnompointdevente;

    @FXML
    private Label dprixapresremise;
        @FXML
    private ImageView imageoffre;
        
    @FXML
    private JFXButton sharefacebook;
    
    @FXML
    private JFXButton sharetwitter;
        
    private Object user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        OffreService offreService = new OffreService();
        AfficherToutesOffresController z = new AfficherToutesOffresController();
       
        Offre x = offreService.findById(Integer.parseInt(AfficherToutesOffresController.getLabelid()));
        
        dnomoffre.setText(x.getNom_offre());
        ddescription.setText(x.getDescription());
        dprixinitial.setText(String.valueOf(x.getPrixinit()));
        dnompointdevente.setText(x.getPtvente().getNom());
        Float tr = x.getTaux_remise();
        Float pr = x.getPrixinit()-(x.getPrixinit()*tr)/100 ;
        dprixapresremise.setText(String.valueOf(pr));
        
        try {
                sharefacebook.setGraphic(new ImageView(new Image(new FileInputStream("/root/NetBeansProjects/karhabti.v/src/images/bouton-facebook.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DetailsmesoffresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        try {
                sharetwitter.setGraphic(new ImageView(new Image(new FileInputStream("/root/NetBeansProjects/karhabti.v1/src/images/boutton-twitter.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DetailsmesoffresController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        File file=null ;
        file = new File(x.getPhoto()); 
  
        Image image = new Image(file.toURI().toString());
      
        imageoffre.setImage(image);
        
                // TODO
    }    
        @FXML
    void detailstouteoffrestohome(ActionEvent event) throws IOException {
           Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();

    }

    @FXML
    void detailstouteoffrestomesoffres(ActionEvent event) throws IOException {
         Parent parent2 = FXMLLoader.load(getClass().getResource("/Gui/FXMLAfficheroffres.fxml"));
       Scene scene2 = new Scene(parent2);
        
       Stage stage2=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage2.hide();
       stage2.setScene(scene2);
       stage2.show();

    }

    @FXML
    void detailstouteoffrestotouteoffres(ActionEvent event) throws IOException {
         Parent parent3 = FXMLLoader.load(getClass().getResource("/Gui/AfficherToutesOffres.fxml"));
       Scene scene3 = new Scene(parent3);
        
       Stage stage3=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage3.hide();
       stage3.setScene(scene3);
       stage3.show();

    }

    @FXML
    void detailstouteoffretoadd(ActionEvent event) throws IOException {
         Parent parent4 = FXMLLoader.load(getClass().getResource("/Gui/Ajout.fxml"));
       Scene scene4 = new Scene(parent4);
        
       Stage stage4=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage4.hide();
       stage4.setScene(scene4);
       stage4.show();

    }

    @FXML
    void detailstouteoffretologin(ActionEvent event) throws IOException {
         Parent parent5 = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
       Scene scene5 = new Scene(parent5);
        
       Stage stage5=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage5.hide();
       stage5.setScene(scene5);
       stage5.show();

    }
    
        @FXML
    void partagerfacebook(ActionEvent event) throws FacebookException, FileNotFoundException {
        
          
        
        OffreService offreService = new OffreService();
        AfficherToutesOffresController z = new AfficherToutesOffresController();
       
        Offre x = offreService.findById(Integer.parseInt(AfficherToutesOffresController.getLabelid()));
        
        
        
        String accessToken = "EAACEdEose0cBAAgOsVlBVZBkCSHiPxfyRP1nsCQbigL2DOIdCOKDjVaOMZAA7JSFUFAG8h6B4TRwaZClqCilvsPVzF0tOCoTCnojVwuJXrSfMiSjXh2Spd1RKSCTZAlpkSZAyoktIWIiHUP3DpfrJ636QlNnxL4WTTXkHcAQuw3PNyIfeZAU4FI25cIJc1MgMZD";

            FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
            
   
            String pathphoto = x.getPhoto();
            String nomphoto = pathphoto.substring(62);
            System.out.println(nomphoto);
          FileInputStream fis = new FileInputStream(new File(pathphoto));
            
            User me = fbClient.fetchObject("me", User.class);
              FacebookType response;
            response = fbClient.publish("me/photos",FacebookType.class,
                   BinaryAttachment.with(nomphoto, fis),
                    Parameter.with("message", x.getPtvente().getNom()+"\n"+x.getNom_offre()+"\n "+x.getDescription()+"\n"+x.getTaux_remise()+"%"));
            
            
            System.out.println(me.getName());
   
    }

    
    
    
    @FXML
    void partagersurtwitter(ActionEvent event) throws TwitterException {
        
        OffreService offreService = new OffreService();
        AfficherToutesOffresController z = new AfficherToutesOffresController();
       
        Offre x = offreService.findById(Integer.parseInt(AfficherToutesOffresController.getLabelid()));
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("zFrvXiT4O6bPh7wtfX2qpxTJM")
                .setOAuthConsumerSecret("pZeG2yJ71HYSu3y8MC4sbOcAeKstRKmp8bVKfCOmxnd4QjsKmC")
                .setOAuthAccessToken("3345656831-F533Ns9kdMPI6GfmHwiVor3BUHM6kYJn4WO4xhq")
                .setOAuthAccessTokenSecret("1hWrntZlel0gOju0xOEVf2t5kRo2CjSp5H75sieOUfT89");
        
        TwitterFactory tf = new TwitterFactory(cb.build());
        
        twitter4j.Twitter tw = tf.getInstance();
        
        
        String pathphoto = x.getPhoto();
            String nomphoto = pathphoto.substring(62);
            System.out.println(nomphoto);
            
        File file=null ;
        file = new File(x.getPhoto()); 
        
        
        String StatusMessage = (x.getPtvente().getNom()+"\n"+x.getDescription()+"\n"+x.getTaux_remise());
        
        
        StatusUpdate status = new StatusUpdate(StatusMessage);
        status.setMedia(file);
        tw.updateStatus(status);

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
