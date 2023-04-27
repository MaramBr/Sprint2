/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Coaching;
import Services.ServiceCoaching;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Maram
 */
public class CoachingFrontController implements Initializable {

    @FXML
    private Label coursField;
    @FXML
    private ImageView imgView;
    @FXML
    private Label descField;
    @FXML
    private Label dispoField;
  


    
    
 ServiceCoaching sc=new ServiceCoaching();
    ObservableList<Coaching> CoachingList;
   int index=-1;
     String path="";
    @FXML
    private TextField idField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
     
       
    }    

     public void setData(Coaching c)
    {
      Image image = new Image(getClass().getResourceAsStream(c.getImgCoach())) ;
       imgView.setImage(image);
        coursField.setText(c.getCours());
       descField.setText(c.getDescCoach());
         dispoField.setText(c.getDispoCoach());


    }

}
    

    

