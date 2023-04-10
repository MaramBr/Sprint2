
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import gestionEvenement.entities.Evenement;
import gestionEvenement.entities.Sponsor;
import gestionEvenement.services.EvenementCRUD;
import gestionEvenement.services.SponsorCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author emna
 */
public class EvenementController implements Initializable {

   
    private EvenementCRUD ecrd = new EvenementCRUD();
    public ObservableList<Evenement> data = FXCollections.observableArrayList();
    public ObservableList<Sponsor> data1 = FXCollections.observableArrayList();
    
    ObservableList<Evenement> list;
  


    @FXML
    private Button btnajouterev;

    @FXML
    private TextField tfnomev;
    @FXML
    private TextField tflieu;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfimage;
    @FXML
    private TextField tfnbParticipant;
    @FXML
    private TextField tfprix;
    @FXML
    private DatePicker tfdatedebut;
    @FXML
    private DatePicker tfdatefin;
    @FXML
    private TableColumn<Evenement, String> callids;
    @FXML
    private TableColumn<Evenement, String> callnomev;
    @FXML
    private TableColumn<Evenement, String> calllieu;
    @FXML
    private TableColumn<Evenement, String> calltype;
    @FXML
    private TableColumn<Evenement, String> calldescription;
    @FXML
    private TableColumn<Evenement,String > calldatedebut;
    @FXML
    private TableColumn<Evenement,String > calldatefin;
    @FXML
    private TableColumn<Evenement, String> callimage;
    @FXML
    private TableColumn<Evenement, Integer> callnbparticipant;
    @FXML
    private TableColumn<Evenement, Integer> callprix;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnsoponsor;
    @FXML
    private Button btnev;
    @FXML
    private TableView<Evenement> tableEvenement;
    @FXML
    private TableColumn<?, ?> callidev;
    @FXML
    private Button browseimg;
    @FXML
    private ComboBox<String> cbsponsor;
    @FXML
    private Button btnrf;
    @FXML
    private TextField tfsp;
    @FXML
    private ImageView image1;

