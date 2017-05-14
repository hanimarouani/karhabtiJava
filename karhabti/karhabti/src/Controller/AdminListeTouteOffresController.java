/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Offre;
import Karhabti.services.OffreService;
import Karhabti.interfaces.IOffreService;
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
public class AdminListeTouteOffresController implements Initializable {
    
    
    @FXML
    private Label aaidoffre;
        @FXML
    private TableView<Offre> aatabview;

    @FXML
    private TableColumn<Offre, String> aanomoffre;

    @FXML
    private TableColumn<Offre, String> aadescription;

    @FXML
    private TableColumn<Offre, Float> aataux;
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
        
        IOffreService offreservice = new OffreService();
        
           List<Offre> List1 = offreservice.getAll();
           List<Offre> Liste2 = List1.stream().filter(x->x.getEtat()==1).collect(Collectors.toList());

        
           ObservableList<Offre> listoffre =FXCollections.observableArrayList(Liste2);
         aanomoffre.setCellValueFactory(new PropertyValueFactory<>("nom_offre"));
        aadescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        aataux.setCellValueFactory(new PropertyValueFactory<>("taux_remise"));
        aatabview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<Offre>   selecteditem,allitems;
      allitems = aatabview.getItems();
       selecteditem = aatabview.getSelectionModel().getSelectedItems();
       

        aaidoffre.setText( selecteditem.get(0).getId()+"");
        if (newSelection != null) {
        aatabview.getSelectionModel().clearSelection();;
    }
    });
       

        aatabview.setItems(listoffre);   
    }
            
            
    @FXML
    void admindetailsoffreconfirmer(ActionEvent event) throws IOException {
       AdminListeTouteOffresController.setLabelid(aaidoffre.getText());
       Parent parent4 = FXMLLoader.load(getClass().getResource("/GUI/DetailsTouteOffreAdmin.fxml"));
       Scene scene4 = new Scene(parent4);
        
       Stage stage4=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage4.hide();
       stage4.setScene(scene4);
       stage4.show();

    }
    
        @FXML
    void adminlistoffreconfirmetononconfirmer(ActionEvent event) throws IOException {
        
          Parent parent6 = FXMLLoader.load(getClass().getResource("/GUI/AdminListOffres.fxml"));
       Scene scene6 = new Scene(parent6);
        
       Stage stage6=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage6.hide();
       stage6.setScene(scene6);
       stage6.show();

    }

    @FXML
    void adminsupprimeroffreconfirmer(ActionEvent event) throws IOException {
        int id = Integer.parseInt(aaidoffre.getText());
             IOffreService offreservice = new OffreService();
            offreservice.delete(id);
        Parent register_parent11 = FXMLLoader.load(getClass().getResource("/GUI/AdminListeTouteOffres.fxml"));
        Scene register_scene11 = new Scene(register_parent11);
        Stage membresStage11 = (Stage)((Node)event.getSource()).getScene().getWindow();
        membresStage11.hide();
        membresStage11.setScene(register_scene11);
        membresStage11.show();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Supression effectuée avec succés !! ");
        alert.setTitle("Confirmation !");
        alert.showAndWait();

    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        LoadElements();
        
        // TODO
    }    

    public static String getLabelid() {
        return labelid;
    }

    public static void setLabelid(String labelid) {
        AdminListeTouteOffresController.labelid = labelid;
    }
    
    
    
}
