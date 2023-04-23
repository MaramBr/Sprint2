/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvenement.gui;

import gestionEvenement.entities.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Wahch
 */
public class CardController implements Initializable {

    @FXML
    private HBox box;

    
private  String [] colors = {"B9E5FF","BDB2FE","FB9AA8","FF5056"} ;
   
    @FXML
    private ImageView image2;
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label datedebut;
    @FXML
    private Label datefin;
    @FXML
    private Label lieu;
    @FXML
    private Label type;
    @FXML
    private Label nbParticipant;
    @FXML
    private Label prix;
    @FXML
    private Label sponsor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    public void setData(Evenement c)
    {
      Image image = new Image(getClass().getResourceAsStream(c.getImage())) ;
       image2.setImage(image);
        nom.setText(c.getNom());
         description.setText(c.getDescription());
         datedebut.setText(c.getDate_debut());
         datefin.setText(c.getDate_fin());
         lieu.setText(c.getLieu());
         type.setText(c.getType());
         nbParticipant.setText(Integer.toString(c.getNb_participant()));
         prix.setText(Integer.toString(c.getPrix()));
         sponsor.setText(Integer.toString(c.getSponsors_id()));

        box.setStyle("-fx-background-color: #" +colors[(int)(Math.random()*colors.length)] 
                +" ; -fx-background-radius: 15;"
                +"-fx-effect : dropshadow(three-pass-box , rgba(0,0,0,0.1) , 10 , 0 ,0 , 10 ) ;");
        
    }
}
