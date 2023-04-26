/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Genre;
import Entities.Reclamation;
import static GUI.ReclamamtionFrontController.ACCOUNT_SID;
import static GUI.ReclamamtionFrontController.AUTH_TOKEN;
import Services.ServiceGenre;
import Services.ServiceReclamation;
import Utils.MyDB;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Majdi
 */
public class RelamationController implements Initializable {
ServiceGenre sr = new ServiceGenre();
   ServiceReclamation sp = new ServiceReclamation();
    public static final String ACCOUNT_SID = "AC099883ea0f07c67cd19e55b497fceb12";
    public static final String AUTH_TOKEN = "0dc81da96bcea4d51424b9bbf5a80f07";
    int index = -1;
    
    
    @FXML
    private TableView<Genre> TableView;
    @FXML
    private TableColumn<Genre, Integer> idG;
    @FXML
    private TableColumn<Genre, String> libellé;
    @FXML
    private TextField TFlibelle;
    @FXML
    private TextField tfrechercheg;
    @FXML
    private TableView<Reclamation> AdminViewRec;
    @FXML
    private TableColumn<Reclamation, Integer> idRec;
    @FXML
    private TableColumn<Reclamation, String> idGenre;
    @FXML
    private TableColumn<Reclamation, String> TitreRec;
    @FXML
    private TableColumn<Reclamation, String> DescR;
    @FXML
    private TableColumn<Reclamation, String> DateRec;
    @FXML
    private TableColumn<Reclamation, String> StatusRec;
    @FXML
    private TableColumn<Reclamation, String> TraitementRec;
    @FXML
    private TextField TFrechercheReca;
    @FXML
    private TextArea TfTraitement;
    @FXML
    private ComboBox<String> boxtraitement;
    @FXML
    private Button front_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList("en attente", "confirmé");
boxtraitement.setItems(options);

        // TODO
       showgenres();
          showreclamations();
    }    
 public void showgenres() {

    ObservableList<Genre> genresList = sr.afficher2();

    idG.setCellValueFactory(new PropertyValueFactory<>("id"));
    libellé.setCellValueFactory(new PropertyValueFactory<>("libelle"));

    TableView.setItems(genresList);
}
 public void showreclamations() {
       // récupérer la liste des réclamations
    ObservableList<Reclamation> reclamations = sp.afficher2();

    // ajouter les réclamations à la TableView
    idRec.setCellValueFactory(new PropertyValueFactory<>("idRec"));
    idGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    TitreRec.setCellValueFactory(new PropertyValueFactory<>("titre"));
    DescR.setCellValueFactory(new PropertyValueFactory<>("description"));
    DateRec.setCellValueFactory(new PropertyValueFactory<>("date"));
    StatusRec.setCellValueFactory(new PropertyValueFactory<>("status"));
    TraitementRec.setCellValueFactory(new PropertyValueFactory<>("traitement"));

    AdminViewRec.setItems(reclamations);
    }
 public void updateTablegenre() {
        ObservableList<Genre> genresList = sr.afficher2();

    idG.setCellValueFactory(new PropertyValueFactory<>("id"));
    libellé.setCellValueFactory(new PropertyValueFactory<>("libelle"));

    TableView.setItems(genresList);
    }
 
