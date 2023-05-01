/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Majdi
 */
public class CardReclamationController implements Initializable {

    @FXML
    private HBox box;
    @FXML
    private Button update;
    @FXML
    private Label Titre;
    @FXML
    private Label Description;
    @FXML
    private Label Statut;
    @FXML
    private Label Traitement;
    
    private Reclamation reclamation;
    @FXML
    private Button delete;
        private  String [] colors = {"B9E5FF","BDB2FE","FB9AA8","FF5056"} ;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    
public void setReclamation(Reclamation r) {
    reclamation = r;
    
    Titre.setText(r.getTitre());
    Description.setText(r.getDescription());
    Statut.setText(r.getStatus());
    Traitement.setText(r.getTraitement());

    // Appliquer des styles esthétiques au label Statut
    if (r.getStatus().equals("confirmé")) {
        Statut.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 12px; -fx-alignment: center;");
        this.update.setVisible(false);
        this.delete.setVisible(false);
    } else if (r.getStatus().equals("en attente")) {
        Statut.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 12px; -fx-alignment: center;");
        this.update.setVisible(true);
        this.delete.setVisible(true);
    }

    // Appliquer des styles esthétiques aux autres labels (Titre, Description, Traitement)
    Titre.setStyle("-fx-font-weight: bold; -fx-font-size: 15px; -fx-alignment: center;");
    Description.setStyle("-fx-font-size: 11px; -fx-alignment: center;");
    Traitement.setStyle("-fx-font-size: 10px; -fx-alignment: center;");

    box.setStyle("-fx-background-color:  #e15e1c; -fx-background-radius: 15;"
            + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 10);");
}



    @FXML
    private void modifier(ActionEvent event) {
        try {
            // Load the "ModifierRec.fxml" file and create a new stage for it
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRec.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Get the controller of the "ModifierRec.fxml" file
            ModifierRecController modifierController = loader.getController();
           

            // Pass the selected reclamation to the controller if it is not null
            if (reclamation != null) {
                modifierController.setSelectedReclamation(reclamation);

                // Show the new stage
                stage.show();
            } else {
                System.out.println("No selected reclamation.");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        
          // Afficher une boîte de dialogue de confirmation pour confirmer la suppression
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de la suppression");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer cette réclamation ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Supprimer la réclamation de la base de données en utilisant le service approprié
        ServiceReclamation sr = new ServiceReclamation();
       
        sr.supprimer(reclamation);

        setReclamation(reclamation);
        // Fermer la fenêtre actuelle ou actualiser l'affichage des réclamations

        // Vous pouvez également actualiser l'affichage des réclamations en appelant une méthode de rafraîchissement appropriée
        // sur le contrôleur de la vue parente.
    }
}
}
