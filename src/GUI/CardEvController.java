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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class CardEvController implements Initializable {

    String path="";
   
    @FXML
    private HBox box;

    
private  String [] colors = {"B9E5FF","BDB2FE","FB9AA8","FF5056"} ;
    @FXML
    private ImageView coursImg;
    @FXML
    private Label coursName;
    @FXML
    private Label coursDesc;
    @FXML
    private Label coursDispo;
    @FXML
    private Button detail;
    @FXML
    private Label eventid;
    @FXML
    private ImageView qrcodee;
    @FXML
    private Label tfprix;
    @FXML
    private Label tflieu;
    @FXML
    private Label tfdatefin;
    @FXML
    private Button BtnQr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Evenement c)
    {
        
         String path = c.getImage();
         File file=new File(path);
         Image img = new Image(file.toURI().toString());
         coursImg.setImage(img);
       // String imagePath = "../img";
       // Image image = new Image(new File(imagePath).toURI().toString());
        //coursImg.setImage(image);

         coursDesc.setText(c.getNom());
         coursName.setText(c.getDate_debut());
         tfdatefin.setText(c.getDate_fin());
         tflieu.setText(c.getLieu());
       coursDispo.setText(String.valueOf(c.getNb_participant()));
         eventid.setText(String.valueOf(c.getId()));
tfprix.setText(String.valueOf(c.getPrix()));

      /*  box.setStyle("-fx-background-color: #" +colors[(int)(Math.random()*colors.length)] 
                +" ; -fx-background-radius: 15;"
                +"-fx-effect : dropshadow(three-pass-box , rgba(0,0,0,0.1) , 10 , 0 ,0 , 10 ) ;");*/
      box.setStyle("-fx-background-color: linear-gradient(to bottom right, #FF9B00, #FFAF5B);" 
             + "-fx-background-radius: 15;" 
             + "-fx-border-color: linear-gradient(to bottom right, #FF9B00, #FFAF5B);"
             + "-fx-border-width: 2px;"
             + "-fx-border-radius: 15;"
             + "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.1), 10, 0, 0, 10);");

        
    }

    @FXML
    private void detail(ActionEvent event) {
            
  
   int idEvent = Integer.parseInt(eventid.getText());
String nomEvent = coursDesc.getText();
    
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
    private void QR(ActionEvent event) {
    String nom = coursDesc.getText();
String date = coursName.getText();
String datefin = tfdatefin.getText();
String lieu = tflieu.getText();
int nbParticipant = Integer.parseInt(coursDispo.getText());

QRCodeWriter qrCodeWriter = new QRCodeWriter();
String Information = "nom : " + nom + "\n" + "lieu : " + lieu + "\n" + "date : " + date + "\n" + "Nombre de participants restant: " + nbParticipant + "\n" + "datefin : " + datefin;
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

/////////////hhhhhhhhhhh-//////////////////////
    
}