@FXML
public void recherche() {
    // Ajouter un listener sur le champ de recherche pour effectuer la recherche à chaque modification du texte
    TFrechercheReca.textProperty().addListener((observable, oldValue, newValue) -> {
        // Filtrer les réclamations en utilisant le nouveau texte de recherche
       List<Reclamation> reclamationrecherche = sp.afficher2().stream()
        .filter(reclamation -> 
            reclamation.getTitre().toLowerCase().contains(newValue.toLowerCase()) ||
            reclamation.getStatus().toLowerCase().contains(newValue.toLowerCase()) ||
            reclamation.getDate().toLowerCase().contains(newValue.toLowerCase()) ||
            reclamation.getDescription().toLowerCase().contains(newValue.toLowerCase())
        )
        .collect(Collectors.toList());

        // Mettre à jour la TableView avec les réclamations filtrées
        AdminViewRec.setItems(FXCollections.observableArrayList(reclamationrecherche));
    });
}
@FXML
public void recherche2() {
    // Ajouter un listener sur le champ de recherche pour effectuer la recherche à chaque modification du texte
    tfrechercheg.textProperty().addListener((observable, oldValue, newValue) -> {
        // Filtrer les réclamations en utilisant le nouveau texte de recherche
       List<Genre> genrerecherche = sr.afficher2().stream()
        .filter(genre -> 
            genre.getLibelle().toLowerCase().contains(newValue.toLowerCase()) 
        )
        .collect(Collectors.toList());

        // Mettre à jour la TableView avec les réclamations filtrées
        TableView.setItems(FXCollections.observableArrayList(genrerecherche));
    });
}
  public void updateTablereclamation() {
        ObservableList<Reclamation> reclamations = sp.afficher2();

    // ajouter les réclamations à la TableView
    idRec.setCellValueFactory(new PropertyValueFactory<>("idRec"));
    idGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    TitreRec.setCellValueFactory(new PropertyValueFactory<>("titre"));
    DescR.setCellValueFactory(new PropertyValueFactory<>("description"));
    DateRec.setCellValueFactory(new PropertyValueFactory<>("date"));
    StatusRec.setCellValueFactory(new PropertyValueFactory<>("status"));
    TraitementRec.setCellValueFactory(new PropertyValueFactory<>("traitement"));

    AdminViewRec.setItems(reclamations);
    }
    @FXML
    private void ModifGenre(ActionEvent event) {
        Genre selectedGenre = TableView.getSelectionModel().getSelectedItem();
    if (selectedGenre == null) {
        // Aucune réclamation sélectionnée
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune réclamation sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une genre dans la table.");
        alert.showAndWait();
        return;
    }

    // Récupération des données de la réclamation à modifier
    
    if (TFlibelle.getText().matches(".*\\d.*")) { //equals string
        JOptionPane.showMessageDialog(null, "Le libellé ne doit pas contenir de chiffres !");
    } else {
        selectedGenre.setLibelle(TFlibelle.getText());
        sr.modifier(selectedGenre);
    updateTablegenre();
    JOptionPane.showMessageDialog(null, "genre modifiée");
    }

    // Enregistrement de la modification
    
        
    }

  @FXML
