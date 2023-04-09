/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Maram
 */
public class MenuController implements Initializable {

    @FXML
    private VBox mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pageCoaching(ActionEvent event)throws IOException {
             mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("AjouterCoaching.fxml"));
        mainPane.getChildren().setAll(Content);
    }
    
 
  

    @FXML
    private void pageRDV(ActionEvent event)throws IOException {
             mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("RendezVous.fxml"));
        mainPane.getChildren().setAll(Content);
    }
    
}
