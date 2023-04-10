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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author 21692
 */
public class CategorieController implements Initializable {
    ServiceCategorie sc=new ServiceCategorie();
    List<Categorie> Categorie;
   int index=-1;

    @FXML
    private TextField idFieldC;
    @FXML
    private TextField libelleFieldC;
    @FXML
    private Button Add;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<Categorie> TableViewC;
    @FXML
    private TableColumn<Categorie, String> libelleC;
    @FXML
    private TableColumn<Categorie,Integer> idC;

    /**
     * Initializes the controller class.
     */
     public void updateTable() {
           ObservableList<Categorie> Categorie = FXCollections.observableArrayList(sc.afficherCategorie());

         idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelleC.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        System.out.println("affichage" + sc.afficherCategorie());
         TableViewC.setItems(Categorie);
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           show();
        // TODO
    }    

    public void show() {
        ObservableList<Categorie> CategorieList = FXCollections.observableArrayList(sc.afficherCategorie());
        System.out.println("affichage" + sc.afficherCategorie());
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelleC.setCellValueFactory(new PropertyValueFactory<>("libelle"));


        TableViewC.setItems(CategorieList);
    }
    
   /* @FXML
    private void ajouterC(ActionEvent event) {
               Categorie p = new Categorie();
        p.setLibelle(libelleFieldC.getText());
        
        sc.ajouterCategorie(p);
        updateTable();
        JOptionPane.showMessageDialog(null, "Categorie Ajoutée");
    }*/
    
    @FXML
private void ajouterC(ActionEvent event) {
    // Vérifiez que le champ de texte libelleFieldC n'est pas vide
    if (libelleFieldC.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Veuillez entrer un libellé pour la catégorie");
        return;
    }

    // Vérifiez que le champ de texte libelleFieldC ne contient pas de caractères non autorisés
    String libelle = libelleFieldC.getText();
    if (!libelle.matches("[a-zA-Z]+")) {
        JOptionPane.showMessageDialog(null, "Le libellé doit contenir uniquement des lettres alphabétiques");
        return;
    }

    /*// Vérifiez que la catégorie n'existe pas déjà dans votre système
    if (sc.categorieExiste(libelle)) {
        JOptionPane.showMessageDialog(null, "La catégorie existe déjà dans le système");
        return;
    }
*/
    // Si toutes les vérifications sont réussies, créez une nouvelle catégorie et ajoutez-la
    Categorie p = new Categorie();
    p.setLibelle(libelle);
    sc.ajouterCategorie(p);
    updateTable();
    JOptionPane.showMessageDialog(null, "Categorie Ajoutée");
}

    @FXML
    private void modifierC(ActionEvent event) {
        Categorie p = new Categorie();
        p.setId(Integer.parseInt(idFieldC.getText()));

        p.setLibelle(libelleFieldC.getText());
        

        sc.modifierCategorie(p);
        updateTable();
        JOptionPane.showMessageDialog(null, "Categorie modifiée");
    }

    @FXML
    private void supprimerC(ActionEvent event) {
        
        Categorie c = new Categorie();
        c.setId(Integer.parseInt(idFieldC.getText()));

        c.setLibelle(libelleFieldC.getText());
        sc.supprimerCategorie(c);
       updateTable();
        JOptionPane.showMessageDialog(null, "Categorie supprimee");
    }

    @FXML
    private void getSelected(MouseEvent event) {
        index =TableViewC.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idFieldC.setText((idC.getCellData(index).toString()));
        libelleFieldC.setText(libelleC.getCellData(index).toString());
       
    
    }
    
}
