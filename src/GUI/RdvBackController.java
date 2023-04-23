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
import Utils.JavaMail;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
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

    etatR.setCellValueFactory(new PropertyValueFactory<>("etatrdv"));
    etatR.setCellFactory(column -> new TableCell<RendezVous, Integer>() {
        @Override
        protected void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            } else {
                setText(item == 1 ? "traité" : "non traité");
            }
        }
    });
    
    tablerdv.setItems(rdvList);
}
 
@FXML
void ban(ActionEvent event) throws Exception{
   
    String typeString ;
     RendezVous selectedUser = tablerdv.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
       
         
       
        ServiceRendezVous rdvs = new ServiceRendezVous();
        rdvs.mailing(selectedUser.getId());
       if(selectedUser.getEtatrdv()==0){
       JavaMail.sendMail("VOTRE RESERVATION EST CONFIRMEE!!","maram.brinsi@esprit.tn");
       
       
        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Rendez vous est confirmé !");
        alert.showAndWait();
       }
       else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Annulation");
        alert.setHeaderText(null);
        alert.setContentText("Rendez vous est non confirmé !");
        alert.showAndWait();
        // Réinitialiser les champs
       }  
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un utilisateur ");
        alert.showAndWait();
    }
     show();
     
}

}