    /**
     * Initializes the controller class.
     */
@Override
public void initialize(URL url, ResourceBundle rb) {
    // Récupérer les sponsors de la base de données et les ajouter à la liste de noms de sponsors
    SponsorCRUD cd = new SponsorCRUD();
   List<Sponsor> SponsorData = cd.afficherSponsor();

    // create a list of conducteur names
    List<String> SponsorNames = new ArrayList<>();
    for (Sponsor Sponsor : SponsorData) {
        SponsorNames.add(Sponsor.getNom_Sponsor());
    }

    // Définir la liste des noms de sponsors comme éléments du ComboBox
    cbsponsor.setItems(FXCollections.observableArrayList(SponsorNames));

    // Ajouter un gestionnaire d'événements pour le ComboBox
    cbsponsor.setOnAction(event -> selectsponsor(event));

    File file = new File("C:/Users/emnaa/OneDrive/Documents/NetBeansProject/GestionEvenement/src/image/logoFit.png");
    String localURL = "";
    try {
        localURL = file.toURI().toURL().toString();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
 image1.setImage(new Image(localURL));
    browseimg.setOnAction(event -> {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        File fileImageV = fileChooser.showOpenDialog(browseimg.getScene().getWindow());
        if (fileImageV != null) {
            tfimage.setText(fileImageV.getPath());
        }
    });
    
    show();
}



    @FXML
private void ajouterev(ActionEvent event) {
    try {
        // Vérifier que tous les champs sont remplis
        if (cbsponsor.getValue() == null || tfnomev.getText().isEmpty() || tflieu.getText().isEmpty()
                || tftype.getText().isEmpty() || tfdescription.getText().isEmpty()
                || tfdatedebut.getValue() == null || tfdatefin.getValue() == null || tfimage.getText().isEmpty()
                || tfnbParticipant.getText().isEmpty() || tfprix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        // Récupérer sponsor
        String sponsor = cbsponsor.getValue();

        // Récupérer les valeurs des champs
        String nom = tfnomev.getText();
        String lieu = tflieu.getText();
        String type = tftype.getText();
        String description = tfdescription.getText();
     ////////Description ne depasse pas 200 caractere ////   
        if (tfdescription.getText().length() > 200) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Description trop longue");
    alert.setHeaderText(null);
    alert.setContentText("La description ne doit pas dépasser 200 caractères !");
    alert.showAndWait();
    return;
}


        // Récupérer la valeur sélectionnée dans le composant DatePicker
        LocalDate datedebut1 = tfdatedebut.getValue();
        LocalDate datefin1 = tfdatefin.getValue();

        // Vérifier que la date de début est avant la date de fin
        if (datefin1.isBefore(datedebut1)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Date invalide");
            alert.setHeaderText(null);
            alert.setContentText("La date de fin doit être après la date de début !");
            alert.showAndWait();
            return;
        }

        // Convertir les dates en chaînes
        String datedebut = datedebut1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String datefin = datefin1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Convertir les chaînes de date en valeurs de date MySQL
        String mysqlDateString = LocalDate.parse(datedebut, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        String mysqlDateString11 = LocalDate.parse(datefin, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        // Récupérer les autres champs
        String image = tfimage.getText();

        // Vérifier que les champs pour le nombre de participants et le prix sont des nombres entiers
        int nb_participant = 0;
        int prix = 0;

        try {
            nb_participant = Integer.parseInt(tfnbParticipant.getText());
            prix = Integer.parseInt(tfprix.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs invalides");
            alert.setHeaderText(null);
            alert.setContentText("Les champs pour le nombre de participants et le prix doivent être des nombres entiers !");
            alert.showAndWait();
            return;
        }

        // Vérifier que le nombre de participants et le prix sont supérieurs à zéro
        if (nb_participant <= 0 || prix <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs invalides");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre de participants et le prix doivent être supérieurs à zéro !");
            alert.showAndWait();
            return;
        }
          int sponsorId = ecrd.getIdSponsor(sponsor);
        ecrd.ajouterEvenement(new Evenement(sponsorId,nom,lieu,type,description,datedebut,datefin,image,nb_participant,prix));

        
       // Evenement s = new Evenement();
        //EvenementCRUD ecrd = new EvenementCRUD();
        //int id_c = ecrd.getIdSponsor(sponsor);
        
        

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("L evenement a été ajoutée avec succès");
        alert.showAndWait();

        // Effacer les zones de texte
        cbsponsor.setValue("");
    } catch (NumberFormatException ex) {
        // Afficher une alerte si la zone de texte idConducteur ne contient pas un entier
        
    }

     
        data.clear();
        show();
        // Rafraîchir la vue de la table
        tableEvenement.refresh();
        // Effacer les champs de saisie
        /*tftype.setValue(null);
    tfdate.setValue(null);
    tfdescription.setText("");*/
    // Appelle la fonction sendMail avec le champ de texte en paramètre
    //sendMail(emailField);

    }
   @FXML
    private void selectsponsor(ActionEvent event) {
    String selectedSponsor = cbsponsor.getValue();
     // do something with the selected conducteur
    System.out.println("Selected sponsor: " + selectedSponsor);
    
}
    
    

    
    @FXML 
private void modifierev(ActionEvent event) {
    
    
    // Vérifier si une ligne est sélectionnée dans la table
    if (tableEvenement.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune ligne sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne dans la table !");
        alert.showAndWait();
        return;
    }
    
   try {
       try {
        Evenement evenementSelectionne = tableEvenement.getSelectionModel().getSelectedItem();
        int idEv = evenementSelectionne.getId();
        
        // Vérifier que tous les champs sont remplis
        if (cbsponsor.getValue() == null || tfnomev.getText().isEmpty() || tflieu.getText().isEmpty()
                || tftype.getText().isEmpty() || tfdescription.getText().isEmpty()
                || tfdatedebut.getValue() == null || tfdatefin.getValue() == null || tfimage.getText().isEmpty()
                || tfnbParticipant.getText().isEmpty() || tfprix.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
        
        // Récupérer les valeurs des champs
        String nomSponsor = cbsponsor.getValue().toString(); // Récupérer le nom du sponsor sélectionné dans la combobox
        SponsorCRUD spcrd = new SponsorCRUD();
        int idsp = spcrd.getIdByNom(nomSponsor); // Récupérer l'ID du sponsor correspondant au nom sélectionné
        String nom= tfnomev.getText();
        String lieu = tflieu.getText();
        String type = tftype.getText();
        String description = tfdescription.getText();
        // Validation de la description
        if (description.length() > 200) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Description trop longue");
            alert.setHeaderText(null);
            alert.setContentText("La description ne doit pas dépasser 200 caractères !");
            alert.showAndWait();
            return;
        }
        
        // Récupérer la valeur sélectionnée dans le composant DatePicker
        LocalDate datedebut1 = tfdatedebut.getValue();
        LocalDate datefin1 = tfdatefin.getValue();
        
        // Vérifier que la date de début est avant la date de fin
        if (datefin1.isBefore(datedebut1)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Date invalide");
            alert.setHeaderText(null);
            alert.setContentText("La date de fin doit être après la date de début !");
            alert.showAndWait();
            return;
        }


        // Convertir la date en une chaîne au format 'JJ/MM/AAAA'
        String datedebut = datedebut1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String datefin= datefin1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Convertir la chaîne de date en une valeur de date MySQL au format 'AAAA-MM-JJ'
        String mysqlDateString = LocalDate.parse(datedebut, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        String mysqlDateString11 = LocalDate.parse(datefin, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        String image = tfimage.getText();
        
        // Vérifier que les champs pour le nombre de participants et le prix sont des nombres entiers
        int nb_participant = 0;
        int prix = 0;

        try {
            nb_participant = Integer.parseInt(tfnbParticipant.getText());
            prix = Integer.parseInt(tfprix.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs invalides");
            alert.setHeaderText(null);
            alert.setContentText("Les champs pour le nombre de participants et le prix doivent être des nombres entiers !");
            alert.showAndWait();
            return;
        }

        // Vérifier que le nombre de participants et le prix sont supérieurs à zéro
        if (nb_participant <= 0 || prix <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs invalides");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre de participants et le prix doivent être supérieurs à zéro !");
            alert.showAndWait();
            return;
        }
        
        // Mettre à jour l'événement dans la base de données
        EvenementCRUD ecrd = new EvenementCRUD();
        ecrd.modifierEvenement(new Evenement(idEv,idsp,nom,lieu,type,description,datedebut,datefin,image,nb_participant,prix));

        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("L'événement a été modifié avec succès");
        alert.showAndWait();

        // Effacer les zones de texte
        tfnomev.setText("");
        tflieu.setText("");
        tftype.setText("");
        tfdescription.setText("");
        tfdatedebut.setValue(null);
        tfdatefin.setValue(null);
        tfimage.setText("");
        tfnbParticipant.setText("");
        tfprix.setText("");
        
        // Rafraîchir la vue de la table
        show();
        tableEvenement.refresh();
    } catch (NumberFormatException ex) {
        // Afficher une alerte si la zone de texte idEv ne contient pas un entier
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("L'identifiant doit être un entier");
        alert.showAndWait();
    }
    } catch (Exception e) {
        e.printStackTrace(); // Afficher l'erreur dans la console
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur est survenue lors de la modification de l'événement");
        alert.setContentText(e.getMessage()); // Afficher le message d'erreur dans la boîte de dialogue
        alert.showAndWait();
    }
}


   @FXML
private void supprimerev(ActionEvent event) {
 // Vérifier si une evénement est sélectionnée
        if (tableEvenement.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner une evénement à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette evénement ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la evénement sélectionnée dans la vue de la table
            int id = tableEvenement.getSelectionModel().getSelectedItem().getId();

            // Supprimer la evénement de la base de données
            ecrd.supprimerEvenement(id);
// Rafraîchir la liste de données
            data.clear();
            show();
            // Rafraîchir la vue de la table
            tableEvenement.refresh();
        }
}


    @FXML
    private void gerersponsor(ActionEvent event) {
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
    private void gererevenement(ActionEvent event) {
          try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("ParticipantBack.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }
    
    
    

    

private void show() {
    
      EvenementCRUD cv = new EvenementCRUD();
    List<Evenement> evenements = cv.afficherEvenement();
    SponsorCRUD sv = new SponsorCRUD();
    if (evenements != null) {
        data = FXCollections.observableArrayList(evenements);
        callidev.setCellValueFactory(new PropertyValueFactory<>("id"));
        callnomev.setCellValueFactory(new PropertyValueFactory<>("nom"));
        calllieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        calltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        calldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        calldatedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        calldatefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        callimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        callnbparticipant.setCellValueFactory(new PropertyValueFactory<>("nb_participant"));
        callprix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        callids.setCellValueFactory(cellData -> {
            Integer sponsorsId = cellData.getValue().getSponsors_id();
            if (sponsorsId != null) {
                String sponsorName = sv.getSponsorNameById(sponsorsId);
                return new SimpleStringProperty(sponsorName);
            } else {
                return new SimpleStringProperty("");
            }
        });
        
        callids.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
callnomev.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
calllieu.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");

calltype.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
calldescription.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
calldatedebut.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
calldatefin.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
callimage.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
callnbparticipant.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
callprix.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");

        
        
          tableEvenement.getColumns().clear(); // Suppression des anciennes colonnes
        tableEvenement.getColumns().add(callids);
        tableEvenement.getColumns().add(callnomev);
        tableEvenement.getColumns().add(calllieu);
        tableEvenement.getColumns().add(calltype);
        tableEvenement.getColumns().add(calldescription);
        tableEvenement.getColumns().add(calldatedebut);
        tableEvenement.getColumns().add(calldatefin);
        tableEvenement.getColumns().add(callimage);
        tableEvenement.getColumns().add(callnbparticipant);
        tableEvenement.getColumns().add(callprix);
        

        tableEvenement.setItems(data);
    } else {
        // afficher un message d'erreur ou effectuer une autre action appropriée
    }
    
    }

    

@FXML
private void afficherEvenementSelectionne(MouseEvent event) {
    Evenement Evenement = tableEvenement.getSelectionModel().getSelectedItem();
    if (Evenement != null) {
        int sponsorId = Evenement.getSponsors_id();
        // Afficher le nom du sponsor dans la ComboBox
        String sponsorName = new SponsorCRUD().getSponsorNameById(sponsorId);
        cbsponsor.setValue(sponsorName);
        // Autres champs
        tfsp.setText(Integer.toString(sponsorId));
        tfnomev.setText(Evenement.getNom());
        tflieu.setText(Evenement.getLieu());
        tftype.setText(Evenement.getType());
        tfdescription.setText(Evenement.getDescription());
        tfdatedebut.setValue(LocalDate.parse(Evenement.getDate_debut()));
        tfdatefin.setValue(LocalDate.parse(Evenement.getDate_fin()));
        tfimage.setText(Evenement.getImage());
        int nbParticipant = Evenement.getNb_participant();
        tfnbParticipant.setText(Integer.toString(nbParticipant));
        int prix = Evenement.getPrix();
        tfprix.setText(Integer.toString(prix));
    } else {
        cbsponsor.setValue("");
        tfsp.setText("");
        tfnomev.setText("");
        tflieu.setText("");
        tftype.setText("");
        tfdescription.setText("");
        tfdatedebut.setValue(null);
        tfdatefin.setValue(null);
        tfimage.setText("");
        tfnbParticipant.setText("");
        tfprix.setText("");
    }
}


   



@FXML
private void rafraichir(ActionEvent event) {
    EvenementCRUD cv = new EvenementCRUD();
    List<Evenement> evenements = cv.afficherEvenement();
    SponsorCRUD sv = new SponsorCRUD();
    if (evenements != null) {
        data = FXCollections.observableArrayList(evenements);
        callidev.setCellValueFactory(new PropertyValueFactory<>("id"));
        callnomev.setCellValueFactory(new PropertyValueFactory<>("nom"));
        calllieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        calltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        calldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        calldatedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        calldatefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        callimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        callnbparticipant.setCellValueFactory(new PropertyValueFactory<>("nb_participant"));
        callprix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        callids.setCellValueFactory(cellData -> {
            Integer sponsorsId = cellData.getValue().getSponsors_id();
            if (sponsorsId != null) {
                String sponsorName = sv.getSponsorNameById(sponsorsId);
                return new SimpleStringProperty(sponsorName);
            } else {
                return new SimpleStringProperty("");
            }
        });
        callids.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
callnomev.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
calllieu.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");

calltype.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
calldescription.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
calldatedebut.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
calldatefin.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
callimage.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
callnbparticipant.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");
callprix.setStyle("-fx-border-color: orange; -fx-border-width: 1px; -fx-border-style: solid;");





        

        
        
          tableEvenement.getColumns().clear(); // Suppression des anciennes colonnes
        tableEvenement.getColumns().add(callids);
        tableEvenement.getColumns().add(callnomev);
        tableEvenement.getColumns().add(calllieu);
        tableEvenement.getColumns().add(calltype);
        tableEvenement.getColumns().add(calldescription);
        tableEvenement.getColumns().add(calldatedebut);
        tableEvenement.getColumns().add(calldatefin);
        tableEvenement.getColumns().add(callimage);
        tableEvenement.getColumns().add(callnbparticipant);
        tableEvenement.getColumns().add(callprix);
        

        tableEvenement.setItems(data);
    } else {
        // afficher un message d'erreur ou effectuer une autre action appropriée
    }
}
////////////////Date////////////////////////////////
private void trierDate(MouseEvent event) {
        TableColumn<Evenement, String> dateColumn = (TableColumn<Evenement, String>) event.getSource();
        tableEvenement.getSortOrder().clear(); // Supprimer tout tri précédent
        data.sort((r1, r2) -> r1.getDate_debut().compareTo(r2.getDate_debut()));
    }

}

   

