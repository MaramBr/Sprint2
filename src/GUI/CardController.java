/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Coaching;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Wahch
 */
public class CardController implements Initializable {

    @FXML
    private HBox box;
    private int idCoaching;


    
private  String [] colors = {"B9E5FF","FFA07A"} ;
    @FXML
    private ImageView coursImg;
    @FXML
    private Label coursName;
    @FXML
    private Label coursDesc;
    @FXML
    private Label coursDispo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Coaching c)
    {
      Image image = new Image(getClass().getResourceAsStream(c.getImgCoach())) ;
       coursImg.setImage(image);
        coursDesc.setText(c.getCours());
       coursName.setText(c.getDescCoach());
         coursDispo.setText(c.getDispoCoach());
         this.idCoaching = c.getId();

        box.setStyle("-fx-background-color: #" +colors[(int)(Math.random()*colors.length)] 
                +" ; -fx-background-radius: 15;"
                +"-fx-effect : dropshadow(three-pass-box , rgba(0,0,0,0.1) , 10 , 0 ,0 , 10 ) ;");
        
    }

    @FXML
    private void reserver(ActionEvent event) {
        MyDB.setIdCoachingToPick(idCoaching);
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("RendezVous.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            MyDB.setStageAdresse(stage);

            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger("C").log(Level.SEVERE, null, ex);
        }
    }
    
}
