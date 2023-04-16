/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Coaching;
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

        box.setStyle("-fx-background-color: #" +colors[(int)(Math.random()*colors.length)] 
                +" ; -fx-background-radius: 15;"
                +"-fx-effect : dropshadow(three-pass-box , rgba(0,0,0,0.1) , 10 , 0 ,0 , 10 ) ;");
        
    }
}
