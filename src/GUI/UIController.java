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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayan
 */
public class UIController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label label;
 Session UserConnected;
    @FXML
    private Circle admin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
        label.setText("BONJOUR"+UserConnected.getNom()+"\t"+UserConnected.getPrenom());
       mainPane.getChildren().clear();
        Parent Content;
          Image image = new Image(new File(UserConnected.getImage()).toURI().toString());
    //improfile.setImage(image);
   //circle.setStroke(Color.SEAGREEN);
   
   admin.setStroke(Color.TRANSPARENT);
   admin.setFill(new ImagePattern(image));
        
        try {
            Content = FXMLLoader.load(getClass().getResource("Admin.fxml"));
             mainPane.getChildren().setAll(Content);
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }   
    @FXML
    private void pageCoaching(MouseEvent event)throws IOException {
             mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void pagereclamation(MouseEvent event) throws IOException {
         mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("Relamation.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void pageproduit(MouseEvent event) throws IOException {
        mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void pagecategorie(MouseEvent event) throws IOException {
        mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void pagerendezvous(MouseEvent event) throws IOException {
        mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("rdvBack.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void pagecoaching(MouseEvent event) throws IOException {
        mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("AjouterCoaching.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void pagepaticipant(MouseEvent event) throws IOException {
        mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("ParticipantBack.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void pagetransporteur(MouseEvent event) throws IOException {
        mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("Transporteur.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    
    
     @FXML
    private void pagecomapanie(MouseEvent event) throws IOException {
        mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("companie.fxml"));
        mainPane.getChildren().setAll(Content);
    }
     @FXML
    private void logout(MouseEvent event) throws IOException {
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
     @FXML
    private void pageevenement(MouseEvent event) throws IOException {
        mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("evenement.fxml"));
        mainPane.getChildren().setAll(Content);
    }
      @FXML
    private void pageSponsor(MouseEvent event) throws IOException {
        mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("Sponsor.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    @FXML
    private void pagecomapanie(ContextMenuEvent event) {
    }

}
