/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pidevhany.Models.mes_tests;
import pidevhany.Services.impl.Tests;
import pidevhany.Techniques.DataSource;

/**
 * FXML Controller class
 *
 * @author HANY
 */
public class DeleteTestsController implements Initializable {
    @FXML
    private TableView<mes_tests> table;
    @FXML
    private TableColumn<mes_tests, Integer> niveau;
    @FXML
    private TableColumn<mes_tests, Integer> id;
    @FXML
    private TableColumn<mes_tests, String> question;
    @FXML
    private TableColumn<mes_tests, String> choix1;
    @FXML
    private TableColumn<mes_tests, String> choix2;
    @FXML
    private TableColumn<mes_tests, String> choix3;
    @FXML
    private TableColumn<mes_tests, Integer> reponse;
    
       public DeleteTestsController() {
             Connection connection = DataSource.getInstane().getConnection();
    }
    @FXML
    public void Modifier(ActionEvent event) throws Exception{
    Parent Retour_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/ModifyTest.fxml"));
         Scene Retour_screen = new Scene(Retour_parent);
         Stage RetourStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         RetourStage.hide();
         RetourStage.setScene(Retour_screen);
         RetourStage.show();
    }
    @FXML
    public void Retour(ActionEvent event) throws Exception{
         Parent Retour_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/Admin.fxml"));
         Scene Retour_screen = new Scene(Retour_parent);
         Stage RetourStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         RetourStage.hide();
         RetourStage.setScene(Retour_screen);
         RetourStage.show();
    }
    @FXML
    public void DeleteTest(ActionEvent event) {
         mes_tests test1 = table.getSelectionModel().getSelectedItem();

        if (test1 == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(table.getScene().getWindow());
            alert.setTitle("Aucune Sélection");
            alert.setHeaderText("Aucun test");
            alert.setContentText("Veuillez Sélectionner un test");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(table.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression test");
            alert.setContentText("Veuillez Confirmer la Suppression");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Tests t =new Tests();
                    System.out.println(test1.getId());
                    t.supprimerTest(test1.getId());

                    table.getItems().remove(table.getSelectionModel().getSelectedItem());
                    table.refresh();

              }
           });
    }
    }
    /**
     * Initializes the controller class.
     */
//    Tests test = new Tests();
//     mes_tests mesTests = table.getSelectionModel().getSelectedItem();
//     
     private IntegerProperty index = new SimpleIntegerProperty();
    public ObservableList<mes_tests> data = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     index.set(-1);
        Connection connection = DataSource.getInstane().getConnection();
        List<mes_tests> tests = new ArrayList();
         try {
            String req = "select * from mes_tests";
            java.sql.PreparedStatement ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                data.add(new mes_tests(resultSet.getInt("id"),resultSet.getInt("niveau"),
                        resultSet.getString("image"), resultSet.getString("question"),
                        resultSet.getInt("reponse"), resultSet.getString("choix1"),
                        resultSet.getString("choix2"), resultSet.getString("choix3")));
            }
//            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id.setCellValueFactory(new PropertyValueFactory<mes_tests, Integer>("id"));
        niveau.setCellValueFactory(new PropertyValueFactory<mes_tests, Integer>("niveau"));
        question.setCellValueFactory(new PropertyValueFactory<mes_tests, String>("question"));
        choix1.setCellValueFactory(new PropertyValueFactory<mes_tests, String>("choix1"));
        choix2.setCellValueFactory(new PropertyValueFactory<mes_tests, String>("choix2"));
        choix3.setCellValueFactory(new PropertyValueFactory<mes_tests, String>("choix3"));
        reponse.setCellValueFactory(new PropertyValueFactory<mes_tests, Integer>("reponse"));

        table.setItems(data);
        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<mes_tests>() {
            @Override
            public void changed(ObservableValue<? extends mes_tests> observable, mes_tests oldValue, mes_tests newValue) {
            index.set(data.indexOf(newValue));
              //  System.out.println(data.indexOf(newValue));
            }
        });
    }    
    
}
