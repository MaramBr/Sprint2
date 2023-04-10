/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import gestionEvenement.entities.Participant;
import gestionEvenement.entities.Sponsor;
import gestionEvenement.utils.MyConnection;
import gestionevenement.services.ParticipantCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
//import javax.mail.*;
//import javax.mail.internet.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class ParticipantController implements Initializable {

 

    private ParticipantCRUD pcrd = new ParticipantCRUD();
    public ObservableList<Participant> data = FXCollections.observableArrayList();
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnenvoyer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button pdf;
   
 private int  idEventSelectionnee;
    @FXML
    private TableView<Participant> tableparticipant;
    @FXML
    private TableColumn<?, ?> colnom;
    @FXML
    private TableColumn<?, ?> colprenom;
    @FXML
    private TableColumn<?, ?> colemail;
    @FXML
    private TableColumn<?, ?> colage;
    @FXML
    private TableColumn<?, ?> coltel;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfage;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TableColumn<?, ?> colidp;
    @FXML
    private TextField tfevent;
    @FXML
    private TextField tfnomev;
    @FXML
    private Button btnEvenement;
    @FXML
    private ImageView image1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        show();
        File file = new File("C:/Users/emnaa/OneDrive/Documents/NetBeansProject/GestionEvenement/src/image/logoFit.png");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
           image1.setImage(new Image(localURL));
    }
    MyConnection cnx = null;
    Statement st = null;
    ParticipantCRUD pcd = new ParticipantCRUD();

    ///////////////////////////////////
    public void show() {
        try {
            String requete = "SELECT * FROM participant";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
            Participant r = new Participant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("age"), rs.getInt("tel"));
                data.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        colidp.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colage.setCellValueFactory(new PropertyValueFactory<>("age"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        
        colidp.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
colnom.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
colprenom.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");

colemail.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
colage.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
coltel.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
       
        
        tableparticipant.setItems(data);
    }

    

   

    
  

   

    @FXML
private void ajouterparticipant(ActionEvent event) {
    // Vérifier que tous les champs sont remplis
    if (tfnom.getText().isEmpty() || tfprenom.getText().isEmpty() || tfemail.getText().isEmpty()) {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Aucune de ces informations ne doit être vide. Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }

    // Vérifier que la longueur de la description est supérieure à 5 caractères
    if (tfnom.getText().length() < 3) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("nom plus que 3 chiffres");
        alert.showAndWait();
        return;
    }

    // Vérifier que l'email est valide
    String email = tfemail.getText();
    if (!email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Email invalide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une adresse email valide.");
        alert.showAndWait();
        return;
    }


    // Récupérer les valeurs des champs
    String nom = tfnom.getText();
    String prenom = tfprenom.getText();
    
    // Vérifier que l'âge et le téléphone sont des nombres entiers et ne dépassent pas 8 chiffres
if (tfage.getText().isEmpty() || tftel.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Champs vides");
    alert.setHeaderText(null);
    alert.setContentText("Aucune de ces informations ne doit être vide. Veuillez remplir tous les champs.");
    alert.showAndWait();
    return;
}
try {
    int age = Integer.parseInt(tfage.getText());
    int tel = Integer.parseInt(tftel.getText());
    if ( tftel.getText().length() < 8) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le téléphone doit avoir au moins 8 chiffres.");
        alert.showAndWait();
        return;
    }
} catch (NumberFormatException e) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("L'âge et le téléphone doivent être des nombres entiers.");
    alert.showAndWait();
    return;
}
    int age = Integer.parseInt(tfage.getText());
    int tel = Integer.parseInt(tftel.getText());

    pcrd.ajouterParticipant(new Participant(nom, prenom, email, age, tel));

    // Ajouter le participant à l'événement
    String idEvent = tfevent.getText();
    ajouterParticipantEvenement(idEvent);

    // Rafraîchir la liste de données
    data.clear();
    show();

    // Rafraîchir la vue de la table
    tableparticipant.refresh();

    // Effacer les champs de saisie
    tfnom.setText("");
    tfprenom.setText("");
    tfemail.setText("");
    tfage.setText("");
    tftel.setText("");
}

    @FXML
