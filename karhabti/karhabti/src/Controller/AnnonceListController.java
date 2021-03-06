/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Karhabti.modeles.Annonces;
import Karhabti.modeles.User;
import Karhabti.services.AnnoncesServices;
import Karhabti.services.SignalisationsServices;
import Karhabti.services.UserServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class AnnonceListController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private JFXListView List;
    
        @FXML
    private Label idAnnonce;
    private static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AnnonceListController.id = id;
    }
    
    @FXML
    void Details(ActionEvent event) {
                ((Node)(event.getSource())).getScene().getWindow().hide();
        try {
        
        Annonces annonce = new Annonces();
        AnnoncesServices in = new AnnoncesServices();
 int index = List.getSelectionModel().getSelectedIndex();
            
       Annonces selecteds = in.getAll().get(index);
        annonce.setId(selecteds.getId());
            setId(annonce.getId());
   
            System.out.println("selected "+selecteds);
             System.out.println("Annonce "+annonce);
            Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Gui/DetailsList.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        DetailsListController rcontroller = fxmlLoader.<DetailsListController>getController();
        
      
           
        
       stage.setScene(new Scene(root));
                stage.setTitle("");
              
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();
                } catch (Exception e) {e.printStackTrace();
        }

    }


    public void initialize(URL url, ResourceBundle rb) {
//      
//try {
//                details1.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\Mohamed\\Documents\\NetBeansProjects\\karhabti\\src\\Image\\voiture.png"))));
//            } catch (FileNotFoundException ex) {
//                //Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
//            }

              File file = null;

        ImageView imagev1 = null;
        // TODO   
         int id = User.getConnectedUser();
        UserServices u3 = new UserServices();
        AnnoncesServices u2= new AnnoncesServices();  
        SignalisationsServices sign = new SignalisationsServices();
        List<Annonces> listS =sign.tonlist(id);
        List<User> listSU =u3.getAll();
        
        ObservableList<User> ListUser = FXCollections.observableArrayList(listSU);
        ObservableList<Annonces> ListFamille = FXCollections.observableArrayList(listS);
         List.setExpanded(true);
         List.depthProperty().set(1);
         for(Annonces p:listS){
             HBox H=new HBox();
             Label lbl = new Label();
             JFXButton button = new JFXButton();
             button.setText("Rejoindre");
             button.setStyle( "-fx-background-color:#2196F3");
             Image image = null;
            file = new File(p.getImage());
            image = new Image(file.toURI().toString());
            System.out.println(p.getImage());

            imagev1 = new ImageView(image);
            imagev1.setFitHeight(80);
            imagev1.setFitWidth(90);
            

             H.setPrefHeight(100); 
             H.setPrefWidth(1000);
             H.getChildren().addAll(imagev1,new Label("TITRE: "+p.getTITRE()),new Label("Date: "+p.getDate()) ,new Label("REGION: "+p.getRegion()),new Label("PRIX: "+p.getPRIX()+"Dt"));
            H.setSpacing(50);
                     List.getItems().add(H);
         }
                }
}
        
    

