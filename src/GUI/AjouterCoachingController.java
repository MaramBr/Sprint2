/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Coaching;
import Entities.RendezVous;
import Services.ServiceCoaching;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Maram
 */
public class AjouterCoachingController implements Initializable {
  
    
    
    ServiceCoaching sc=new ServiceCoaching();
    ObservableList<Coaching> CoachingList;
   int index=-1;
     String path="";
    
    @FXML
    private TextField coursField;
    @FXML
    private TextField descField;
    @FXML
    private TextField dispoField;
    @FXML
    private Button imgField;
    
  
    @FXML
    private ImageView imgView;
    
      @FXML
    private TableView<Coaching> CoachingTable;

    @FXML
    private TableColumn<Coaching, Integer> idC;

    @FXML
    private TableColumn<Coaching, String> CoursC;

    @FXML
    private TableColumn<Coaching, String> DescC;

    @FXML
    private TableColumn<Coaching,String> DispoC;

    @FXML
    private TableColumn<Coaching, String> ImgC;
    @FXML
    private Button ModifierC;
    @FXML
    private TextField idField;
    @FXML
    private TextField chercherField;
    
     
    @FXML
    private CheckBox checkYoga;

    @FXML
    private CheckBox checkFitness;
    
     @FXML
    private CheckBox checkBoxing;
     
         @FXML
    private ComboBox<String> dispoCombo;
    @FXML
    private Button refreshB;
    private VBox mainPane;
    

    /**
     * Initializes the controller class.
     */
    
    
    
    
    @FXML
       public void updateTable() {
         idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        CoursC.setCellValueFactory(new PropertyValueFactory<>("cours"));
        DispoC.setCellValueFactory(new PropertyValueFactory<>("dispoCoach"));
        DescC.setCellValueFactory(new PropertyValueFactory<>("descCoach"));
        ImgC.setCellValueFactory(new PropertyValueFactory<>("imgCoach"));

        CoachingList = sc.afficher();
        CoachingTable.setItems(CoachingList);
        chercherCoaching();
        
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> typeList = FXCollections.observableArrayList("Weekend", "Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche");
        dispoCombo.setItems(typeList);
        
         idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        CoursC.setCellValueFactory(new PropertyValueFactory<>("cours"));
        DispoC.setCellValueFactory(new PropertyValueFactory<>("dispoCoach"));
        DescC.setCellValueFactory(new PropertyValueFactory<>("descCoach"));
        ImgC.setCellValueFactory(new PropertyValueFactory<>("imgCoach"));

        CoachingList = sc.afficher();
        CoachingTable.setItems(CoachingList);
        chercherCoaching();
    }    

    @FXML
    private void ajouter(javafx.scene.input.MouseEvent event) {
        
                Image photo=imgView.getImage();
        
                String cours = coursField.getText();
                String descCoach = descField.getText();
                String dispoCoach = dispoCombo.getValue();
                
                String imagePath = path.substring(path.lastIndexOf("/img/"));
                
                
  if(coursField.getText().trim().length() == 0) {
    JOptionPane.showMessageDialog(null, "Veuillez remplir le champ 'cours'");
    return;
}
 
      if(descField.getText().trim().length() == 0) {
    JOptionPane.showMessageDialog(null, "Veuillez remplir le champ 'description'");
    return;
}
         if(dispoCombo.getValue().trim().length() == 0) {
    JOptionPane.showMessageDialog(null, "Veuillez remplir le champ 'disponibilité'");
    return;
}
         if (imgView.getImage() == null) {
    JOptionPane.showMessageDialog(null, "Veuillez ajouter une image");
    return;
}
         //instanciation de entite coaching
   Coaching u = new Coaching( descCoach,cours, dispoCoach, imagePath);
    
    // Insert new user into the database
        sc.ajouter(u);
        updateTable();
      //  JOptionPane.showMessageDialog(null, "Seance Ajoutée");

        
        
        
    }
    
    
    
    