private void modifierparticipant(ActionEvent event) {
    // Récupérer le participant sélectionné dans la table
    Participant participant = tableparticipant.getSelectionModel().getSelectedItem();
    if (participant == null) {
        // Si aucun participant n'est sélectionné, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Participant non sélectionné");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un participant à modifier dans la liste.");
        alert.showAndWait();
        return;
    }

    // Vérifier que tous les champs sont remplis
    if (tfnom.getText().isEmpty() || tfprenom.getText().isEmpty() || tfemail.getText().isEmpty()) {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Aucune de ces informations ne doit être vide. Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }

    // Vérifier que la longueur du nom est supérieure à 3 caractères
    if (tfnom.getText().length() < 3) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Le nom doit contenir au moins 3 caractères");
        alert.showAndWait();
        return;
    }
     // Vérifier que l'email est valide
    String email = tfemail.getText();
    if (!email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Email invalide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une adresse email valide.");
        alert.showAndWait();
        return;
    }
    

    // Mettre à jour les informations du participant
    participant.setNom(tfnom.getText());
    participant.setPrenom(tfprenom.getText());
    participant.setEmail(tfemail.getText());
    participant.setAge(Integer.parseInt(tfage.getText()));
    participant.setTel(Integer.parseInt(tftel.getText()));

    // Mettre à jour le participant dans la liste
    pcrd.modifierParticipant(participant);

    // Rafraîchir la liste de données
    data.clear();
    show();

    // Rafraîchir la vue de la table
    tableparticipant.refresh();

    // Effacer les champs de saisie
    tfnom.setText("");
    tfprenom.setText("");
    tfemail.setText("");
    tfage.setText("");
    tftel.setText("");

    // Afficher un message de confirmation
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Participant modifié");
    alert.setHeaderText(null);
    alert.setContentText("Le participant a été modifié avec succès.");
    alert.showAndWait();
}


    @FXML
    private void afficherParticipantSelectionnee(MouseEvent event) {
        
           Participant Participant = tableparticipant.getSelectionModel().getSelectedItem();
        if (Participant != null) {
            tfnom.setText(Participant.getNom());
            tfprenom.setText(Participant.getPrenom());
            tfemail.setText(Participant.getEmail());
            tfage.setText(String.valueOf(Participant.getAge()));

            tftel.setText(String.valueOf(Participant.getTel()));

            
          
          
        } else {
            tfnom.setText("");
            tfprenom.setText("");
            tfemail.setText("");
             tfage.setText("");
              tftel.setText("");
        }
    }

    @FXML
    private void generatePDF(ActionEvent event) {
        
        
    }

    
    
    
public void setIdEvent(int idEvent, String nomEvent) {
    this.idEventSelectionnee = idEvent;
    tfevent.setText(String.valueOf(idEvent));
    tfnomev.setText(nomEvent);
}

  
public void ajouterParticipantEvenement(String tfEvent) {

    // Récupérer l'ID de l'événement à partir du champ de texte
    String idEvent = tfevent.getText();

    // Récupérer l'ID du dernier participant ajouté à partir de la table participant de la base de données
    String idParticipant = null;
    String sql = "SELECT id FROM participant ORDER BY id DESC LIMIT 1";
    try {
        Statement stmt = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            idParticipant = rs.getString("id");
        }
        rs.close();
        stmt.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    // Ajouter l'ID de l'événement et l'ID du participant dans la table participant_evenement de la base de données
    try {
        Statement stm = MyConnection.getInstance().getCnx().createStatement();
        String sql1 = "INSERT INTO participant_evenement (participant_id, evenement_id) VALUES ('" + idParticipant + "', '" + idEvent + "')";
        stm.executeUpdate(sql1);
        stm.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


    @FXML
    private void GoEvenementfront(ActionEvent event) {
         try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("EvenementFront.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }

  @FXML
private void supprimerparticipant(ActionEvent event) {
    // Récupérer le participant sélectionné dans la table view
    Participant selectedParticipant = tableparticipant.getSelectionModel().getSelectedItem();
    if (selectedParticipant == null) {
        // Aucune ligne n'est sélectionnée
        return;
    }
    
    // Récupérer les valeurs des colonnes "nom", "prenom" et "email" du participant sélectionné
    String nom = selectedParticipant.getNom();
    String prenom = selectedParticipant.getPrenom();
    String email = selectedParticipant.getEmail();
    
    // Créer la requête SQL pour supprimer le participant de la base de données en fonction des valeurs des colonnes sélectionnées
    String sql = "DELETE FROM participant WHERE nom = '" + nom + "' AND prenom = '" + prenom + "' AND email = '" + email + "'";
    
    try {
        // Exécuter la requête SQL
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(sql);
        
        // Supprimer le participant de la table view
        tableparticipant.getItems().remove(selectedParticipant);
        
        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suppression de participant");
        alert.setHeaderText(null);
        alert.setContentText("Le participant a été supprimé avec succès.");
        alert.showAndWait();
        
        // Fermer la connexion à la base de données
        st.close();
    } catch (SQLException e) {
        // Afficher un message d'erreur en cas d'échec de la suppression
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de la suppression de participant");
        alert.setHeaderText(null);
        alert.setContentText("Impossible de supprimer le participant sélectionné.");
        alert.showAndWait();
        e.printStackTrace();
    }
}
}
