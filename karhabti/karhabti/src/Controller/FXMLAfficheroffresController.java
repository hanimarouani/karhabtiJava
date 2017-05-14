/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Offre;
import Karhabti.modeles.Pi_User;
import Karhabti.modeles.PointDeVente;
import Karhabti.services.OffreService;
import Karhabti.services.PointDeVenteService;
import Karhabti.services.pi_userService;
import Karhabti.interfaces.IOffreService;
import Karhabti.interfaces.IPointVenteService;
import Karhabti.interfaces.Ipi_userService;
import Karhabti.modeles.User;
import Karhabti.services.AnnoncesServices;
import Karhabti.services.UserServices;
import com.jfoenix.controls.JFXComboBox;
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
import java.util.stream.Collectors;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class FXMLAfficheroffresController implements Initializable {
   @FXML
    private TableView<Offre> tableview;
   
    @FXML
    private TableColumn<Offre, String> nomoffreColumn;
    
    

    @FXML
    private TableColumn<Offre,String> nomptcolumn;
        @FXML
    private TableColumn<Offre, Float> tauxremiseColumn;
  @FXML
    private JFXTextField imagepath;

    @FXML
    private JFXTextField taux;
    
    @FXML
    private Label idoffre;
@FXML
    private JFXComboBox<String> ptventeid;
    @FXML
    private JFXTextField prinxinitial;

    @FXML
    private JFXTextField nomoffre;

    @FXML
    private JFXTextArea description;
    private static String labelid ;

   private static Path destination;
    private static File selectedfile;

    @FXML
    void ajouterimage(ActionEvent event) {
FileChooser fc = new FileChooser();
      selectedfile = fc.showOpenDialog(null);
        
      destination = Paths.get("/root/NetBeansProjects/karhabti.v1/src/Image",selectedfile.getName());
       
        imagepath.setText(selectedfile.getAbsolutePath());
    }
    
    @FXML
    void supprimer(ActionEvent event) throws IOException {
        int id = Integer.parseInt(idoffre.getText());
 OffreService offreservice = new OffreService();
 offreservice.delete(id);
 Parent register_parent11 = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheroffres.fxml"));
        Scene register_scene11 = new Scene(register_parent11);
        Stage membresStage11 = (Stage)((Node)event.getSource()).getScene().getWindow();
        membresStage11.hide();
        membresStage11.setScene(register_scene11);
        membresStage11.show();
        Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Supression effectué avec succès !!")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showError();
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {
UserServices id = new UserServices();

               User user = id.findById(User.getConnectedUser());
           AnnoncesServices annonceservice = new AnnoncesServices();
        pi_userService userservice = new pi_userService();
        //Pi_User user = userservice.findById(Pi_User.getConnectedUser());
        OffreService offreservice = new OffreService();
        PointDeVenteService ptventeservice = new PointDeVenteService();
        PointDeVente pt = ptventeservice.getptVenteByName(ptventeid.getValue());
        Offre offre = new Offre(nomoffre.getText(),description.getText(),pt,0, user,Float.parseFloat(prinxinitial.getText()), Float.parseFloat(taux.getText()));
        offre.setId(Integer.parseInt(idoffre.getText()));
         if(selectedfile==null){
         offre.setPhoto(null);
        }else{
            Files.copy(selectedfile.toPath(),destination,StandardCopyOption.REPLACE_EXISTING); 
         offre.setPhoto(destination.toString());
        }


       
        offreservice.update(offre);
        
        Parent register_parent11 = FXMLLoader.load(getClass().getResource("/GUI/FXMLAfficheroffres.fxml"));
        Scene register_scene11 = new Scene(register_parent11);
        Stage membresStage11 = (Stage)((Node)event.getSource()).getScene().getWindow();
        membresStage11.hide();
        membresStage11.setScene(register_scene11);
        membresStage11.show();
        
      
        Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Modification effectuée avec succès !!")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showWarning();
        
        
        
        
        
        
    }
  

 @FXML
    private TableColumn<Offre,String> descColumn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
      LoadElements();
    }  
    public void LoadElements(){
         PointDeVenteService  ptventeservices = new PointDeVenteService();
        List<PointDeVente> list6 = ptventeservices.getAllpointVente();
   
     int i=0;
     for(PointDeVente pt :list6) {
     ptventeid.getItems().add(i++,pt.getNom());
        
        
     }
        
        OffreService offreservice = new OffreService();
        
           List<Offre> List1 = offreservice.getAll();
           int id = User.getConnectedUser();
           List<Offre> Liste2 = List1.stream().filter(x->x.getProprietaire().getId()==id).collect(Collectors.toList());
           System.out.println(id);
           ObservableList<Offre> listoffre =FXCollections.observableArrayList(Liste2);
         nomoffreColumn.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tauxremiseColumn.setCellValueFactory(new PropertyValueFactory<>("taux_remise"));
        tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<Offre>   selecteditem,allitems;
      allitems = tableview.getItems();
       selecteditem = tableview.getSelectionModel().getSelectedItems();
       
        description.setText(selecteditem.get(0).getDescription());
        nomoffre.setText(selecteditem.get(0).getNom_offre());
        prinxinitial.setText(String.valueOf(selecteditem.get(0).getPrixinit()));
        taux.setText(String.valueOf(selecteditem.get(0).getTaux_remise()));
        ptventeid.setValue(selecteditem.get(0).getPtvente().getNom());
        idoffre.setText( selecteditem.get(0).getId()+"");
//        
        if (newSelection != null) {
        tableview.getSelectionModel().clearSelection();;
    }
    });
       

        tableview.setItems(listoffre);   
    }

      @FXML
    void showmoredetails(ActionEvent event) throws IOException {
        FXMLAfficheroffresController.setLabelid(idoffre.getText());
         Parent parent4 = FXMLLoader.load(getClass().getResource("/Gui/Detailsmesoffres.fxml"));
       Scene scene4 = new Scene(parent4);
        
       Stage stage4=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage4.hide();
       stage4.setScene(scene4);
       stage4.show();
// IOffreService offreservice = new OffreService();
//Offre offredetails = offreservice.findById(Integer.parseInt(idoffre.getText()));
//Alert alert = new Alert(AlertType.INFORMATION);
//alert.setTitle("detaille de l'offre "+offredetails.getNom_offre());
//
//alert.setContentText("description "+offredetails.getDescription()+"\n"+
//"nom point de vente "+offredetails.getPtvente().getNom());
//
//alert.showAndWait();


    }
    @FXML
    void listtoaccueil(ActionEvent event) throws IOException {
        
        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();
    }
    @FXML
    void listtoajout(ActionEvent event) throws IOException {
        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Ajout_1.fxml"));
       Scene scene2 = new Scene(parent1);
        
       Stage stage2=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage2.hide();
       stage2.setScene(scene2);
       stage2.show();

    }
        @FXML
    void mesoffresdeconnecter(ActionEvent event) throws IOException {
        
           Parent parent3 = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
       Scene scene3 = new Scene(parent3);
        
       Stage stage3=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage3.hide();
       stage3.setScene(scene3);
       stage3.show();

    }

    @FXML
    void mesoffrestotoutesoffres(ActionEvent event) throws IOException {
            Parent parent4 = FXMLLoader.load(getClass().getResource("/Gui/AfficherToutesOffres.fxml"));
       Scene scene4 = new Scene(parent4);
        
       Stage stage4=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage4.hide();
       stage4.setScene(scene4);
       stage4.show();

    }

    public static String getLabelid() {
        return labelid;
    }

    public static void setLabelid(String labelid) {
        FXMLAfficheroffresController.labelid = labelid;
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
