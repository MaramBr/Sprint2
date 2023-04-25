/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import gestionEvenement.entities.Evenement;
import gestionEvenement.services.EvenementCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class AfficherEvenementController implements Initializable {

    @FXML
    private GridPane gridEvent;
    @FXML
    private TextField chercherEventField;
    @FXML
    private Button ajouterEventBUTTON;

   /**
     * Initializes the controller class.
     */
        EvenementCRUD Ev=new EvenementCRUD();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
      
        try {
            afficherEvenement();
        } catch (SQLException ex) {
            
        }
       
               
    
    }   
        public void afficherEvenement() throws SQLException{
         try {
            List<Evenement> Evenements = Ev.recupererEvenement();
            gridEvent.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < Evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("card.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
               AfficherEvenementController controller = loader.getController();
            
                gridEvent.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
             
         }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }


    @FXML
    private void rechercherevenement(KeyEvent event) {
    }

    @FXML
    private void ajouterEvenement(ActionEvent event) {
    }

    @FXML
    private void trierEvenement(ActionEvent event) {
    }
    
}
