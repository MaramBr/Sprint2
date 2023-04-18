/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userjava;

import Entities.User;
import Services.UserService;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author rayan
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       
  
      try {   
       
         Parent root;
            root = FXMLLoader.load(getClass().getResource("/GUI/Admin.fxml"));
        Scene scene = new Scene(root);
       
        primaryStage.setTitle("E-Fit");
        primaryStage.setScene(scene);
        primaryStage.show();
       
                } catch (IOException ex) {
System.out.println(ex.getMessage());           
        }
    

       
       
      
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
          UserService u = new UserService();
    
          
        //_____________Supprimer_________________
        
       
    }
    
}
