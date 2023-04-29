/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<<< HEAD:src/userjava/Categorie.java
package userjava;
========
package GUI;
>>>>>>>> origin/Emna:src/GUI/HomePage.java

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
<<<<<<<< HEAD:src/userjava/Categorie.java
 * @author 21692
 */
public class Categorie extends Application {
    
    @Override
    public void start(Stage primaryStage) {
           try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Categorie.fxml"));
========
 * @author
 */
public class HomePage extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Parent root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("EvenementFront.fxml"));
>>>>>>>> origin/Emna:src/GUI/HomePage.java
            Scene scene = new Scene(root);
            primaryStage.setTitle("Ajout d'un Evenement");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
<<<<<<<< HEAD:src/userjava/Categorie.java
            System.out.println(ex);        }
        
      
    }
========
            System.out.println(ex.getMessage());
        }
        }
>>>>>>>> origin/Emna:src/GUI/HomePage.java

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
