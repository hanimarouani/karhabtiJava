/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Annonces;
import Karhabti.services.AnnoncesServices;
import Karhabti.services.UserServices;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AdminListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<Annonces, String> TITRE;

    @FXML
    private TableView<Annonces> list;

    @FXML
    private TableColumn<Annonces, String> REGION;
    @FXML
    private Label idAnnonce;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        LoadElements();
    }

    public void LoadElements() {

        Annonces annonce = new Annonces();
        AnnoncesServices annonceservice = new AnnoncesServices();

        List<Annonces> annonces = annonceservice.getAnnoncesNonValider();
        ObservableList<Annonces> data = FXCollections.observableArrayList(annonces);

        TITRE.setCellValueFactory(new PropertyValueFactory<>("TITRE"));
        REGION.setCellValueFactory(new PropertyValueFactory<>("region"));

        list.setItems(data);
        list.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            ObservableList<Annonces> selecteditem, allitems;
            allitems = list.getItems();
            selecteditem = list.getSelectionModel().getSelectedItems();

            idAnnonce.setText(selecteditem.get(0).getId() + "");
//        
            if (newSelection != null) {
                list.getSelectionModel().clearSelection();;
            }

        });
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
        System.out.println("succsessfully inserted ");
    }

    @FXML
    void annonceSignaler(ActionEvent event) throws IOException {
        Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/SignalerList.fxml"));
        Scene scene1 = new Scene(parent1);

        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage1.hide();
        stage1.setScene(scene1);
        stage1.show();
    }
}
