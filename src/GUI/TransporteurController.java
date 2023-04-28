/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Companie;
import Entities.Transporteur;
import Services.ServiceCompanie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Services.ServiceTransporteur;
import static Services.sms.sendsms;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CompanieAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;












    
    



/**
 * FXML Controller class
 *
 * @author 21692
 */
public class TransporteurController implements Initializable {
    ServiceTransporteur sc=new ServiceTransporteur();
    List<Transporteur> Transporteur;
   int index=-1;
    String path ="";

    private int Companies_idToadd ;
    @FXML
    private Button selectimageBtn;
    @FXML
    private ImageView imgView;
    @FXML
    private TextField Recherche;
    @FXML
    private ImageView iheb;
    
      public int getCompanies_idToadd() {
        return Companies_idToadd;
    }

    public void setCompanies_idToadd(int Companies_idToadd) {
        this.Companies_idToadd = Companies_idToadd;
    }
   
   
   
   
   
   
  
    @FXML
    private Button Add;
    @FXML
    private TextField numeroFieldP;
    @FXML
    private TextField prixFieldP;
    @FXML
    private TextField imageFieldP;
    @FXML
    private TextField descriptionFieldP;
    @FXML
    private TextField nomFieldP;
    @FXML
    private TextField idFieldP;
    @FXML
    private TableColumn<Transporteur, String> nomP;
    @FXML
    private TableColumn<Transporteur, String> descriptionP;
    @FXML
    private TableColumn<Transporteur, Integer> numeroP;
    @FXML
    private TableColumn<Transporteur, Float> prixP;
    @FXML
    private TableColumn<Transporteur, String> imageP;
    @FXML
    private TableColumn<Transporteur, Integer> idP;
    @FXML
    private TableView<Transporteur> TableView;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TableColumn<Transporteur, String> CompanieP;
    @FXML
    private ComboBox<String> catPField;
    
    @FXML
    private CheckBox checkAcc;

    @FXML
    private CheckBox checkApp;

    @FXML
    private CheckBox checkVet;

    
    private Image image;
    /**
     * Initializes the controller class.
     */
    
    
     public void updateTable() {
           ObservableList<Transporteur> Transporteur = FXCollections.observableArrayList(sc.afficherTransporteur());

         idP.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionP.setCellValueFactory(new PropertyValueFactory<>("description"));
        numeroP.setCellValueFactory(new PropertyValueFactory<>("numero"));
        prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
        imageP.setCellValueFactory(new PropertyValueFactory<>("image"));

        System.out.println("affichage" + sc.afficherTransporteur());
         TableView.setItems(Transporteur);
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
        
        
        // TODO
    }    

