/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Participant;
import Entities.Sponsor;
import Utils.MyDB;
import Services.PDFGenerator;
import Services.ParticipantCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
    private ImageView image1;
  @FXML
private Pane card;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelPrenom;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelAge;
    @FXML
    private Label labelTel;
    @FXML
    private Label labelEvent;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
     
        File file = new File("C:/Users/Majdi/Documents/NetBeansProjects/integration/Sprint2/src/images/bg.png");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
           image1.setImage(new Image(localURL));
    }
    MyDB cnx = null;
    Statement st = null;
    ParticipantCRUD pcd = new ParticipantCRUD();

    ///////////////////////////////////
    public void show() {
        try {
            String requete = "SELECT * FROM participant";
            Statement st = MyDB.getInstance().getCnx().createStatement();
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

    

   
public void show2() {
    try {
        String requete = "SELECT * FROM participant ORDER BY id DESC LIMIT 1";
        Statement st = MyDB.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        if (rs.next()) {
            Participant r = new Participant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("age"), rs.getInt("tel"));
            data.clear(); // Clear previous data
            data.add(r); // Add the last row to the data list
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
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

public void show3() {
    try {
        String requete = "SELECT * FROM participant ORDER BY id DESC LIMIT 1";
        Statement st = MyDB.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        if (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            int age = rs.getInt("age");
            int tel = rs.getInt("tel");

            labelNom.setText(nom);
            labelPrenom.setText(prenom);
            labelEmail.setText(email);
            labelAge.setText(Integer.toString(age));
            labelTel.setText(Integer.toString(tel));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

}
   
public void show4() {
    try {
        String requete = "SELECT * FROM participant ORDER BY id DESC LIMIT 1";
        Statement st = MyDB.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        if (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            int age = rs.getInt("age");
            int tel = rs.getInt("tel");
            String nomEvenement = tfnomev.getText();

            labelNom.setText(nom);
            labelPrenom.setText(prenom);
            labelEmail.setText(email);
            labelAge.setText(Integer.toString(age));
            labelTel.setText(Integer.toString(tel));
            labelEvent.setText(nomEvenement);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
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
    show4();
    show2();
  sendMail(tfemail);
    // Rafraîchir la vue de la table
    tableparticipant.refresh();
 
    // Effacer les champs de saisie
    tfnom.setText("");
    tfprenom.setText("");
    tfemail.setText("");
    tfage.setText("");
    tftel.setText("");
    
     // Appelle la fonction sendMail avec le champ de texte en paramètre
   
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
    show2();

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

    // Récupérer les données des labels

String nom = labelNom.getText();
String prenom = labelPrenom.getText();
String email = labelEmail.getText();
int age = Integer.parseInt(labelAge.getText());
int tel = Integer.parseInt(labelTel.getText());
String nomev = labelEvent.getText();


// Créer un Participant avec les données récupérées
Participant participant = new Participant(nom, prenom, email, age, tel,nomev);

FileChooser fileChooser = new FileChooser();

// Définir l'extension de fichier par défaut
FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
fileChooser.getExtensionFilters().add(extFilter);

// Afficher la boîte de dialogue pour enregistrer le fichier
File file = fileChooser.showSaveDialog(null);

if (file != null) {
    PDFGenerator pdfGenerator = new PDFGenerator();
    // Générer le PDF avec une liste contenant le Participant créé
    pdfGenerator.generatePDF(Arrays.asList(participant), file.getAbsolutePath());
}

}
    
    
    
public void setIdEvent(int idEvent, String nomEvent) {
    this.idEventSelectionnee = idEvent;
    tfevent.setText(String.valueOf(idEvent));
    tfnomev.setText(nomEvent);
}

  
public void ajouterParticipantEvenement(String tfEvent) {
    // Récupérer l'ID de l'événement à partir du champ de texte
    String idEvent = tfevent.getText();

    // Ajouter le participant à l'événement dans la table participant_evenement
    String idParticipant = null;
    String sql = "SELECT id FROM participant ORDER BY id DESC LIMIT 1";
    try {
        Statement stmt = MyDB.getInstance().getCnx().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            idParticipant = rs.getString("id");
        }
        rs.close();
        stmt.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    try {
        Statement stm = MyDB.getInstance().getCnx().createStatement();
        String sql1 = "INSERT INTO participant_evenement (participant_id, evenement_id) VALUES ('" + idParticipant + "', '" + idEvent + "')";
        stm.executeUpdate(sql1);
        stm.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    // Mettre à jour le nombre de participants dans la table evenement
    String sql2 = "UPDATE evenement SET nb_participant = nb_participant - 1 WHERE id = ?";
    try {
        PreparedStatement stmt = MyDB.getInstance().getCnx().prepareStatement(sql2);
        stmt.setString(1, idEvent);
        stmt.executeUpdate();
        stmt.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}



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
    
      // Vider la tableview
    tableparticipant.getItems().clear();

    // Mettre à jour le nombre de participants dans la table evenement
    String sql2 = "UPDATE evenement SET nb_participant = nb_participant + 1 WHERE id = ?";
    try {
        String idEvent = tfevent.getText();
        PreparedStatement stmt = MyDB.getInstance().getCnx().prepareStatement(sql2);
        stmt.setString(1, idEvent);
        stmt.executeUpdate();
        stmt.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}






public void sendMail(TextField tfemail) {

    // Récupère l'adresse e-mail saisie dans le schamp de texte
    String recipient = tfemail.getText();

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
String nomev = tfnomev.getText();

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
}
