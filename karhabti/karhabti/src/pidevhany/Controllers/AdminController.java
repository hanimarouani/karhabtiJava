/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevhany.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HANY
 */
public class AdminController implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXHamburger Humburger;

    @FXML
    private JFXDrawer drawer;
@FXML
public void Ajouter(ActionEvent event) throws Exception{
         Parent niv1_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/AddTest.fxml"));
         Scene niv1_screen = new Scene(niv1_parent);
         Stage nivUnStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivUnStage.hide();
         nivUnStage.setScene(niv1_screen);
         nivUnStage.show();
        
    }
@FXML
public void Modifier(ActionEvent event) throws Exception{
         Parent niv1_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/ModifyTests.fxml"));
         Scene niv1_screen = new Scene(niv1_parent);
         Stage nivUnStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivUnStage.hide();
         nivUnStage.setScene(niv1_screen);
         nivUnStage.show();
        
    }
@FXML
public void Supprimer(ActionEvent event) throws Exception{
         Parent niv1_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/DeleteTests.fxml"));
         Scene niv1_screen = new Scene(niv1_parent);
         Stage nivUnStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivUnStage.hide();
         nivUnStage.setScene(niv1_screen);
         nivUnStage.show();
        
    }
@FXML
public void Lister(ActionEvent event) throws Exception{
         Parent niv1_parent = FXMLLoader.load(getClass().getResource("/pidevhany/GUI/ListeTets.fxml"));
         Scene niv1_screen = new Scene(niv1_parent);
         Stage nivUnStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         nivUnStage.hide();
         nivUnStage.setScene(niv1_screen);
         nivUnStage.show();
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            HamburgerBackArrowBasicTransition menu = new HamburgerBackArrowBasicTransition(Humburger);
        menu.setRate(-1);
        Humburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
        menu.setRate(menu.getRate() * -1);
        menu.play();
//            if (drawer.isShown()) {
//                drawer.close();
//            }
//            else drawer.open();
        });
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }    
    
}
