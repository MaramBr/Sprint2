/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Coaching;
import Entities.RendezVous;
import Services.ServiceCoaching;
import Services.ServiceRendezVous;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author Maram
 */
public class RendezVousController implements Initializable {

    ServiceRendezVous sr = new ServiceRendezVous();
    ObservableList<RendezVous> rdvList;
    int index = -1;
    
    private int idCoachingToadd ;

    public int getIdCoachingToadd() {
        return idCoachingToadd;
    }

    public void setIdCoachingToadd(int idCoachingToadd) {
        this.idCoachingToadd = idCoachingToadd;
    }
    

    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox<String> coachingField;
    @FXML

    private TableColumn<RendezVous, Integer> idR;
    @FXML
    private TableColumn<RendezVous, LocalDate> dateR;
    @FXML
    private TableColumn<RendezVous, String> coursR;
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
                coursR.setCellValueFactory(new PropertyValueFactory<>("nomCours"));

        dateR.setCellValueFactory(new PropertyValueFactory<>("daterdv"));
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
      

        System.out.println("affichage" + sr.afficherrdv());
        idR.setCellValueFactory(new PropertyValueFactory<>("id"));
        coursR.setCellValueFactory(new PropertyValueFactory<>("nomCours"));

        dateR.setCellValueFactory(new PropertyValueFactory<>("daterdv"));
        etatR.setCellValueFactory(new PropertyValueFactory<>("etatrdv"));
        
        

        TableView.setItems(rdvList);
        
        //////////////////////////////////////////
         ServiceCoaching sc = new ServiceCoaching();
        List<String> nomsCours = new ArrayList<>();
        for (Coaching c : sc.afficher()) {
            nomsCours.add(c.getCours());

        }
        coachingField.setItems(FXCollections.observableArrayList(nomsCours));

        coachingField.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // get the selected Personne object based on the selected nom value
                Coaching selectedCours = null;
                for (Coaching c: sc.afficher()) {
                    if (c.getCours().equals(newValue)) {
                        selectedCours = c;
                        break;
                    }
                }
                if (selectedCours != null) {
                    int id = selectedCours.getId();
                    System.out.println("Selected vehicule id: " + id);
                    // do something with the id...
                    setIdCoachingToadd(id);
                }
            }
        });}
    

    @FXML
    private void ModifierRdv(ActionEvent event) {
        RendezVous c = new RendezVous();
        c.setId(Integer.parseInt(idField.getText()));
               c.setIdCoaching(idCoachingToadd);
  // Convert java.util.Date to java.sql.Date
    Date utilDate = Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    c.setDaterdv(sqlDate);

    dateField.setValue(c.getDaterdv().toLocalDate());
    

        sr.modifier(c);
        updateTable();
        JOptionPane.showMessageDialog(null, "Rendez_Vous modifiée");
    }

    @FXML
    private void AjouterRdv(ActionEvent event) {

        RendezVous c = new RendezVous();
        c.setIdCoaching(idCoachingToadd);
  // Convert java.util.Date to java.sql.Date
    Date utilDate = Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    c.setDaterdv(sqlDate);

    dateField.setValue(c.getDaterdv().toLocalDate());

        sr.ajouter(c);
        updateTable();
        JOptionPane.showMessageDialog(null, "rendez vous Ajoutée");
    }

    @FXML
    private void SupprimerRdv(ActionEvent event) {

        RendezVous c = new RendezVous();
        c.setId(Integer.parseInt(idField.getText()));

       dateField.setValue(c.getDaterdv().toLocalDate());
        c.setNomCours( coachingField.getValue());
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
    coachingField.setValue(coursR.getCellData(index));
    LocalDate localDate = dateR.getCellData(index);
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    dateField.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

    // Do something with the selected date

    

    // Faire quelqssue chose avec la date sélectionnée
    // ...
}

    @FXML
    private void Addrdv(MouseEvent event) {
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("/tableView/addStudent.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
                     System.out.println(ex.getMessage());         }
    }
    
   
        
    

}