    public void show() {
        ObservableList<Transporteur> TransporteurList = FXCollections.observableArrayList(sc.afficherTransporteur());
        System.out.println("affichage" + sc.afficherTransporteur());
        idP.setCellValueFactory(new PropertyValueFactory<>("id"));
        CompanieP.setCellValueFactory(new PropertyValueFactory<>("nomCompanie"));

        nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));

        descriptionP.setCellValueFactory(new PropertyValueFactory<>("description"));
        numeroP.setCellValueFactory(new PropertyValueFactory<>("numero"));
          prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
        imageP.setCellValueFactory(new PropertyValueFactory<>("image"));

        TableView.setItems(TransporteurList);
        chercherTransporteur();
        //////////////////////////////////////////
         ServiceCompanie sc = new ServiceCompanie();
        List<String> nomsCataegory = new ArrayList<>();
        for (Companie c : sc.afficherCompanie()) {
            nomsCataegory.add(c.getLibelle());

        }
        catPField.setItems(FXCollections.observableArrayList(nomsCataegory));

        catPField.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // get the selected Personne object based on the selected nom value
                Companie selectedLibelle = null;
                for (Companie c: sc.afficherCompanie()) {
                    if (c.getLibelle().equals(newValue)) {
                        selectedLibelle = c;
                        break;
                    }
                }
                if (selectedLibelle != null) {
                    int id = selectedLibelle.getId();
                    System.out.println("Selected companie id: " + id);
                    // do something with the id...
                    setCompanies_idToadd(id);
                }
            }
        });}
    

    
   /* @FXML
    private void ajouterP(ActionEvent event) {
        Transporteur p = new Transporteur();
        p.setNom(nomFieldP.getText());
         p.setCompanies_id(Companies_idToadd);
        p.setDescription(descriptionFieldP.getText());
        p.setNumero(Integer.parseInt(numeroFieldP.getText()));
        p.setPrix(Float.parseFloat(prixFieldP.getText()));
         p.setImage(imageFieldP.getText());
        sc.ajouterTransporteur(p);
        updateTable();
        JOptionPane.showMessageDialog(null, "transporteur Ajoutée");
    }
*/
    
    @FXML
    /*
private void ajouterP(ActionEvent event) {
Transporteur p = new Transporteur();
// Vérification des champs obligatoires
if (nomFieldP.getText().isEmpty() || descriptionFieldP.getText().isEmpty() || numeroFieldP.getText().isEmpty() || prixFieldP.getText().isEmpty()) {
JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoires.");
return;
}
// Vérification que la quantité et le prix sont des nombres valides
try {
p.setNumero(Integer.parseInt(numeroFieldP.getText()));
p.setPrix(Float.parseFloat(prixFieldP.getText()));
} catch (NumberFormatException e) {
JOptionPane.showMessageDialog(null, "La quantité et le prix doivent être des nombres.");
return;
}
p.setNom(nomFieldP.getText());
p.setCompanies_id(Companies_idToadd);
p.setDescription(descriptionFieldP.getText());
p.setImage(imageFieldP.getText());
sc.ajouterTransporteur(p);
updateTable();
JOptionPane.showMessageDialog(null, "Transporteur ajouté.");
}*/

    
    
    /*
    private void ajouterP(ActionEvent event) {
    Transporteur p = new Transporteur();
    // Vérification des champs obligatoires
    if (nomFieldP.getText().isEmpty() || descriptionFieldP.getText().isEmpty() || numeroFieldP.getText().isEmpty() || prixFieldP.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoires.");
        return;
    }
    
    // Vérification que le nom ne contient pas de chiffres et fait au moins 3 caractères
    String nom = nomFieldP.getText();
    if (!nom.matches("[a-zA-Z\\s]+") || nom.length() < 3) {
        JOptionPane.showMessageDialog(null, "Le nom doit contenir au moins 3 lettres et ne doit pas contenir de chiffres.");
        return;
    }

    // Vérification que le prix est un nombre positif
    try {
        float prix = Float.parseFloat(prixFieldP.getText());
        if (prix < 0) {
            JOptionPane.showMessageDialog(null, "Le prix doit être positif.");
            return;
        }
        p.setPrix(prix);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Le prix doit être un nombre.");
        return;
    }

    // Vérification que la quantité est un nombre positif
    try {
        p.setNumero(Integer.parseInt(numeroFieldP.getText()));
        if (p.getNumero() < 0) {
            JOptionPane.showMessageDialog(null, "La quantité doit être positive.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "La quantité doit être un nombre.");
        return;
    }

    p.setNom(nom);
    p.setCompanies_id(Companies_idToadd);
    p.setDescription(descriptionFieldP.getText());
    p.setImage(imageFieldP.getText());
    sc.ajouterTransporteur(p);
    updateTable();
    JOptionPane.showMessageDialog(null, "Transporteur ajouté.");
}*/
    
    
    
    
    
    private void ajouterP(ActionEvent event) throws IOException {
        
    Image photo = imgView.getImage();
    String nomTransporteur = nomFieldP.getText();
    String descTransporteur = descriptionFieldP.getText();
    String numString = numeroFieldP.getText();
    String prixString = prixFieldP.getText();
    
    // Vérification de la saisie du nom du transporteur
    if (nomTransporteur.isEmpty()) {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Contrôle de saisie");
        al.setHeaderText("Erreur de saisie !");
        al.setContentText("Veuillez saisir un nom de transporteur.");
        al.show();
        return;
    }
    
    // Vérification de la saisie de la description du transporteur
    if (descTransporteur.isEmpty()) {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Contrôle de saisie");
        al.setHeaderText("Erreur de saisie !");
        al.setContentText("Veuillez saisir une description de vehicule valide.");
        al.show();
        return;
    }
    
    // Vérification de la saisie du numéro du transporteur
    if (!numString.matches("\\d{8}")) {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Contrôle de saisie");
        al.setHeaderText("Erreur de saisie !");
        al.setContentText("Le numéro doit contenir exactement 8 chiffres.");
        al.show();
        return;
    }
    
    // Vérification de la saisie du prix du transporteur
    try {
        Float.parseFloat(prixString);
    } catch (NumberFormatException e) {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Contrôle de saisie");
        al.setHeaderText("Erreur de saisie !");
        al.setContentText("Le prix doit être un nombre.");
        al.show();
        return;
    }
    
    Float prixTransporteur = Float.parseFloat(prixString);
    int numero = Integer.parseInt(numString);
    
    String imagePath = path.substring(path.lastIndexOf("/img/"));
    Transporteur u = new Transporteur(nomTransporteur, descTransporteur, numero, prixTransporteur, imagePath);

    // Toutes les saisies sont valides, création et ajout du transporteur
    Transporteur c = new Transporteur();
    c.setNom(nomTransporteur);
    c.setDescription(descTransporteur);
    c.setPrix(prixTransporteur);
    c.setImage(imagePath);
    c.setCompanies_id(Companies_idToadd);

    sc.ajouterTransporteur(c);
    QRcode(c);
    sendsms();
    updateTable();
    JOptionPane.showMessageDialog(null, "Transporteur ajouté.");
}

    
    
    
    
    
    
    
    
    @FXML
    private void getSelected(MouseEvent event) {
        
        Transporteur c=TableView.getSelectionModel().getSelectedItem();
        
         index =TableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idFieldP.setText((idP.getCellData(index).toString()));
        nomFieldP.setText(nomP.getCellData(index));
        descriptionFieldP.setText(descriptionP.getCellData(index));
        numeroFieldP.setText(numeroP.getCellData(index).toString());
        prixFieldP.setText(prixP.getCellData(index).toString());
        catPField.setValue(CompanieP.getCellData(index));
        Image image = new Image(new File(c.getImage()).toURI().toString());
            imgView.setImage(image);

    
    }
    

    @FXML
    private void supprimerP(ActionEvent event) {
        

        Transporteur c = new Transporteur();
        c.setId(Integer.parseInt(idFieldP.getText()));

        c.setNom(nomFieldP.getText());
        c.setDescription((descriptionFieldP.getText()));
        c.setNumero(Integer.parseInt(numeroFieldP.getText()));
        c.setPrix(Float.parseFloat(prixFieldP.getText()));
        c.setImage(imageFieldP.getText());

        c.setNomCompanie( catPField.getValue());

        
        sc.supprimerTransporteur(c);
       updateTable();
        JOptionPane.showMessageDialog(null, "transporteur supprimee");
    }
        
    

    @FXML
    private void modifierP(ActionEvent event) {
    {
        Transporteur p = new Transporteur();
        p.setId(Integer.parseInt(idFieldP.getText()));

        p.setNom(nomFieldP.getText());
         p.setCompanies_id(Companies_idToadd);
        p.setDescription(descriptionFieldP.getText());
        p.setNumero(Integer.parseInt(numeroFieldP.getText()));
        p.setPrix(Float.parseFloat(prixFieldP.getText()));
         p.setImage(imageFieldP.getText());
         p.setNomCompanie(catPField.getValue());
        // c.setIdCoaching(Integer.parseInt(coachingField.getText()));
      

        sc.modifierTransporteur(p);
        updateTable();
        JOptionPane.showMessageDialog(null, "transporteur modifiée");
    }
    
    
}

    @FXML
    private void selectimage(MouseEvent event) {
       
 FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Image File");

    // Ajouter un filtre pour n'afficher que les fichiers d'images
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );

    // Afficher la boîte de dialogue de sélection de fichiers
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
    // load the selected image into the image view
    path=selectedFile.getAbsolutePath().replace("\\", "/");

    Image image = new Image(selectedFile.toURI().toString());
    imgView.setImage(image);
    
    
    }
        
    }

    
    
        
     public void chercherTransporteur() {
        FilteredList<Transporteur> filteredData = new FilteredList<>(FXCollections.observableArrayList(sc.afficherTransporteur()), b -> true);
        Recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rec -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (rec.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (rec.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }  else {
                    return false;
                }

            });
        });
        SortedList<Transporteur> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableView.comparatorProperty());
        TableView.setItems(sortedData);
    }

  @FXML
    private void stat(ActionEvent event) {

            ObservableList<Transporteur> rdv = TableView.getItems();
    Map<String, Integer> statistiques = new HashMap<>();

    // Calcul des statistiques
    for (Transporteur r : rdv) {
        String cours = r.getNomCompanie();
        if (statistiques.containsKey(cours)) {
            statistiques.put(cours, statistiques.get(cours) + 1);
        } else {
            statistiques.put(cours, 1);
        }
    }

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    int totalrdv = 0;
    for (Map.Entry<String, Integer> entry : statistiques.entrySet()) {
        String type = entry.getKey();
        int nbrdv = entry.getValue();
        totalrdv += nbrdv;
        pieChartData.add(new PieChart.Data(type + " (" + nbrdv + ")", nbrdv));
    }

    // Calcul des pourcentages
    for (PieChart.Data data : pieChartData) {
        double pourcentage = (data.getPieValue() / totalrdv) * 100;
        String label = data.getName() + " - " + String.format("%.2f", pourcentage) + "%";
        data.setName(label);
    }

    PieChart chart = new PieChart(pieChartData);
    chart.setTitle("Statistiques de nombre des rendez_vous par cours");

    Stage stage = new Stage();
    Scene scene = new Scene(new Group(chart), 600, 400);
    stage.setScene(scene);
    stage.show();        
    }

    public Map<String, Integer> getTransporteurCountByCompanie(List<Transporteur> transporteurs) {
    Map<String, Integer> countMap = new HashMap<>();

    for (Transporteur transporteur : transporteurs) {
        String companie = transporteur.getNomCompanie();
        if (countMap.containsKey(companie)) {
            countMap.put(companie, countMap.get(companie) + 1);
        } else {
            countMap.put(companie, 1);
        }
    }

    return countMap;
}