private void AjoutGenre(ActionEvent event) {
    Genre c = new Genre();
    String libelle = TFlibelle.getText();
    if (libelle.matches(".*\\d.*")) { //equals string
        JOptionPane.showMessageDialog(null, "Le libellé ne doit pas contenir de chiffres !");
    } else {
        c.setLibelle(libelle);
        sr.ajouter(c);
        updateTablegenre();
        JOptionPane.showMessageDialog(null, "Genre ajouté avec succès");
    }
}

    @FXML
    private void SupprimerGenre(ActionEvent event) {
     Genre selectedGenre = TableView.getSelectionModel().getSelectedItem();
    if (selectedGenre == null) {
        // Aucune réclamation sélectionnée
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucun genre sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une genre dans la table.");
        alert.showAndWait();
        return;
    }

    // Confirmation de la suppression
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de la suppression");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer genre sélectionnée ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Supprimer la réclamation de la base de données
        sr.supprimer(selectedGenre);

        // Supprimer la réclamation de la table
       // AdminViewRec.getItems().remove(selectedGenre);
       updateTablegenre();
    
}
        
        
    }

    @FXML
    private void getSelected2(MouseEvent event) {
        
     index = TableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        
        TFlibelle.setText(libellé.getCellData(index));

        
    }   
    

    @FXML
    private void modifierReca(ActionEvent event) {
       Reclamation selectedReclamation = AdminViewRec.getSelectionModel().getSelectedItem();
    if (selectedReclamation == null) {
        // Aucune réclamation sélectionnée
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune réclamation sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une réclamation dans la table.");
        alert.showAndWait();
        return;
    }

    // Récupération des données de la réclamation à modifier
    selectedReclamation.setTraitement(TfTraitement.getText());
    selectedReclamation.setStatus(boxtraitement.getSelectionModel().getSelectedItem());
    

    // Enregistrement de la modification
    sp.modifier(selectedReclamation);
   sendSms("+21692524435", "Votre réclamation est  confirmée , consultez notre site ou notre application pour consultez le traitement");
    updateTablereclamation();
    JOptionPane.showMessageDialog(null, "Réclamation modifiée");
        
    }

    @FXML
    private void SupprimerReca(ActionEvent event) {
    Reclamation selectedReclamation = AdminViewRec.getSelectionModel().getSelectedItem();
    if (selectedReclamation == null) {
        // Aucune réclamation sélectionnée
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune réclamation sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une réclamation dans la table.");
        alert.showAndWait();
        return;
    }

    // Confirmation de la suppression
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de la suppression");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer la réclamation sélectionnée ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Supprimer la réclamation de la base de données
        sp.supprimer(selectedReclamation);
 updateTablereclamation();
        // Supprimer la réclamation de la table
        AdminViewRec.getItems().remove(selectedReclamation);
    
}

    }

    @FXML
    private void getSelectedrec(MouseEvent event) {
        index = AdminViewRec.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        
        TfTraitement.setText(TraitementRec.getCellData(index));
        boxtraitement.setValue(StatusRec.getCellData(index));
        
    }
    
    
    @FXML
    public void rechercheGenre(ActionEvent event){
     /*   List<Genre> genres = sr.rechercher(tfrechercheg.getText());
        ObservableList<Genre> olp = FXCollections.observableArrayList(genres);
        idG.setCellValueFactory(new PropertyValueFactory<>("id"));
        libellé.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        TableView.setItems(olp);*/
}
    
    
    
        /*
        List<Reclamation> Reclamations = sp.rechercher(TFrechercheReca.getText());
        ObservableList<Reclamation> olp = FXCollections.observableArrayList(Reclamations);
         idRec.setCellValueFactory(new PropertyValueFactory<>("idRec"));
    idGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
    TitreRec.setCellValueFactory(new PropertyValueFactory<>("titre"));
    DescR.setCellValueFactory(new PropertyValueFactory<>("description"));
    DateRec.setCellValueFactory(new PropertyValueFactory<>("date"));
    StatusRec.setCellValueFactory(new PropertyValueFactory<>("status"));
    TraitementRec.setCellValueFactory(new PropertyValueFactory<>("traitement"));

    AdminViewRec.setItems(olp);
*/


    @FXML
    private void Front(ActionEvent event) throws IOException {
            Parent newPage = FXMLLoader.load(getClass().getResource("ReclamamtionFront.fxml"));
        Scene scene = front_btn.getScene();
        scene.setRoot(newPage);

    }

    @FXML
    private void statisques(ActionEvent event) {
        ObservableList<Reclamation> Reclamations = AdminViewRec.getItems();
    Map <String, Integer> statistiques = new HashMap<>();

    // Calcul des statistiques
    for (Reclamation r : Reclamations) {
        String type = r.getGenre();
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
        int nbreclamations = entry.getValue();
        totalSponsors += nbreclamations;
        pieChartData.add(new PieChart.Data(type + " (" + nbreclamations + ")", nbreclamations));
    }

    // Calcul des pourcentages
    for (PieChart.Data data : pieChartData) {
        double pourcentage = (data.getPieValue() / totalSponsors) * 100;
        String label = data.getName() + " - " + String.format("%.2f", pourcentage) + "%";
        data.setName(label);
    }

    PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Statistiques des réclamations par leurs catégories");

    Stage stage = new Stage();
    Scene scene = new Scene(new Group(chart), 600, 400);
    stage.setScene(scene);
    stage.show();
    }
    
     public static void sendSms(String recipient, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(recipient), // To number
                new PhoneNumber("+12766336884"), // From number
                messageBody) // SMS body
                .create();

        System.out.println("Message sent: " + message.getSid());
    }

}
