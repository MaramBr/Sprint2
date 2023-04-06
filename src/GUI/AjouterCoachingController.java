/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Coaching;
import Services.ServiceCoaching;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
    @FXML
    private Button Add;
    
  
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

    /**
     * Initializes the controller class.
     */
    
    
    
    
       public void updateTable() {
         idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        CoursC.setCellValueFactory(new PropertyValueFactory<>("cours"));
        DispoC.setCellValueFactory(new PropertyValueFactory<>("dispoCoach"));
        DescC.setCellValueFactory(new PropertyValueFactory<>("descCoach"));
        ImgC.setCellValueFactory(new PropertyValueFactory<>("imgCoach"));

        CoachingList = sc.afficher();
        CoachingTable.setItems(CoachingList);
        
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        CoursC.setCellValueFactory(new PropertyValueFactory<>("cours"));
        DispoC.setCellValueFactory(new PropertyValueFactory<>("dispoCoach"));
        DescC.setCellValueFactory(new PropertyValueFactory<>("descCoach"));
        ImgC.setCellValueFactory(new PropertyValueFactory<>("imgCoach"));

        CoachingList = sc.afficher();
        CoachingTable.setItems(CoachingList);
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        Coaching c = new Coaching();
        c.setCours(coursField.getText());
        c.setDescCoach(descField.getText());
        c.setDispoCoach(dispoField.getText());
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
    private void ModifierCoaching(ActionEvent event) {
        
         Coaching c = new Coaching();
        c.setId(Integer.parseInt(idField.getText()));
        c.setCours(coursField.getText());
        c.setDispoCoach(dispoField.getText());
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
        dispoField.setText(DispoC.getCellData(index));
        descField.setText(DescC.getCellData(index));
        imgField.setText(ImgC.getCellData(index));
    }

    @FXML
    private void SupprimerCoaching(ActionEvent event) {
           Coaching c = new Coaching();
        c.setId(Integer.parseInt(idField.getText()));
        c.setCours(coursField.getText());
        c.setDispoCoach(dispoField.getText());
        c.setDescCoach(descField.getText());
        c.setImgCoach(imgField.getText());

        sc.supprimer(c);
        updateTable();
        JOptionPane.showMessageDialog(null, "Seance supprimee");
    }
}