/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.RendezVous;
import Services.ServiceRendezVous;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Maram
 */
public class RendezVousController implements Initializable {

    ServiceRendezVous sr = new ServiceRendezVous();
    ObservableList<RendezVous> rdvList;
    int index = -1;

    @FXML
    private TextField dateField;
    @FXML
    private ComboBox<String> coachingField;
    @FXML

    private TableColumn<RendezVous, Integer> idR;
    @FXML
    private TableColumn<RendezVous, String> dateR;
    @FXML
    private TableColumn<RendezVous, Integer> coursR;
    @FXML
    private TableColumn<RendezVous, Boolean> etatR;
    @FXML
    private TableView<RendezVous> TableView;
    @FXML
    private TextField idField;

    /**
     * Initializes the controller class.
     */
    public void updateTable() {
        ObservableList<RendezVous> rdvList = FXCollections.observableArrayList(sr.afficherrdv());

        idR.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateR.setCellValueFactory(new PropertyValueFactory<>("daterdv"));
        coursR.setCellValueFactory(new PropertyValueFactory<>("idCoaching"));
        etatR.setCellValueFactory(new PropertyValueFactory<>("etatrdv"));

        System.out.println("affichage" + sr.afficherrdv());
        TableView.setItems(rdvList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        show();
    }

    public void show() {

        ObservableList<RendezVous> rdvList = FXCollections.observableArrayList(sr.afficherrdv());
       ObservableList<String> typeList = FXCollections.observableArrayList("Panne", "Charge");
        coachingField.setItems(typeList);

        System.out.println("affichage" + sr.afficherrdv());
        idR.setCellValueFactory(new PropertyValueFactory<>("id"));
        coursR.setCellValueFactory(new PropertyValueFactory<>("nomCours"));

        dateR.setCellValueFactory(new PropertyValueFactory<>("daterdv"));
        etatR.setCellValueFactory(new PropertyValueFactory<>("etatrdv"));

        TableView.setItems(rdvList);
    }

    @FXML
    private void ModifierRdv(ActionEvent event) {
        RendezVous c = new RendezVous();
        c.setId(Integer.parseInt(idField.getText()));

        c.setDaterdv(dateField.getText());
        // c.setIdCoaching(Integer.parseInt(coachingField.getText()));
        sr.ajouter(c);
        updateTable();

        sr.modifier(c);
        updateTable();
        JOptionPane.showMessageDialog(null, "Rendez_Vous modifiée");
    }

    @FXML
    private void AjouterRdv(ActionEvent event) {

        RendezVous c = new RendezVous();
        c.setDaterdv(dateField.getText());
        //c.setIdCoaching(Integer.parseInt(coachingField.getText()));
        sr.ajouter(c);
        updateTable();
        JOptionPane.showMessageDialog(null, "rendez vous Ajoutée");
    }

    @FXML
    private void SupprimerRdv(ActionEvent event) {

        RendezVous c = new RendezVous();
        c.setId(Integer.parseInt(idField.getText()));

        c.setDaterdv(dateField.getText());
        c.setNomCours((String) coachingField.getValue());
        sr.supprimer(c);
        updateTable();
        JOptionPane.showMessageDialog(null, "Seance supprimee");
    }

    @FXML
    private void getSelected(MouseEvent event) {

        index = TableView.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idField.setText((idR.getCellData(index).toString()));
        coachingField.setValue(coursR.getCellData(index).toString());

        dateField.setText(dateR.getCellData(index));
    }

}
