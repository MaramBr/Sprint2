/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import gestionEvenement.entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class CardController implements Initializable {

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
       coursDispo.setText(String.valueOf(c.getNb_participant()));
         eventid.setText(String.valueOf(c.getId()));

        box.setStyle("-fx-background-color: #" +colors[(int)(Math.random()*colors.length)] 
                +" ; -fx-background-radius: 15;"
                +"-fx-effect : dropshadow(three-pass-box , rgba(0,0,0,0.1) , 10 , 0 ,0 , 10 ) ;");
        
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


    
}


