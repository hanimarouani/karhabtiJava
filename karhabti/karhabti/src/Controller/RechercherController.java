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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class RechercherController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField TITRE;

    @FXML
    private JFXTextField PRIX_MIN;

    @FXML
    private JFXTextField PRIX_MAX;

    @FXML
    private JFXComboBox<String> REGION;

    @FXML
    private Label nom;

    @FXML
    private Label Email;

    @FXML
    private JFXComboBox<String> BOITE;

    @FXML
    private ImageView image;
    private static List<Annonces> list;

    public static List<Annonces> getList() {
        return list;
    }

    public static void setList(List<Annonces> list) {
        RechercherController.list = list;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        BOITE.getItems().addAll("","AUTOUMATIQUE", "MANUEL");
        
        REGION.getItems().addAll(
                "",
                "ARIANA",
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
        BOITE.getSelectionModel().selectFirst();
        REGION.getSelectionModel().selectFirst();
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
    }

    @FXML
    public void Recherche(ActionEvent event) throws IOException {

        AnnoncesServices annoncesservice = new AnnoncesServices();
        if (PRIX_MAX.getText().trim().equals("") && PRIX_MIN.getText().trim().equals("")) {
            List<Annonces> annonce = annoncesservice.rechercherprix(TITRE.getText(), BOITE.getValue(), REGION.getValue());
            RechercherController.setList(annonce);
            System.out.println(annonce);
            Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/ListRecherche.fxml"));
            Scene scene1 = new Scene(parent1);

            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage1.hide();
            stage1.setScene(scene1);
            stage1.show();
        } else if (PRIX_MIN.getText().trim().equals("")) {
            List<Annonces> annonce = annoncesservice.rechercherprixmax(TITRE.getText(), BOITE.getValue(), REGION.getValue(), Float.parseFloat(PRIX_MAX.getText()));

            RechercherController.setList(annonce);
            System.out.println(annonce);
            Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/ListRecherche.fxml"));
            Scene scene1 = new Scene(parent1);

            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage1.hide();
            stage1.setScene(scene1);
            stage1.show();
        } else if (PRIX_MAX.getText().trim().equals("")) {

            List<Annonces> annonce = annoncesservice.rechercheprixmin(TITRE.getText(), BOITE.getValue(), REGION.getValue(), Float.parseFloat(PRIX_MIN.getText()));

            RechercherController.setList(annonce);
            System.out.println(annonce);
            Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/ListRecherche.fxml"));
            Scene scene1 = new Scene(parent1);

            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage1.hide();
            stage1.setScene(scene1);
            stage1.show();
        } else {
            List<Annonces> annonce = annoncesservice.rechercher(TITRE.getText(), BOITE.getValue(), REGION.getValue(), Float.parseFloat(PRIX_MIN.getText()), Float.parseFloat(PRIX_MAX.getText()));

            RechercherController.setList(annonce);

            System.out.println(annonce);
            Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/ListRecherche.fxml"));
            Scene scene1 = new Scene(parent1);

            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage1.hide();
            stage1.setScene(scene1);
            stage1.show();
        }
        System.out.println(REGION.getValue());

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

}
