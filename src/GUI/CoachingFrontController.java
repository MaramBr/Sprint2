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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
  
    private TableView<Coaching> CoachingTable;

    @FXML
    private TableColumn<Coaching, Integer> idC;

    @FXML
    private TableColumn<Coaching, String> coursC;

    @FXML
    private TableColumn<Coaching, String> descC;

    @FXML
    private TableColumn<Coaching,String> dispoC;

    @FXML
    private TableColumn<Coaching, String> imgC;
    
    
 ServiceCoaching sc=new ServiceCoaching();
    ObservableList<Coaching> CoachingList;
   int index=-1;
     String path="";
    @FXML
    private TableView<Coaching> table;
    @FXML
    private TextField idField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
      Coaching c = new Coaching();
       
         idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        coursC.setCellValueFactory(new PropertyValueFactory<>("cours"));
        dispoC.setCellValueFactory(new PropertyValueFactory<>("dispoCoach"));
        descC.setCellValueFactory(new PropertyValueFactory<>("descCoach"));
        imgC.setCellValueFactory(new PropertyValueFactory<>("imgCoach"));

        CoachingList = sc.afficher();
        table.setItems(CoachingList);
     
       
    }    

    @FXML
    private void getSelected(MouseEvent event) {
         
        
   
        
         Coaching c=table.getSelectionModel().getSelectedItem();
         index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idField.setText((idC.getCellData(index).toString()));
        coursField.setText(coursC.getCellData(index));
        dispoField.setText(dispoC.getCellData(index));
        descField.setText(descC.getCellData(index));
            Image image = new Image(new File(c.getImgCoach()).toURI().toString());
            imgView.setImage(image);

   
    
    
          
    }
    
    }
    

    

