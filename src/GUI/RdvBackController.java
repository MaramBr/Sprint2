/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Coaching;
import Entities.RendezVous;
import Services.ServiceCoaching;
import Services.ServiceRendezVous;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Maram
 */
public class RdvBackController implements Initializable {

    @FXML
    private TableColumn<RendezVous, Integer> idR;
    @FXML
    private TableColumn<RendezVous, String> coursR;
    @FXML
    private TableColumn<RendezVous, Integer> etatR;
    @FXML
    private TableColumn<RendezVous, java.sql.Date> dateR;
      ServiceRendezVous sr = new ServiceRendezVous();
    ObservableList<RendezVous> rdvList;
    int index = -1;
    @FXML
    private TableView<RendezVous> tablerdv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();
    }    
 
    
     public void show() {

        ObservableList<RendezVous> rdvList = FXCollections.observableArrayList(sr.afficherrdv());
      

        System.out.println("affichage" + sr.afficherrdv());
        idR.setCellValueFactory(new PropertyValueFactory<>("id"));
        coursR.setCellValueFactory(new PropertyValueFactory<>("nomCours"));

        dateR.setCellValueFactory(new PropertyValueFactory<>("daterdv"));
        
        

        tablerdv.setItems(rdvList);
        
        //////////////////////////////////////////
       
}
}
