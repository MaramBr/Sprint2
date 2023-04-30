/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Sponsor;
import Utils.MyDB;
import Services.SponsorCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
//import javax.mail.*;
//import javax.mail.internet.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class SponsorController implements Initializable {

    
    @FXML
    private ChoiceBox<String> tftype;
    

    private SponsorCRUD rcrd = new SponsorCRUD();
  
   
   
    public ObservableList<Sponsor> data = FXCollections.observableArrayList();
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnenvoyer;
    @FXML
    private Button btnmodifier;
    @FXML
    private TextField emailField;
    @FXML
    private Button btnretour;
    @FXML
    private ImageView image1;
    @FXML
    private TableView<Sponsor> tableSponsors;
    @FXML
    private TextField tfnom;
    @FXML
    private TableColumn<?, ?> colnom;
    @FXML
    private TableColumn<?, ?> colemail;
    @FXML
    private TableColumn<?, ?> colinvest;
    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private Button statistique;
    @FXML
    private Button trie;
    @FXML
    private Button trie1;
    @FXML
    private TextField searchInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tftype.getItems().addAll("Service", "Technique", "financiere");
        tftype.getSelectionModel().selectFirst();
     
      
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
    MyDB cnx = null;
    Statement st = null;
    SponsorCRUD rcd = new SponsorCRUD();

 

    @FXML
    private void ajoutersponsor(ActionEvent event) {
 // Vérifier que tous les champs sont remplis
        if (tftype.getValue() == null || emailField.getText().isEmpty() || tfnom.getText().isEmpty()) {
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
            alert.setHeaderText("le nom doit contenir au moins 3 caractères");
            alert.showAndWait();
            return;
        }
        
        // Récupérer les valeurs des champs
       
      
        String nom = tfnom.getText();
       // Vérifier que l'email est valide
    String email = emailField.getText();
    if (!email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Email invalide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une adresse email valide.");
        alert.showAndWait();
        return;
    }
          String invest = tftype.getValue();
        rcrd.ajouterSponsor(new Sponsor(nom, email, invest));
        // Rafraîchir la liste de données
        data.clear();
        show();
        // Rafraîchir la vue de la table
        tableSponsors.refresh();
   
    }

   @FXML
private void afficherReclamationSelectionnee(MouseEvent event) {
    Sponsor sponsor = tableSponsors.getSelectionModel().getSelectedItem();
    if (sponsor != null) {
        tfnom.setText(sponsor.getNom_Sponsor());
        emailField.setText(sponsor.getEmail());
        tftype.setValue(sponsor.getInvest());
    } else {
        tfnom.setText("");
        emailField.setText("");
        tftype.getSelectionModel().clearSelection();
    }
}


    @FXML
    private void modifiersponsor(ActionEvent event) {
   
        Sponsor Sponsor = tableSponsors.getSelectionModel().getSelectedItem();
        if (Sponsor == null) {
            // Aucune evénement sélectionnée, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une Sponsor à modifier.");
            alert.showAndWait();
        } else {
            // Vérifier que tous les champs sont remplis
            if (tftype.getValue() == null  || tfnom.getText().isEmpty()|| emailField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs avant de modifier une sponsor.");
                alert.showAndWait();
                return;
            }

            // Vérifier que la longueur de la description est supérieure à 5 caractères
            if (tfnom.getText().length() < 3) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("le nom doit contenir au moins 3 caractères");
                alert.showAndWait();
                return;
            }
 
            // Récupérer les nouvelles valeurs
          
         
            String nom = tfnom.getText();
               // Vérifier que l'email est valide
    String email = emailField.getText();
    if (!email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Email invalide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une adresse email valide.");
        alert.showAndWait();
        return;
    }
              String invest = tftype.getValue();

            // Mettre à jour la evénement dans la base de données
            rcrd.modifierSponsor(new Sponsor(Sponsor.getId(), nom, email, invest));

            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussie");
            alert.setHeaderText(null);
            alert.setContentText("La sponsor a été modifiée avec succès.");
            alert.showAndWait();

            // Rafraîchir la table view pour afficher les nouvelles données
            data.clear();
            show();
        }
    }

    
    @FXML
    private void supprimersponsor(ActionEvent event) {
       
            // Récupérer la ligne sélectionnée dans la table view
            Sponsor selectedSponsors = tableSponsors.getSelectionModel().getSelectedItem();
            if (selectedSponsors == null) {
            // Aucune ligne n'est sélectionnée
            return;
            }
            
            // Récupérer les valeurs des colonnes "coltype", "coldate" et "coldescription" de la ligne sélectionnée
            String nom_Sponsor = selectedSponsors.getNom_Sponsor();
            String email = selectedSponsors.getEmail();
            String invest = selectedSponsors.getInvest();
            
            // Créer la requête SQL pour supprimer la evénement de la base de données en fonction des valeurs des colonnes sélectionnées
            String sql = "DELETE FROM sponsor WHERE nom_sponsor = '" + nom_Sponsor + "' AND email = '" + email + "' AND invest = '" + invest + "'";
            
            try {
            // Exécuter la requête SQL
            Statement st = MyDB.getInstance().getCnx().createStatement();
            st.executeUpdate(sql);
            
            // Supprimer la ligne de la table view
            tableSponsors.getItems().remove(selectedSponsors);
            
            // Afficher un message de confirmation
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Suppression deSponsors");
            alert.setHeaderText(null);
            alert.setContentText("La Sponsors a été supprimée avec succès.");
            alert.showAndWait();
            
            // Fermer la connexion à la base de données
            st.close();
            } catch (SQLException e) {
            // Afficher un message d'erreur en cas d'échec de la suppression
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur lors de la suppression de Sponsors");
            alert.setHeaderText(null);
            alert.setContentText("Impossible de supprimer la Sponsors sélectionnée.");
            alert.showAndWait();
            e.printStackTrace();
            
            }
   
    } 

    
  

    
    
    
    
    
