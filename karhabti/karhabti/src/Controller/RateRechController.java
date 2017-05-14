package Controller;

import Karhabti.modeles.Annonces;
import Karhabti.modeles.Rate;
import Karhabti.modeles.User;
import Karhabti.services.AnnoncesServices;
import Karhabti.services.RateService;
import Karhabti.services.UserServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class RateRechController extends Application implements Initializable {

    Double note;

    @FXML
    private Rating rating;
    
    @FXML
    private Rating rating2;
    
        RateService Rs = new RateService();
        int id = User.getConnectedUser();
        UserServices user = new UserServices();
        User user1 = user.findById(id);
        User U=new User(8);
            AnnoncesServices annonceservice = new AnnoncesServices() ;
         Annonces annonce = annonceservice.findById(ListRechercheController.getIdRech());
        
    @FXML
    void rate(MouseEvent event) throws IOException {
                if (id == annonce.getUser().getId()){
                Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Vous pouvez pas Notez Votre Propre Annonce")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showError();
                }else{
        Rate c = new Rate(rating.getRating(), user1, annonce);
        note = rating.getRating();
        Rs.ajouter(c);
        
        Parent creerGroupe = FXMLLoader.load(getClass().getResource("/Gui/RateRech.fxml"));
            Scene sceneAffichage = new Scene(creerGroupe);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(sceneAffichage);
            stage.show();
             Notifications NotificationBuilder = Notifications.create()
                                .title("Succès")
                                .text("Ton Note à été enregistrer")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showInformation();
    }}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rating.setOrientation(Orientation.HORIZONTAL);
        rating.setUpdateOnHover(false);
        //rating.setPartialRating(true);
        rating2.setRating(Rs.Moyenne());
        System.out.println(Rs.Moyenne());
        
    
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/Gui/RateRech.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
                @FXML
    void Retour(ActionEvent event) throws IOException {
            Parent parent1 = FXMLLoader.load(getClass().getResource("/Gui/homeTest.fxml"));
            Scene scene1 = new Scene(parent1);

            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage1.hide();
            stage1.setScene(scene1);
            stage1.show();
    }
    
}