/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

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
        
//        
//
//        // Confirmation de la suppression
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation de la suppression");
//        alert.setHeaderText(null);
//        alert.setContentText("Êtes-vous sûr de vouloir supprimer la réclamation sélectionnée ?");
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.isPresent() && result.get() == ButtonType.OK) {
//            // Supprimer la réclamation de la base de données
//            sr.supprimer(selectedReclamation);
//            // Appeler la méthode de notification
//            String message = "Une réclamation a été supprimée.";
//            sp.notification(message);
//            // refresh table
//           
//            // Supprimer la réclamation de la table
//           

        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        
        
    } 
}
