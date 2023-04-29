/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import Entities.Evenement;
import Services.EvenementCRUD;
import Services.SponsorCRUD;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



/**
 * FXML Controller class
 *
 * @author emna
 */
public class EvenementFrontController implements Initializable {

  
    
    private EvenementCRUD ecrd = new EvenementCRUD();
    
    public ObservableList<Evenement> data = FXCollections.observableArrayList();
private Evenement evenementSelectionne;

    @FXML
    private Button btnrafraichira;
    @FXML
    private Button btnretour;

    @FXML
    private Button participer;
    @FXML
    private TableColumn<Evenement, String> colsponsor;
    @FXML
    private TableColumn<Evenement, String> colnom;
    @FXML
    private TableColumn<Evenement, String> collieu;
    @FXML
    private TableColumn<Evenement, String> coltype;
    @FXML
    private TableColumn<Evenement, String> coldescription;
    @FXML
    private TableColumn<Evenement, String> coldatedebut;
    @FXML
    private TableColumn<Evenement, String> coldatefin;
    @FXML
    private TableColumn<Evenement, String> colimage;
    @FXML
    private TableColumn<Evenement, Integer> colnbparticipant;
    @FXML
    private TableColumn<Evenement, Integer> colprix;
    @FXML
    private TableColumn<Evenement, Integer> colidevent;
   @FXML
    private TableView<Evenement> tableEvenement;
    @FXML
    private ImageView image1;
    @FXML
    private Button detail;
    @FXML
    private ImageView image2;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tftype;
    @FXML
    private TextArea tfdescription;
    @FXML
    private TextField tfnbparticipant;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfdatefin;
    @FXML
    private TextField tfdatedebut;
    @FXML
    private TextField tflieu;
    @FXML
    private ImageView qrcodee;
    @FXML
    private Button BtnQr;
    
// Association de l'ObservableList au TableView

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //show(event);
        File file = new File("C:/Users/emnaa/OneDrive/Documents/NetBeansProject/GestionEvenement/src/image/logoEfit.png");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         image1.setImage(new Image(localURL));
          show();
       
    }  
    
   
   

   

    @FXML
    public void show() {
     EvenementCRUD cv = new EvenementCRUD();
    List<Evenement> evenements = cv.afficherEvenement();
    SponsorCRUD sv = new SponsorCRUD();
    if (evenements != null) {
        data = FXCollections.observableArrayList(evenements);
        colidevent.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       collieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        coldatedebut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        coldatefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
       colnbparticipant.setCellValueFactory(new PropertyValueFactory<>("nb_participant"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        colsponsor.setCellValueFactory(cellData -> {
            Integer sponsorsId = cellData.getValue().getSponsors_id();
            if (sponsorsId != null) {
                String sponsorName = sv.getSponsorNameById(sponsorsId);
                return new SimpleStringProperty(sponsorName);
            } else {
                return new SimpleStringProperty("");
            }
        });
        
       
        
        
        tableEvenement.getColumns().clear(); // Suppression des anciennes colonnes
        tableEvenement.getColumns().add(colsponsor);
        tableEvenement.getColumns().add(colnom);
        //tableEvenement.getColumns().add(collieu);
        //tableEvenement.getColumns().add(coltype);
        //tableEvenement.getColumns().add(coldescription);
        tableEvenement.getColumns().add(coldatedebut);
        //tableEvenement.getColumns().add(coldatefin);
        //tableEvenement.getColumns().add(colimage);
        //tableEvenement.getColumns().add(colnbparticipant);
        //tableEvenement.getColumns().add(colprix);
        

        tableEvenement.setItems(data);
    } else {
        // afficher un message d'erreur ou effectuer une autre action appropriée
    }
    
}

    

    @FXML
    private void retour(ActionEvent event) {
           try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }

    @FXML
private void participer(ActionEvent event) {
    
     // Vérifier si une ligne est sélectionnée dans la table
    if (tableEvenement.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aucune ligne sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une ligne dans la table !");
        alert.showAndWait();
        return;
    }
    Evenement selectedEvenement = tableEvenement.getSelectionModel().getSelectedItem();
    
    if (selectedEvenement != null) {
        int idEvent = selectedEvenement.getId();
        String nomEvent = selectedEvenement.getNom();
        try {
            
           /////redirection//////
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Participant.fxml"));
            Parent messageParent = loader.load();
            ParticipantController ParticipantController = loader.getController();
            ParticipantController.setIdEvent(idEvent, nomEvent);
            Scene messageScene = new Scene(messageParent);
            Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
            window.setScene(messageScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

///////////////Date////////////////////////////////
private void trierDate(MouseEvent event) {
        TableColumn<Evenement, String> dateColumn = (TableColumn<Evenement, String>) event.getSource();
        tableEvenement.getSortOrder().clear(); // Supprimer tout tri précédent
        data.sort((r1, r2) -> r1.getDate_debut().compareTo(r2.getDate_debut()));
    }

    @FXML
    private void detail(ActionEvent event) {

    
     // Vérifier si une ligne est sélectionnée dans la table
if (tableEvenement.getSelectionModel().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Aucune ligne sélectionnée");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner une ligne dans la table !");
    alert.showAndWait();
    return;
}
Evenement selectedEvenement = tableEvenement.getSelectionModel().getSelectedItem();
if (selectedEvenement != null) {
    int idEvent = selectedEvenement.getId();
    int id = selectedEvenement.getId();
    String nom = selectedEvenement.getNom();
    String lieu = selectedEvenement.getLieu();
    String type = selectedEvenement.getType();
    Image image = new Image(new File(selectedEvenement.getImage()).toURI().toString());
    String description = selectedEvenement.getDescription();
    String dateDebut = selectedEvenement.getDate_debut();
    String dateFin = selectedEvenement.getDate_fin();
    int nbParticipants = selectedEvenement.getNb_participant();
    int prix = selectedEvenement.getPrix();
    try {

        /////redirection//////
        FXMLLoader loader = new FXMLLoader(getClass().getResource("detail.fxml"));
        Parent messageParent = loader.load();
        DetailController detailController = loader.getController();
        detailController.setIdEvent1(idEvent,id, nom, lieu, type,image, description, dateDebut, dateFin, nbParticipants, prix);
        Scene messageScene = new Scene(messageParent);
        Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
        window.setScene(messageScene);
        window.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}

 /*@FXML
private void afficherEvenementSelectionne(MouseEvent event) {
    Evenement Evenement = tableEvenement.getSelectionModel().getSelectedItem();
    if (Evenement != null) {
       
        tfnom.setText(Evenement.getNom());
        tflieu.setText(Evenement.getLieu());
        tftype.setText(Evenement.getType());
        tfdescription.setText(Evenement.getDescription());
        tfdatedebut.setText(Evenement.getDate_debut());
        tfdatefin.setText(Evenement.getDate_fin());
       // tfimage.setText(Evenement.getImage());
        Image image = new Image(new File(Evenement.getImage()).toURI().toString());
            image2.setImage(image);
        int nbParticipant = Evenement.getNb_participant();
        tfnbparticipant.setText(Integer.toString(nbParticipant));
        int prix = Evenement.getPrix();
        tfprix.setText(Integer.toString(prix));
    } else {
        
  
        tfnom.setText("");
        tflieu.setText("");
        tftype.setText("");
        tfdescription.setText("");
        tfdatedebut.setText(null);
        tfdatefin.setText(null);

        tfnbparticipant.setText("");
        tfprix.setText("");
    }
}*/

    @FXML
    private void QR(ActionEvent event) {
        
         Evenement p = tableEvenement.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "nom  : "+p.getNom()+"\n"+"Description : "+p.getDescription()+"\n"+"date : "+p.getDate_debut()+"\n"+"nombre participant restant : "+p.getNb_participant()+"\n"+"Lieu : "+p.getLieu();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
         try{
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
            qrcodee.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
        }


    
  
        
    
    }

    }


  
    


