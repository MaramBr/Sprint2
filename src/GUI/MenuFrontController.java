/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Maram
 */
public class MenuFrontController implements Initializable {

    @FXML
    private HBox mainPane;
    @FXML
    private Circle profil;
     Session UserConnected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image = new Image(new File(UserConnected.getImage()).toURI().toString());
    //improfile.setImage(image);
   //circle.setStroke(Color.SEAGREEN);
   
    profil.setStroke(Color.TRANSPARENT);
   profil.setFill(new ImagePattern(image));
    }    

    @FXML
    private void pagerdv(MouseEvent event) throws IOException {
         mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("RendezVous.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void CoachingPage(MouseEvent event) throws IOException {
         mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("Front.fxml"));
        mainPane.getChildren().setAll(Content);
    }
    @FXML
    private void Produit(MouseEvent event) throws IOException {
         mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("frontP.fxml"));
        mainPane.getChildren().setAll(Content);
    }
 @FXML
    private void Reclamation(MouseEvent event) throws IOException {
         mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("ReclamamtionFront.fxml"));
        mainPane.getChildren().setAll(Content);
    }
     @FXML
    private void Event(MouseEvent event) throws IOException {
         mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("FrontEv.fxml"));
        mainPane.getChildren().setAll(Content);
    }
 @FXML
    private void tls(MouseEvent event) throws IOException {
         mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("FrontT.fxml"));
        mainPane.getChildren().setAll(Content);
    }
    @FXML
    private void profil(MouseEvent event) throws IOException {
        
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Profil.fxml"));
            Parent root = loader.load();
            mainPane.getScene().setRoot(root);
           ProfilController Profil = loader.getController();
              
    Profil.setUser(UserConnected);
     
    }


    private void BackC(MouseEvent event) throws IOException {
         mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("AjouterCoaching.fxml"));
        mainPane.getChildren().setAll(Content);
        
      
    }

    private void Backrdv(MouseEvent event) throws IOException {
           mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("rdvBack.fxml"));
        mainPane.getChildren().setAll(Content);
    }
    
    private void logout(MouseEvent event) throws IOException {
        
    }

    @FXML
    private void logoutt(MouseEvent event) {
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
