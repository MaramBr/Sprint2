/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author rayan
 */
public class ResetController implements Initializable {

    @FXML
    private PasswordField mdp;
    @FXML
    private Button btnreset;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void update(String email){
       //User u = new User();
            UserService US = new UserService();
            User u = US.getUser(email);
            //String myPwd = "$2y" + BCrypt.hashpw(mdp.getText(), BCrypt.gensalt(13)).substring(3);
            String mdp1 =mdp.getText();
            System.out.println(mdp1+"mdpnouveau");
                        System.out.println(u.getEmail());

             btnreset.setOnAction(e->{
             u.setPassword(mdp.getText());
             US.updatemdp(u);
        try {
         
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
            Parent root = loader.load();
            rootPane.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ResetController.class.getName()).log(Level.SEVERE, null, ex);
        }

    });
    }
     
          @FXML
  void retour(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("mdpoubliee.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
}
