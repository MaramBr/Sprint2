/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Categorie;
import Entities.Produit;
import Services.ServiceCategorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Services.ServiceProduit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
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
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author 21692
 */
public class ProduitController implements Initializable {
    ServiceProduit sc=new ServiceProduit();
    List<Produit> Produit;
   int index=-1;

    private int idCategorieToadd ;
    @FXML
    private Button selectimageBtn;
    @FXML
    private ImageView imgView;
    @FXML
    private TextField Recherche;
    
      public int getIdCategorieToadd() {
        return idCategorieToadd;
    }

    public void setIdCategorieToadd(int idCategorieToadd) {
        this.idCategorieToadd = idCategorieToadd;
    }
   
   
   
   
   
   
  
    @FXML
    private Button Add;
    @FXML
    private TextField quantiteFieldP;
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
    private TableColumn<Produit, String> nomP;
    @FXML
    private TableColumn<Produit, String> descriptionP;
    @FXML
    private TableColumn<Produit, Integer> quantiteP;
    @FXML
    private TableColumn<Produit, Float> prixP;
    @FXML
    private TableColumn<Produit, String> imageP;
    @FXML
    private TableColumn<Produit, Integer> idP;
    @FXML
    private TableView<Produit> TableView;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TableColumn<Produit, String> CategoryP;
    @FXML
    private ComboBox<String> catPField;

    
    private Image image;
    /**
     * Initializes the controller class.
     */
    
    
     public void updateTable() {
           ObservableList<Produit> Produit = FXCollections.observableArrayList(sc.afficherProduit());

         idP.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionP.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantiteP.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
        imageP.setCellValueFactory(new PropertyValueFactory<>("image"));

        System.out.println("affichage" + sc.afficherProduit());
         TableView.setItems(Produit);
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
        
        
        // TODO
    }    

