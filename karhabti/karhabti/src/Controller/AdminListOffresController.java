/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Offre;
import Karhabti.modeles.Pi_User;
import Karhabti.interfaces.IOffreService;
import Karhabti.modeles.User;
import Karhabti.services.AnnoncesServices;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import Karhabti.services.OffreService;
import Karhabti.services.Sms;
import Karhabti.services.SmsService;
import Karhabti.services.UserServices;
import Karhabti.technique.Mailer;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class AdminListOffresController implements Initializable {
    
       
    
    @FXML
    private Label aidoffre;
    @FXML
    private TableView<Offre> tbvadmin;
    @FXML
    private TableColumn<Offre, String> anomoffre;

    @FXML
    private TableColumn<Offre, Float> ataux;

    @FXML
    private TableColumn<Offre, String> adescription;
     private static String labelid ;
       @FXML
    void retour(ActionEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/homeAdmin.fxml"));

                    Scene register_scene1 = new Scene(register_parent1);

                    Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    membresStage1.hide();
                    membresStage1.setScene(register_scene1);
                    membresStage1.show();
    }
    @FXML
            
            public void LoadElements(){
        
        OffreService offreservice = new OffreService();
        
           List<Offre> List1 = offreservice.getAll();
           List<Offre> Liste2 = List1.stream().filter(x->x.getEtat()==0).collect(Collectors.toList());

        
           ObservableList<Offre> listoffre =FXCollections.observableArrayList(Liste2);
         anomoffre.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        adescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        ataux.setCellValueFactory(new PropertyValueFactory<>("taux_remise"));
        tbvadmin.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<Offre>   selecteditem,allitems;
      allitems = tbvadmin.getItems();
       selecteditem = tbvadmin.getSelectionModel().getSelectedItems();
       

        aidoffre.setText( selecteditem.get(0).getId()+"");
        if (newSelection != null) {
        tbvadmin.getSelectionModel().clearSelection();;
    }
    });
       

        tbvadmin.setItems(listoffre);   
    }
            
    @FXML
    void adminafficherdetails(ActionEvent event) throws IOException {
        AdminListOffresController.setLabelid(aidoffre.getText());
       Parent parent4 = FXMLLoader.load(getClass().getResource("/GUI/DetailsOffreAdmin.fxml"));
       Scene scene4 = new Scene(parent4);
        
       Stage stage4=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage4.hide();
       stage4.setScene(scene4);
       stage4.show();

    }
        @FXML
    void adminmesoffrestoalloffres(ActionEvent event) throws IOException {
        Parent parent5 = FXMLLoader.load(getClass().getResource("/GUI/AdminListeTouteOffres.fxml"));
       Scene scene5 = new Scene(parent5);
        
       Stage stage5=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage5.hide();
       stage5.setScene(scene5);
       stage5.show();

    }
            
            
    @FXML
    void accepteroffre(ActionEvent event) throws IOException {
        
        int id = Integer.parseInt(aidoffre.getText());
                   AnnoncesServices offreservice = new AnnoncesServices();

       // UserServices offreservice = new UserServices();
        OffreService offreservices = new OffreService();
        String y = offreservice.findById(id).getUser().getNumTel();
        
        User user =offreservice.findById(id).getUser();

        offreservices.updateoffre(id);
        
        new SmsService().sendSms(new Sms("+216"+y, "Votre offre a été confirmé et ajouté avec succès!"));        
        Mailer.send("headevelopers@gmail.com","123456*/",user.getEmail(),"Offre Confirmée !","Bonsoir, "+user.getUsername()+"\n"+"Votre offre "+offreservices.findById(id).getNom_offre()+" à été ajouté avec succès. "+"\n"+"Cordiallement, "+"\n"+"He&A developers team.");  
        
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/GUI/AdminListOffres.fxml"));
        Scene register_scene1 = new Scene(register_parent1);
        Stage membresStage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();
        
        Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Offre confirmé avec succès ! ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showInformation();
        

    }

    @FXML
    void refuseroffre(ActionEvent event) throws IOException {
        int id = Integer.parseInt(aidoffre.getText());
 OffreService offreservice = new OffreService();
 offreservice.delete(id);
 Parent register_parent11 = FXMLLoader.load(getClass().getResource("/GUI/AdminListOffres.fxml"));
        Scene register_scene11 = new Scene(register_parent11);
        Stage membresStage11 = (Stage)((Node)event.getSource()).getScene().getWindow();
        membresStage11.hide();
        membresStage11.setScene(register_scene11);
        membresStage11.show();
        Notifications NotificationBuilder = Notifications.create()
                                .title("Refus")
                                .text("Offre refusé avec succès !!")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showInformation();

    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         LoadElements();
    }    

    public static String getLabelid() {
        return labelid;
    }

    public static void setLabelid(String labelid) {
        AdminListOffresController.labelid = labelid;
    }
    
    
     
    
}
