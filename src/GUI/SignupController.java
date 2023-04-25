/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayan
 */
public class SignupController implements Initializable {
@FXML
    private TextField prenomsignup ;
 
    @FXML
    private TextField emailsignup;
    @FXML
    private TextField nomsignup;
    
     @FXML
    private PasswordField mdpsignup;
    @FXML
    private ImageView imagesignup;
    String path="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void ajouterimagesignup(ActionEvent event) {
 
     FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image");
    fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
    // load the selected image into the image view
    path=selectedFile.getAbsolutePath();
    Image image = new Image(selectedFile.toURI().toString());
    imagesignup.setImage(image);
    }

      
    }

      
        
        
    
    
     @FXML
    private void ajoutesignup(ActionEvent event) {
        Image photo=imagesignup.getImage();  ///image Done
        
                String nomValue = nomsignup.getText();
                String prenomValue = prenomsignup.getText();
                String pwdValue = mdpsignup.getText();
                String emailValue = emailsignup.getText();
                String typeUserValue = "[\"ROLE_USER\"]";
                String imagePath = path;
        
                 // Validate email input
    if (emailValue.isEmpty() || !emailValue.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une adresse email valide.");
        alert.showAndWait();
        return;
    }
 if (nomValue.isEmpty() || !nomValue.matches("[a-zA-Z]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Un Nom ne doit contenir que des lettres.");
        alert.showAndWait();
        return;
    }
     
      if (prenomValue.isEmpty() || !prenomValue.matches("[a-zA-Z]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Un prenom ne doit contenir que des lettres.");
        alert.showAndWait();
        return;
    }
       if (pwdValue.isEmpty() || !(pwdValue.length() > 5)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("mot de passe doit Supérieur a 5 caracteres");
        alert.showAndWait();
        return;
    }
     // Check if email is already used by another user
    UserService userService = new UserService();
    if (userService.isEmailUsed(emailValue)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'adresse email est déjà utilisée par un autre utilisateur.");
        alert.showAndWait();
        return;
    }
    
    // Create new user object
    User u = new User(nomValue, prenomValue, pwdValue, emailValue, typeUserValue, imagePath);
    
    // Insert new user into the database
    userService.insert(u);
    
    // Show success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Utilisateur ajouté avec succès !");
    alert.showAndWait();
    
    // Clear input fields
    nomsignup.setText("");
    prenomsignup.setText("");
   mdpsignup.setText("");
    emailsignup.setText("");
    imagesignup.setImage(null);
        
    }
    @FXML
    void retour(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    
}
