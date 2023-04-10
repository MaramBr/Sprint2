/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnadmin;
    @FXML
    private Label message;
    @FXML
    private Button btnclient;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        File file = new File("");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      
    }    

  

    @FXML
    private void movetoevenement(ActionEvent event) {
           try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("Evenement.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }

    @FXML
    private void movetoevenementfront(ActionEvent event) {
           try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("EvenementFront.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }
    
}
