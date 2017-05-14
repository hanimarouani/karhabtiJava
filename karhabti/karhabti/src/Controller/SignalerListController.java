/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Annonces;
import Karhabti.modeles.Signalisations;
import Karhabti.services.AnnoncesServices;
import Karhabti.services.SignalisationsServices;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class SignalerListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Signalisations> list;

    @FXML
    private TableColumn<Signalisations,Integer> count;

    @FXML
    private TableColumn<Signalisations, String> titre;

    @FXML
    private TableColumn<Signalisations,String> Nom_usersig;

    @FXML
    private Label idAnnonce;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               Annonces annonce = new Annonces();
        SignalisationsServices sign = new SignalisationsServices();
        
       List<Signalisations> annonces = sign.getAll();
        ObservableList<Signalisations> data = FXCollections.observableArrayList(annonces);
       
        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        Nom_usersig.setCellValueFactory(CellData -> new ReadOnlyStringWrapper(CellData.getValue().getId_usersig().getUsername()));
         titre.setCellValueFactory(CellData -> new ReadOnlyStringWrapper(CellData.getValue().getId_annonce().getTITRE()));
    
     list.setItems(data);
   list.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<Signalisations>   selecteditem,allitems;
      allitems = list.getItems();
       selecteditem = list.getSelectionModel().getSelectedItems();
       
        idAnnonce.setText( selecteditem.get(0).getId_annonce().getId()+"");
//        
        if (newSelection != null) {
        list.getSelectionModel().clearSelection();;
    }
    
    }); 
    }
    @FXML
    void supprimer(ActionEvent event) {
        int id = Integer.parseInt(idAnnonce.getText());
        AnnoncesServices annonceeservice = new AnnoncesServices();
        annonceeservice.supprimer(id);
                SignalisationsServices sign = new SignalisationsServices();

                list.getItems().removeAll();
        ObservableList<Signalisations> l = FXCollections.observableArrayList();
        l = FXCollections.observableArrayList(sign.getAll());
        list.setItems(l);
        list.refresh();
                    Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Suppression effectué avec succès!! ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showInformation();
                        
    }    
    
}