public void show() {
        try {
            String requete = "SELECT * FROM sponsor";
            Statement st = MyDB.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Sponsor r = new Sponsor(rs.getInt("id"), rs.getString("nom_Sponsor"), rs.getString("email"), rs.getString("invest"));
                data.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

       
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom_Sponsor"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colinvest.setCellValueFactory(new PropertyValueFactory<>("invest"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableSponsors.setItems(data);
        
         


    }


    @FXML
    private void retour(ActionEvent event) {
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
    private void statistique(ActionEvent event) {
         ObservableList<Sponsor> Sponsors = tableSponsors.getItems();
    Map<String, Integer> statistiques = new HashMap<>();

    // Calcul des statistiques
    for (Sponsor r : Sponsors) {
        String type = r.getInvest();
        if (statistiques.containsKey(type)) {
            statistiques.put(type, statistiques.get(type) + 1);
        } else {
            statistiques.put(type, 1);
        }
    }

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    int totalSponsors = 0;
    for (Map.Entry<String, Integer> entry : statistiques.entrySet()) {
        String type = entry.getKey();
        int nbSponsors = entry.getValue();
        totalSponsors += nbSponsors;
        pieChartData.add(new PieChart.Data(type + " (" + nbSponsors + ")", nbSponsors));
    }

    // Calcul des pourcentages
    for (PieChart.Data data : pieChartData) {
        double pourcentage = (data.getPieValue() / totalSponsors) * 100;
        String label = data.getName() + " - " + String.format("%.2f", pourcentage) + "%";
        data.setName(label);
    }

    PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Statistiques des sponsors par type d'investissement");

    Stage stage = new Stage();
    Scene scene = new Scene(new Group(chart), 600, 400);
    stage.setScene(scene);
    stage.show();
        
    }

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

    private void gererParticipant(ActionEvent event) {
               try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("ParticipantBack.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }

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
private void triNomD(ActionEvent event) {
    SponsorCRUD sc = new SponsorCRUD();
    colnom.setCellValueFactory(new PropertyValueFactory<>("nom_Sponsor"));
    colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    colinvest.setCellValueFactory(new PropertyValueFactory<>("invest"));
    colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        ObservableList<Sponsor> sponsorList = sc.triNomDESC();
    tableSponsors.setItems(sponsorList);
}

    @FXML
    private void triNomA(ActionEvent event) {
        SponsorCRUD ss = new SponsorCRUD();
    colnom.setCellValueFactory(new PropertyValueFactory<>("nom_Sponsor"));
    colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    colinvest.setCellValueFactory(new PropertyValueFactory<>("invest"));
    colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        ObservableList<Sponsor> sponsorList = ss.triNomASC();
    tableSponsors.setItems(sponsorList);
    }

    @FXML
    private void searchSponsors(ActionEvent event) {
        
        String searchTerm = searchInput.getText();
    SponsorCRUD sponsorService = new SponsorCRUD();
        ObservableList<Sponsor> sponsorList = sponsorService.searchSponsors(searchTerm);
    tableSponsors.setItems(sponsorList);
    }

    }

