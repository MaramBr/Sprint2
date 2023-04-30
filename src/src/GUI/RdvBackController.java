/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.RendezVous;
import Entities.Session;
import Services.ServiceCoaching;
import Services.ServiceRendezVous;
import Utils.javamail1;
import Utils.MyDB;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * FXML Controller class
 *
 * @author Maram
 */
public class RdvBackController implements Initializable {

    @FXML
    private TableColumn<RendezVous, Integer> idR;
    @FXML
    private TableColumn<RendezVous, String> coursR;
    @FXML
    private TableColumn<RendezVous, Integer> etatR;
    @FXML
    private TableColumn<RendezVous, java.sql.Date> dateR;
      ServiceRendezVous sr = new ServiceRendezVous();
    ObservableList<RendezVous> rdvList;
    int index = -1;
    @FXML
    private TableView<RendezVous> tablerdv;
    @FXML
    private Button Etat;
    Session UserConnected;

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
        tablerdv.setItems(rdvList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();
    }    
 
    
     public void show() {
    ObservableList<RendezVous> rdvList = FXCollections.observableArrayList(sr.afficherrdv());
    System.out.println("affichage" + sr.afficherrdv());

    idR.setCellValueFactory(new PropertyValueFactory<>("id"));
    coursR.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
    dateR.setCellValueFactory(new PropertyValueFactory<>("daterdv"));

    etatR.setCellValueFactory(new PropertyValueFactory<>("etatrdv"));
    etatR.setCellFactory(column -> new TableCell<RendezVous, Integer>() {
        @Override
        protected void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
            } else {
                setText(item == 1 ? "traité" : "non traité");
            }
        }
    });
    
    tablerdv.setItems(rdvList);
}
 
@FXML
void ban(ActionEvent event) throws Exception{
   
    String typeString ;
     RendezVous selectedrdv = tablerdv.getSelectionModel().getSelectedItem();
    if (selectedrdv != null) {
       
         
       
        ServiceRendezVous rdvs = new ServiceRendezVous();
        rdvs.mailing(selectedrdv.getId());
        
       if(selectedrdv.getEtatrdv()==0){
       javamail1.sendMail("VOTRE RESERVATION EST CONFIRMEE!!",UserConnected.getEmail());
       
       
        // Afficher un message de confirmation
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Rendez vous est confirmé !");
        alert.showAndWait();
       }
       else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Annulation");
        alert.setHeaderText(null);
        alert.setContentText("Rendez vous est non confirmé !");
        alert.showAndWait();
        // Réinitialiser les champs
       }  
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un rendezVous ");
        alert.showAndWait();
    }
     show();
     
}

    @FXML
    private void statistique(ActionEvent event) {
        
            ObservableList<RendezVous> rdv = tablerdv.getItems();
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
    private void Import(ActionEvent event) {
        
 importFromExcel();
    }
    
    
    public void importFromExcel() {
    Connection conn = null;
    try {
        // Afficher le file chooser dialog pour sélectionner le fichier Excel
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionner le fichier Excel à importer");
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Récupération du fichier Excel
            Workbook workbook = Workbook.getWorkbook(file);

            // Récupération de la feuille de calcul
            Sheet sheet = workbook.getSheet(0);

            // Récupération de la connexion à la base de données
            conn = MyDB.getInstance().getCnx();

            // Récupération des données de chaque ligne et insertion dans la base de données
            for (int i = 1; i < sheet.getRows(); i++) {
                String cours = sheet.getCell(0, i).getContents();
                String daterdv = sheet.getCell(1, i).getContents(); // récupère le contenu de la cellule sous forme de chaîne de caractères

                // Convertir la chaîne de caractères en objet de type java.util.Date
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yy"); // adapté au format de date utilisé dans la cellule Excel
                inputFormat.setLenient(true);
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
        
            System.out.println("Données importées avec succès depuis le fichier " + file.getName() + " !");
        }
        
    } catch (Exception e) {
        System.out.println("Erreur lors de l'import des données depuis le fichier Excel : " + e.getMessage());
    } }
    
    @FXML
    private void Export(ActionEvent event) {
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
       

        // Écriture du fichier Excel
        workbook.write();
        workbook.close();
        System.out.println("Données exportées avec succès vers le fichier rendezvous.xls !");
    } catch (Exception e) {
        System.out.println("Erreur lors de l'export des données vers le fichier Excel : " + e.getMessage());
    }
}
}