@FXML
    private void filtre(ActionEvent event) {
            ObservableList<Transporteur> transporteurList = FXCollections.observableArrayList(sc.afficherTransporteur());

    
        
           boolean accSelected = checkAcc.isSelected();
    boolean appSelected = checkApp.isSelected();
    boolean vetSelected = checkVet.isSelected();

    // Créer une liste filtrée en fonction de la valeur des CheckBox
    List<Transporteur> filteredList = transporteurList.stream()
        .filter(p -> {
            boolean isAcc = p.getNomCompanie().equals("GLOVO");
            boolean isApp = p.getNomCompanie().equals("ESPRIT");
            boolean isVet = p.getNomCompanie().equals("SPEEDO");
            return (isAcc && accSelected) || (isApp && appSelected) || (isVet && vetSelected);
        })
        .collect(Collectors.toList());

    // Afficher la liste filtrée dans la tableView
    if (accSelected || appSelected || vetSelected){
            TableView.setItems(FXCollections.observableArrayList(filteredList));

    }if (!accSelected && !appSelected && !vetSelected){
              
         idP.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionP.setCellValueFactory(new PropertyValueFactory<>("description"));
        numeroP.setCellValueFactory(new PropertyValueFactory<>("numero"));
        prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
        imageP.setCellValueFactory(new PropertyValueFactory<>("image"));

        System.out.println("affichage" + sc.afficherTransporteur());
         TableView.setItems(transporteurList);
        
    }


    }
    
    
    
                public static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    private void QRcode(Transporteur u) throws FileNotFoundException, IOException {
        String contenue = "Nom : " + u.getNom()+"Prenom :"+u.getNumero()+ "\n" + "Email: " + u.getDescription()+ "\n" + "Role: " + u.getNomCompanie()
                ; 
        ByteArrayOutputStream out = QRCode.from(contenue).to(net.glxn.qrgen.image.ImageType.JPG).stream();
        File f = new File(projectPath + "\\src\\images\\" + u.getId()+ ".jpg");
        System.out.println(f.getPath());
        FileOutputStream fos = new FileOutputStream(f); //creation du fichier de sortie
        fos.write(out.toByteArray()); //ecrire le fichier du sortie converter
        fos.flush(); // creation final
        Image image = new Image(f.toURI().toString());
        iheb.setImage(image);


     }

}
