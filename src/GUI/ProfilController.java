/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayan
 */
public class ProfilController implements Initializable {

    @FXML
    private TextField Nom;
    @FXML
    private Button changernom;
       Session UserConnected;
       String path="";
    @FXML
    private TextField id;
    @FXML
    private ImageView improfile;
    @FXML
    private TextField email;
    @FXML
    private Button newimg;
    @FXML
    private PasswordField newpwd;
    @FXML
    private TextField prenom;
    @FXML
    private Circle circle;
    @FXML
    private Label profil;
    @ FXML
       void btn_changer_nom(ActionEvent event) {
                    String imagePath = path;
Image photo=improfile.getImage(); 
         String nouveauNom = Nom.getText();
         //String prenomom = Prenom.getText();
         System.out.println("new"+nouveauNom);
         String nomUser = Nom.getText();
        String email1 = email.getText();
        String prenom1 =prenom.getText();
          UserConnected.setPrenom(prenom1);
    UserConnected.setNom(nomUser);
    UserConnected.setEmail(email1);
    UserConnected.setImage(path);
         UserService US=new UserService();
         //US.ChangerNom(nouveauNom, setUser(UserConnected));
         //US.updateprofile(UserConnected, setUser(UserConnected));
         US.updateprofile(setUser(UserConnected));
         System.out.println("nom modifier");
         Nom.setText(nouveauNom);
    }
       
       @FXML
    private void ajouterimage(ActionEvent event) {
 
     FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image");
    fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
    // load the selected image into the image view
    path=selectedFile.getAbsolutePath();
    Image image = new Image(selectedFile.toURI().toString());
    //improfile.setImage(image);
    //circle.setStrcircleoke(Color.SEAGREEN);
    circle.setStroke(Color.TRANSPARENT);
    circle.setFill(new ImagePattern(image));
    //circle.setEffect(new DropShadow(+25d,0d,+2d,Color.DARKSEAGREEN));

    }
    }
       
        @ FXML
       void modifiermdp(ActionEvent event) {
         String nouveaumdp = newpwd.getText();
         System.out.println("new"+nouveaumdp);
         User u;
         u=setUser(UserConnected);
         u.setPassword(nouveaumdp);
         UserService US=new UserService();
         //US.ChangerNom(nouveauNom, setUser(UserConnected));
         //US.updateprofile(UserConnected, setUser(UserConnected));
        US.updatemdp(u);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /* Nom.setText(UserConnected.getNom());
    email.setText(UserConnected.getEmail());
      prenom.setText(UserConnected.getPrenom());
      */
    }    
    
     /*int setUser(Session UserConnected) {
         this.UserConnected=UserConnected;
         System.out.println("utilisateu connecter :"+UserConnected.getPassword());
              System.out.println("utilisateu connecter :"+UserConnected.getEmail());
                   System.out.println("utilisateu connecter :"+UserConnected.getRoles());
                        System.out.println("utilisateu connecter :"+UserConnected.getImage());
         
         
         
         id.setText(String.valueOf(UserConnected.getId()));
         Nom.setText(UserConnected.getNom());
          email.setText(UserConnected.getEmail());
          String im=UserConnected.getImage();
          System.out.println("image est "+im);
        Image image = new Image(new File(UserConnected.getImage()).toURI().toString());
            improfile.setImage(image);
        return UserConnected.getId();
     }*/
     User setUser(Session UserConnected) {
    this.UserConnected = UserConnected;
    System.out.println("utilisateur connecté :" + UserConnected.getPassword());
    System.out.println("utilisateur connecté :" + UserConnected.getEmail());
    System.out.println("utilisateur connecté :" + UserConnected.getRoles());
    System.out.println("utilisateur connecté :" + UserConnected.getImage());

    id.setText(String.valueOf(UserConnected.getId()));
    Nom.setText(UserConnected.getNom());
    email.setText(UserConnected.getEmail());
      prenom.setText(UserConnected.getPrenom());
      profil.setText("BIENVENUE "+UserConnected.getNom()+"\t"+UserConnected.getPrenom()+":");
    String im=UserConnected.getImage();
    System.out.println("image est "+im);
    Image image = new Image(new File(UserConnected.getImage()).toURI().toString());
    //improfile.setImage(image);
   //circle.setStroke(Color.SEAGREEN);
   
    circle.setStroke(Color.TRANSPARENT);
    circle.setFill(new ImagePattern(image));
    //circle.setEffect(new DropShadow(+25d,0d,+2d,Color.DARKSEAGREEN));

    User user = new User();
    user.setId(UserConnected.getId());
    user.setNom(UserConnected.getNom());
    user.setPrenom(UserConnected.getPrenom());
    user.setEmail(UserConnected.getEmail());
    user.setPassword(UserConnected.getPassword());
    user.setRoles(UserConnected.getRoles());
    user.setImage(UserConnected.getImage());

    return user;
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
                Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
     
    
}
