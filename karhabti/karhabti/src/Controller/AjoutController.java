/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Karhabti.modeles.Annonces;
import Karhabti.modeles.User;

import Karhabti.services.AnnoncesServices;
import Karhabti.services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AjoutController implements Initializable {
    @FXML
    private JFXTextField TITRE;

    @FXML
    private JFXTextField CYLINDREE;

    @FXML
    private JFXTextField PRIX;

    @FXML
    private JFXButton ListAnnonces;

    @FXML
    private JFXTextArea DESCRIPITION;



    @FXML
    private JFXTextField PUISSANCE_FISCALE;



    @FXML
    private JFXTextField Image;

  @FXML
    private JFXTextField NOMBRE_DE_CYLINDRES;

    @FXML
    private JFXComboBox<String> REGION;

    @FXML
    private JFXComboBox<String> BOITE;

    @FXML
    private JFXComboBox<String> ENERGIE;
        @FXML
    private Label nom;

    @FXML
    private Label Email;
        @FXML
    private Label id_user;

    @FXML
    private ImageView imageu;
    private static Path destination;
    private static File selectedfile;
        @FXML
    void uplod(ActionEvent event) {
FileChooser fc = new FileChooser();
       selectedfile = fc.showOpenDialog(null);
        
        destination = Paths.get("D:\\Esprit\\Atelier Java\\piweb\\karhabti\\src\\Image",selectedfile.getName());
       
        Image.setText(selectedfile.getAbsolutePath());
    }


    @FXML
    void addAction(ActionEvent event) throws IOException {
  UserServices user = new UserServices();
         User id = user.findById(User.getConnectedUser());
           AnnoncesServices annonceservice = new AnnoncesServices();
  for (int i = 0 ; i<TITRE.getText().length();i++){
               char c = TITRE.getText().charAt(i);
              if (c =='$'||c =='&'||c =='%'||c =='-'||c =='{'||c =='}'||c =='"'||c =='#'||c =='['||c ==']'||c =='('||c ==')'){
                     Notifications NotificationBuilder = Notifications.create()
                                .title("Error")
                                .text("Vous pouvez pas Entrez des caractere spéciaux")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showError();
               }        else   if (i == TITRE.getText().length()-1){
                       if(DESCRIPITION.getText().length()<20){
                 Notifications NotificationBuilder = Notifications.create()
                                .title("Error")
                                .text("Vous pouvez pas ecrire une descripition moins de 20 caractéres")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showError();}
           else if (PRIX.getText().isEmpty()||Float.parseFloat(PRIX.getText())<1000 ){
                 Notifications NotificationBuilder = Notifications.create()
                                .title("Error")
                                .text("Entrez une prix réél")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showError();
           
                   }
           else{
           
           
           
           
         Annonces annonce = new Annonces(TITRE.getText(),BOITE.getValue(),REGION.getValue(),Integer.parseInt( CYLINDREE.getText()),ENERGIE.getValue(),DESCRIPITION.getText(),Float.parseFloat(PRIX.getText()),Float.parseFloat(PUISSANCE_FISCALE.getText()),Float.parseFloat(NOMBRE_DE_CYLINDRES.getText()),id);
                   
        if(selectedfile==null){
         annonce.setImage(null);
        }else{
            Files.copy(selectedfile.toPath(),destination); 
         annonce.setImage(destination.toString());
        }
           
         
         annonceservice.ajouter(annonce);
    Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Ajout effectué avec succès!! Vous devez attendre la confirmation de votre offre. ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showInformation();
                   }}
  }}
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            //TODO   
       BOITE.getItems().addAll("Autoumatique", "Manuel");
       
      ENERGIE.getItems().addAll("Essence","Diesel","Electrique","Gaz");
       REGION.getItems().addAll("ARIANA",
                "Béja",
                "BenArous",
                "Bizerte",
                "Gabès",
                "Gafsa",
                "Jendouba",
                "Kairouan",
                "Kasserine",
                "Kébili",
                "Kef",
                "Mahdia",
                "Manouba",
                "Médenine",
                "Monastir",
                "Nabeul",
                "Sfax",
                "SidiBouzid",
                "Siliana",
                "Sousse",
                "Tataouine",
                "Tozeur",
                "Tunis",
                "Zaghouan"
        );
        //
//                  int id = User.getConnectedUser();
//           UserServices user = new UserServices();
//           User user1 = user.findById(id);
////           nom.setText(user1.getUsername());
//           Email.setText(user1.getEmail());
//                  File file=null ;
//        file = new File(user1.getImage()); 
//  
//        Image image1 = new Image(file.toURI().toString());
//      
//        imageu.setImage(image1);
    
    }
     @FXML
    void Home(ActionEvent event) throws IOException {
 Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
    }
    
    @FXML
    void deconnexion(ActionEvent event) throws IOException {
 Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
    }

  

 

    
    
}
