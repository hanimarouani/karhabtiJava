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
import javafx.geometry.Pos;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ELYES
 */
public class MesannonceController implements Initializable {
    @FXML
    private TextField CYLINDREE;

    @FXML
    private TextField NOMBRE_DE_CYLINDRES;

    @FXML
    private TableColumn<Annonces, String> TITREC;

    @FXML
    private TableColumn<Annonces,String> REGIONC;
        @FXML
    private TableColumn<Annonces,String> NOMBRE_DE_CYLINDRESC;

    @FXML
    private TableColumn<Annonces,String> PRIXC;
    @FXML
    private TableColumn<Annonces, String> BOITEC;
        @FXML
    private Label nom;

    @FXML
    private Label Email;
        @FXML
    private ImageView imageu;

    @FXML
    private TextField PUISSANCE_FISCALE;

    @FXML
    private TextField BOITE;

    @FXML
    private TextField Image;

    @FXML
    private TextField REGION;

    @FXML
    private TextField TITRE;
        @FXML
    private Label idAnnonce;

    @FXML
    private TextField PRIX;

    @FXML
    private TextArea DESCRIPITION;

    @FXML
    private TextField ENERGIE;

    @FXML
    private TableView<Annonces> tableview;
    
    private static String id;

   private static Path destination;
    private static File selectedfile;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        MesannonceController.id = id;
    }

    @FXML
    void ajouterimage(ActionEvent event) {
FileChooser fc = new FileChooser();
      selectedfile = fc.showOpenDialog(null);
        
      destination = Paths.get("/root/NetBeansProjects/karhabti.v1/src/Image",selectedfile.getName());
       
        Image.setText(selectedfile.getAbsolutePath());
    }
    
    @FXML
    void supprimer(ActionEvent event) {
int id = Integer.parseInt(idAnnonce.getText());
 AnnoncesServices annonceservice = new AnnoncesServices();
 annonceservice.supprimer(id);
         tableview.getItems().removeAll();

        List<Annonces> List1 = annonceservice.getAll();
        int id1 = User.getConnectedUser();

        List<Annonces> Liste2 = List1.stream().filter(x -> x.getUser().getId() == id1).collect(Collectors.toList());

        ObservableList<Annonces> list = FXCollections.observableArrayList(Liste2);
        tableview.setItems(list);
tableview.refresh();
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {
           UserServices userservice = new UserServices();
        User user = userservice.findById(User.getConnectedUser());
        AnnoncesServices annonceservice = new AnnoncesServices();
   Annonces annonce = new Annonces(TITRE.getText(),BOITE.getText(),REGION.getText(),Integer.parseInt( NOMBRE_DE_CYLINDRES.getText()),ENERGIE.getText(),DESCRIPITION.getText(),Float.parseFloat(PRIX.getText()),Float.parseFloat(PUISSANCE_FISCALE.getText()),Float.parseFloat(CYLINDREE.getText()));
       annonce.setId(Integer.parseInt(idAnnonce.getText()));
   if(selectedfile==null){
         annonce.setImage(null);
        }else{
            Files.copy(selectedfile.toPath(),destination,StandardCopyOption.REPLACE_EXISTING); 
         annonce.setImage(destination.toString());
        }


       
        annonceservice.modifier(annonce);
                tableview.getItems().
                        removeAll();

        List<Annonces> List1 = annonceservice.getAll();
        int id = User.getConnectedUser();

        List<Annonces> Liste2 = List1.stream().filter(x -> x.getUser().getId() == id).collect(Collectors.toList());

        ObservableList<Annonces> list = FXCollections.observableArrayList(Liste2);
        tableview.setItems(list);
        tableview.refresh();
        
           Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Votre Modification effectué avec succès!! ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showInformation();
        
        
        
        
        
    }
  




    @Override
    public void initialize(URL url, ResourceBundle rb) {
//                           int id = User.getConnectedUser();
//           UserServices user = new UserServices();
//           User user1 = user.findById(id);
//           nom.setText(user1.getUsername());
//           Email.setText(user1.getEmail());
//                  File file1=null ;
//        file1 = new File(user1.getImage()); 
//  
//        Image image2 = new Image(file1.toURI().toString());
//      
//        imageu.setImage(image2);
         LoadElements();
        
   }  
     
      
    public void LoadElements(){
       
        
        
     
   
        
        AnnoncesServices offreservice = new AnnoncesServices();
        
           List<Annonces> List1 = offreservice.getAll();
           int id = User.getConnectedUser();
         
           List<Annonces> Liste2 = List1.stream().filter(x->x.getUser().getId()==id).collect(Collectors.toList());
         
         ObservableList<Annonces> listannonce =FXCollections.observableArrayList(Liste2);
               TITREC.setCellValueFactory(new PropertyValueFactory<>("TITRE"));
        REGIONC.setCellValueFactory(new PropertyValueFactory<>("region"));
        BOITEC.setCellValueFactory(new PropertyValueFactory<>("BOITE"));
        NOMBRE_DE_CYLINDRESC.setCellValueFactory(new PropertyValueFactory<>("NOMBRE_DE_CYLINDRES"));
        PRIXC.setCellValueFactory(new PropertyValueFactory<>("PRIX"));
tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<Annonces>   selecteditem,allitems;
      allitems = tableview.getItems();
       selecteditem = tableview.getSelectionModel().getSelectedItems();
       
        TITRE.setText(selecteditem.get(0).getTITRE());
        BOITE.setText(selecteditem.get(0).getBOITE());
        DESCRIPITION.setText(selecteditem.get(0).getDESCRIPITION());
        REGION.setText(selecteditem.get(0).getRegion());
        ENERGIE.setText(selecteditem.get(0).getENERGIE());
        Image.setText(selecteditem.get(0).getImage());
        CYLINDREE.setText(String.valueOf(selecteditem.get(0).getCYLINDREE()));
        PUISSANCE_FISCALE.setText(String.valueOf(selecteditem.get(0).getPUISSANCE_FISCALE()));
        NOMBRE_DE_CYLINDRES.setText(String.valueOf(selecteditem.get(0).getNOMBRE_DE_CYLINDRES()));
        PRIX.setText(String.valueOf(selecteditem.get(0).getPRIX()));
        idAnnonce.setText( selecteditem.get(0).getId()+"");
//     
        if (newSelection != null) {
        tableview.getSelectionModel().clearSelection();
    }
        
    }
);


        tableview.setItems(listannonce);   
       
    }

      @FXML
    void showmoredetails(ActionEvent event) throws IOException {
       MesannonceController.setId(idAnnonce.getText());
       AnnoncesServices annonceservice = new AnnoncesServices() ;
          System.out.println(annonceservice.findById(Integer.parseInt(MesannonceController.getId())));
          System.out.println("hhhh");
    Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/Details.fxml"));
       Scene scene1 = new Scene(parent1);
        
       Stage stage1=(Stage)((Node)event.getSource()).getScene().getWindow();
       stage1.hide();
       stage1.setScene(scene1);
       stage1.show();


    }

       




}
