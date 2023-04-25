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
import javafx.scene.control.Button;
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
    private Button participer;
    @FXML
    private TextField idevent;
    @FXML
    private Button btnretour;



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
}



