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
import Karhabti.interfaces.IOffreService;
import Karhabti.interfaces.IPointVenteService;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class AfficherToutesOffresController implements Initializable {
    
    @FXML
    private TableColumn<Offre, String> descco;

    @FXML
    private TableColumn<Offre, Float> tauxco;

    @FXML
    private TableColumn<Offre, String> nomoffco;

    @FXML
    private TableView<Offre> tabview;
    @FXML
    private Label idoffree;
    private static String labelid ;

    public AfficherToutesOffresController() {
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LoadElements();
    }    
    
    public void LoadElements(){
        
        IOffreService offreservice = new OffreService();
        
           List<Offre> List1 = offreservice.getAll();
           List<Offre> Liste2 = List1.stream().filter(x->x.getEtat()==1).collect(Collectors.toList());

        
           ObservableList<Offre> listoffre =FXCollections.observableArrayList(Liste2);
         nomoffco.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        descco.setCellValueFactory(new PropertyValueFactory<>("description"));
        tauxco.setCellValueFactory(new PropertyValueFactory<>("taux_remise"));
        tabview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<Offre>   selecteditem,allitems;
      allitems = tabview.getItems();
       selecteditem = tabview.getSelectionModel().getSelectedItems();
       

        idoffree.setText( selecteditem.get(0).getId()+"");
        if (newSelection != null) {
        tabview.getSelectionModel().clearSelection();;
    }
    });
       

        tabview.setItems(listoffre);   
    }
    
        @FXML
    void alloffertoaccueil(ActionEvent event) throws IOException {
             Parent parent1 = FXMLLoader.load(getClass().getResource("/GUI/Home.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();

    }

    

    @FXML
    void alloffertoajout(ActionEvent event) throws IOException {
            Parent parent2 = FXMLLoader.load(getClass().getResource("/Gui/Ajout_1.fxml"));
       Scene scene2 = new Scene(parent2);
        
       Stage stage2=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage2.hide();
       stage2.setScene(scene2);
       stage2.show();

    }

  

    @FXML
    void alloffertomesoffres(ActionEvent event) throws IOException {
       Parent parent3 = FXMLLoader.load(getClass().getResource("/Gui/FXMLAfficheroffres.fxml"));
       Scene scene3 = new Scene(parent3);
        
       Stage stage3=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage3.hide();
       stage3.setScene(scene3);
       stage3.show();

    }
    
    @FXML
    void plusdedetails(ActionEvent event) throws IOException {
      AfficherToutesOffresController.setLabelid(idoffree.getText());
       Parent parent4 = FXMLLoader.load(getClass().getResource("/GUI/DetailsOffre.fxml"));
       Scene scene4 = new Scene(parent4);
        
       Stage stage4=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage4.hide();
       stage4.setScene(scene4);
       stage4.show();
       
//Alert alert = new Alert(Alert.AlertType.INFORMATION);
//alert.setTitle("detaille de l'offre "+offredetails.getNom_offre());
//
//alert.setContentText("description "+offredetails.getDescription()+"\n"+
//"nom point de vente "+offredetails.getPtvente().getNom());
//
//alert.showAndWait();

    }
    
        @FXML
    void toutelesoffrestologin(ActionEvent event) throws IOException {
        Parent parent5 = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
       Scene scene5 = new Scene(parent5);
        
       Stage stage5=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage5.hide();
       stage5.setScene(scene5);
       stage5.show();

    }

    public static String getLabelid() {
        return labelid;
    }

    public static void setLabelid(String labelid) {
        AfficherToutesOffresController.labelid = labelid;
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
