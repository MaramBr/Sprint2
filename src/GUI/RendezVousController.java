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
import Utils.MyDB;
import java.io.File;
import java.io.FileOutputStream;
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
import java.time.ZoneId;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Button;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;
import jxl.write.WriteException;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jxl.WorkbookSettings;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jxl.*;
import java.io.*;
import java.sql.SQLException;
import jxl.read.biff.BiffException;
import org.apache.poi.ss.usermodel.Row;






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
    @FXML
    private Button ExcelBtn;

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
    private TableColumn<RendezVous, java.sql.Date> dateR;
    @FXML
    private TableColumn<RendezVous, String> coursR;
    private TableColumn<RendezVous, Integer> etatR;
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
                    System.out.println("Selected Cours id: " + id);
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

        sr.modifier(c);
        updateTable();
        JOptionPane.showMessageDialog(null, "Rendez_Vous modifiée");
    }

    @FXML
    private void AjouterRdv(ActionEvent event) {

        // Récupérer la date sélectionnée dans le DatePicker
LocalDate selectedDate = dateField.getValue();

// Vérifier si la date est supérieure à la date d'aujourd'hui
if (selectedDate.isBefore(LocalDate.now())) {
    // Afficher un message d'erreur et sortir de la méthode
    JOptionPane.showMessageDialog(null, "La date sélectionnée doit être supérieure à la date d'aujourd'hui.");
    return;
}

// Calculer la date d'il y a 3 mois
LocalDate threeMonthsAgo = LocalDate.now().plusMonths(3);

// Vérifier si la date est inférieure à 3 mois
if (selectedDate.isAfter(threeMonthsAgo)) {
    // Afficher un message d'erreur et sortir de la méthode
    JOptionPane.showMessageDialog(null, "La date sélectionnée doit être inférieure à 3 mois.");
    return;
}

// Si la date est valide, ajouter le rendez-vous
RendezVous c = new RendezVous();
c.setIdCoaching(idCoachingToadd);
Date utilDate = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
c.setDaterdv(sqlDate);
sr.ajouter(c);
updateTable();
JOptionPane.showMessageDialog(null, "Rendez-vous ajouté avec succès.");

    }

    @FXML
    private void SupprimerRdv(ActionEvent event) {

        RendezVous c = new RendezVous();
        c.setId(Integer.parseInt(idField.getText()));

        c.setNomCours( coachingField.getValue());
       Date utilDate = Date.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    c.setDaterdv(sqlDate);
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
 
    dateField.setValue(dateR.getCellData(index).toLocalDate());
   // dateField.setValue(LocalDate.parse());

    // Do something with the selected date

    

    // Faire quelqssue chose avec la date sélectionnée
    // ...
     
     
     
     
}

    @FXML
    private void ViderRdv(ActionEvent event) {
        
        dateField.setValue(null);
        coachingField.setValue(null);
       
    }

    @FXML
    private void excel(ActionEvent event) throws IOException  {
        File file = new File("rendezvous.xls");

     

      exportToExcel();
      
}



   
    
    public void exportToExcel() {
        ServiceRendezVous sr=new ServiceRendezVous();
    try {
        // Création du fichier Excel
        File file = new File("rendezExprot.xls");
        WritableWorkbook workbook = Workbook.createWorkbook(file);

        // Création de la feuille de calcul
        WritableSheet sheet = workbook.createSheet("Rendez-vous", 0);

        // Ajout des en-têtes de colonnes
        //sheet.addCell(new Label(0, 0, "ID"));
        sheet.addCell(new Label(0, 0, "Nom du cours"));
        sheet.addCell(new Label(1, 0, "Date de rendez-vous"));

        // Récupération des données depuis la base de données
       
        Connection conn ;
           conn = MyDB.getInstance().getCnx();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT rdv.id, c.cours, rdv.daterdv FROM rendez_vous rdv JOIN coaching c ON rdv.coachings_id = c.id");

        // Ajout des données dans la feuille de calcul
        int row = 1;
        while (rs.next()) {
            //sheet.addCell(new jxl.write.Number(0, row, rs.getInt("id")));

            //sheet.addCell(new Label(0, row, rs.getString("coachings_id")));
           sheet.addCell(new Label(0, row, rs.getString("cours")));

            sheet.addCell(new DateTime(1, row, rs.getTimestamp("daterdv")));
            row++;
        }

        // Fermeture de la connexion à la base de données
        rs.close();
        stmt.close();
        conn.close();

        // Écriture du fichier Excel
        workbook.write();
        workbook.close();
        System.out.println("Données exportées avec succès vers le fichier rendezvous.xls !");
    } catch (Exception e) {
        System.out.println("Erreur lors de l'export des données vers le fichier Excel : " + e.getMessage());
    }
}
    


    @FXML
    private void stats(ActionEvent event) {
     
            ObservableList<RendezVous> rdv = TableView.getItems();
    Map<String, Integer> statistiques = new HashMap<>();

    // Calcul des statistiques
    for (RendezVous r : rdv) {
        String cours = r.getNomCours();
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

    @FXML
    private void importExcel(ActionEvent event) {
              

        importFromExcel();
   
    }
public void importFromExcel() {
    try {
        // Récupération du fichier Excel
        File file = new File("rendezExprot.xls");
        Workbook workbook = Workbook.getWorkbook(file);

        // Récupération de la feuille de calcul
        Sheet sheet = workbook.getSheet(0);

        // Récupération des données de chaque ligne et insertion dans la base de données
        Connection conn = MyDB.getInstance().getCnx();
            for (int i = 1; i < sheet.getRows(); i++) {
                String cours = sheet.getCell(0, i).getContents();
                String daterdv = sheet.getCell(1, i).getContents(); // récupère le contenu de la cellule sous forme de chaîne de caractères

                // Convertir la chaîne de caractères en objet de type java.util.Date
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd"); // adapté au format de date utilisé dans la cellule Excel
                Date date = inputFormat.parse(daterdv);

                // Formater la date pour l'insérer dans la requête SQL
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd"); // adapté au format de date attendu par la base de données
                String formattedDate = outputFormat.format(date);

                // Insertion des données dans la base de données
                String query = "INSERT INTO rendez_vous (coachings_id, daterdv) "
                             + "SELECT id, '" + formattedDate + "' FROM coaching WHERE cours = '" + cours + "'";
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate(query);
                }
                updateTable();
            }
        
        System.out.println("Données importées avec succès depuis le fichier rendezvous.xls !");
        
        
    } catch (Exception e) {
        System.out.println("Erreur lors de l'import des données depuis le fichier Excel : " + e.getMessage());
    }
}







}
