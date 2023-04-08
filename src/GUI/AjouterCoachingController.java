/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Coaching;
import Entities.RendezVous;
import Services.ServiceCoaching;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    
    
    @FXML
    private TextField coursField;
    @FXML
    private TextField descField;
    @FXML
    private TextField dispoField;
    @FXML
    private Button imgField;
    
  
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
        c.setCours(coursField.getText());
        c.setDescCoach(descField.getText());
        c.setDispoCoach(dispoCombo.getValue());
        c.setImgCoach(imgField.getText());
        sc.ajouter(c);
        updateTable();
        JOptionPane.showMessageDialog(null, "Seance Ajoutée");

        
        
        
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
            // Charger l'image à partir du fichier sélectionné
            WritableImage image = null;
       

            if (image != null) {
                // Afficher l'image dans l'objet ImageView de votre interface utilisateur
                imgView.setImage(image);

                // Enregistrer l'image sélectionnée dans votre objet Coaching
              //  c.setImgCoach(image);
/*
BufferedImage bufferedimg =ImageIO.read(file);
WritableImage image = SwingFXUtils.toFXImage(bufferedimg,null);
        c.setImgCoach(imgField.getText());*/
}
        }}

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
        JOptionPane.showMessageDialog(null, "Seance modifiée");

    }
    

    @FXML
    private void getSelected(MouseEvent event) {
        
     
         index = CoachingTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idField.setText((idC.getCellData(index).toString()));
        coursField.setText(CoursC.getCellData(index));
        dispoCombo.setValue(DispoC.getCellData(index));
        descField.setText(DescC.getCellData(index));
        imgField.setText(ImgC.getCellData(index));
    }

    private void SupprimerCoaching(javafx.scene.input.MouseEvent event) {
           Coaching c = new Coaching();
        c.setId(Integer.parseInt(idField.getText()));
        c.setCours(coursField.getText());
        c.setDispoCoach(dispoCombo.getValue());
        c.setDescCoach(descField.getText());
        c.setImgCoach(imgField.getText());

        sc.supprimer(c);
        updateTable();
        JOptionPane.showMessageDialog(null, "Seance supprimee");
    }
    
    
     public void chercherCoaching() {
        FilteredList<Coaching> filteredData = new FilteredList<>(FXCollections.observableArrayList(sc.afficher()), b -> true);
        chercherField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rec -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (rec.getCours().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (rec.getDispoCoach().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (rec.getDescCoach().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<Coaching> sortedData = new SortedList<>(filteredData);
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
    private void ModifierCoaching(ActionEvent event) {
    }

    @FXML
    private void SupprimerCoaching(ActionEvent event) {
      
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
        JOptionPane.showMessageDialog(null, "Seance supprimee");
    }


 
 
}