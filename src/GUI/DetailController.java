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
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class DetailController implements Initializable {
    

private int  idEventSelectionnee;
    @FXML
    private ImageView image2;
    private TextField tfNom;
    private TextField tfLieu;
    private TextField tfType;
    private TextField tfDescription;
    private TextField tfDateFin;
    private TextField tfNbParticipant;
    private TextField tfPrix;
    private TextField tfDateDebut;
    private TextField tfid;
    @FXML
    private Label tfnom;
    @FXML
    private Label tftype;
    @FXML
    private Label tfdescription;
    @FXML
    private Label tfnbparticipant;
    @FXML
    private Label tfprix;
    @FXML
    private Label tfdatefin;
    @FXML
    private Label tfdatedebut;
    @FXML
    private Label tflieu;
    @FXML
    private Button participer;
    @FXML
    private TextField idevent;
    @FXML
    private Button btnretour;
    @FXML
    private ImageView qrcodee;
    @FXML
    private Button BtnQr;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         // TODO
        //show(event);
   
        
            
     
    
    }
   
public void setIdEvent(int idEvent,String nom, String lieu, String type, String description, String dateDebut, String dateFin, int nbParticipants, int prix) {
this.idEventSelectionnee = idEvent;
tfid.setText(String.valueOf(idEvent));
tfNom.setText(nom);
tfLieu.setText(lieu);
tfType.setText(type);
tfDescription.setText(description);
tfDateDebut.setText(dateDebut);
tfDateFin.setText(dateFin);
tfNbParticipant.setText(String.valueOf(nbParticipants));
tfPrix.setText(String.valueOf(prix));
}

public void setIdEvent1(int idEvent,int id,String nom, String lieu,  String type, Image image, String description, String dateDebut, String dateFin, int nbParticipants, int prix) {
this.idEventSelectionnee = idEvent;

idevent.setText(String.valueOf(id));
tfnom.setText(nom);
tflieu.setText(lieu);
tftype.setText(type);
tfdescription.setText(description);
tfdatedebut.setText(dateDebut);
tfdatefin.setText(dateFin);
//tfimage.setText(Evenement.getImage());
     
            image2.setImage(image);
tfnbparticipant.setText(String.valueOf(nbParticipants));
tfprix.setText(String.valueOf(prix));
}

    @FXML
    private void participer(ActionEvent event) {
        
         
 
   int idEvent = Integer.parseInt(idevent.getText());
String nomEvent = tfnom.getText();
    
    try {
            
           /////redirection//////
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Participant.fxml"));
Parent messageParent = loader.load();
ParticipantController participantController = loader.getController();
participantController.setIdEvent(idEvent, nomEvent);
Scene messageScene = new Scene(messageParent);
Stage window = (Stage) (((Button) event.getSource()).getScene().getWindow());
window.setScene(messageScene);
window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
        Parent EvenementsParent = FXMLLoader.load(getClass().getResource("EvenementFront.fxml"));
        Scene EvenementsScene = new Scene(EvenementsParent);
        Stage window = (Stage)(((Button)event.getSource()).getScene().getWindow());
        window.setScene(EvenementsScene);
        window.show();
    } catch (IOException e) {
    }
    }

    void setIdEvent2(int idEvent, int id, String nom, String lieu1, String type1, Image image, String description1, String dateDebut, String dateFin, int nbParticipants, int prix1) {
       this.idEventSelectionnee = idEvent;

idevent.setText(String.valueOf(id));
tfnom.setText(nom);
tflieu.setText(lieu1);
tftype.setText(type1);
tfdescription.setText(description1);
tfdatedebut.setText(dateDebut);
tfdatefin.setText(dateFin);
//tfimage.setText(Evenement.getImage());
     
            image2.setImage(image);
tfnbparticipant.setText(String.valueOf(nbParticipants));
tfprix.setText(String.valueOf(prix1));
    }

   
    
    void setIdEvent3(int idEvent, int id, String nom, String lieu1, String type1, Image image, String description1, String dateDebut, String dateFin, int nbParticipants, int prix1) {
    this.idEventSelectionnee = idEvent;

    idevent.setText(String.valueOf(id));
    tfnom.setText(nom);
    tflieu.setText(lieu1);
    tftype.setText(type1);
    tfdescription.setText(description1);
    tfdatedebut.setText(dateDebut);
    tfdatefin.setText(dateFin);
    image2.setImage(image);
    tfnbparticipant.setText(String.valueOf(nbParticipants));
    tfprix.setText(String.valueOf(prix1));
}

    void setIdEvent4(int idEvent, int id, String nom, String lieu1, String type1, String description1, String dateDebut, String dateFin, int nbParticipants, int prix1) {
       this.idEventSelectionnee = idEvent;

    idevent.setText(String.valueOf(id));
    tfnom.setText(nom);
    tflieu.setText(lieu1);
    tftype.setText(type1);
    tfdescription.setText(description1);
    tfdatedebut.setText(dateDebut);
    tfdatefin.setText(dateFin);
  
    tfnbparticipant.setText(String.valueOf(nbParticipants));
    tfprix.setText(String.valueOf(prix1));
    }

    @FXML
    private void QR(ActionEvent event) {
       String nom = tfnom.getText();
String description = tfdescription.getText();
String date = tfdatedebut.getText();
String lieu= tflieu.getText();
int nbParticipant = Integer.parseInt(tfnbparticipant.getText());


      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
String Information = "nom : " + nom + "\n" + "Description : " + description + "\n" + "date : " + date + "\n" + "Nombre de participants restant: " + nbParticipant ;
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