    @FXML
 private void choisirImage(ActionEvent event) {
    Coaching c = new Coaching();

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
    private void ModifierCoaching(javafx.scene.input.MouseEvent event) {
        
         if(coursField.getText().trim().length() == 0) {
    JOptionPane.showMessageDialog(null, "Veuillez remplir le champ 'cours'");
    return;
}
 
      if(descField.getText().trim().length() == 0) {
    JOptionPane.showMessageDialog(null, "Veuillez remplir le champ 'description'");
    return;
}
         if(dispoCombo.getValue().trim().length() == 0) {
    JOptionPane.showMessageDialog(null, "Veuillez remplir le champ 'disponibilité'");
    return;
}
        
         Coaching c = new Coaching();
        c.setId(Integer.parseInt(idField.getText()));
        c.setCours(coursField.getText());
        c.setDispoCoach(dispoCombo.getValue());
        c.setDescCoach(descField.getText());
        c.setImgCoach(imgField.getText());

        sc.modifier(c);
        updateTable();
      //  JOptionPane.showMessageDialog(null, "Seance modifiée");

    }
    

    
    
   


    
    public void chercherCoaching() {
    ObservableList<Coaching> coachingList = FXCollections.observableArrayList(sc.afficher());

    FilteredList<Coaching> filteredData = coachingList.filtered(b -> true);
    chercherField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(rec -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            return Stream.of(rec.getCours(), rec.getDispoCoach(), rec.getDescCoach())
                    .anyMatch(field -> field.toLowerCase().contains(lowerCaseFilter));
        });
    });

    SortedList<Coaching> sortedData = filteredData.sorted();
    sortedData.comparatorProperty().bind(CoachingTable.comparatorProperty());
    CoachingTable.setItems(sortedData);
}


     
@FXML
    private void filtreCoaching(ActionEvent event) {
        
    boolean yogaSelected = checkYoga.isSelected();
    boolean fitnessSelected = checkFitness.isSelected();
    boolean boxingSelected = checkBoxing.isSelected();

    // Créer une liste filtrée en fonction de la valeur des CheckBox
    List<Coaching> filteredList = CoachingList.stream()
        .filter(c -> {
            boolean isYoga = c.getCours().equals("Yoga");
            boolean isFitness = c.getCours().equals("Fitness");
            boolean isBoxing = c.getCours().equals("Boxing");
            return (isYoga && yogaSelected) || (isFitness && fitnessSelected) || (isBoxing && boxingSelected);
        })
        .collect(Collectors.toList());

    // Afficher la liste filtrée dans la tableView
    if (yogaSelected || fitnessSelected || boxingSelected){
            CoachingTable.setItems(FXCollections.observableArrayList(filteredList));

    }if (!yogaSelected && !fitnessSelected && !boxingSelected){
              
         idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        CoursC.setCellValueFactory(new PropertyValueFactory<>("cours"));
        DispoC.setCellValueFactory(new PropertyValueFactory<>("dispoCoach"));
        DescC.setCellValueFactory(new PropertyValueFactory<>("descCoach"));
        ImgC.setCellValueFactory(new PropertyValueFactory<>("imgCoach"));

        CoachingList = sc.afficher();
        CoachingTable.setItems(CoachingList);
        
    }
    }


 
    @FXML
    private void SupprimerC(javafx.scene.input.MouseEvent event) {
           Coaching c = new Coaching();
        c.setId(Integer.parseInt(idField.getText()));
        c.setCours(coursField.getText());
        c.setDispoCoach(dispoCombo.getValue());
        c.setDescCoach(descField.getText());
        c.setImgCoach(imgField.getText());

        sc.supprimer(c);
        updateTable();
        //JOptionPane.showMessageDialog(null, "Seance supprimee");
    }

 
    @FXML
    private void RefrechC(MouseEvent event) {
          coursField.setText("");
          descField.setText("");

          dispoCombo.setValue(null);
             imgView.setImage(null);

          

       
    }

    private void pagerdv(ActionEvent event) throws IOException {
        
        mainPane.getChildren().clear();
        Parent Content = FXMLLoader.load(getClass().getResource("RendezVous.fxml"));
        mainPane.getChildren().setAll(Content);
    }

    private void imageafficher(ActionEvent event) {
        
             Coaching selectedCoaching= CoachingTable.getSelectionModel().getSelectedItem();
    
    if (selectedCoaching!= null) {
      
 
         Image image = new Image(new File(selectedCoaching.getImgCoach()).toURI().toString());
            imgView.setImage(image);
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un utilisateur à modifier.");
        alert.showAndWait();
    }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        
   
        
         Coaching c=CoachingTable.getSelectionModel().getSelectedItem();
         index = CoachingTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idField.setText((idC.getCellData(index).toString()));
        coursField.setText(CoursC.getCellData(index));
        dispoCombo.setValue(DispoC.getCellData(index));
        descField.setText(DescC.getCellData(index));
            Image image = new Image(new File(c.getImgCoach()).toURI().toString());
            imgView.setImage(image);

   
    
    
          
    }

    @FXML
    private void ModifierCoaching(ActionEvent event) {
    }

    @FXML
    private void SupprimerCoaching(ActionEvent event) {
    }
    
    

   

    
   
    }


 
 
