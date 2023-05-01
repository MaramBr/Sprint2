/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author rayan
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TextField emailLogin;
    @FXML
    private PasswordField pwdLogin;
     @FXML
    private Text nullErr;
       @FXML
    private Text nullmdp;
        @FXML
    private AnchorPane rootPane;
    @FXML
    private Button btnlogin;
    private void handleClose(ActionEvent event){
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

 @FXML
    private void login(ActionEvent event) {

        Boolean error = false;

        System.out.println("login: " + emailLogin.getText());
        System.out.println("password_field: " + pwdLogin.getText());
         String e = emailLogin.getText();
    String mdp = pwdLogin.getText();
    
    if (e == null || e.isEmpty() || mdp == null || mdp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir l'email et le mot de passe.");
        alert.showAndWait();
        return;
    }

       
       
   

        if (!error) {
            UserService US = new UserService();
            if (US.login(emailLogin.getText(), pwdLogin.getText())) {
                

              
                    
            try {
                 Session now =new Session();
                 System.out.println("ETAT"+now.getIs_active());
                 if(now.getIs_active()==1){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Attention");
    alert.setHeaderText(null);
    alert.setContentText("Vous êtes maintenant Bloqué!");
    alert.showAndWait();
                 }
                 else{
                 
          String s = String.valueOf(now.getRoles()) ;
          
                       if(s.equals("[\"ROLE_ADMIN\"]")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Admin.fxml"));
            Parent root = loader.load();
            rootPane.getScene().setRoot(root);}
                       else if(s.equals("[\"ROLE_Coach\"]")){FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/front.fxml"));
            Parent root = loader.load();
            rootPane.getScene().setRoot(root);}
                       else{
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText(null);
    alert.setContentText("Vous êtes maintenant connecté!");
    alert.showAndWait();
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Profil.fxml"));
            Parent root = loader.load();
            rootPane.getScene().setRoot(root);
              ProfilController Profil = loader.getController();
              
    Profil.setUser(now);
     
                           
                       }
                 }} catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       /* } catch (IOException ex) {
            Logger.getLogger(AllStudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
                

/*
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/edspace/gui/AddStudent.fxml"));
                try {
                    if (Session.getRoles().contains("ADMIN")) {
                        Parent parent = FXMLLoader.load(getClass().getResource("/edu/edspace/gui/HomeBack.fxml")); //backoffice
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UTILITY);
                        stage.show();
                    } else {
                        Parent parent = FXMLLoader.load(getClass().getResource("/edu/edspace/gui/FrontHome.fxml"));
                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UTILITY);
                        stage.show();
                    }

                } catch (IOException ex) {
                    Logger.getLogger(AllStudentsController.class.getName()).log(Level.SEVERE, null, ex);
                }*/


            } else {
                //  msgerreur.setVisible(true);
                // JOptionPane.showMessageDialog(null, "verifier");
                /*
                JOptionPane.showMessageDialog(null, "votre Email ou mot de passe que vous avez saisi(e) n'est pas associé(e) à un compte ",
                        "Alerte", JOptionPane.WARNING_MESSAGE);*/
                Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("votre Email ou mot de passe sont incorrectes!");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez vérifier l'adresse e-mail,et le mot de passe que vous avez entrés et réessayer.!");
    alert.showAndWait();
            }
            }
        }
        
       
    
    @FXML
      void Gotoacoount(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("signup.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        

    }

      @FXML
      void mdpoubliee(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("mdpoubliee.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                  


     
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        

    }

    @FXML
    private void handleClose(MouseEvent event) {
    }
    
}
