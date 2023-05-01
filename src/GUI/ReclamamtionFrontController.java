/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Genre;
import Entities.Reclamation;
import Services.ServiceGenre;
import Services.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

//import static GUIadd.SmsController.ACCOUNT_SID;
//import static GUIadd.SmsController.AUTH_TOKEN;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Majdi
 */
public class ReclamamtionFrontController implements Initializable {

    ServiceGenre sr = new ServiceGenre();
    ServiceReclamation sp = new ServiceReclamation();
    public static final String ACCOUNT_SID = "AC099883ea0f07c67cd19e55b497fceb12";
    public static final String AUTH_TOKEN = "0dc81da96bcea4d51424b9bbf5a80f07";
    int index = -1;

    private int idCoachingToadd;
    private Button admin_btn;
    @FXML
    private TableColumn<?, ?> idC;
    @FXML
    private TextField idField;
    @FXML
    private Button stati;
    @FXML
    private Button showallButton;

    public int getIdCoachingToadd() {
        return idCoachingToadd;
    }

    public void setIdCoachingToadd(int idCoachingToadd) {
        this.idCoachingToadd = idCoachingToadd;
    }

    @FXML
    private TextField TFTitre;
    @FXML
    private Button Add;
    @FXML
    private TableView<Reclamation> ReclamationTable;
    
    private TableColumn<Reclamation, Integer> genre;
    @FXML
    private TableColumn<Reclamation, String> Titre;
    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private TableColumn<Reclamation, String> Date;
    @FXML
    private TableColumn<Reclamation, String> Status;
    @FXML
    private Button ModifierC;

    @FXML
    private ComboBox<Genre> boxGenre;
    @FXML
    private TextField descTF;
    @FXML
    private TableColumn<Reclamation, String> idgenre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();

        //  remplirComboBoxGenres();
        ServiceGenre sc = new ServiceGenre();
        ObservableList<Genre> genres = FXCollections.observableArrayList(sc.afficherGenres());
        boxGenre.setItems(genres);
        boxGenre.setConverter(new StringConverter<Genre>() {
            @Override
            public String toString(Genre genre) {
                return genre.getLibelle();
            }

            @Override
            public Genre fromString(String string) {
                return null;
            }
        });
        boxGenre.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int id = newValue.getId();
                System.out.println("Selected libelle id: " + id);
                // do something with the id...
                setIdCoachingToadd(id);
            }
        });

    }

    @FXML
    private void ajouterRec(ActionEvent event) {

        // Créer un objet Reclamation avec les informations de la nouvelle réclamation
        Reclamation r = new Reclamation();
        r.setTitre(TFTitre.getText());
        r.setDescription(descTF.getText());
        if (r.getDescription().length() < 5) {
            // La description doit contenir au moins 5 caractères
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Description trop courte");
            alert.setHeaderText(null);
            alert.setContentText("La description doit contenir au moins 5 caractères.");
            alert.showAndWait();
            return;
        }
        if (TFTitre.getText().length() == 0 || descTF.getText().length() == 0) {
            // La description doit contenir au moins 5 caractères
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("champ vide");
            alert.setHeaderText(null);
            alert.setContentText(" remplir les champs.");
            alert.showAndWait();
            return;
        }

        // Récupérer le genre sélectionné à partir du ComboBox
        Genre g = boxGenre.getSelectionModel().getSelectedItem();
        if (g == null) {
            // Aucun genre sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun genre sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un genre.");
            alert.showAndWait();
            return;
        }
        r.setIdG(g.getId());

        // Ajouter la réclamation à la base de données
        sp.ajouter(r);
        // envoye d'un sms
        //  sendSms("+21692524435", "une réclamation a été ajoutée du titre " + r.getTitre() + ", veuillez le traiter svp");
        // Appeler la méthode de notification
        String message = "Une nouvelle réclamation a été ajoutée.";
        sp.notification(message);
        // Actualiser la table des réclamations
        updateTablereclamation();
        // Afficher un message de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Réclamation ajoutée avec succès.");
        alert.showAndWait();
    }

    public void updateTablereclamation() {
        ObservableList<Reclamation> reclamations = sp.afficher2();
        ObservableList<Reclamation> reclamations2 = sp.afficher2();

        // ajouter les réclamations en attente à la TableView
        FilteredList<Reclamation> reclamationsFiltrees = new FilteredList<>(reclamations, p -> p.getStatus().equals("en attente"));
        idgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        ReclamationTable.setItems(reclamationsFiltrees);

    }

    @FXML
    private void getSelected(MouseEvent event) {
        index = ReclamationTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        TFTitre.setText(Titre.getCellData(index));
        descTF.setText(Description.getCellData(index));

    }

    @FXML
    private void ModifierRec(ActionEvent event) {
        Reclamation selectedReclamation = ReclamationTable.getSelectionModel().getSelectedItem();
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
        selectedReclamation.setTitre(TFTitre.getText());
        selectedReclamation.setDescription(descTF.getText());
        //selectedReclamation.setGenre(boxGenre.getSelectionModel().getSelectedItem());
        if (descTF.getText().length() < 5) {
            // La description doit contenir au moins 5 caractères
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Description trop courte");
            alert.setHeaderText(null);
            alert.setContentText("La description doit contenir au moins 5 caractères.");
            alert.showAndWait();
            return;
        }
        if (TFTitre.getText().length() == 0 || descTF.getText().length() == 0) {
            // La description doit contenir au moins 5 caractères
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("champ vide");
            alert.setHeaderText(null);
            alert.setContentText(" remplir les champs.");
            alert.showAndWait();
            return;
        }

        // Enregistrement de la modification
        sp.modifier(selectedReclamation);
        // Appeler la méthode de notification
        String message = "Une réclamation a été modifié.";
        sp.notification(message);
        // refresh table 
        updateTablereclamation();
        JOptionPane.showMessageDialog(null, "Réclamation modifiée");

    }

    @FXML
    private void SupprimerRec(ActionEvent event) {
        Reclamation selectedReclamation = ReclamationTable.getSelectionModel().getSelectedItem();
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
            // Appeler la méthode de notification
            String message = "Une réclamation a été supprimée.";
            sp.notification(message);
            // refresh table
            updateTablereclamation();
            // Supprimer la réclamation de la table
            ReclamationTable.getItems().remove(selectedReclamation);

        }

    }

    private void show() {
        ObservableList<Reclamation> reclamations = sp.afficher2();

        // ajouter les réclamations à la TableView
        FilteredList<Reclamation> reclamationsFiltrees = new FilteredList<>(reclamations, p -> p.getStatus().equals("en attente"));
        idgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));

        ReclamationTable.setItems(reclamationsFiltrees);

    }



    @FXML
    private void showall(ActionEvent event) throws IOException {
        Parent newPage = FXMLLoader.load(getClass().getResource("ReclamationCard.fxml"));
        Scene scene = showallButton.getScene();
        scene.setRoot(newPage);
    }

    @FXML
    private void stat(ActionEvent event) {
        ObservableList<Reclamation> Reclamations = ReclamationTable.getItems();
        Map<String, Integer> statistiques = new HashMap<>();

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

}
