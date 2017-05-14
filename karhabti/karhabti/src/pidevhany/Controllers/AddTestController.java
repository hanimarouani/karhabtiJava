/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;

import com.sun.javafx.scene.control.skin.TextAreaSkin;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;

import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.textfield.TextFields;
import pidevhany.Models.mes_tests;
import pidevhany.Services.impl.Tests;
/**
 * FXML Controller class
 *
 * @author HANY
 */
public class AddTestController implements Initializable {



    private  Path destination;
    private  File selectedfile;
    private int irep;
    private int iniv;
    @FXML 
    private BorderPane AddTestpane;
    @FXML
    private TextArea  reponse;
    @FXML
    private TextArea question;
    @FXML
    private TextArea choix2;
    @FXML
    private TextArea choix1;
    @FXML
    private TextArea choix3;
    @FXML
    private TextArea  niveau;
    @FXML
    private TextField imagepath;
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
    public void UploadImage(ActionEvent event) {

        FileChooser fc = new FileChooser();
       selectedfile = fc.showOpenDialog(null);
        
        destination = Paths.get(selectedfile.getName());
       
        imagepath.setText(selectedfile.getAbsolutePath());
    }
    @FXML
     public void AddAction(ActionEvent e) throws IOException{
         
    
      mes_tests test = new mes_tests();
     
       String niv = niveau.getText();
       String ques = question.getText();
       String ch1 = choix1.getText();
       String ch2 = choix2.getText();
       String ch3 = choix3.getText();
       String rep = reponse.getText();
         if(selectedfile==null){
        test.setImage(null);
        }else{
            Files.copy(selectedfile.toPath(),destination,StandardCopyOption.REPLACE_EXISTING); 
             test.setImage(destination.toString());
      iniv = Integer.parseInt(niv);
      irep = Integer.parseInt(rep);
      test.setNiveau(iniv);
      test.setReponse(irep);
      test.setChoix1(ch1);
      test.setChoix2(ch2);
      test.setChoix3(ch3);
      test.setChoix4(null);
      test.setChoix5(null);
      test.setQuestion(ques);
      Tests tt = new Tests();
      tt.ajouterTest(test);
             JOptionPane.showMessageDialog(null, " Test ajouté :) ");
      
        
    }
}
    Set<String> possibleWords = new HashSet<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         TextFields.bindAutoCompletion(imagepath, possibleWords);
         
    }    
   
}