    public void show() {
        ObservableList<Produit> ProduitList = FXCollections.observableArrayList(sc.afficherProduit());
        System.out.println("affichage" + sc.afficherProduit());
        idP.setCellValueFactory(new PropertyValueFactory<>("id"));
        CategoryP.setCellValueFactory(new PropertyValueFactory<>("nomCategory"));

        nomP.setCellValueFactory(new PropertyValueFactory<>("nom"));

        descriptionP.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantiteP.setCellValueFactory(new PropertyValueFactory<>("quantite"));
          prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
        imageP.setCellValueFactory(new PropertyValueFactory<>("image"));

        TableView.setItems(ProduitList);
        chercherCoaching();
        //////////////////////////////////////////
         ServiceCategorie sc = new ServiceCategorie();
        List<String> nomsCataegory = new ArrayList<>();
        for (Categorie c : sc.afficherCategorie()) {
            nomsCataegory.add(c.getLibelle());

        }
        catPField.setItems(FXCollections.observableArrayList(nomsCataegory));

        catPField.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // get the selected Personne object based on the selected nom value
                Categorie selectedLibelle = null;
                for (Categorie c: sc.afficherCategorie()) {
                    if (c.getLibelle().equals(newValue)) {
                        selectedLibelle = c;
                        break;
                    }
                }
                if (selectedLibelle != null) {
                    int id = selectedLibelle.getId();
                    System.out.println("Selected categorie id: " + id);
                    // do something with the id...
                    setIdCategorieToadd(id);
                }
            }
        });}
    

    
   /* @FXML
    private void ajouterP(ActionEvent event) {
        Produit p = new Produit();
        p.setNom(nomFieldP.getText());
         p.setIdCategory(idCategorieToadd);
        p.setDescription(descriptionFieldP.getText());
        p.setQuantite(Integer.parseInt(quantiteFieldP.getText()));
        p.setPrix(Float.parseFloat(prixFieldP.getText()));
         p.setImage(imageFieldP.getText());
        sc.ajouterProduit(p);
        updateTable();
        JOptionPane.showMessageDialog(null, "produit Ajoutée");
    }
*/
    
    @FXML
    /*
private void ajouterP(ActionEvent event) {
Produit p = new Produit();
// Vérification des champs obligatoires
if (nomFieldP.getText().isEmpty() || descriptionFieldP.getText().isEmpty() || quantiteFieldP.getText().isEmpty() || prixFieldP.getText().isEmpty()) {
JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoires.");
return;
}
// Vérification que la quantité et le prix sont des nombres valides
try {
p.setQuantite(Integer.parseInt(quantiteFieldP.getText()));
p.setPrix(Float.parseFloat(prixFieldP.getText()));
} catch (NumberFormatException e) {
JOptionPane.showMessageDialog(null, "La quantité et le prix doivent être des nombres.");
return;
}
p.setNom(nomFieldP.getText());
p.setIdCategory(idCategorieToadd);
p.setDescription(descriptionFieldP.getText());
p.setImage(imageFieldP.getText());
sc.ajouterProduit(p);
updateTable();
JOptionPane.showMessageDialog(null, "Produit ajouté.");
}*/

    
    
    
    private void ajouterP(ActionEvent event) {
    Produit p = new Produit();
    // Vérification des champs obligatoires
    if (nomFieldP.getText().isEmpty() || descriptionFieldP.getText().isEmpty() || quantiteFieldP.getText().isEmpty() || prixFieldP.getText().isEmpty()) {
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
        p.setQuantite(Integer.parseInt(quantiteFieldP.getText()));
        if (p.getQuantite() < 0) {
            JOptionPane.showMessageDialog(null, "La quantité doit être positive.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "La quantité doit être un nombre.");
        return;
    }

    p.setNom(nom);
    p.setIdCategory(idCategorieToadd);
    p.setDescription(descriptionFieldP.getText());
    p.setImage(imageFieldP.getText());
    sc.ajouterProduit(p);
    updateTable();
    JOptionPane.showMessageDialog(null, "Produit ajouté.");
}

    
    
    
    
    
    @FXML
    private void getSelected(MouseEvent event) {
        
         index =TableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idFieldP.setText((idP.getCellData(index).toString()));
        nomFieldP.setText(nomP.getCellData(index));
        descriptionFieldP.setText(descriptionP.getCellData(index));
        quantiteFieldP.setText(quantiteP.getCellData(index).toString());
        prixFieldP.setText(prixP.getCellData(index).toString());
        imageFieldP.setText(imageP.getCellData(index));
        catPField.setValue(CategoryP.getCellData(index));

    
    }
    

    @FXML
    private void supprimerP(ActionEvent event) {
        

        Produit c = new Produit();
        c.setId(Integer.parseInt(idFieldP.getText()));

        c.setNom(nomFieldP.getText());
        c.setDescription((descriptionFieldP.getText()));
        c.setQuantite(Integer.parseInt(quantiteFieldP.getText()));
        c.setPrix(Float.parseFloat(prixFieldP.getText()));
        c.setImage(imageFieldP.getText());

        c.setNomCategory( catPField.getValue());

        
        sc.supprimerProduit(c);
       updateTable();
        JOptionPane.showMessageDialog(null, "produit supprimee");
    }
        
    

    @FXML
    private void modifierP(ActionEvent event) {
    {
        Produit p = new Produit();
        p.setId(Integer.parseInt(idFieldP.getText()));

        p.setNom(nomFieldP.getText());
         p.setIdCategory(idCategorieToadd);
        p.setDescription(descriptionFieldP.getText());
        p.setQuantite(Integer.parseInt(quantiteFieldP.getText()));
        p.setPrix(Float.parseFloat(prixFieldP.getText()));
         p.setImage(imageFieldP.getText());
         p.setNomCategory(catPField.getValue());
        // c.setIdCoaching(Integer.parseInt(coachingField.getText()));
      

        sc.modifierProduit(p);
        updateTable();
        JOptionPane.showMessageDialog(null, "produit modifiée");
    }
    
    
}

    @FXML
    private void selectimage(MouseEvent event) {
        String path ="";
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

    @FXML
    private void voirProduit(ActionEvent event) {
    }
    
    
        
     public void chercherCoaching() {
        FilteredList<Produit> filteredData = new FilteredList<>(FXCollections.observableArrayList(sc.afficherProduit()), b -> true);
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
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableView.comparatorProperty());
        TableView.setItems(sortedData);
    }

    @FXML
    private void stat(ActionEvent event) {
        generateStatistics();
        
    }

    public Map<String, Integer> getProduitCountByCategory(List<Produit> produits) {
    Map<String, Integer> countMap = new HashMap<>();

    for (Produit produit : produits) {
        String category = produit.getNomCategory();
        if (countMap.containsKey(category)) {
            countMap.put(category, countMap.get(category) + 1);
        } else {
            countMap.put(category, 1);
        }
    }

    return countMap;
}



public void generateStatistics() {
    // Retrieve the list of products
    ObservableList<Produit> produitList = FXCollections.observableArrayList(sc.afficherProduit());

    // Calculate the number of products by category
    Map<String, Integer> countMap = getProduitCountByCategory(produitList);

    // Create the data for the chart
    XYChart.Series<String, Number> countData = new XYChart.Series<>();
    for (String category : countMap.keySet()) {
        countData.getData().add(new XYChart.Data<>(category, countMap.get(category)));
    }

    // Create the chart
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
    chart.getData().add(countData);

    // Show the chart in a dialog box
    Alert chartAlert = new Alert(Alert.AlertType.INFORMATION);
    chartAlert.setTitle("Product Statistics");
    chartAlert.setHeaderText("Number of Products by Category");
    chartAlert.getDialogPane().setContent(chart);
    chartAlert.showAndWait();
}


}
