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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
                    System.out.println("Selected vehicule id: " + id);
                    // do something with the id...
                    setIdCategorieToadd(id);
                }
            }
        });}
    

    
    @FXML
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
        JOptionPane.showMessageDialog(null, "Seance Ajoutée");
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
        JOptionPane.showMessageDialog(null, "Seance supprimee");
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
}
