/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.UserService;
import Utils.JavaMail;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayan
 */
public class MdpoublieeController implements Initializable {

    @FXML
    private TextField emailmdp;
    @FXML
    private Button mdpOublier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     void envoyermail(ActionEvent event) throws Exception {
 
   String emailvalue = emailmdp.getText();
   //JavaMail.sendMail(emailvalue);
    }
    
    @FXML
    private void mdpOublier(ActionEvent event) throws Exception {
        UserService u = new UserService();
        //System.out.println(emailmdp.getText());
          if (emailmdp.getText().isEmpty() || !emailmdp.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une adresse email valide.");
        alert.showAndWait();
        return;
    }
         if(u.existence(emailmdp.getText())==0){
           FXMLLoader blog_parent = new FXMLLoader(getClass().getResource("/GUI/CodeTest.fxml"));
        try {
           
            Parent root1 = blog_parent.load();
                   CodeTestController TC = blog_parent.getController();
                   TC.codereset(emailmdp.getText());
                   
                  mdpOublier.getScene().setRoot(root1);
       // Scene blog_scene = new Scene(blog_parent);
       /// Stage app_stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        
       // app_stage.setScene(blog_scene);
       // app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MdpoublieeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Email n'existe pas");
        alert.setContentText("Verifier votre adresse mail!");
        alert.showAndWait();
       }
       
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
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    
}
