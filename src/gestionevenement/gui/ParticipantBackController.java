/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import gestionEvenement.entities.Participant;
import gestionEvenement.utils.MyConnection;
import gestionevenement.services.ParticipantCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class ParticipantBackController implements Initializable {
    
     private ParticipantCRUD pcrd = new ParticipantCRUD();
    public ObservableList<Participant> data = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colidp;
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
    private Button btnsupprimer;
    @FXML
    private TableView<Participant> tableparticipant;
    @FXML
    private Button btnevenement;
    @FXML
    private Button btnSponsor;
    @FXML
    private Button btnParticipant;
    @FXML
    private ImageView image1;
    @FXML
    private TextField searchInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    show();
        File file = new File("C:/Users/emnaa/OneDrive/Documents/NetBeansProject/GestionEvenement/src/image/logoEfit.png");
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

    
    @FXML
    private void gererEvenement(ActionEvent event) {
         try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("Evenement.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }
    
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
        
      
       
        
        tableparticipant.setItems(data);
    }

    @FXML
    private void gererSponsor(ActionEvent event) {
         try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("Sponsor.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }

 

    @FXML
    private void gereParticipant(ActionEvent event) {
         try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("Participant.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }
 public void sendMail(TableColumn colemail) {

    // Récupère l'adresse e-mail saisie dans le schamp de texte
    String recipient = colemail.getText();

    // Vérifie que l'adresse e-mail est valide
    if (!isValidEmailAddress(recipient)) {
        // Affiche une notification d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("L'adresse e-mail saisie n'est pas valide.");
        alert.showAndWait();
        return;
    }
// Récupère le type de evénement sélectionné dans la ChoiceBox
String nomev = colemail.getText();

    // Paramètres de configuration pour le serveur SMTP de Gmail
    String host = "smtp.gmail.com";
    int port = 587;
    String username = "emna.abbessi@esprit.tn";
    String password = "12715163";

    // Configuration de la session avec les propriétés nécessaires
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", port);

    // Création de la session avec authentification
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        // Création du message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("Confirmation de réception de votre evénement");
        message.setText("Cher client,\n"
                + "vous etes participer dans l evenement " +nomev+
                "\nElle est prise en considération et nous vous remercions pour votre participation \nN'hésitez pas à nous contacter si vous avez besoin d'aide.\nCordialement,\nL'équipe du service client.");            
        // Envoi du message
        Transport.send(message);

        // Affiche une notification de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Votre evénement est envoyée avec succès !");
        alert.showAndWait();

    } catch (MessagingException e) {
        // Affiche une notification d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur s'est produite lors de l'envoi du message : " + e.getMessage());
        alert.showAndWait();
    }
}

private boolean isValidEmailAddress(String email) {
    // Vérifie que l'adresse e-mail est valide en utilisant une expression régulière
    String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    return email.matches(regex);
}   

    @FXML
    private void searchParticipant(ActionEvent event) {
        
        String searchTerm = searchInput.getText();
    ParticipantCRUD sponsorService = new ParticipantCRUD();
         ObservableList<Participant> sponsorList = sponsorService.searchParticipants(searchTerm);
    tableparticipant.setItems(sponsorList);
    }
}
