/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pidevhany.Models.mes_tests;
import pidevhany.Services.impl.Tests;
import pidevhany.Techniques.DataSource;

/**
 * FXML Controller class
 *
 * @author HANY
 */
public class ModifyTestController implements Initializable {
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
    @FXML
    private TableColumn<mes_tests, String> image;
    @FXML
    private TextArea jniveau;

    @FXML
    private TextArea jchoix2;

    @FXML
    private TextArea jchoix1;

    @FXML
    private Label jid;

    @FXML
    private TextArea jchoix3;

    @FXML
    private TextArea jpath;

    @FXML
    private TextArea jquestion;

    @FXML
    private TextArea jreponse;
    private  Path destination;
    private  File selectedfile;
    

    @FXML
    //houni?? yes
    public void Retour(ActionEvent event) throws Exception{
           //akl
         Parent Retour_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/Admin.fxml"));
         Scene Retour_screen = new Scene(Retour_parent);
         Stage RetourStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         RetourStage.hide();
         RetourStage.setScene(Retour_screen);
         RetourStage.show();
    }
    @FXML
    public void UploadImage(ActionEvent event) {

        FileChooser fc = new FileChooser();
       selectedfile = fc.showOpenDialog(null);
        
        destination = Paths.get("D:\\Esprit\\Atelier Java\\piweb\\pidevHany\\src\\pidevHany\\images",selectedfile.getName());
       
        jpath.setText(selectedfile.getAbsolutePath());
    }
    @FXML 
    public void Recuperer(ActionEvent event) throws Exception{
        mes_tests test1 = table.getSelectionModel().getSelectedItem();
      jid.setText(String.valueOf(test1.getId()));
      jniveau.setText(String.valueOf(test1.getNiveau()));
       jquestion.setText(String.valueOf(test1.getQuestion()));
       jchoix1.setText(String.valueOf(test1.getChoix1()));
       jchoix2.setText(String.valueOf(test1.getChoix2()));
       jchoix3.setText(String.valueOf(test1.getChoix3()));
       jreponse.setText(String.valueOf(test1.getReponse()));
       jpath.setText(String.valueOf(test1.getImage()));
    }
    @FXML
    public void Modifier(ActionEvent event) throws Exception{
    // ps.setInt(1,test.getNiveau());
            
Tests claimAjout=new Tests();
        mes_tests claim=new mes_tests();
        
        claim.setId(Integer.parseInt(jid.getText()));
        claim.setNiveau(Integer.parseInt(jniveau.getText()));
        claim.setQuestion(jquestion.getText());
        claim.setChoix1(jchoix1.getText());
        claim.setChoix2(jchoix2.getText());
        claim.setChoix3(jchoix3.getText());
        claim.setReponse(Integer.parseInt(jreponse.getText()));
        claim.setImage(jpath.getText());
   
        
        
        
      
       claimAjout.modifierTest(claim,Integer.parseInt(jid.getText()));
       JOptionPane.showMessageDialog(null, " Test modifié :) ");
       table.refresh();
    }
    /**
     * Initializes the controller class.
     */
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
        image.setCellValueFactory(new PropertyValueFactory<mes_tests, String>("image"));

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
