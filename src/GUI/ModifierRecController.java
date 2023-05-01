/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Services.ServiceReclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Majdi
 */
public class ModifierRecController implements Initializable {

    @FXML
    private TextField TFTitre1;
    @FXML
    private Button Add;
    @FXML
    private Button ModifierC;
    @FXML
    private TextArea descTF1;
    
    ServiceReclamation sr = new ServiceReclamation();
    private Reclamation selectedReclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedReclamation = new Reclamation();
        setSelectedReclamation(selectedReclamation);
        // TODO
    }    

    @FXML
    private void ajouterRec(ActionEvent event) {
    }

    @FXML
    private void ModifierRec(ActionEvent event) {
        // Get the updated values from the text fields and text area
    String newTitre = TFTitre1.getText();
    String newDescription = descTF1.getText();

    // Update the selected reclamation object
    selectedReclamation.setTitre(newTitre);
    selectedReclamation.setDescription(newDescription);

    // Call the modifier method in your service to update the reclamation in the database
    sr.modifier(selectedReclamation);

    // Close the current stage
    Stage currentStage = (Stage) ModifierC.getScene().getWindow();
    currentStage.close();
    }

   

    public void setSelectedReclamation(Reclamation reclamation) {
        selectedReclamation = reclamation;
        // Set the values of the text fields and text area based on the selected reclamation
        TFTitre1.setText(reclamation.getTitre());
        descTF1.setText(reclamation.getDescription());
    }

    
}
