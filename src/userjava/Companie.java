/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<<< HEAD:src/userjava/NewFXMain1.java
package userjava;
========
package pi;
>>>>>>>> origin/IhebTransport:src/pi/Companie.java

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
<<<<<<<< HEAD:src/userjava/NewFXMain1.java
 * @author Majdi
 */
public class NewFXMain1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Admin.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            primaryStage.setTitle("MAJDI");
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

========
 * @author 21692
 */
public class Companie extends Application {
    
    @Override
    public void start(Stage primaryStage) {
           try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Companie.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex);        }
        
      
>>>>>>>> origin/IhebTransport:src/pi/Companie.java
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
