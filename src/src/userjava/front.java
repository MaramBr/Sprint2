package userjava;



import Entities.User;
import Services.UserService;
import java.io.IOException;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
<<<<<<<< HEAD:src/workshop3a53/front.java
 * @author Majdi
========
 * @author rayan
>>>>>>>> origin/UserRayan:src/userjava/NewFXMain.java
 */
public class front extends Application {


    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ReclamamtionFront.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("MAJDI");
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


  /*  public void start(Stage primaryStage) throws Exception {
       
  
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
*/    

       
       
      

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
