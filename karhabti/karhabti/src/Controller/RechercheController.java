/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Annonces;
import Karhabti.modeles.User;
import Karhabti.services.AnnoncesServices;
import Karhabti.services.UserServices;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class RechercheController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Annonces> list;

    @FXML
    private TableColumn<Annonces, String> TITRE;

    @FXML
    private TableColumn<Annonces, String> REGION;

    @FXML
    private TableColumn<Annonces, String> BOITE;

    @FXML
    private TableColumn<Annonces, Integer> NOMBRE_DE_CYLINDRES;

    @FXML
    private TableColumn<Float, String> PRIX;

    @FXML
    private Label nom;

    @FXML
    private Label Email;

    @FXML
    private ImageView image;
        @FXML
    private Label idAnnonce;
private static String id ;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        RechercheController.id = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
//     
        if (newSelection != null) {
        list.getSelectionModel().clearSelection();
    }
        });
        LoadElements();
    }

    public void LoadElements() {

        Annonces annonce = new Annonces();
        AnnoncesServices annonceservice = new AnnoncesServices();

        List<Annonces> list1 = RechercherController.getList();

        ObservableList<Annonces> data = FXCollections.observableArrayList(list1);

        TITRE.setCellValueFactory(new PropertyValueFactory<>("TITRE"));
        REGION.setCellValueFactory(new PropertyValueFactory<>("region"));
        BOITE.setCellValueFactory(new PropertyValueFactory<>("BOITE"));
        PRIX.setCellValueFactory(new PropertyValueFactory<>("PRIX"));

        list.setItems(data);
    }

    @FXML
    void Home(ActionEvent event) throws IOException {
        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
        Scene scene1 = new Scene(parent1);

        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.hide();
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void deconnexion(ActionEvent event) throws IOException {
        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Login.fxml"));
        Scene scene1 = new Scene(parent1);

        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.hide();
        stage1.setScene(scene1);
        stage1.show();
    }
        @FXML
    void Details(ActionEvent event) throws IOException {
       RechercheController.setId(idAnnonce.getText());
        
        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/DetailsRecherche.fxml"));
        Scene scene1 = new Scene(parent1);

        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.hide();
        stage1.setScene(scene1);
        stage1.show();
    }

}
