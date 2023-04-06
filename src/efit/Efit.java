/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package efit;

import Entites.Coaching;
import Entities.RendezVous;
import Services.ServiceCoaching;
import Services.ServiceRendezVous;
import Utils.MyDB;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Maram
 */
public class Efit extends Application {
  
      
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("../Efit/GUI/AjouterCoaching.fxml"));
            Scene scene= new Scene(root); 
            primaryStage.setScene(scene);
            primaryStage.show(); //projecteur
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
              // MyDB a = MyDB.getInstance();
//RendezVous p4 = new RendezVous(39, "45",false);
               ServiceRendezVous sc = new ServiceRendezVous();

      // sc.ajouter(p4);
        System.out.println(sc.afficher());


    }

   
}
