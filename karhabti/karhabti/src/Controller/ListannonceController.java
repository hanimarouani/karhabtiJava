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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ListannonceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label nom;

    @FXML
    private Label Email;

    @FXML
    private ImageView image;

    @FXML
    private TableView<Annonces> list;

    @FXML
    private TableColumn<Annonces,String> TITRE;

    @FXML
    private TableColumn<Annonces,String> REGION;

    @FXML
    private TableColumn<Annonces,String> BOITE;

    @FXML
    private TableColumn<Annonces,String> NOMBRE_DE_CYLINDRES;

    @FXML
    private TableColumn<Annonces,Float> PRIX;
        @FXML
    private TableColumn<Annonces, Date> Date;
        @FXML
    private Label id_user;
    @FXML
    private Label idAnnonce;
private static String idlist ;

    public static String getIdlist() {
        return idlist;
    }

    public static void setIdlist(String idlist) {
        ListannonceController.idlist = idlist;
    }


    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        int id = User.getConnectedUser();
//        UserServices user = new UserServices();
//        User user1 = user.findById(id);
//        nom.setText(user1.getUsername());
//        Email.setText(user1.getEmail());
//        File file = null;
//        file = new File(user1.getImage());
//
//        Image image1 = new Image(file.toURI().toString());
//
//        image.setImage(image1);
        list.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<Annonces>   selecteditem,allitems;
      allitems = list.getItems();
       selecteditem = list.getSelectionModel().getSelectedItems();
       
        TITRE.setText(selecteditem.get(0).getTITRE());
        idAnnonce.setText( selecteditem.get(0).getId()+"");
        id_user.setText(selecteditem.get(0).getUser().getId()+"");
//     
        if (newSelection != null) {
        list.getSelectionModel().clearSelection();
    }
        });
        LoadElements();
    }
        public void LoadElements(){

        int id = User.getConnectedUser();       
       Annonces annonce = new Annonces();
        AnnoncesServices annonceservice = new AnnoncesServices();
        
       List<Annonces> annonces = annonceservice.getAnnoncesNonValider();
        ObservableList<Annonces> data = FXCollections.observableArrayList(annonces);
       
        TITRE.setCellValueFactory(new PropertyValueFactory<>("TITRE"));
        REGION.setCellValueFactory(new PropertyValueFactory<>("region"));
        BOITE.setCellValueFactory(new PropertyValueFactory<>("BOITE"));
        NOMBRE_DE_CYLINDRES.setCellValueFactory(new PropertyValueFactory<>("NOMBRE_DE_CYLINDRES"));
        PRIX.setCellValueFactory(new PropertyValueFactory<>("PRIX"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
         list.setItems(data);
    }



        @FXML
    void Details(ActionEvent event) throws IOException {
       ListannonceController.setIdlist(idAnnonce.getText());
        
        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/DetailsList.fxml"));
        Scene scene1 = new Scene(parent1);

        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.hide();
        stage1.setScene(scene1);
        stage1.show();
    }
    public void Accepter() {
        int id = Integer.parseInt(idAnnonce.getText());
        AnnoncesServices annonceservice = new AnnoncesServices();
        annonceservice.Accepter(id);
        list.getItems().removeAll();
        ObservableList<Annonces> l = FXCollections.observableArrayList();
        l = FXCollections.observableArrayList(annonceservice.getAnnoncesNonValider());
        list.setItems(l);
        list.refresh();
       
    }
        @FXML
    void Supprimer(ActionEvent event) {
        int id = Integer.parseInt(idAnnonce.getText());
        AnnoncesServices annonceservice = new AnnoncesServices();
        annonceservice.supprimer(id);
                list.getItems().removeAll();
        ObservableList<Annonces> l = FXCollections.observableArrayList();
        l = FXCollections.observableArrayList(annonceservice.getAnnoncesNonValider());
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