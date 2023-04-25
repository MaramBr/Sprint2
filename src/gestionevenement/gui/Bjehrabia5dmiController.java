/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import gestionEvenement.entities.Evenement;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author emna
 */
public class Bjehrabia5dmiController implements Initializable {

    
    
     int idEvent;
    @FXML
    private Label titrelabel;
    @FXML
    private Label typelabel;
    @FXML
    private Label lieulabel;
    @FXML
    private ImageView image2;
    @FXML
    private Button participer;
    @FXML
    private Label labelid;
    @FXML
    private Label labelsponsors_id;
    @FXML
    private Label labeldecription;
    @FXML
    private Label labeldatedebut;
    @FXML
    private Label labeldatefin;
    @FXML
    private Label labelprix;
    @FXML
    private Label labelnbParticipant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 private Evenement eve=new Evenement();
   
    public void setEvennement(Evenement e) {
        this.eve=e;
        
      
         
         labelid.setText(String.valueOf(e.getId())); 
         labelsponsors_id.setText(String.valueOf(e.getSponsors_id()));
        titrelabel.setText(e.getNom());
          lieulabel.setText(e.getLieu());
         typelabel.setText(e.getType());
           labeldecription.setText(e.getDescription());
         labeldatedebut.setText(e.getDate_debut());
         labeldatefin.setText(e.getDate_fin());
         // image2.setText(e.getImage());
           String path = e.getImage();
         File file=new File(path);
         Image img = new Image(file.toURI().toString());
         image2.setImage(img);
         labelnbParticipant.setText(String.valueOf(e.getNb_participant())); 
         labelprix.setText(String.valueOf(e.getPrix())); 
       
   

      

    }
    public void setIdevent(int idevent){
        this.idEvent=idevent;
    }
    
}
