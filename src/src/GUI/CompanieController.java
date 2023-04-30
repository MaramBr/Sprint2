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
public class CompanieController implements Initializable {
    ServiceCompanie sc=new ServiceCompanie();
    List<Companie> Companie;
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
    private TableView<Companie> TableViewC;
    @FXML
    private TableColumn<Companie, String> libelleC;
    @FXML
    private TableColumn<Companie,Integer> idC;

    /**
     * Initializes the controller class.
     */
     public void updateTable() {
           ObservableList<Companie> Companie = FXCollections.observableArrayList(sc.afficherCompanie());

         idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelleC.setCellValueFactory(new PropertyValueFactory<>("libelle"));

        System.out.println("affichage" + sc.afficherCompanie());
         TableViewC.setItems(Companie);
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           show();
        // TODO
    }    

    public void show() {
        ObservableList<Companie> CompanieList = FXCollections.observableArrayList(sc.afficherCompanie());
        System.out.println("affichage" + sc.afficherCompanie());
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelleC.setCellValueFactory(new PropertyValueFactory<>("libelle"));


        TableViewC.setItems(CompanieList);
    }
    
   /* @FXML
    private void ajouterC(ActionEvent event) {
               Companie p = new Companie();
        p.setLibelle(libelleFieldC.getText());
        
        sc.ajouterCompanie(p);
        updateTable();
        JOptionPane.showMessageDialog(null, "Companie Ajoutée");
    }*/
    
    @FXML
private void ajouterC(ActionEvent event) {
    // Vérifiez que le champ de texte libelleFieldC n'est pas vide
    if (libelleFieldC.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Veuillez entrer un libellé pour la companie");
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
    Companie p = new Companie();
    p.setLibelle(libelle);
    sc.ajouterCompanie(p);
    updateTable();
    JOptionPane.showMessageDialog(null, "Companie Ajoutée");
}

    @FXML
    private void modifierC(ActionEvent event) {
        Companie p = new Companie();
        p.setId(Integer.parseInt(idFieldC.getText()));

        p.setLibelle(libelleFieldC.getText());
        

        sc.modifierCompanie(p);
        updateTable();
        JOptionPane.showMessageDialog(null, "Companie modifiée");
    }

    @FXML
    private void supprimerC(ActionEvent event) {
        
        Companie c = new Companie();
        c.setId(Integer.parseInt(idFieldC.getText()));

        c.setLibelle(libelleFieldC.getText());
        sc.supprimerCompanie(c);
       updateTable();
        JOptionPane.showMessageDialog(null, "Companie supprimee");
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
