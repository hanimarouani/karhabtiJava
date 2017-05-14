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
import Karhabti.interfaces.Iannonces;
import Karhabti.interfaces.Iuser;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class MesAnnoncesController implements Initializable {

    @FXML
    private JFXTextField TITRE;

    @FXML
    private JFXTextField CYLINDREE;

    @FXML
    private JFXTextField NOMBRE_DE_CYLINDRES;

    @FXML
    private JFXTextField PRIX;

    @FXML
    private Label idAnnonce;

    @FXML
    private JFXTextArea DESCRIPITION;

    @FXML
    private JFXTextField ENERGIE;

    @FXML
    private JFXTextField PUISSANCE_FISCALE;

    @FXML
    private JFXTextField BOITE;

    @FXML
    private TableView<Annonces> tableview;
    @FXML
    private TableColumn<Annonces, String> TITREC;

    @FXML
    private TableColumn<Annonces, String> REGIONC;
    @FXML
    private JFXTextField Image;

    @FXML
    private JFXTextField REGION;

    private static Path destination;
    private static File selectedfile;

    @FXML
    void ajouterimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        selectedfile = fc.showOpenDialog(null);

        destination = Paths.get("/root/NetBeansProjects/karhabti.v1/src/Image", selectedfile.getName());

        Image.setText(selectedfile.getAbsolutePath());
    }

    @FXML
    void supprimer(ActionEvent event) {
        int id = Integer.parseInt(idAnnonce.getText());
        AnnoncesServices offreservice = new AnnoncesServices();
        offreservice.supprimer(id);
        AnnoncesServices annonceservice = new AnnoncesServices();
        tableview.getItems().removeAll();

        List<Annonces> List1 = offreservice.getAll();
        int id1 = User.getConnectedUser();

        List<Annonces> Liste2 = List1.stream().filter(x -> x.getUser().getId() == id1).collect(Collectors.toList());

        ObservableList<Annonces> listoffre = FXCollections.observableArrayList(Liste2);
tableview.refresh();
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {
        UserServices userservice = new UserServices();
        User user = userservice.findById(User.getConnectedUser());
        AnnoncesServices annonceservice = new AnnoncesServices();
        Annonces annonce = new Annonces(TITRE.getText(), BOITE.getText(), REGION.getText(), Integer.parseInt(CYLINDREE.getText()), ENERGIE.getText(), DESCRIPITION.getText(), Float.parseFloat(PRIX.getText()), Float.parseFloat(PUISSANCE_FISCALE.getText()), Float.parseFloat(CYLINDREE.getText()));
        annonce.setId(Integer.parseInt(idAnnonce.getText()));
        if (selectedfile == null) {
            annonce.setImage(null);
        } else {
            Files.copy(selectedfile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            annonce.setImage(destination.toString());
        }

        annonceservice.modifier(annonce);
        tableview.getItems().removeAll();
        AnnoncesServices offreservice = new AnnoncesServices();

        List<Annonces> List1 = offreservice.getAll();
        int id = User.getConnectedUser();

        List<Annonces> Liste2 = List1.stream().filter(x -> x.getUser().getId() == id).collect(Collectors.toList());

        ObservableList<Annonces> listoffre = FXCollections.observableArrayList(Liste2);
tableview.refresh();
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadElements();
    }

    public void LoadElements() {

        AnnoncesServices offreservice = new AnnoncesServices();

        List<Annonces> List1 = offreservice.getAll();
        int id = User.getConnectedUser();

        List<Annonces> Liste2 = List1.stream().filter(x -> x.getUser().getId() == id).collect(Collectors.toList());

        ObservableList<Annonces> listoffre = FXCollections.observableArrayList(Liste2);
        TITREC.setCellValueFactory(new PropertyValueFactory<>("TITRE"));
        REGIONC.setCellValueFactory(new PropertyValueFactory<>("region"));
        tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            ObservableList<Annonces> selecteditem, allitems;
            allitems = tableview.getItems();
            selecteditem = tableview.getSelectionModel().getSelectedItems();

            DESCRIPITION.setText(selecteditem.get(0).getDESCRIPITION());
            TITRE.setText(selecteditem.get(0).getTITRE());
            BOITE.setText(selecteditem.get(0).getDESCRIPITION());
            idAnnonce.setText(selecteditem.get(0).getId() + "");
//        
            if (newSelection != null) {
                tableview.getSelectionModel().clearSelection();;
            }
        });

        tableview.setItems(listoffre);
    }

    @FXML
    void showmoredetails(ActionEvent event) throws IOException {
        AnnoncesServices offreservice = new AnnoncesServices();
        Annonces annonce = offreservice.findById(Integer.parseInt(idAnnonce.getText()));
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("detaille de l'offre " + annonce.getTITRE());

        alert.setContentText("DESCRIPITION" + annonce.getDESCRIPITION());

        alert.showAndWait();

    }

}
