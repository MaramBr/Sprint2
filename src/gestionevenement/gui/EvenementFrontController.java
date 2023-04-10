/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import gestionEvenement.entities.Evenement;
import gestionEvenement.services.EvenementCRUD;
import gestionEvenement.services.SponsorCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



/**
 * FXML Controller class
 *
 * @author emna
 */
public class EvenementFrontController implements Initializable {

  
    
    private EvenementCRUD ecrd = new EvenementCRUD();
    
    public ObservableList<Evenement> data = FXCollections.observableArrayList();

    @FXML
    private Button btnrafraichira;
    @FXML
    private Button btnretour;

    @FXML
    private Button participer;
    @FXML
    private TableColumn<Evenement, String> colsponsor;
    @FXML
    private TableColumn<Evenement, String> colnom;
    @FXML
    private TableColumn<Evenement, String> collieu;
    @FXML
    private TableColumn<Evenement, String> coltype;
    @FXML
    private TableColumn<Evenement, String> coldescription;
    @FXML
    private TableColumn<Evenement, String> coldatedebut;
    @FXML
    private TableColumn<Evenement, String> coldatefin;
    @FXML
    private TableColumn<Evenement, String> colimage;
    @FXML
    private TableColumn<Evenement, Integer> colnbparticipant;
    @FXML
    private TableColumn<Evenement, Integer> colprix;
    @FXML
    private TableColumn<Evenement, Integer> colidevent;
   @FXML
    private TableView<Evenement> tableEvenement;
    @FXML
    private ImageView image1;
    
// Association de l'ObservableList au TableView

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //show(event);
        File file = new File("C:/Users/emnaa/OneDrive/Documents/NetBeansProject/GestionEvenement/src/image/logoEfit.png");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         image1.setImage(new Image(localURL));
          show();
       
    }  
    
   
   

   

    @FXML
    public void show() {
     EvenementCRUD cv = new EvenementCRUD();
    List<Evenement> evenements = cv.afficherEvenement();
    SponsorCRUD sv = new SponsorCRUD();
    if (evenements != null) {
        data = FXCollections.observableArrayList(evenements);
        colidevent.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       collieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        coldatedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
       colnbparticipant.setCellValueFactory(new PropertyValueFactory<>("nb_participant"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        colsponsor.setCellValueFactory(cellData -> {
            Integer sponsorsId = cellData.getValue().getSponsors_id();
            if (sponsorsId != null) {
                String sponsorName = sv.getSponsorNameById(sponsorsId);
                return new SimpleStringProperty(sponsorName);
            } else {
                return new SimpleStringProperty("");
            }
        });
        
        colsponsor.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
colnom.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
collieu.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");

coltype.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
coldescription.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
coldatedebut.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
coldatefin.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
colimage.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
colnbparticipant.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
colprix.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");

        
        
          tableEvenement.getColumns().clear(); // Suppression des anciennes colonnes
        tableEvenement.getColumns().add(colsponsor);
        tableEvenement.getColumns().add(colnom);
        tableEvenement.getColumns().add(collieu);
        tableEvenement.getColumns().add(coltype);
        tableEvenement.getColumns().add(coldescription);
        tableEvenement.getColumns().add(coldatedebut);
        tableEvenement.getColumns().add(coldatefin);
        tableEvenement.getColumns().add(colimage);
        tableEvenement.getColumns().add(colnbparticipant);
        tableEvenement.getColumns().add(colprix);
        

        tableEvenement.setItems(data);
    } else {
        // afficher un message d'erreur ou effectuer une autre action appropriée
    }
    
}

    

    @FXML
    private void retour(ActionEvent event) {
           try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }

    @FXML
private void participer(ActionEvent event) {
    
     // Vérifier si une ligne est sélectionnée dans la table
    if (tableEvenement.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune ligne sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne dans la table !");
        alert.showAndWait();
        return;
    }
    Evenement selectedEvenement = tableEvenement.getSelectionModel().getSelectedItem();
    if (selectedEvenement != null) {
        int idEvent = selectedEvenement.getId();
        String nomEvent = selectedEvenement.getNom();
        try {
            
           /////redirection//////
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Participant.fxml"));
            Parent messageParent = loader.load();
            ParticipantController ParticipantController = loader.getController();
            ParticipantController.setIdEvent(idEvent, nomEvent);
            Scene messageScene = new Scene(messageParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(messageScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

///////////////Date////////////////////////////////
private void trierDate(MouseEvent event) {
        TableColumn<Evenement, String> dateColumn = (TableColumn<Evenement, String>) event.getSource();
        tableEvenement.getSortOrder().clear(); // Supprimer tout tri précédent
        data.sort((r1, r2) -> r1.getDate_debut().compareTo(r2.getDate_debut()));
    }


}
    
    


